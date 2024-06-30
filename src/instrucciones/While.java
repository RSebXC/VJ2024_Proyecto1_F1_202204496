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

public class While extends Instruccion {
    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;

    public While(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }
    
    @Override
public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
    // Crear un nuevo entorno para la ejecución del bucle
    var newTabla = new tablaSimbolos(tabla);
    newTabla.setNombre("While");

    // Evaluar la condición inicial
    var cond = this.condicion.interpretar(arbol, newTabla);
    if (cond instanceof Errores) {
        return cond;
    }

    // Verificar que la condición sea de tipo booleano
    if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
        return new Errores("SEMANTICO", "La condición debe ser bool", this.linea, this.col);
    }

    // Ejecutar el bucle mientras la condición sea verdadera
    while ((boolean) this.condicion.interpretar(arbol, newTabla)) {
        // Crear un nuevo entorno para cada iteración del bucle
        var newTabla2 = new tablaSimbolos(newTabla);
        newTabla2.setNombre("Iteración While");

        // Ejecutar las instrucciones del bucle
        for (var i : this.instrucciones) {
            var resIns = i.interpretar(arbol, newTabla2);
            if (resIns instanceof Errores) {
                return resIns;
            }
            if (resIns instanceof Break) {
                return null;
            }
            if (resIns instanceof Continue) {
                break;
            }
        }
    }
    return null;
}

}
