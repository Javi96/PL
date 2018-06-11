package alex;

public class ALexOperations {
	private AnalizadorLexicoTiny alex;
	public ALexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex;   
	}
	public UnidadLexica unidadIgnorable() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.IGNORABLE,alex.lexema()); 
	} 
	public UnidadLexica unidadSuma() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.SUMA); 
	} 
	public UnidadLexica unidadResta() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.RESTA); 
	} 
	public UnidadLexica unidadMul() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MUL); 
	} 
	public UnidadLexica unidadDiv() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.DIV); 
	} 
	public UnidadLexica unidadMenorQue() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MENOR_QUE); 
	} 	
	public UnidadLexica unidadMayorQue() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MAYOR_QUE); 
	} 
	public UnidadLexica unidadMenorIgualQue() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MENOR_IGUAL_QUE); 
	} 
	public UnidadLexica unidadMayorIgualQue() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MAYOR_IGUAL_QUE); 
	} 
	public UnidadLexica unidadIgual() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.IGUAL); 
	}
	public UnidadLexica unidadIgualIgual() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.IGUAL_IGUAL); 
	} 
	public UnidadLexica unidadDistinto() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.DISTINTO); 
	} 
	public UnidadLexica unidadPApertura() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.PAPERTURA); 
	} 
	public UnidadLexica unidadPCierre() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.PCIERRE); 
	} 
	public UnidadLexica unidadNum() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.NUM); 
	} 
	public UnidadLexica unidadBoolean() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.BOOL); 
	} 
	public UnidadLexica unidadSepEntrada() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.SEP_ENTRADA); 
	} 
	public UnidadLexica unidadFinLinea() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.FIN_LINEA); 
	} 
	public UnidadLexica unidadVariable() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.VARIABLE,alex.lexema()); 
	} 
	public UnidadLexica unidadTrue() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.TRUE); 
	} 
	public UnidadLexica unidadFalse() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.FALSE); 
	} 
	public UnidadLexica unidadValor() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.VALOR,alex.lexema()); 
	}
	public UnidadLexica unidadLetra() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.LETRA,alex.lexema()); 
	}
	public UnidadLexica unidadDigito() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.DIGITO,alex.lexema()); 
	}
	public UnidadLexica unidadAnd() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.AND); 
	}
	public UnidadLexica unidadOr() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.OR); 
	}
	public UnidadLexica unidadNot() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.NOT); 
	}
	public UnidadLexica unidadEof() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.EOF); 
	}
	public void error() {
		System.err.println("***"+alex.fila()+" Caracter inexperado: "+alex.lexema());
	}
}
