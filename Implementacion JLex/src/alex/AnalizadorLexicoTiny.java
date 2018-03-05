package alex;


class AnalizadorLexicoTiny {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;

  private ALexOperations ops;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	AnalizadorLexicoTiny (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	AnalizadorLexicoTiny (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private AnalizadorLexicoTiny () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

  ops = new ALexOperations(this);
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NOT_ACCEPT,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NOT_ACCEPT,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NOT_ACCEPT,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NOT_ACCEPT,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NOT_ACCEPT,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"0:9,1:2,0:2,1,0:18,1,9,0:4,22,1,10,11,4,2,27,3,29,5,28:10,0,23,6,8,7,0:2,31" +
":4,30,31:21,0:4,32,0,19,15,31,20,24,25,31:5,17,14,12,16,31:2,18,26,21,13,31" +
":5,0:65413,33:2")[0];

	private int yy_rmap[] = unpackFromString(1,52,
"0,1,2:2,1:2,3,4,5,1:2,6,1,7,1:5,8:2,1,8:6,9,2,10,11,12,13,14,15,16,17,18,19" +
",11,20,21,8,22,23,24,25,26,27,28,29")[0];

	private int yy_nxt[][] = unpackFromString(30,34,
"-1,1,2,3,4,5,6,7,8,28,9,10,11,43:2,45,30,43:2,47,43,49,33,12,43,51,43,29,13" +
",-1,43:2,-1,14,-1:62,13,-1:13,15,-1:33,16,-1:33,17,-1:37,19,32,19:2,35,19:5" +
",-1:2,19:3,-1,19,-1,19:3,-1:25,36,-1:3,13,38,36,-1:15,19:10,-1:2,19:3,-1,19" +
",-1,19:3,-1:9,18,-1:37,19:6,20,19:3,-1:2,19:3,-1,19,-1,19:3,-1:29,31,-1:17," +
"19:2,22,19:7,-1:2,19:3,-1,19,-1,19:3,-1:23,21,-1:35,36,-1:3,34,-1,36,-1:15," +
"19:9,23,-1:2,19:3,-1,19,-1,19:3,-1:3,40:2,-1:23,40,31,-1:17,19:8,24,19,-1:2" +
",19:3,-1,19,-1,19:3,-1:29,34,-1:17,19:5,25,19:4,-1:2,19:3,-1,19,-1,19:3,-1:" +
"13,19:10,-1:2,26,19:2,-1,19,-1,19:3,-1:13,19:10,-1:2,27,19:2,-1,19,-1,19:3," +
"-1:13,19:4,39,19:5,-1:2,19:3,-1,19,-1,19:3,-1:13,19:4,44,19:5,-1:2,19:3,-1," +
"19,-1,19:3,-1:13,19,41,19:8,-1:2,19:3,-1,19,-1,19:3,-1:13,37,19:9,-1:2,19:3" +
",-1,19,-1,19:3,-1:13,19:10,-1:2,19:2,42,-1,19,-1,19:3,-1:13,19:6,46,19:3,-1" +
":2,19:3,-1,19,-1,19:3,-1:13,19:5,48,19:4,-1:2,19:3,-1,19,-1,19:3,-1:13,19:7" +
",50,19:2,-1:2,19:3,-1,19,-1,19:3,-1");

	public UnidadLexica yylex ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

  return ops.unidadEof();
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						{}
					case -2:
						break;
					case 2:
						{return ops.unidadSuma();}
					case -3:
						break;
					case 3:
						{return ops.unidadResta();}
					case -4:
						break;
					case 4:
						{return ops.unidadMul();}
					case -5:
						break;
					case 5:
						{return ops.unidadDiv();}
					case -6:
						break;
					case 6:
						{return ops.unidadMenorQue();}
					case -7:
						break;
					case 7:
						{return ops.unidadMayorQue();}
					case -8:
						break;
					case 8:
						{return ops.unidadIgual();}
					case -9:
						break;
					case 9:
						{return ops.unidadPApertura();}
					case -10:
						break;
					case 10:
						{return ops.unidadPCierre();}
					case -11:
						break;
					case 11:
						{return ops.unidadLetra();}
					case -12:
						break;
					case 12:
						{return ops.unidadFinLinea();}
					case -13:
						break;
					case 13:
						{return ops.unidadValor();}
					case -14:
						break;
					case 14:
						
					case -15:
						break;
					case 15:
						{return ops.unidadMenorIgualQue();}
					case -16:
						break;
					case 16:
						{return ops.unidadMayorIgualQue();}
					case -17:
						break;
					case 17:
						{return ops.unidadIgualIgual();}
					case -18:
						break;
					case 18:
						{return ops.unidadDistinto();}
					case -19:
						break;
					case 19:
						{return ops.unidadVariable();}
					case -20:
						break;
					case 20:
						{return ops.unidadOr();}
					case -21:
						break;
					case 21:
						{return ops.unidadSepEntrada();}
					case -22:
						break;
					case 22:
						{return ops.unidadNum();}
					case -23:
						break;
					case 23:
						{return ops.unidadNot();}
					case -24:
						break;
					case 24:
						{return ops.unidadAnd();}
					case -25:
						break;
					case 25:
						{return ops.unidadBoolean();}
					case -26:
						break;
					case 26:
						{return ops.unidadTrue();}
					case -27:
						break;
					case 27:
						{return ops.unidadFalse();}
					case -28:
						break;
					case 29:
						{}
					case -29:
						break;
					case 30:
						{return ops.unidadLetra();}
					case -30:
						break;
					case 31:
						{return ops.unidadValor();}
					case -31:
						break;
					case 32:
						{return ops.unidadVariable();}
					case -32:
						break;
					case 34:
						{return ops.unidadValor();}
					case -33:
						break;
					case 35:
						{return ops.unidadVariable();}
					case -34:
						break;
					case 37:
						{return ops.unidadVariable();}
					case -35:
						break;
					case 39:
						{return ops.unidadVariable();}
					case -36:
						break;
					case 41:
						{return ops.unidadVariable();}
					case -37:
						break;
					case 42:
						{return ops.unidadVariable();}
					case -38:
						break;
					case 43:
						{return ops.unidadLetra();}
					case -39:
						break;
					case 44:
						{return ops.unidadVariable();}
					case -40:
						break;
					case 45:
						{return ops.unidadLetra();}
					case -41:
						break;
					case 46:
						{return ops.unidadVariable();}
					case -42:
						break;
					case 47:
						{return ops.unidadLetra();}
					case -43:
						break;
					case 48:
						{return ops.unidadVariable();}
					case -44:
						break;
					case 49:
						{return ops.unidadLetra();}
					case -45:
						break;
					case 50:
						{return ops.unidadVariable();}
					case -46:
						break;
					case 51:
						{return ops.unidadLetra();}
					case -47:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
