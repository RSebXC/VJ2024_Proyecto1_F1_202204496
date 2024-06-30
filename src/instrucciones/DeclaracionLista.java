package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.*;
import java.util.LinkedList;

public class DeclaracionLista extends Instruccion {
    private String id;
    private Tipo tipoElemento;

    public DeclaracionLista(Tipo tipoElemento, String id, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.tipoElemento = tipoElemento;
        this.id = id;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // Verificar si la variable ya existe en la tabla de símbolos
        if (tabla.getVariable(this.id) != null) {
            return new Errores("SEMANTICO", "Variable ya existente", this.linea, this.col);
        }

        // Crear una nueva lista vacía
        LinkedList<Object> nuevaLista = new LinkedList<>();

        // Crear un nuevo símbolo para la lista
        Simbolo nuevoSimbolo = new Simbolo(new Tipo(tipoDato.LISTA), this.id, nuevaLista);

        // Insertar el nuevo símbolo en la tabla de símbolos
        boolean creado = tabla.setVariable(nuevoSimbolo);
        if (!creado) {
            return new Errores("SEMANTICO", "Error al crear la variable", this.linea, this.col);
        }

        return null;
    }

    public String getId() {
        return id;
    }

    public Tipo getTipoElemento() {
        return tipoElemento;
    }
}
