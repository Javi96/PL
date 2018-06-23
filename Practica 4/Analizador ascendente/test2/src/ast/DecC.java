package ast;

public class DecC extends Dec{
	private Dec dec;
	private Tipo tipo;
	private String variable;
	public DecC(Tipo tipo, String variable, Dec dec){
		this.dec = dec;
		this.tipo = tipo;
		this.variable = variable;
	}

	@Override
   	public String toString(){
   		return Tipos.DECC.toString() + "(" + dec.toString() + "," + tipo.toString() + "," + variable + ")";
   	}    
}