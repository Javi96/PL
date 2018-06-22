package apartado1;

public class Real extends Expresion{

	public Real(){
	}

	@Override
   	public String toString(){
   		return Tipo.REAL.toString() + "()";
   	}
}