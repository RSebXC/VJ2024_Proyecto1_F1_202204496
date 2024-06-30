package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import expresiones.Nativo;
import simbolo.*;
import java.util.LinkedList;

public class AsignacionVec extends Instruccion {
    private String id;
    private Instruccion indiceFila;
    private Instruccion indiceColumna;
    private Instruccion valor;
    private boolean esMatriz;

    public AsignacionVec(String id, Instruccion indiceFila, Instruccion valor, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.indiceFila = indiceFila;
        this.valor = valor;
        this.esMatriz = false;
    }

    public AsignacionVec(String id, Instruccion indiceFila, Instruccion indiceColumna, Instruccion valor, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.indiceFila = indiceFila;
        this.indiceColumna = indiceColumna;
        this.valor = valor;
        this.esMatriz = true;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo variable = tabla.getVariable(this.id);
        if (variable == null) {
            return new Errores("SEMANTICA", "Variable no existente", this.linea, this.col);
        }

        if (!(variable.getValor() instanceof LinkedList)) {
            return new Errores("SEMANTICA", "Variable no es un vector o matriz", this.linea, this.col);
        }

        LinkedList<?> vectorObtenido = (LinkedList<?>) variable.getValor();

        // Interpretar los índices y el valor
        Object indiceFilaEvaluado = indiceFila.interpretar(arbol, tabla);
        if (indiceFilaEvaluado instanceof Errores) return indiceFilaEvaluado;
        int indiceFila = (int) indiceFilaEvaluado;

        Object valorEvaluado = valor.interpretar(arbol, tabla);
        if (valorEvaluado instanceof Errores) return valorEvaluado;

        if (esMatriz) {
            Object indiceColumnaEvaluado = indiceColumna.interpretar(arbol, tabla);
            if (indiceColumnaEvaluado instanceof Errores) return indiceColumnaEvaluado;
            int indiceColumna = (int) indiceColumnaEvaluado;

            // Asignación en matriz
            if (indiceFila >= 0 && indiceFila < vectorObtenido.size()) {
                LinkedList<?> fila = (LinkedList<?>) vectorObtenido.get(indiceFila);
                if (fila instanceof LinkedList) {
                    LinkedList<Nativo> filaNativa = (LinkedList<Nativo>) fila;
                    if (indiceColumna >= 0 && indiceColumna < filaNativa.size()) {
                        filaNativa.set(indiceColumna, Nativo.crearNativo(valorEvaluado, this.linea, this.col));
                        return null; // Asignación exitosa
                    } else {
                        return new Errores("SEMANTICA", "Índice de columna fuera de rango", this.linea, this.col);
                    }
                } else {
                    return new Errores("SEMANTICA", "Fila no es un LinkedList de Nativo", this.linea, this.col);
                }
            } else {
                return new Errores("SEMANTICA", "Índice de fila fuera de rango", this.linea, this.col);
            }
        } else {
            // Asignación en vector
            if (indiceFila >= 0 && indiceFila < vectorObtenido.size()) {
                if (vectorObtenido instanceof LinkedList) {
                    LinkedList<Nativo> vectorNativo = (LinkedList<Nativo>) vectorObtenido;
                    vectorNativo.set(indiceFila, Nativo.crearNativo(valorEvaluado, this.linea, this.col));
                    return null; // Asignación exitosa
                } else {
                    return new Errores("SEMANTICA", "Vector no es un LinkedList de Nativo", this.linea, this.col);
                }
            } else {
                return new Errores("SEMANTICA", "Índice fuera de rango", this.linea, this.col);
            }
        }
    }

    public String getId() {
        return id;
    }
}
