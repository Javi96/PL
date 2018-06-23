package cup;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;


public class Main {
	public static void main(String[] args) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream("input.txt"));
		AnalizadorLexicoTiny lexer = new AnalizadorLexicoTiny(input);
		AnalizadorSintacticoTiny constast = new AnalizadorSintacticoTiny(lexer);
		System.out.println(constast.parse().value);
	}
}

//Dec ::= Tipo: aTipo VARIABLE: lexVARIABLE
//{: RESULT=ex.decS(aTipo, lexVARIABLE); :};