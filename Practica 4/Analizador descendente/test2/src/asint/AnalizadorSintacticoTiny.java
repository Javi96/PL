/* AnalizadorSintacticoTiny.java */
/* Generated By:JavaCC: Do not edit this line. AnalizadorSintacticoTiny.java */
package asint;

import ast.*;

public class AnalizadorSintacticoTiny implements AnalizadorSintacticoTinyConstants {
        private GenerateExp ex = new GenerateExp();



        private final static  int SUMA = 0;
        private final static  int RESTA = 1;
        private final static  int MUL = 2;
        private final static  int DIV = 3;
        private final static  int AND = 4;
        private final static  int OR = 5;
        private final static  int NOT = 6;
        private final static  int MAYOR_QUE = 7;
        private final static  int MENOR_QUE = 8;
        private final static  int MAYOR_IGUAL_QUE = 9 ;
        private final static  int MENOR_IGUAL_QUE = 10 ;
        private final static  int DISTINTO = 11;
        private final static  int IGUAL_IGUAL = 12;
        private final static  int MENOS_UN = 13;

        private Expresion switchBin(int opCode, Expresion op1, Expresion op2) {
                switch(opCode) {
                        case SUMA:
                                return ex.mas(op1, op2);
                        case RESTA :
                                return ex.menos(op1, op2);
                        case MUL :
                                return ex.mul(op1, op2);
                        case DIV :
                                return ex.div(op1, op2);
                        case AND :
                                return ex.and(op1, op2);
                        case OR :
                                return ex.or(op1, op2);
                        case MAYOR_QUE :
                                return ex.mayor(op1, op2);
                        case MENOR_QUE :
                                return ex.menor(op1, op2);
                        case MAYOR_IGUAL_QUE  :
                                return ex.mayorIgual(op1, op2);
                        case MENOR_IGUAL_QUE  :
                                return ex.menorIgual(op1, op2);
                        case DISTINTO :
                                return ex.distinto(op1, op2);
                        case IGUAL_IGUAL :
                                return ex.igualIgual(op1, op2);
                        default:
                                throw new IllegalArgumentException("El codigo de operacion: " + opCode + " es erroneo");
                }
        }

        private Expresion switchUn(int opCode, Expresion op){
                switch(opCode){
                        case NOT:
                                return ex.not(op);
                        case MENOS_UN:
                                return ex.menosUn(op);
                        default:
                                throw new IllegalArgumentException("El codigo de operacion: " + opCode + " es erroneo");
                }
        }

  final public Prog S() throws ParseException {Prog a;
    a = Prog();
    jj_consume_token(0);
{if ("" != null) return a;}
    throw new Error("Missing return statement in function");
  }

  final public Prog Prog() throws ParseException {Dec decC; Ins insC;
    decC = Declaraciones();
    jj_consume_token(separador);
    insC = Instrucciones();
{if ("" != null) return ex.prog(decC, insC);}
    throw new Error("Missing return statement in function");
  }

  final public Dec Declaraciones() throws ParseException {DeclaracionS declaracion; Dec RDeclaraciones;
    declaracion = Declaracion();
    RDeclaraciones = RDeclaraciones(ex.decS(declaracion.tipo, declaracion.variable));
{if ("" != null) return RDeclaraciones;}
    throw new Error("Missing return statement in function");
  }

