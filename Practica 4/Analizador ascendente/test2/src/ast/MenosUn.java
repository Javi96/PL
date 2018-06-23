package ast;

public class MenosUn extends ExpresionUn{

	private Expresion operando1;
	public MenosUn(Expresion operando1){
		this.operando1 = operando1;
	}

	@Override
   	public String toString(){
   		return Tipos.MENOS_UNARIO.toString() + "("+this.operando1.toString()+  ")";
   	}

}