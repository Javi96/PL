package ast;

public class DecS extends Dec{
	private Tipo tipo;
	private String variable;
	
	public DecS(Tipo tipo, String variable){
		this.tipo = tipo;
		this.variable = variable;
	}

	@Override
   	public String toString(){
   		return Tipos.DECS.toString() + "(" + tipo.toString() + "," + variable + ")";
   	}    
}