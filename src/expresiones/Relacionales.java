/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.*;
/**
 *
 * @author rodri
 */
public class Relacionales extends Instruccion{
    private Instruccion cond1;
    private Instruccion cond2;
    private OperadoresRelacionales relacional;
    private Instruccion relacionalUnico;

    
    public Relacionales(Instruccion relacionalUnico, OperadoresRelacionales relacional, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.relacional = relacional;
        this.relacionalUnico = relacionalUnico;
    }

    public Relacionales(Instruccion cond1, Instruccion cond2, OperadoresRelacionales relacional, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.cond1 = cond1;
        this.cond2 = cond2;
        this.relacional = relacional;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
         
        Object condIzq = null, condDer = null, Unico = null;
        if (this.relacionalUnico != null) {
            Unico = this.relacionalUnico.interpretar(arbol, tabla);
            if (Unico instanceof Errores) {
                return Unico;
            }
        } else {
            condIzq = this.cond1.interpretar(arbol, tabla);
            if (condIzq instanceof Errores) {
                return condIzq;
            }
            condDer = this.cond2.interpretar(arbol, tabla);
            if (condDer instanceof Errores) {
                return condDer;
            }
        }

        return switch (relacional) {
            case EQUALS ->
                this.equals(condIzq, condDer);
            case MENOR ->
                this.menor(condIzq, condDer);   
            case MAYOR ->  
                this.mayor(condIzq, condDer);
            case DIF -> 
                this.dif(condIzq, condDer);
            case MENORIGUAL ->  
                this.menorigual(condIzq, condDer);
            case MAYORIGUAL ->  
                this.mayorigual(condIzq, condDer);
            
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }

    public Object equals(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch (comparando1) {
            case tipoDato.ENTERO ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (int) comp1 == (int) comp2;
                    case tipoDato.DECIMAL ->
                        (int) comp1 == (double) comp2;
                    case tipoDato.CARACTER ->
                        (int) comp1 == (char) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.DECIMAL ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (double) comp1 == (int) comp2;
                    case tipoDato.DECIMAL ->
                        (double) comp1 == (double) comp2;
                    case tipoDato.CARACTER ->
                        (double) comp1 == (char) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.CADENA ->
                switch (comparando2) {
                    case tipoDato.CADENA ->
                        comp1.toString().equalsIgnoreCase(comp2.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.BOOLEANO ->
                switch (comparando2) {
                    case tipoDato.BOOLEANO ->
                        (boolean) comp1 == (boolean) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.CARACTER ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (char) comp1 == (int) comp2;
                    case tipoDato.DECIMAL ->
                        (char) comp1 == (double) comp2;
                    case tipoDato.CARACTER ->
                        (char) comp1 == (char) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }
    
    public Object menor(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch (comparando1) {
            
            case tipoDato.ENTERO ->
                
                switch (comparando2) {
                    
                    case tipoDato.ENTERO ->
                        (int) comp1 < (int) comp2;
                    case tipoDato.DECIMAL ->
                        (int) comp1 < (double) comp2;
                    case tipoDato.CARACTER ->
                        (int) comp1 < (char) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.DECIMAL ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (double) comp1 < (int) comp2;
                    case tipoDato.DECIMAL ->
                        (double) comp1 < (double) comp2;
                    case tipoDato.CARACTER ->
                        (double) comp1 < (char) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.CARACTER ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (char) comp1 < (int) comp2;
                    case tipoDato.DECIMAL ->
                        (char) comp1 < (double) comp2;
                    case tipoDato.CARACTER ->
                        (char) comp1 < (char) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            
            
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }

    public Object mayor(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch (comparando1) {
            
            case tipoDato.ENTERO ->
                
                switch (comparando2) {
                    
                    case tipoDato.ENTERO ->
                        (int) comp1 > (int) comp2;
                    case tipoDato.DECIMAL ->
                        (int) comp1 > (double) comp2;
                    case tipoDato.CARACTER ->
                        (int) comp1 > (char) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.DECIMAL ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (double) comp1 > (int) comp2;
                    case tipoDato.DECIMAL ->
                        (double) comp1 > (double) comp2;
                    case tipoDato.CARACTER ->
                        (double) comp1 > (char) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.CARACTER ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (char) comp1 > (int) comp2;
                    case tipoDato.DECIMAL ->
                        (char) comp1 > (double) comp2;
                    case tipoDato.CARACTER ->
                        (char) comp1 > (char) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };

            
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }

    public Object dif(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch (comparando1) {
            
            case tipoDato.ENTERO ->
                
                switch (comparando2) {
                    
                    case tipoDato.ENTERO ->
                        (int) comp1 != (int) comp2;
                    case tipoDato.DECIMAL ->
                        (int) comp1 != (double) comp2;
                    case tipoDato.CARACTER ->
                        (int) comp1 != (char) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.DECIMAL ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (double) comp1 != (int) comp2;
                    case tipoDato.DECIMAL ->
                        (double) comp1 != (double) comp2;
                    case tipoDato.CARACTER ->
                        (double) comp1 != (char) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.CARACTER ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (char) comp1 != (int) comp2;
                    case tipoDato.DECIMAL ->
                        (char) comp1 != (double) comp2;
                    case tipoDato.CARACTER ->
                        (char) comp1 != (char) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };

            case tipoDato.CADENA ->
                switch (comparando2) {
                    case tipoDato.CADENA ->
                        !comp1.toString().equalsIgnoreCase(comp2.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }

    public Object menorigual(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch (comparando1) {
            
            case tipoDato.ENTERO ->
                
                switch (comparando2) {
                    
                    case tipoDato.ENTERO ->
                        (int) comp1 <= (int) comp2;
                    case tipoDato.DECIMAL ->
                        (int) comp1 <= (double) comp2;
                    case tipoDato.CARACTER ->
                        (int) comp1 <= (char) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.DECIMAL ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (double) comp1 <= (int) comp2;
                    case tipoDato.DECIMAL ->
                        (double) comp1 <= (double) comp2;
                    case tipoDato.CARACTER ->
                        (double) comp1 <= (char) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.CARACTER ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (char) comp1 <= (int) comp2;
                    case tipoDato.DECIMAL ->
                        (char) comp1 <= (double) comp2;
                    case tipoDato.CARACTER ->
                        (char) comp1 <= (char) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }

    public Object mayorigual(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch (comparando1) {
            
            case tipoDato.ENTERO ->
                
                switch (comparando2) {
                    
                    case tipoDato.ENTERO ->
                        (int) comp1 >= (int) comp2;
                    case tipoDato.DECIMAL ->
                        (int) comp1 >= (double) comp2;
                    case tipoDato.CARACTER ->
                        (int) comp1 >= (char) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.DECIMAL ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (double) comp1 >= (int) comp2;
                    case tipoDato.DECIMAL ->
                        (double) comp1 >= (double) comp2;
                    case tipoDato.CARACTER ->
                        (double) comp1 >= (char) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.CARACTER ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (char) comp1 >= (int) comp2;
                    case tipoDato.DECIMAL ->
                        (char) comp1 >= (double) comp2;
                    case tipoDato.CARACTER ->
                        (char) comp1 >= (char) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }
}