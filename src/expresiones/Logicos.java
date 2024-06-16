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
public class Logicos extends Instruccion{
    private Instruccion cond1;
    private Instruccion cond2;
    private OperadoresLogicos logico;
    private Instruccion operandoUnico;

    
    public Logicos(Instruccion operandoUnico, OperadoresLogicos logico, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.logico = logico;
        this.operandoUnico = operandoUnico;
    }
    
    public Logicos(Instruccion cond1, Instruccion cond2, OperadoresLogicos logico, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.cond1 = cond1;
        this.cond2 = cond2;
        this.logico = logico;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) { 
        Object condIzq = null, condDer = null, Unico = null;
        if (this.operandoUnico != null) {
            Unico = this.operandoUnico.interpretar(arbol, tabla);
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

        return switch (logico) {
            case OR ->
                this.or(condIzq, condDer);
            case AND ->
                this.and(condIzq, condDer);
            case XOR ->
                this.xor(condIzq, condDer);
            case NOT ->
                this.not(condIzq);
                
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }

    public Object or(Object op1, Object op2) {
        var tipo1 = this.cond1.tipo.getTipo();
        var tipo2 = this.cond2.tipo.getTipo();
        
        switch (tipo1) {
            case tipoDato.BOOLEANO -> {
                switch (tipo2) {
                    case tipoDato.BOOLEANO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (Boolean) op1 || (Boolean) op2;
                    }
                    
                    default -> {
                        return new Errores("SEMANTICO", "Comparacion erronea", this.linea, this.col);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Comparacion erronea", this.linea, this.col);

            }
        }
    }
    
    public Object and(Object op1, Object op2) {
        var tipo1 = this.cond1.tipo.getTipo();
        var tipo2 = this.cond2.tipo.getTipo();
        
        switch (tipo1) {
            case tipoDato.BOOLEANO -> {
                switch (tipo2) {
                    case tipoDato.BOOLEANO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (Boolean) op1 && (Boolean) op2;
                    }
                    
                    default -> {
                        return new Errores("SEMANTICO", "Comparacion erronea", this.linea, this.col);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Comparacion erronea", this.linea, this.col);

            }
        }
    }

    public Object xor(Object op1, Object op2) {
        var tipo1 = this.cond1.tipo.getTipo();
        var tipo2 = this.cond2.tipo.getTipo();
        
        switch (tipo1) {
            case tipoDato.BOOLEANO -> {
                switch (tipo2) {
                    case tipoDato.BOOLEANO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (Boolean) op1 ^ (Boolean) op2;
                    }
                    
                    default -> {
                        return new Errores("SEMANTICO", "Comparacion erronea", this.linea, this.col);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Comparacion erronea", this.linea, this.col);

            }
        }
    }
    
    public Object not(Object op1) {
        var opU = this.operandoUnico.tipo.getTipo();
        switch (opU) {
            case tipoDato.BOOLEANO -> {
                this.tipo.setTipo(tipoDato.BOOLEANO);
                return !(Boolean) op1 ;
            }
            default -> {
                return new Errores("SEMANTICO", "Negacion erronea", this.linea, this.col);
            }
        }
    }
}
