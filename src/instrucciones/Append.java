package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.*;
import java.util.LinkedList;

public class Append extends Instruccion {
    private String id;
    private Instruccion valor;

    public Append(String id, Instruccion valor, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
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
        Object valorInterpretado = valor.interpretar(arbol, tabla);

        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }

        lista.add(valorInterpretado);
        return null;
    }
}
