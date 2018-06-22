package apartado1;

public class InsC{
	private Varible variable;
	private Expresion expresion;
	private Ins ins;
	public InsC(Varible variable, Expresion expresion, Ins ins){
		this.variable = variable;
		this.expresion = expresion;
		this.ins = ins;
	}

	@Override
   	public String toString(){
   		return Tipos.INSC.toString() + "(" + variable.toString() + "," + 
   		expresion.toString() + 
   		ins.toString() + ")";
   	}
}