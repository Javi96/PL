/* Generated By:JavaCC: Do not edit this line. AnalizadorSintacticoTiny.java */
package asint;

public class AnalizadorSintacticoTiny implements AnalizadorSintacticoTinyConstants {

  final public void Sp() throws ParseException {
    S();
    jj_consume_token(0);
  }

  final public void S() throws ParseException {
    DEC();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case puntoComa:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(puntoComa);
      DEC();
    }
    jj_consume_token(separador);
    INS();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case puntoComa:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      jj_consume_token(puntoComa);
      INS();
    }
  }

  final public void DEC() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case num:
      jj_consume_token(num);
      break;
    case bool:
      jj_consume_token(bool);
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(identificador);
  }

  final public void INS() throws ParseException {
    jj_consume_token(identificador);
    jj_consume_token(19);
    E0();
  }

  final public void E0() throws ParseException {
    E1();
    E01();
  }

  final public void E01() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 20:
    case 21:
      OP0();
      E1();
      E01();
      break;
    default:
      jj_la1[3] = jj_gen;

    }
  }

  final public void E1() throws ParseException {
    E2();
    FE2();
  }

  final public void FE2() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case and:
      jj_consume_token(and);
      E1();
      break;
    case or:
      jj_consume_token(or);
      E2();
      break;
    default:
      jj_la1[4] = jj_gen;

    }
  }

  final public void E2() throws ParseException {
    E3();
    FE3();
  }

  final public void FE3() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 22:
    case 23:
    case 24:
    case 25:
    case 26:
      OP2();
      E3();
      break;
    default:
      jj_la1[5] = jj_gen;

    }
  }

  final public void E3() throws ParseException {
    E4();
    E31();
  }

  final public void E31() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 27:
    case 28:
      OP3();
      E4();
      E31();
      break;
    default:
      jj_la1[6] = jj_gen;

    }
  }

  final public void E4() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case men:
      jj_consume_token(men);
      E4();
      break;
    case not:
      jj_consume_token(not);
      E5();
      break;
    default:
      jj_la1[7] = jj_gen;
      E5();
    }
  }

  final public void E5() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case pa:
      jj_consume_token(pa);
      E0();
      jj_consume_token(pc);
      break;
    case identificador:
      jj_consume_token(identificador);
      break;
    case numeroReal:
      jj_consume_token(numeroReal);
      break;
    default:
      jj_la1[8] = jj_gen;

    }
  }

  final public void OP0() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 20:
      jj_consume_token(20);
      break;
    case 21:
      jj_consume_token(21);
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void OP1() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case and:
      jj_consume_token(and);
      break;
    case or:
      jj_consume_token(or);
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void OP2() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 22:
      jj_consume_token(22);
      break;
    case 23:
      jj_consume_token(23);
      break;
    case 24:
      jj_consume_token(24);
      break;
    case 25:
      jj_consume_token(25);
      break;
    case 26:
      jj_consume_token(26);
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void OP3() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 27:
      jj_consume_token(27);
      break;
    case 28:
      jj_consume_token(28);
      break;
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void OP4() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 29:
      jj_consume_token(29);
      break;
    case not:
      jj_consume_token(not);
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
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
  final private int[] jj_la1 = new int[14];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x10000,0x10000,0x1800,0x300000,0x180,0x7c00000,0x18000000,0x600,0x60020,0x300000,0x180,0x7c00000,0x18000000,0x20000200,};
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
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public AnalizadorSintacticoTiny(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new AnalizadorSintacticoTinyTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public AnalizadorSintacticoTiny(AnalizadorSintacticoTinyTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(AnalizadorSintacticoTinyTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
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

  private int jj_ntk() {
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
    boolean[] la1tokens = new boolean[30];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 14; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 30; i++) {
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
