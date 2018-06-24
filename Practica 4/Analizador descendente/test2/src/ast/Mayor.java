package ast;

public class Mayor extends ExpresionBin{

	private Expresion operando1;
	private Expresion operando2;
	public Mayor(Expresion operando1, Expresion operando2){
		this.operando1 = operando1;
		this.operando2 = operando2;
	}

	@Override
   	public String toString(){
   		return Tipos.MAYOR.toString() + "("+this.operando1.toString() + "," + this.operando2.toString() + ")";
   	}
}