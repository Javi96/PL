package ast;

public class ExpresionUn extends Expresion{

	public ExpresionUn(){
	}

	@Override
   	public String toString(){
   		return Tipos.EXPUN.toString() + "()";
   	}
}