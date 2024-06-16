/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;
import simbolo.*;
/**
 *
 * @author rodri
 */
public class For extends Instruccion {
    private Instruccion asignacion;
    private Instruccion condicion;
    private Instruccion actualizacion;
    private LinkedList<Instruccion> instrucciones;

    public For(Instruccion asignacion, Instruccion condicion, Instruccion actualizacion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.asignacion = asignacion;
        this.condicion = condicion;
        this.actualizacion = actualizacion;
        this.instrucciones = instrucciones;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // Crear un nuevo entorno para la tabla de símbolos
        var newTabla = new tablaSimbolos(tabla);

        // Interpretar la asignación inicial del bucle
        var res1 = this.asignacion.interpretar(arbol, newTabla);
        if (res1 instanceof Errores) {
            return res1;
        }

        // Validar la condición booleana inicial del bucle
        var cond = this.condicion.interpretar(arbol, newTabla);
        if (cond instanceof Errores) {
            return cond;
        }

        while ((boolean) cond) {
            // Crear un nuevo entorno para cada iteración del bucle
            var newTabla2 = new tablaSimbolos(newTabla);

            // Ejecutar las instrucciones dentro del bucle
            for (var i : this.instrucciones) {
                if (i instanceof Break) {
                    return null; // Salir del bucle completamente
                }
                if (i instanceof Continue){
                    break;
                }
                var resIns = i.interpretar(arbol, newTabla2);
                if (resIns instanceof Break) {
                    return null; // Salir del bucle completamente
                }
                if (resIns instanceof Continue) {
                    
                    break; // Ir a la siguiente iteración del bucle while
                }
                if (resIns instanceof Errores) {
                    return resIns;
                }
            }

            // Actualizar la variable de control del bucle
            var act = this.actualizacion.interpretar(arbol, newTabla);
            if (act instanceof Errores) {
                return act;
            }

            // Re-evaluar la condición del bucle para la siguiente iteración
            cond = this.condicion.interpretar(arbol, newTabla);
            if (cond instanceof Errores) {
                return cond;
            }
        }

        return null;
    }
}