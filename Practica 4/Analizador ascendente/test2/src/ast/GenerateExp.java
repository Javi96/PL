package ast;


public class GenerateExp {
	  public Expresion mas(Expresion operando1, Expresion operando2) {return new Mas(operando1,operando2);}
	  public Expresion menos(Expresion operando1, Expresion operando2) {return new Menos(operando1,operando2);}
	  public Expresion mul(Expresion operando1, Expresion operando2) {return new Mul(operando1,operando2);}
	  public Expresion div(Expresion operando1, Expresion operando2) {return new Div(operando1,operando2);}
	  public Expresion igualIgual(Expresion operando1, Expresion operando2) {return new IgualIgual(operando1,operando2);}
	  public Expresion distinto(Expresion operando1, Expresion operando2) {return new Distinto(operando1,operando2);}
	  public Expresion mayor(Expresion operando1, Expresion operando2) {return new Mayor(operando1,operando2);}
	  public Expresion mayorIgual(Expresion operando1, Expresion operando2) {return new MayorIgual(operando1,operando2);}
	  public Expresion menor(Expresion operando1, Expresion operando2) {return new Menor(operando1,operando2);}
	  public Expresion menorIgual(Expresion operando1, Expresion operando2) {return new MenorIgual(operando1,operando2);}
	  public Expresion and(Expresion operando1, Expresion operando2) {return new And(operando1,operando2);}
	  public Expresion or(Expresion operando1, Expresion operando2) {return new Or(operando1,operando2);}
	  public Expresion not(Expresion operando1) {return new Not(operando1);}
	  public Expresion menosUn(Expresion operando1) {return new MenosUn(operando1);}
	  public Tipo num() {return new Num();}
	  public Tipo bool() {return new Bool();}
	  public Expresion variable(String valor) {return new Variable(valor);}
	  
	  public Expresion trueLex() {return new True();}
	  public Expresion real(String valor) {return new Real(valor);}
	  
	  public Expresion falseLex() {return new False();}
	  
	  public Ins insS(String variable, Expresion expresion) {
	    return new InsS(variable, expresion);
	  }
	  public Ins insC(String variable, Expresion expresion, Ins listaInstrucciones) {
		  return new InsC(variable, expresion, listaInstrucciones);
	  }
	  public Dec decS(Tipo tipo, String variable) {
	    return new DecS(tipo, variable);
	  }
	  public Dec decC(Tipo tipo, String variable, Dec listaDeclaraciones) {
	    return new DecC(tipo, variable, listaDeclaraciones);
	  }
	  public Prog prog(Dec listaDeclaraciones, Ins listaInstrucciones) {
		  return new Prog(listaDeclaraciones, listaInstrucciones);
	  }
}