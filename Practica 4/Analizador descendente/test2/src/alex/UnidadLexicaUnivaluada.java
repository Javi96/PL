package alex;

public class UnidadLexicaUnivaluada extends UnidadLexica {
   public String lexema() {throw new UnsupportedOperationException();}   
   public UnidadLexicaUnivaluada(int fila, int clase, String lexema) {
     super(fila,clase, lexema);  
   }
  public String toString() {
    return "[clase:"+clase()+",fila:"+fila()+",col:"+lexema()+"]";  
  }   
}
