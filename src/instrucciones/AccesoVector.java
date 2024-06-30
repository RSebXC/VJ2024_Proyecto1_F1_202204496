package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import expresiones.Nativo;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.Simbolo;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

public class AccesoVector extends Instruccion {

    private String id;
    private int indiceFila;
    private int indiceColumna;
    private boolean esMatriz;

    // Constructor para matriz
    public AccesoVector(String id, int indiceFila, int indiceColumna, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.indiceFila = indiceFila;
        this.indiceColumna = indiceColumna;
        this.esMatriz = true;
    }

    // Constructor para vector
    public AccesoVector(String id, int indiceFila, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.indiceFila = indiceFila;
        this.esMatriz = false;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var valor = tabla.getVariable(this.id);

        if (valor != null && valor.getValor() instanceof LinkedList) {
            LinkedList<?> vectorObtenido = (LinkedList<?>) valor.getValor();

            if (esMatriz) {
                // Acceso a matriz
                if (indiceFila >= 0 && indiceFila < vectorObtenido.size()) {
                    LinkedList<?> fila = (LinkedList<?>) vectorObtenido.get(indiceFila);
                    if (indiceColumna >= 0 && indiceColumna < fila.size()) {
                        Nativo elemento = (Nativo) fila.get(indiceColumna);
                        this.tipo.setTipo(elemento.tipo.getTipo());
                        return elemento.getValor();
                    } else {
                        return new Errores("SEMANTICO", "Índice de columna fuera de rango", this.linea, this.col);
                    }
                } else {
                    return new Errores("SEMANTICO", "Índice de fila fuera de rango", this.linea, this.col);
                }
            } else {
                // Acceso a vector
                if (indiceFila >= 0 && indiceFila < vectorObtenido.size()) {
                    Nativo elemento = (Nativo) vectorObtenido.get(indiceFila);
                    this.tipo.setTipo(elemento.tipo.getTipo());
                    return elemento.getValor();
                } else {
                    return new Errores("SEMANTICO", "Índice fuera de rango", this.linea, this.col);
                }
            }
        }
        return new Errores("SEMANTICO", "Variable no encontrada o no es un vector/matriz", this.linea, this.col);
    }

    public String getId() {
        return id;
    }
}
