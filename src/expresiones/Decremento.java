/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.*;

public class Decremento extends Instruccion {
    private Instruccion operandoUnico;

    public Decremento(Instruccion operandoUnico, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.operandoUnico = operandoUnico;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object Unico = this.operandoUnico.interpretar(arbol, tabla);
        if (Unico instanceof Errores) {
            return Unico;
        }

        if (this.operandoUnico instanceof AccesoVar) {
            var valor = tabla.getVariable(((AccesoVar) this.operandoUnico).getId());
            if (valor == null) {
                return new Errores("SEMANTICA", "Variable no existente", this.linea, this.col);
            }
            Unico = valor.getValor();

            // Realizar la operación de decremento
            Object resultado = null;
            if (Unico instanceof Integer) {
                resultado = (int) Unico - 1;
            } else if (Unico instanceof Double) {
                resultado = (double) Unico - 1.0;
            } else {
                return new Errores("SEMANTICO", "Decremento erróneo: tipo no soportado", this.linea, this.col);
            }

            // Actualizar el valor de la variable en la tabla de símbolos
            valor.setValor(resultado);
            tabla.actualizarVariable(((AccesoVar) this.operandoUnico).getId(), valor);
            this.tipo.setTipo(valor.getTipo().getTipo());
            return resultado;
        } else {
            return new Errores("SEMANTICO", "Decremento erróneo: operando no es una variable", this.linea, this.col);
        }
    }
}
