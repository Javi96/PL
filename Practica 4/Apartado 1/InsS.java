package apartado1;

public class InsS{
	private Varible variable;
	private Expresion expresion;
	public InsS(Varible variable, Expresion expresion){
		this.variable = variable;
		this.expresion = expresion;
	}

	@Override
   	public String toString(){
   		return Tipos.INSS.toString() + "(" + variable.toString() + "," + expresion.toString() + ")";
   	}
}