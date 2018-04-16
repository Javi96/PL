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
			empareja(ClaseLexica.NUM);
			empareja(ClaseLexica.VARIABLE);
			empareja(ClaseLexica.FIN_LINEA);
		case BOOL:
			empareja(ClaseLexica.BOOL);
			empareja(ClaseLexica.VARIABLE);
			empareja(ClaseLexica.FIN_LINEA);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.NUM, ClaseLexica.BOOL);
		}
	}

	private void Instrucciones() {
		switch (anticipo.clase()) {
		case VARIABLE:
			empareja(ClaseLexica.VARIABLE);
			empareja(ClaseLexica.IGUAL);
			E0();
			empareja(ClaseLexica.FIN_LINEA);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.VARIABLE);
		}
	}

	private void E0() {
		switch (anticipo.clase()) {
		case RESTA:
		case NOT:
		case VARIABLE:
		case VALOR:
		case PAPERTURA:
			E1();
			E01();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.RESTA, ClaseLexica.NOT, ClaseLexica.VARIABLE,
					ClaseLexica.VALOR, ClaseLexica.PAPERTURA);
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
		case EOF:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.SUMA, ClaseLexica.RESTA, ClaseLexica.EOF);
		}
	}

	private void E1() {
		switch (anticipo.clase()) {
		case VARIABLE:
		case RESTA:
		case NOT:
		case VALOR:
		case PAPERTURA:
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
		case EOF:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.AND, ClaseLexica.OR);
		}
	}

	private void E2() {
		switch (anticipo.clase()) {
		case RESTA:
		case NOT:
		case VARIABLE:
		case VALOR:
		case PAPERTURA:
			E3();
			Rel();
			E3();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.VARIABLE, ClaseLexica.RESTA, ClaseLexica.NOT,
					ClaseLexica.VALOR, ClaseLexica.PAPERTURA);
		}
	}

	private void Rel() {
		switch (anticipo.clase()) {
		case MENOR_QUE:
			empareja(ClaseLexica.MENOR_QUE);
			break;
		case MAYOR_QUE:
			empareja(ClaseLexica.MAYOR_QUE);
			break;
		case MAYOR_IGUAL_QUE:
			empareja(ClaseLexica.MAYOR_IGUAL_QUE);
			break;
		case MENOR_IGUAL_QUE:
			empareja(ClaseLexica.MENOR_IGUAL_QUE);
			break;
		case IGUAL:
			empareja(ClaseLexica.IGUAL);
			break;
		case DISTINTO:
			empareja(ClaseLexica.DISTINTO);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.MENOR_QUE, ClaseLexica.MAYOR_QUE,
					ClaseLexica.MAYOR_IGUAL_QUE, ClaseLexica.MENOR_IGUAL_QUE,
					ClaseLexica.IGUAL, ClaseLexica.DISTINTO);
		}

	}

	private void E3() {
		switch (anticipo.clase()) {
		case RESTA:
		case NOT:
		case VARIABLE:
		case VALOR:
		case PAPERTURA:
			E4();
			E31();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.VARIABLE, ClaseLexica.RESTA, ClaseLexica.NOT,
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
			E5();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.clase(),
					ClaseLexica.NOT, ClaseLexica.RESTA, ClaseLexica.VARIABLE,
					ClaseLexica.VALOR, ClaseLexica.PAPERTURA);
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
