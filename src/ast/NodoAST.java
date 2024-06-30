package ast;

import java.util.ArrayList;
import java.util.List;

public class NodoAST {
    private String nombre;
    private List<NodoAST> hijos;

    public NodoAST(String nombre) {
        this.nombre = nombre;
        this.hijos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<NodoAST> getHijos() {
        return hijos;
    }

    public void agregarHijo(NodoAST hijo) {
        this.hijos.add(hijo);
    }
}
