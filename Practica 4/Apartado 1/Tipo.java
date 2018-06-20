package apartado1;

public enum Tipo{
	PROG, DEC, INS, DECS, DECC, INSS, INSC,
	NUM, BOOL, TRUE, FALSE, NOT, MENOS,
	DIV, MUL, MAS, OR, AND, MEMOS_UNARIO,
	MENOR_IGUAL, MAYOR_IGUAL, MENOR, MAYOR,
	IGUAL_IGUAL, DISTINTO	

	@Override
	public String toString() {
		switch(this) {
			case PROG: return "Prog";
			case DEC: return "Dec";
			case INS: return "Ins";
			case DECS: return "Decs";
			case DECC: return "Decc";
			case INSS: return "Inss";
			case INSC: return "Insc";
			case NUM: return "Num";
			case BOOL: return "Bool";
			case TRUE: return "True";
			case FALSE: return "False";
			case NOT: return "Not";
			case MENOS: return "Menos";
			case DIV: return "Div";
			case MUL: return "Mul";
			case MAS: return "Mas";
			case OR: return "Or";
			case AND: return "And";
			case MEMOS_UNARIO: return "MenosUnario";
			case MENOR_IGUAL: return "MenorIgual";
			case MAYOR_IGUAL: return "MayorIgual";
			case MENOR: return "Menor";
			case MAYOR: return "Mayor";
			case IGUAL_IGUAL: return "IgualIgual";
			case DISTINTO: return "Distinto";
			default: throw new IllegalArgumentException();
		}
	}
}