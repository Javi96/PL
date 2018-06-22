package apartado1;

public class Not extends ExpresionUn{

	public Not(){
	}

	@Override
   	public String toString(){
   		return Tipo.NOT.toString() + "()";
   	}
}