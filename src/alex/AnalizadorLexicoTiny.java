package alex;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;

public class AnalizadorLexicoTiny {

	private Reader input;
	private StringBuffer lex;
	private int sigCar;
	private int filaInicio;
	private int columnaInicio;
	private int filaActual;
	private int columnaActual;
	private static String NL = System.getProperty("line.separator");

	private static enum Estado {
		INICIO, MAS, MENOS, MULT, DIV, DIST_1, DIST_2, MENOR_TH, MENOR_EQ, MAYOR_TH, MAYOR_EQ, ASIG, EQ, END_L, PAP, PCI, AND_1, AND_2, NUM, DEC, NUM_DEC, EXP, NUM_EXP, EXP_MAS, EXP_MENOS, LETRA_1, LETRA_2;
	}

	private Estado estado;

	public AnalizadorLexicoTiny(Reader input) throws IOException {
		this.input = input;
		lex = new StringBuffer();
		sigCar = input.read();
		filaActual = 1;
		columnaActual = 1;
	}

	public UnidadLexica sigToken() throws IOException {
		estado = Estado.INICIO;
		filaInicio = filaActual;
		columnaInicio = columnaActual;
		lex.delete(0, lex.length());
		while (true) {
			switch (estado) {
			case INICIO:
				if (hayLetra())
					transita(Estado.LETRA_1);
				else if (hayDigitoPos())
					transita(Estado.NUM);
				else if (haySuma())
					transita(Estado.MAS);
				else if (hayResta())
					transita(Estado.MENOS);
				else if (hayMul())
					transita(Estado.MULT);
				else if (hayDiv())
					transita(Estado.DIV);
				else if (hayPAp())
					transita(Estado.PAP);
				else if (hayPCierre())
					transita(Estado.PCI);
				else if (hayIgual())
					transita(Estado.ASIG);
				else if (hayPuntoComa())
					transita(Estado.END_L);
				else if (hayExclmacion())
					transita(Estado.DIST_1);
				else if (hayMenor())
					transita(Estado.MENOR_TH);
				else if (hayMayor())
					transita(Estado.MAYOR_TH);
				else if (hayIgual())
					transita(Estado.ASIG);
				else if (hayAmpersand())
					transita(Estado.AND_1);
				else if (haySep())
					transitaIgnorando(Estado.INICIO);
				else
					error();
				break;
			case LETRA_1:
				if (hayLetra() || hayDigito() || haySubrayado())
					transita(Estado.LETRA_2);
				else
					return unidadId();
				break;
			case NUM:
				if (hayDigito())
					transita(Estado.NUM);
				else if (hayPunto())
					transita(Estado.DEC);
				else if(hayExp())
					transita(Estado.EXP);
				else
					return unidadEnt();
				break;
			case MAS:
				if (hayDigito())
					transita(Estado.NUM);
				else
					return unidadEnt();
				break;
			case MENOS:
				if (hayDigito())
					transita(Estado.NUM);
				else
					return unidadMas();
				break;
			case DIST_1:
				if (hayIgual())
					transita(Estado.DIST_2);
				else
					return unidadDist();
				break;
			case MENOR_TH:
				if(hayIgual())
					transita(Estado.MENOR_EQ);
				else
					return unidadPor();
			case MAYOR_TH:
				if(hayIgual())
					transita(Estado.MAYOR_EQ);
				else
					return unidaMayorTh();
			case ASIG:
				if(hayIgual())
					transita(Estado.EQ);
				else
					return unidadEq();
			case AND_1:
				if(hayAmpersand())
					transita(Estado.AND_2);
				else
					return error();
			case DEC:
				if(hayDigito())
					transita(Estado.NUM_DEC);
			case NUM_DEC:
				if(hayExp())
					transita(Estado.EXP);
				
			case EXP:
				if(hayMas())
					transita(Estado.EXP_MAS);
				else if(hayMenos())
					transita(Estado.EXP_MENOS);
				else if(hayDigito())
					transita(Estado.NUM_EXP);
				break;
			case NUM_DEC:
				if(hayDigito())
					transita(Estado.NUM_DEC);
				else
					return unidadNumDec();
			case EXP_MAS:
				if (hayDigito())
					transita(Estado.NUM_EXP);
				else
					error();
				break;
				
			case EXP_MENOS:
				if (hayDigito())
					transita(Estado.NUM_EXP);
				else
					error();
				break;
			case NUM_EXP:
				if (hayDigito())
					transita(Estado.NUM_EXP);
				else
					return unidadNumExp();
				break;
		}
	}

	private void transita(Estado sig) throws IOException {
		lex.append((char) sigCar);
		sigCar();
		estado = sig;
	}

	private void transitaIgnorando(Estado sig) throws IOException {
		sigCar();
		filaInicio = filaActual;
		columnaInicio = columnaActual;
		estado = sig;
	}

	private void sigCar() throws IOException {
		sigCar = input.read();
		if (sigCar == NL.charAt(0))
			saltaFinDeLinea();
		if (sigCar == '\n') {
			filaActual++;
			columnaActual = 0;
		} else {
			columnaActual++;
		}
	}

	private void saltaFinDeLinea() throws IOException {
		for (int i = 1; i < NL.length(); i++) {
			sigCar = input.read();
			if (sigCar != NL.charAt(i))
				error();
		}
		sigCar = '\n';
	}

	private boolean hayLetra() {
		return sigCar >= 'a' && sigCar <= 'z' || sigCar >= 'A' && sigCar <= 'z';
	}

	private boolean hayDigitoPos() {
		return sigCar >= '1' && sigCar <= '9';
	}

	private boolean hayCero() {
		return sigCar == '0';
	}

	private boolean hayDigito() {
		return hayDigitoPos() || hayCero();
	}

	private boolean haySuma() {
		return sigCar == '+';
	}

	private boolean hayResta() {
		return sigCar == '-';
	}

	private boolean hayMul() {
		return sigCar == '*';
	}

	private boolean hayDiv() {
		return sigCar == '/';
	}

	private boolean hayPAp() {
		return sigCar == '(';
	}

	private boolean hayPCierre() {
		return sigCar == ')';
	}

	private boolean hayIgual() {
		return sigCar == '=';
	}

	private boolean hayPuntoComa() {
		return sigCar == ';';
	}

	private boolean hayPunto() {
		return sigCar == '.';
	}

	private boolean hayExponente() {
		return sigCar == 'e' || sigCar == 'E';
	}

	private boolean haySep() {
		return sigCar == ' ' || sigCar == '\t' || sigCar == '\n';
	}

	private boolean hayNL() {
		return sigCar == '\r' || sigCar == '\b' || sigCar == '\n';
	}

	private boolean hayEOF() {
		return sigCar == -1;
	}

	private UnidadLexica unidadId() {
		// palabras reservadas
		switch (lex.toString()) {
		case "evalua":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.EVALUA);
		case "donde":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.DONDE);
		default:
			return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.IDEN, lex.toString());
		}
	}

	private UnidadLexica unidadEnt() {
		return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.ENT, lex.toString());
	}

	private UnidadLexica unidadReal() {
		return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.REAL, lex.toString());
	}

	private UnidadLexica unidadMas() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAS);
	}

	private UnidadLexica unidadMenos() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOS);
	}

	private UnidadLexica unidadPor() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.POR);
	}

	private UnidadLexica unidadDiv() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.DIV);
	}

	private UnidadLexica unidadPAp() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PAP);
	}

	private UnidadLexica unidadPCierre() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PCIERRE);
	}

	private UnidadLexica unidadIgual() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.IGUAL);
	}

	private UnidadLexica unidadComa() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.COMA);
	}

	private UnidadLexica unidadEof() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.EOF);
	}

	private void error() {
		System.err.println("(" + filaActual + ',' + columnaActual + "):Caracter inexperado");
		System.exit(1);
	}

	public static void main(String arg[]) throws IOException {
		Reader input = new InputStreamReader(new FileInputStream("input.txt"));
		AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
		UnidadLexica unidad;
		do {
			unidad = al.sigToken();
			System.out.println(unidad);
		} while (unidad.clase() != ClaseLexica.EOF);
	}
}