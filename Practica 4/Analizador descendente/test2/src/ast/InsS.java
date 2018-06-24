package ast;

public class InsS extends Ins{
	private String variable;
	private Expresion expresion;
	public InsS(String variable, Expresion expresion){
		this.variable = variable;
		this.expresion = expresion;
	}

	@Override
   	public String toString(){
   		return Tipos.INSS.toString() + "(" + variable.toString() + "," + expresion.toString() + ")";
   	}
}