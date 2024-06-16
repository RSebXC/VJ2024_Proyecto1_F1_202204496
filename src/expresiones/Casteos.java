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
public class Casteos extends Instruccion {
    private OperadoresCasteos casteo;
    private Instruccion operandoUnico;
    private Tipo tipo; 
    
    public Casteos(Instruccion operandoUnico, Tipo tipo, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.operandoUnico = operandoUnico;
        this.tipo = tipo;
        this.casteo = convertirAEnumOperadoresCasteos(tipo);
    }
    
    private OperadoresCasteos convertirAEnumOperadoresCasteos(Tipo tipo) {
        switch (tipo.getTipo()) {
            case ENTERO:
                return OperadoresCasteos.INT;
            case DECIMAL:
                return OperadoresCasteos.DOUBLE;
            case CARACTER:
                return OperadoresCasteos.CHAR;
            default:
                throw new IllegalArgumentException("Tipo de casteo desconocido");
        }
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object Unico = null;
        if (this.operandoUnico != null) {
            Unico = this.operandoUnico.interpretar(arbol, tabla);
            if (Unico instanceof Errores) {
                return Unico;
            }
        }
        
        // Validamos los tipos
        switch (this.casteo) {
            case INT:
                return Cint(Unico);
            case DOUBLE:
                return Cdouble(Unico);
            case CHAR:
                return Chara(Unico);
            default:
                return new Errores("SEMANTICO", "Tipo de casteo desconocido", this.linea, this.col);
        }
    }
    
    public Object Cint(Object op1) {
    if (op1 instanceof Number) {
        return ((Number) op1).intValue();
    } else if (op1 instanceof String) {
        String strValue = (String) op1;
        try {
            // Intentar convertir la cadena a entero
            return Integer.parseInt(strValue);
        } catch (NumberFormatException e) {
            return new Errores("SEMANTICO", "No se pudo convertir a entero", this.linea, this.col);
        }
    }
    return new Errores("SEMANTICO", "Casteo a INT incorrecto", this.linea, this.col);
}

public Object Cdouble(Object op1) {
    if (op1 instanceof Number) {
        return ((Number) op1).doubleValue();
    } else if (op1 instanceof String) {
        String strValue = (String) op1;
        try {
            // Intentar convertir la cadena a double
            return Double.parseDouble(strValue);
        } catch (NumberFormatException e) {
            return new Errores("SEMANTICO", "No se pudo convertir a double", this.linea, this.col);
        }
    }
    return new Errores("SEMANTICO", "Casteo a DOUBLE incorrecto", this.linea, this.col);
}

    public Object Chara(Object op1) {
    if (op1 instanceof Number) {
        int intValue = ((Number) op1).intValue();
        return (char) intValue;
    } else if (op1 instanceof String) {
        String strValue = (String) op1;
        if (strValue.length() == 1) {
            return strValue.charAt(0); // Convertir el primer carácter a char (ASCII)
        } else {
            return new Errores("SEMANTICO", "Cadena no válida para casteo a CHAR", this.linea, this.col);
        }
    }
    return new Errores("SEMANTICO", "Casteo a CHAR incorrecto", this.linea, this.col);
}
}