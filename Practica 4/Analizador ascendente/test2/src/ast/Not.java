package ast;

public class Not extends ExpresionUn{

	private Expresion operando1;
	public Not(Expresion operando1){
		this.operando1 = operando1;
	}

	@Override
   	public String toString(){
   		return Tipos.NOT.toString() + "("+this.operando1.toString()+  ")";
   	}
}