/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asint;

import alex.UnidadLexica;
import alex.AnalizadorLexicoTiny;
import alex.ClaseLexica;
import errors.GestionErroresTiny;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnalizadorSintacticoTiny {
	private UnidadLexica anticipo;
	private AnalizadorLexicoTiny alex;
	private GestionErroresTiny errores;

	public AnalizadorSintacticoTiny(Reader input) {
		errores = new GestionErroresTiny();
		alex = new AnalizadorLexicoTiny(input);
		alex.fijaGestionErrores(errores);
		sigToken();
	}

	public void Sp() {
		Prog();
		empareja(ClaseLexica.EOF);
	}

	private void Prog() {
		switch (anticipo.clase()) {
		case NUM:
		case BOOL:
			Declaraciones();
			empareja(ClaseLexica.SEP_ENTRADA);
			Instrucciones();
			break;
		case EOF:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.NUM, ClaseLexica.BOOL);
		}
	}

	private void Declaraciones() {
		switch (anticipo.clase()) {
		case NUM:
		case BOOL:
			Declaracion();
			RDeclaraciones();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.NUM, ClaseLexica.BOOL);
		}
	}

	private void Declaracion() {
		switch (anticipo.clase()) {
		case NUM:
			empareja(ClaseLexica.NUM);
			empareja(ClaseLexica.VARIABLE);
			break;
		case BOOL:
			empareja(ClaseLexica.BOOL);
			empareja(ClaseLexica.VARIABLE);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.NUM, ClaseLexica.BOOL);
		}
	}

	private void RDeclaraciones() {
		switch (anticipo.clase()) {
		case FIN_LINEA:
			empareja(ClaseLexica.FIN_LINEA);
			Declaracion();
			RDeclaraciones();
			break;
		case SEP_ENTRADA:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.NUM, ClaseLexica.BOOL);
		}
	}

	private void Instrucciones() {
		switch (anticipo.clase()) {
		case VARIABLE:
			Instruccion();
			RInstrucciones();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.VARIABLE);
		}
	}

	private void Instruccion() {
		switch (anticipo.clase()) {
		case VARIABLE:
			empareja(ClaseLexica.VARIABLE);
			empareja(ClaseLexica.IGUAL);
			E0();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.VARIABLE);
		}
	}
	
	private void RInstrucciones() {
		switch (anticipo.clase()) {
		case FIN_LINEA:
			empareja(ClaseLexica.FIN_LINEA);
			Instruccion();
			RInstrucciones();
			break;
		case EOF:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.NUM, ClaseLexica.BOOL);
		}
	}

	private void E0() {
		switch (anticipo.clase()) {
		case AND:
		case OR:
		case MUL:
		case DIV:
		case RESTA:
		case NOT:
		case MENOR_QUE:
		case MAYOR_QUE:
		case MENOR_IGUAL_QUE:
		case MAYOR_IGUAL_QUE:
		case IGUAL_IGUAL:
		case DISTINTO:
		case PAPERTURA:
		case VARIABLE:
		case VALOR:
		case SUMA:
		case PCIERRE:
		case FIN_LINEA:
			E1();
			E01();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.AND, ClaseLexica.OR, ClaseLexica.MUL, ClaseLexica.DIV,
					ClaseLexica.RESTA, ClaseLexica.NOT, ClaseLexica.MENOR_QUE,
					ClaseLexica.MAYOR_QUE, ClaseLexica.MAYOR_IGUAL_QUE,
					ClaseLexica.MENOR_IGUAL_QUE, ClaseLexica.IGUAL_IGUAL, ClaseLexica.DISTINTO,
					ClaseLexica.PAPERTURA, ClaseLexica.PCIERRE, ClaseLexica.VARIABLE, 
					ClaseLexica.VALOR,ClaseLexica.SUMA, ClaseLexica.FIN_LINEA);
		}
	}

	private void E01() {
		switch (anticipo.clase()) {
		case SUMA:
			empareja(ClaseLexica.SUMA);
			E1();
			E01();
			break;
		case RESTA:
			empareja(ClaseLexica.RESTA);
			E1();
			E01();
			break;
		case PCIERRE:
		case FIN_LINEA:
		case EOF:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.SUMA, ClaseLexica.RESTA, ClaseLexica.EOF);
		}
	}

	private void E1() {
		switch (anticipo.clase()) {
		case AND:
		case OR:
		case MUL:
		case DIV:
		case RESTA:
		case NOT:
		case MENOR_QUE:
		case MAYOR_QUE:
		case MENOR_IGUAL_QUE:
		case MAYOR_IGUAL_QUE:
		case IGUAL_IGUAL:
		case DISTINTO:
		case PAPERTURA:
		case VARIABLE:
		case VALOR:
		case SUMA:
		case PCIERRE:
		case FIN_LINEA:
			E2();
			FE2();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.VARIABLE, ClaseLexica.RESTA, ClaseLexica.NOT,
					ClaseLexica.VALOR, ClaseLexica.PAPERTURA);
		}
	}

	private void FE2() {
		switch (anticipo.clase()) {
		case AND:
			empareja(ClaseLexica.AND);
			E1();
			break;
		case OR:
			empareja(ClaseLexica.OR);
			E2();
			break;
		case PCIERRE:
		case SUMA:
		case RESTA:
		case FIN_LINEA:
		case EOF:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.AND, ClaseLexica.OR);
		}
	}

	private void E2() {
		switch (anticipo.clase()) {
		case AND:
		case OR:
		case MUL:
		case DIV:
		case RESTA:
		case NOT:
		case MENOR_QUE:
		case MAYOR_QUE:
		case MENOR_IGUAL_QUE:
		case MAYOR_IGUAL_QUE:
		case IGUAL_IGUAL:
		case DISTINTO:
		case PAPERTURA:
		case VARIABLE:
		case VALOR:
		case SUMA:
		case PCIERRE:
		case FIN_LINEA:
			E3();
			FE3();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.VARIABLE, ClaseLexica.RESTA, ClaseLexica.NOT,
					ClaseLexica.VALOR, ClaseLexica.PAPERTURA);
		}
	}

	private void FE3() {
		switch (anticipo.clase()) {
		case MENOR_QUE:
			empareja(ClaseLexica.MENOR_QUE);
			E3();
			break;
		case MAYOR_QUE:
			empareja(ClaseLexica.MAYOR_QUE);
			E3();
			break;
		case MAYOR_IGUAL_QUE:
			empareja(ClaseLexica.MAYOR_IGUAL_QUE);
			E3();
			break;
		case MENOR_IGUAL_QUE:
			empareja(ClaseLexica.MENOR_IGUAL_QUE);
			E3();
			break;
		case IGUAL_IGUAL:
			empareja(ClaseLexica.IGUAL_IGUAL);
			E3();
			break;
		case DISTINTO:
			empareja(ClaseLexica.DISTINTO);
			E3();
			break;
		case AND:
		case OR:
		case PCIERRE:
		case SUMA:
		case RESTA:
		case FIN_LINEA:
		case EOF:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.MENOR_QUE, ClaseLexica.MAYOR_QUE,
					ClaseLexica.MAYOR_IGUAL_QUE, ClaseLexica.MENOR_IGUAL_QUE,
					ClaseLexica.IGUAL_IGUAL, ClaseLexica.DISTINTO);
		}
	}

	private void E3() {
		switch (anticipo.clase()) {
		case AND:
		case OR:
		case MUL:
		case DIV:
		case RESTA:
		case NOT:
		case MENOR_QUE:
		case MAYOR_QUE:
		case MENOR_IGUAL_QUE:
		case MAYOR_IGUAL_QUE:
		case IGUAL_IGUAL:
		case DISTINTO:
		case PAPERTURA:
		case VARIABLE:
		case VALOR:
		case SUMA:
		case PCIERRE:
		case FIN_LINEA:
			E4();
			E31();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.AND, ClaseLexica.RESTA, ClaseLexica.NOT,
					ClaseLexica.VALOR, ClaseLexica.PAPERTURA);
		}
	}

	private void E31() {
		switch (anticipo.clase()) {
		case MUL:
			empareja(ClaseLexica.MUL);
			E4();
			E31();
			break;
		case DIV:
			empareja(ClaseLexica.DIV);
			E4();
			E31();
			break;
		case MENOR_QUE:
		case MAYOR_QUE:
		case MENOR_IGUAL_QUE:
		case MAYOR_IGUAL_QUE:
		case IGUAL_IGUAL:
		case DISTINTO:
		case AND:
		case OR:
		case PCIERRE:
		case SUMA:
		case RESTA:
		case FIN_LINEA:
		case EOF:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.MUL, ClaseLexica.DIV);
		}
	}

	private void E4() {
		switch (anticipo.clase()) {
		case RESTA:
			empareja(ClaseLexica.RESTA);
			E4();
			break;
		case NOT:
			empareja(ClaseLexica.NOT);
			E5();
			break;
		case VARIABLE:
		case VALOR:
		case PAPERTURA:
		case PCIERRE:
		case AND:
		case OR:
		case FIN_LINEA:
		case MUL:
		case DIV:
		case MENOR_QUE:
		case MAYOR_QUE:
		case MENOR_IGUAL_QUE:
		case MAYOR_IGUAL_QUE:
		case IGUAL_IGUAL:
		case DISTINTO:
		case SUMA:
			E5();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.RESTA, ClaseLexica.NOT);
		}
	}

	private void E5() {
		switch (anticipo.clase()) {
		case VARIABLE:
			empareja(ClaseLexica.VARIABLE);
			break;
		case VALOR:
			empareja(ClaseLexica.VALOR);
			break;
		case PAPERTURA:
			empareja(ClaseLexica.PAPERTURA);
			E0();
			empareja(ClaseLexica.PCIERRE);
			break;
		case PCIERRE:
		case AND:
		case OR:
		case FIN_LINEA:
		case MUL:
		case DIV:
		case MENOR_QUE:
		case MAYOR_QUE:
		case MENOR_IGUAL_QUE:
		case MAYOR_IGUAL_QUE:
		case IGUAL_IGUAL:
		case DISTINTO:
		case SUMA:
		case RESTA:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.VARIABLE, ClaseLexica.VALOR,
					ClaseLexica.PAPERTURA);
		}
	}

	private void empareja(ClaseLexica claseEsperada) {
		if (anticipo.clase() == claseEsperada)
			sigToken();
		else
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					claseEsperada);
	}

	private void sigToken() {
		try {
			anticipo = alex.yylex();
		} catch (IOException e) {
			errores.errorFatal(e);
		}
	}

}
