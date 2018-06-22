package apartado1;

public class Menos extends ExpresionBin{

	public Menos(){
	}

	@Override
   	public String toString(){
   		return Tipo.MENOS.toString() + "()";
   	}
}