package ast;

public class Real extends Expresion{

	private String valor;
	
	public Real(String valor){
		this.valor = valor;
	}

	@Override
   	public String toString(){
   		return this.valor.toString();
   	}
}