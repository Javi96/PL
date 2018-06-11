package alex;

import java_cup.runtime.Symbol;

public class UnidadLexica extends Symbol {
   private int fila;
   public UnidadLexica(int fila, int clase, String lexema) {
     super(clase,lexema);
	 this.fila = fila;
   }
   
   public UnidadLexica(int fila, ClaseLexica clase) {
	   super(fila);
	   }

public int fila() {return fila;}
   
public int clase () {return sym;}
   public String lexema() {return (String)value;}
}
