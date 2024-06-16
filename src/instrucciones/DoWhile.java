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
public class DoWhile extends Instruccion {
    private LinkedList<Instruccion> instrucciones;
    private Instruccion condicion;

    public DoWhile(LinkedList<Instruccion> instrucciones, Instruccion condicion, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.instrucciones = instrucciones;
        this.condicion = condicion;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // Crear un nuevo entorno para la ejecución del bucle
        var newTabla = new tablaSimbolos(tabla);
        
        do {
            // Crear un nuevo entorno para las instrucciones del bucle
            var newTabla2 = new tablaSimbolos(newTabla);

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

            // Evaluar la condición después de ejecutar las instrucciones
            var cond = this.condicion.interpretar(arbol, newTabla);
            if (cond instanceof Errores) {
                return cond;
            }

            // Verificar que la condición sea de tipo booleano
            if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
                return new Errores("SEMANTICO", "La condición debe ser bool", this.linea, this.col);
            }
        } while ((boolean) this.condicion.interpretar(arbol, newTabla));
        
        return null;
    }
}
