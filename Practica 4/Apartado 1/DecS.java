package apartado1;

public class DecS extends Dec{
	private Tipo tipo;
	private Variable variable;
	
	public DecS(Tipo tipo, Variable variable){
		this.tipo = tipo;
		this.variable = variable;
	}

	@Override
   	public String toString(){
   		return Tipos.DECS.toString() + "(" + tipo.toString() + "," + variable.toString() + ")";
   	}    
}