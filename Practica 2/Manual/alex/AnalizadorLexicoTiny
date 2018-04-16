package alex;

import errors.GestionErroresTiny;

%%
%line
%class AnalizadorLexicoTiny
%type  UnidadLexica
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public void fijaGestionErrores(GestionErroresTiny errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}


and = and
or = or
not = not

operadorSuma = \+
operadorResta = \-
operadorMultiplicacion = \*
operadorDivision = /

operadorMenorQue = \<
operadorMayorQue = \>
operadorMenorIgualQue = \<=
operadorMayorIgualQue = \>=
operadorIgual = \=
operadorDistinto = \!=
operadorIgualIgual = \==
parentesisApertura = \(

parentesisCierre = \)

numero = num
boolean = bool

separadorEntrada = \&&

ignorable = [\t,\r,' ',\n]

finLinea = \;

letra = ([A-Z]|[a-z])
digito = [0-9]

variable = {letra}({letra}|{digito}|\_)*


cierto = true
falso = false

decimal = \.{digito}{digito}*
exponente = (e|E)([\+,\-])?{digito}{digito}*
valor = ([\+,\-])?{digito}{digito}*({decimal})?({exponente})?

%%

{ignorable}				{}
{operadorSuma} 			{return ops.unidadSuma();}
{operadorResta}  		{return ops.unidadResta();}
{operadorMultiplicacion}  	{return ops.unidadMul();}
{operadorDivision}  		{return ops.unidadDiv();}
{operadorMenorQue}  		{return ops.unidadMenorQue();}
{operadorMayorQue} 		{return ops.unidadMayorQue();}
{operadorMenorIgualQue}  	{return ops.unidadMenorIgualQue();}
{operadorMayorIgualQue}  	{return ops.unidadMayorIgualQue();}
{operadorIgual}  		{return ops.unidadIgual();}
{operadorIgualIgual}  		{return ops.unidadIgualIgual();}
{operadorDistinto}		{return ops.unidadDistinto();}
{parentesisApertura}		{return ops.unidadPApertura();}
{parentesisCierre}		{return ops.unidadPCierre();}
{numero}			{return ops.unidadNum();}
{boolean}			{return ops.unidadBoolean();}
{or}				{return ops.unidadOr();}
{and}				{return ops.unidadAnd();}
{not}				{return ops.unidadNot();}
{separadorEntrada}		{return ops.unidadSepEntrada();}
{finLinea} 			{return ops.unidadFinLinea();}
{cierto}			{return ops.unidadTrue();}
{falso}				{return ops.unidadFalse();}
{valor}				{return ops.unidadValor();}
{letra}				{return ops.unidadLetra();}
{digito}			{return ops.unidadDigito();}
{variable}			{return ops.unidadVariable();}


