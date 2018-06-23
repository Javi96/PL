package ast;

public class ExpresionBin extends Expresion{

	public ExpresionBin(){
	}

	@Override
   	public String toString(){
   		return Tipos.EXPBIN.toString() + "()";
   	}
}