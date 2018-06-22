package apartado1;

public class Igual extends ExpresionBin{

	public Igual(){
	}

	@Override
   	public String toString(){
   		return Tipo.IGUAL.toString() + "()";
   	}
}