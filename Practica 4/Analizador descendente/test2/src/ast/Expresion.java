package ast;

public abstract class Expresion{
    public Expresion _true(){throw new UnsupportedOperationException("true");}
    public Expresion _false(){throw new UnsupportedOperationException("false");}
    public Expresion _real(){throw new UnsupportedOperationException("real");}
}