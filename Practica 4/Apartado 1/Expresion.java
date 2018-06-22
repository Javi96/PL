package apartado1;

public abstract class Expresion{
    public E true(){throw new UnsupportedOperationException("true");}
    public E false(){throw new UnsupportedOperationException("false");}
    public E real(){throw new UnsupportedOperationException("real");}
}