  final public Dec RDeclaraciones(Dec ahRDeclaraciones) throws ParseException {DeclaracionS declaracion; Dec RDeclaraciones;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case puntoComa:{
      jj_consume_token(puntoComa);
      declaracion = Declaracion();
      RDeclaraciones = RDeclaraciones(ex.decC(declaracion.tipo, declaracion.variable, ahRDeclaraciones));
{if ("" != null) return RDeclaraciones;}
      break;
      }
    default:
      jj_la1[0] = jj_gen;
{if ("" != null) return ahRDeclaraciones;}
    }
    throw new Error("Missing return statement in function");
  }

  final public DeclaracionS Declaracion() throws ParseException {Tipo tipo; Token v;
    tipo = Tipo();
    v = jj_consume_token(identificador);
{if ("" != null) return new DeclaracionS(tipo, v.image);}
    throw new Error("Missing return statement in function");
  }

  final public Tipo Tipo() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case num:{
      jj_consume_token(num);
{if ("" != null) return new Num();}
      break;
      }
    case bool:{
      jj_consume_token(bool);
{if ("" != null) return new Bool();}
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Ins Instrucciones() throws ParseException {InstruccionS instruccion; Ins Rinstrucciones;
    instruccion = Instruccion();
    Rinstrucciones = Rinstrucciones(ex.insS(instruccion.variable, instruccion.expresion));
{if ("" != null) return Rinstrucciones;}
    throw new Error("Missing return statement in function");
  }

  final public Ins Rinstrucciones(Ins ahRinstrucciones) throws ParseException {InstruccionS instruccion; Ins rinstrucciones;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case puntoComa:{
      jj_consume_token(puntoComa);
      instruccion = Instruccion();
      rinstrucciones = Rinstrucciones(ex.insC(instruccion.variable, instruccion.expresion, ahRinstrucciones));
{if ("" != null) return rinstrucciones;}
      break;
      }
    default:
      jj_la1[2] = jj_gen;
{if ("" != null) return ahRinstrucciones;}
    }
    throw new Error("Missing return statement in function");
  }

  final public InstruccionS Instruccion() throws ParseException {Token v; Expresion e;
    v = jj_consume_token(identificador);
    jj_consume_token(21);
    e = E0();
{if ("" != null) return new InstruccionS(v.image, e);}
    throw new Error("Missing return statement in function");
  }

  final public Expresion E0() throws ParseException {Expresion e1; Expresion e01;
    e1 = E1();
    e01 = E01(e1);
{if ("" != null) return e01;}
    throw new Error("Missing return statement in function");
  }

  final public Expresion E01(Expresion ahE01) throws ParseException {int op0; Expresion e1; Expresion e01;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 22:
    case 23:{
      op0 = Op0();
      e1 = E1();
      e01 = E01(switchBin(op0, ahE01, e1));
{if ("" != null) return e01;}
      break;
      }
    default:
      jj_la1[3] = jj_gen;
{if ("" != null) return ahE01;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Expresion E1() throws ParseException {Expresion e2; Expresion fe2;
    e2 = E2();
    fe2 = Fe2(e2);
{if ("" != null) return fe2;}
    throw new Error("Missing return statement in function");
  }

  final public Expresion Fe2(Expresion ahFe2) throws ParseException {int op1; Expresion e1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case and:{
      jj_consume_token(and);
      e1 = E1();
{if ("" != null) return switchBin(AND, ahFe2, e1);}
      break;
      }
    case or:{
      jj_consume_token(or);
      e1 = E2();
{if ("" != null) return switchBin(OR, ahFe2, e1);}
      break;
      }
    default:
      jj_la1[4] = jj_gen;
{if ("" != null) return ahFe2;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Expresion E2() throws ParseException {Expresion e3; Expresion fe3;
    e3 = E3();
    fe3 = Fe3(e3);
{if ("" != null) return fe3;}
    throw new Error("Missing return statement in function");
  }

  final public Expresion Fe3(Expresion ahFe3) throws ParseException {int op2; Expresion e3;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 24:
    case 25:
    case 26:
    case 27:
    case 28:
    case 29:{
      op2 = Op2();
      e3 = E3();
{if ("" != null) return switchBin(op2, ahFe3, e3);}
      break;
      }
    default:
      jj_la1[5] = jj_gen;
{if ("" != null) return ahFe3;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Expresion E3() throws ParseException {Expresion e4; Expresion e31;
    e4 = E4();
    e31 = E31(e4);
{if ("" != null) return e31;}
    throw new Error("Missing return statement in function");
  }

  final public Expresion E31(Expresion ahE31) throws ParseException {int op3; Expresion e4; Expresion e31;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 30:
    case 31:{
      op3 = Op3();
      e4 = E4();
{if ("" != null) return switchBin(op3, ahE31, e4);}
      break;
      }
    default:
      jj_la1[6] = jj_gen;
{if ("" != null) return ahE31;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Expresion E4() throws ParseException {int op; Expresion e;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 22:{
      jj_consume_token(22);
      e = E4();
{if ("" != null) return switchUn(MENOS_UN, e);}
      break;
      }
    case not:{
      jj_consume_token(not);
      e = E5();
{if ("" != null) return switchUn(NOT, e);}
      break;
      }
    case pa:
    case t:
    case f:
    case numeroReal:
    case identificador:{
      e = E5();
{if ("" != null) return e;}
      break;
      }
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Expresion E5() throws ParseException {Token token; Expresion e0;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case t:{
      token = jj_consume_token(t);
{if ("" != null) return ex.trueLex();}
      break;
      }
    case f:{
      token = jj_consume_token(f);
{if ("" != null) return ex.falseLex();}
      break;
      }
    case numeroReal:{
      token = jj_consume_token(numeroReal);
{if ("" != null) return ex.real(token.image);}
      break;
      }
    case identificador:{
      token = jj_consume_token(identificador);
{if ("" != null) return ex.variable(token.image);}
      break;
      }
    case pa:{
      token = jj_consume_token(pa);
      e0 = E0();
      jj_consume_token(pc);
{if ("" != null) return e0;}
      break;
      }
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public int Op0() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 23:{
      jj_consume_token(23);
{if ("" != null) return SUMA;}
      break;
      }
    case 22:{
      jj_consume_token(22);
{if ("" != null) return RESTA;}
      break;
      }
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public int Op2() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 24:{
      jj_consume_token(24);
{if ("" != null) return IGUAL_IGUAL;}
      break;
      }
    case 25:{
      jj_consume_token(25);
{if ("" != null) return DISTINTO;}
      break;
      }
    case 26:{
      jj_consume_token(26);
{if ("" != null) return MENOR_QUE;}
      break;
      }
    case 27:{
      jj_consume_token(27);
{if ("" != null) return MAYOR_QUE;}
      break;
      }
    case 28:{
      jj_consume_token(28);
{if ("" != null) return MENOR_IGUAL_QUE;}
      break;
      }
    case 29:{
      jj_consume_token(29);
{if ("" != null) return MAYOR_IGUAL_QUE;}
      break;
      }
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public int Op3() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 30:{
      jj_consume_token(30);
{if ("" != null) return MUL;}
      break;
      }
    case 31:{
      jj_consume_token(31);
{if ("" != null) return DIV;}
      break;
      }
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public AnalizadorSintacticoTinyTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[12];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x40000,0x6000,0x40000,0xc00000,0x180,0x3f000000,0xc0000000,0x581a20,0x181820,0xc00000,0x3f000000,0xc0000000,};
   }

  /** Constructor with InputStream. */
  public AnalizadorSintacticoTiny(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public AnalizadorSintacticoTiny(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new AnalizadorSintacticoTinyTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public AnalizadorSintacticoTiny(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new AnalizadorSintacticoTinyTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public AnalizadorSintacticoTiny(AnalizadorSintacticoTinyTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(AnalizadorSintacticoTinyTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[32];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 12; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 32; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
