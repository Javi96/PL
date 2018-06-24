package ast;

public class Variable extends Expresion{
	
	private String valor;
	
	public Variable(String valor){
		this.valor = valor;
	}

	@Override
   	public String toString(){
   		return valor;
   	}
}