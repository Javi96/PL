package apartado1;

public class DecC extends Dec{
	private Dec dec;
	private Tipo tipo;
	private Variable variable;
	public DecS(Dec dec, Tipo tipo, Variable variable){
		this.dec = dec;
		this.tipo = tipo;
		this.variable = variable;
	}

	@Override
   	public String toString(){
   		return Tipos.DECC.toString() + "(" + dec.toString() + "," + tipo.toString() + "," + variable.toString() + ")";
   	}    
}