package ast;

public class InsC extends Ins{
	private String variable;
	private Expresion expresion;
	private Ins ins;
	public InsC(String variable, Expresion expresion, Ins ins){
		this.variable = variable;
		this.expresion = expresion;
		this.ins = ins;
	}

	@Override
   	public String toString(){
   		return Tipos.INSC.toString() + "(" + ins.toString()   + variable.toString() + "," + 
   				expresion.toString() + 
   		")";

   	}
}