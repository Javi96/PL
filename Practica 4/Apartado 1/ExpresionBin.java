package apartado1;

public class ExpresionBin extends Expresion{

	public ExpresionBin(){
	}

	@Override
   	public String toString(){
   		return Tipo.EXPBIN.toString() + "()";
   	}
}