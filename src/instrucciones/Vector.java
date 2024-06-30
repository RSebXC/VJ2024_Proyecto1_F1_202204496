package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.Simbolo;
import simbolo.Tipo;
import simbolo.tablaSimbolos;

public class Vector extends Instruccion {
    public String identificador;
    private LinkedList<Instruccion> valores;
    private LinkedList<LinkedList<Instruccion>> valoresMatriz;
    private boolean esMatriz;

    // Constructor para vector
    public Vector(String identificador, LinkedList<Instruccion> valores, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.identificador = identificador;
        this.valores = valores;
        this.esMatriz = false;
    }

    // Constructor para matriz
    public Vector(String identificador, LinkedList<LinkedList<Instruccion>> valoresMatriz, Tipo tipo, boolean esMatriz, int linea, int col) {
        super(tipo, linea, col);
        this.identificador = identificador;
        this.valoresMatriz = valoresMatriz;
        this.valores = new LinkedList<>();
        for (var lista : valoresMatriz) {
            this.valores.addAll(lista);
        }
        this.esMatriz = true;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        if (esMatriz) {
            for (var lista : this.valoresMatriz) {
                for (var i : lista) {
                    if (i.tipo.getTipo() != this.tipo.getTipo()) {
                        return new Errores("SEMANTICO", "Tipos erroneos", this.linea, this.col);
                    }
                }
            }
        } else {
            for (var i : this.valores) {
                if (i.tipo.getTipo() != this.tipo.getTipo()) {
                    return new Errores("SEMANTICO", "Tipos erroneos", this.linea, this.col);
                }
            }
        }

        Simbolo s = new Simbolo(this.tipo, this.identificador, esMatriz ? valoresMatriz : valores);
        boolean creacion = tabla.setVariable(s);
        if (!creacion) {
            return new Errores("SEMANTICO", "Variable ya existente", this.linea, this.col);
        }

        // Imprimir valores del vector o matriz
        if (esMatriz) {
            System.out.println("Matriz: " + this.valoresMatrizToString());
        } else {
            System.out.println("Vector: " + this.valoresToString());
        }

        return null;
    }

    private String valoresToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < valores.size(); i++) {
            sb.append(valores.get(i).toString());
            if (i < valores.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private String valoresMatrizToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (var lista : valoresMatriz) {
            sb.append("[");
            for (int i = 0; i < lista.size(); i++) {
                sb.append(lista.get(i).toString());
                if (i < lista.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("], ");
        }
        if (!valoresMatriz.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }

    public LinkedList<Instruccion> getValores() {
        return valores;
    }

    public LinkedList<LinkedList<Instruccion>> getValoresMatriz() {
        return valoresMatriz;
    }
}
