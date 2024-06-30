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
public class AsignacionLista extends Instruccion {
    private String id;
    private int indice;
    private Instruccion valor;

    public AsignacionLista(String id, int indice, Instruccion valor, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.indice = indice;
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo simbolo = tabla.getVariable(id);
        if (simbolo == null) {
            return new Errores("SEMANTICO", "Variable no existente: " + id, this.linea, this.col);
        }

        if (!(simbolo.getValor() instanceof LinkedList)) {
            return new Errores("SEMANTICO", "La variable no es una lista: " + id, this.linea, this.col);
        }

        LinkedList<Object> lista = (LinkedList<Object>) simbolo.getValor();
        if (indice < 0 || indice >= lista.size()) {
            return new Errores("SEMANTICO", "Índice fuera de los límites de la lista: " + id, this.linea, this.col);
        }

        Object valorInterpretado = valor.interpretar(arbol, tabla);
        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }

        lista.set(indice, valorInterpretado);
        return null;
    }
}