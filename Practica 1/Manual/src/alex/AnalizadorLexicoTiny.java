package alex;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

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
		INICIO, MAS, MENOS, MULT, DIV, DIST_1, DIST_2, MENOR_TH, MENOR_EQ, MAYOR_TH, MAYOR_EQ, ASIG, EQ, END_L, PAP, PCI, AND_1, AND_2, NUM, DEC, NUM_DEC, EXP, NUM_EXP, EXP_MAS, EXP_MENOS, LETRA_1, LETRA_2, REC_EOF;	}

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
				if(haySuma()){
					transita(Estado.MAS);
				}else if(hayResta()){
					transita(Estado.MENOS);
				}else if(hayMul()){
					transita(Estado.MULT);
				}else if(hayDiv()){
					transita(Estado.DIV);
				}else if(hayExcl()){
					transita(Estado.DIST_1);
				}else if(hayMenor()){
					transita(Estado.MENOR_TH);
				}else if(hayMayor()){
					transita(Estado.MAYOR_TH);
				}else if(hayIgual()){
					transita(Estado.ASIG);
				}else if(hayPuntoComa()){
					transita(Estado.END_L);
				}else if(hayPAp()){
					transita(Estado.PAP);
				}else if(hayPCierre()){
					transita(Estado.PCI);
				}else if(hayAmpersand()){
					transita(Estado.AND_1);
				}else if(hayDigito()){
					transita(Estado.NUM);
				}else if(hayLetra()){
					transita(Estado.LETRA_1);
				}else if(haySep()){
					transitaIgnorando(Estado.INICIO);
				}else if(hayEOF()){
					transita(Estado.REC_EOF);
				}else error();
				break;
			case MAS:
				if(hayDigito())
					transita(Estado.NUM);
				else
					return unidadMas();
				break;
			
			case MENOS:
				if(hayDigito())
					transita(Estado.NUM);
				else
					return unidadMenos();
				break;
			case MULT:
				return unidadPor();
			
			case DIV:
				return unidadDiv();
			
			case DIST_1:
				if(hayIgual())
					transita(Estado.DIST_2);
				break;
			case MENOR_TH:
				if(hayIgual())
					transita(Estado.MENOR_EQ);
				else
					return unidadMenor();
				break;
			case MAYOR_TH:
				if(hayIgual())
					transita(Estado.MAYOR_EQ);
				else
					return unidadMayor();
				break;
			case ASIG:
				if(hayIgual())
					transita(Estado.EQ);
				else
					return unidadIgual();
				break;
			case END_L:
				return unidadPuntoComa();
			case PAP:
				return unidadPAp();
			case PCI:
				return unidadPCierre();
			case AND_1:
				if(hayAmpersand())
					transita(Estado.AND_2);
				break;
			case LETRA_1:
				if(hayLetra())
					transita(Estado.LETRA_2);
				else
					return unidadId();
				break;
			case NUM:
				if(hayDigito())
					transita(Estado.NUM);
				else if(hayPunto())
					transita(Estado.DEC);
				else if(hayExponente())
					transita(Estado.EXP);
				else
					return unidadEnt();
				break;
			case DIST_2:
				return unidadDistinto();
			case MENOR_EQ:
				return unidadMenorIgual();
			case MAYOR_EQ:
				return unidadMayorIgual();
			case EQ:
				return unidadIgualIgual();
			case AND_2:
				return unidadAmpersand();
			case LETRA_2:
				if(hayLetra()||hayDigito()||haySeparador())
					transita(Estado.LETRA_2);
				else
					return unidadId();
				break;
			case DEC:
				if(hayDigito())
					transita(Estado.NUM_DEC);
				break;
			case EXP:
				if(hayDigito())
					transita(Estado.NUM_EXP);
				else if(haySuma())
					transita(Estado.EXP_MAS);
				else if(hayResta())
					transita(Estado.EXP_MENOS);
				break;
			case EXP_MAS:
				if(hayDigito())
					transita(Estado.NUM_EXP);
				break;
			case EXP_MENOS:
				if(hayDigito())
					transita(Estado.NUM_EXP);
				break;
			case NUM_DEC:
				if(hayDigito())
					transita(Estado.NUM_DEC);
				else if(hayExponente())
					transita(Estado.EXP);
				else
					return unidadReal();
				break;
			case NUM_EXP:
				if(hayDigito())
					transita(Estado.NUM_EXP);
				else
					return unidadExp();
				break;
			case REC_EOF:
				return unidadEof();
			default:
				break;
			}
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

	private boolean hayEOF() {
		return sigCar == -1;
	}
	
	private boolean hayAmpersand() {

		return sigCar == '&';
	}
	
	private boolean haySeparador() {

		return sigCar == '_';
	}

	private boolean hayMayor() {

		return sigCar == '>';
	}

	private boolean hayMenor() {

		return sigCar == '<';
	}

	private boolean hayExcl() {

		return sigCar == '!';
	}

	private UnidadLexica unidadId() {
		// palabras reservadas
		switch (lex.toString()) {
		case "true":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.TRUE);
		case "false":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.FALSE);
		case "num":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.NUM);
		case "bool":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.BOOL);

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
	
	private UnidadLexica unidadExp() {

		return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.EXP, lex.toString());
	}

	private UnidadLexica unidadAmpersand() {

		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.AMPERSAND);
	}

	private UnidadLexica unidadIgualIgual() {

		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.IGUALIGUAL);
	}

	private UnidadLexica unidadMayorIgual() {

		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAYORIGUAL);
	}

	private UnidadLexica unidadMenorIgual() {

		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENORIGUAL);
	}

	private UnidadLexica unidadDistinto() {

		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.DISTINTO);
	}

	private UnidadLexica unidadPuntoComa() {

		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PUNTOCOMA);
	}

	private UnidadLexica unidadMayor() {

		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAYOR);
	}
	
	private UnidadLexica unidadMenor() {

		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOR);
	}

	private UnidadLexica unidadEof() {
	     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.EOF);     
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