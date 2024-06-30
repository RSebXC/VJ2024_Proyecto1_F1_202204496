package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tipoDato;
import java.util.LinkedList;
import simbolo.Simbolo;
import simbolo.tablaSimbolos;

public class Remove extends Instruccion {
    private String id;
    private Instruccion posicion;

    public Remove(String id, Instruccion posicion, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.posicion = posicion;
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
        Object posicionInterpretada = posicion.interpretar(arbol, tabla);

        if (posicionInterpretada instanceof Errores) {
            return posicionInterpretada;
        }

        if (!(posicionInterpretada instanceof Integer)) {
            return new Errores("SEMANTICO", "La posición debe ser un entero", this.linea, this.col);
        }

        int index = (int) posicionInterpretada;

        if (index < 0 || index >= lista.size()) {
            return new Errores("SEMANTICO", "Índice fuera de rango", this.linea, this.col);
        }

        Object valorEliminado = lista.remove(index);
        return valorEliminado;
    }
}
