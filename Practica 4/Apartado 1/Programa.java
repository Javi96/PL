package apartado1;

public class Prog{
	private Dec dec;
	private Ins ins;
	public Prog(Dec dec, Ins ins){
		this.dec = dec;
		this.ins = ins;
	}

	@Override
   	public String toString(){
   		return Tipo.PROG.toString() + "(" + dec.toString() + "," + ins.toString() + ")";
   	}
}