package apartado1;

public class And extends ExpresionBin{

	public And(){
	}

	@Override
   	public String toString(){
   		return Tipo.AND.toString() + "()";
   	}
}