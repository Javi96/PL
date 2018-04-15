/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asint;

import alex.UnidadLexica;
import alex.AnalizadorLexicoTiny;
import alex.ClaseLexica;
import errors.GestionErroresTiny;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnalizadorSintacticoTiny {
   private UnidadLexica anticipo;
   private AnalizadorLexicoTiny alex;
   private GestionErroresTiny errores;
   public AnalizadorSintacticoTiny(Reader input) {
      errores = new GestionErroresTiny();
      alex = new AnalizadorLexicoTiny(input);
      alex.fijaGestionErrores(errores);
      sigToken();
   }
   public void Sp() {
      Prog();
      empareja(ClaseLexica.EOF);
   }
   private void Prog() {
     switch(anticipo.clase()) {
         case NUM: case BOOL:        
              Declaraciones();
              empareja(ClaseLexica.SEP);
              Instrucciones();
              break;
        case EOF: break;
         default: errores.errorSintactico(anticipo.fila(),anticipo.clase(),
                                          ClaseLexica.EVALUA);                                            
   }
   }
   private void Declaraciones() {
      switch(anticipo.clase()) {
          case NUM: 
                empareja(ClaseLexica.NUM);
                empareja(ClaseLexica.IDEN);
                empareja(ClaseLexica.PUNTOCOMA);
          case BOOL:
              empareja(ClaseLexica.BOOL);
              empareja(ClaseLexica.IDEN);
              empareja(ClaseLexica.PUNTOCOMA);
              break;
          default: errores.errorSintactico(anticipo.fila(),anticipo.clase(),
                                          ClaseLexica.NUM,ClaseLexica.BOOL);                                       
      } 
   }
   private void Instrucciones() {
      switch(anticipo.clase()) {
       case IDEN:    
           empareja(ClaseLexica.IGUAL);
           E0();
           empareja(ClaseLexica.PUNTOCOMA);
           break;
       default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(),
                                         ClaseLexica.IDEN);                                       
   }
   }    
   private void E0() {
      switch(anticipo.clase()) {
       case MENOS: case NOT: case IDEN: case N: case PAP:    
           E1();
           E01();
           break;    
       default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(),
                                         ClaseLexica.COMA, ClaseLexica.EOF);                                       
      }          
   }   
   private void E01() {
     switch(anticipo.clase()) {       
       case MAS: 
            empareja(ClaseLexica.MAS);
            E1();
            E01();
       break;
       case MENOS:   
            empareja(ClaseLexica.MENOS);
            E1();
            E01();
        break;
        case EOF:
            break;
       default: errores.errorSintactico(anticipo.fila(),anticipo.clase(),
                                         ClaseLexica.IDEN);                                       
     }
   }
   private void E1() {
     switch(anticipo.clase()) {
         case IDEN: case MENOS: case NOT: case N: case PAP:
             E2();
             FE2();
             break;
         default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(),
                                           ClaseLexica.IDEN,ClaseLexica.ENT,
                                           ClaseLexica.REAL, ClaseLexica.PAP);                                    
     }  
   }
   private void FE2() {
      switch(anticipo.clase()) {
          case AND:  
             empareja(ClaseLexica.AND);
                E1();
             break;

        case OR:
            empareja(ClaseLexica.OR);
            E2();
            break;
          default:    
              errores.errorSintactico(anticipo.fila(),anticipo.clase(),
                                      ClaseLexica.MAS,ClaseLexica.MENOS);                                              
      } 
   }
   private void E2() {
     switch(anticipo.clase()) {
         case MENOS: case NOT: case IDEN: case N: case PAP:
             E3();
             empareja(ClaseLexica.REL);
             E3();
            break;
         default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(),
                                           ClaseLexica.IDEN,ClaseLexica.ENT,
                                           ClaseLexica.REAL, ClaseLexica.PAP);                                    
     }  
   }
   private void E3() {
      switch(anticipo.clase()) {
          case MENOS: case NOT: case IDEN: case N: case PAP:
             E4();
             E31();
             break;
          default:    
              errores.errorSintactico(anticipo.fila(),anticipo.clase(),
                                      ClaseLexica.POR,ClaseLexica.DIV,
                                      ClaseLexica.MAS, ClaseLexica.MENOS);                                              
      } 
   }
   private void E31() {
      switch(anticipo.clase()) {
          case POR:
            empareja(ClaseLexica.POR);
            E4();
            E31();
            break;
          case DIV:
            empareja(ClaseLexica.DIV);
            E4();
            E31();
          default:     
              errores.errorSintactico(anticipo.fila(),anticipo.clase(),
                                      ClaseLexica.ENT,ClaseLexica.REAL,
                                      ClaseLexica.PAP);
   }   
   }
   private void E4() {
     switch(anticipo.clase()) {
        case MENOS:
            empareja(ClaseLexica.MENOS);
            E4();
            break;
        case NOT: 
            empareja(ClaseLexica.NOT);
            E5();
            break;
        case IDEN: case N: case PAP:
            E5();
        break;
         default:    
              errores.errorSintactico(anticipo.fila(),anticipo.clase(),
                                      ClaseLexica.MAS,ClaseLexica.MENOS);
     }  
   }
   private void E5() {
     switch(anticipo.clase()) {
         case IDEN:
            empareja(ClaseLexica.IDEN);
            break;
         case N: 
            empareja(ClaseLexica.NUMERO);
            break;
        case PAP:
            empareja(ClaseLexica.PAP);
            E0();
            break; 
         default:    
              errores.errorSintactico(anticipo.fila(),anticipo.clase(),
                                      ClaseLexica.POR,ClaseLexica.DIV);
     }  
   }
   
   private void empareja(ClaseLexica claseEsperada) {
      if (anticipo.clase() == claseEsperada)
          sigToken();
      else errores.errorSintactico(anticipo.fila(),anticipo.clase(),claseEsperada);
   }
   private void sigToken() {
      try {
        anticipo = alex.yylex();
      }
      catch(IOException e) {
        errores.errorFatal(e);
      }
   }
   
}
