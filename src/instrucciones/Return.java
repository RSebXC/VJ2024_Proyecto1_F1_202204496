package instrucciones;

import abstracto.Instruccion;
import simbolo.Arbol;
import simbolo.tablaSimbolos;
import simbolo.Tipo;
import simbolo.tipoDato;

/**
 * Clase para la instrucción de retorno (return) en el lenguaje.
 */
public class Return extends Instruccion {
    private Instruccion valorRetorno;

    // Constructor para el caso de return con expresión.
    public Return(Instruccion valorRetorno, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.valorRetorno = valorRetorno;
    }

    // Constructor para el caso de return sin expresión.
    public Return(int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.valorRetorno = null;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // Interpretar el valor de retorno, si existe.
        if (valorRetorno != null) {
            return valorRetorno.interpretar(arbol, tabla);
        }
        return null; // Si no hay expresión, retorna null.
    }

    public Instruccion getValorRetorno() {
        return valorRetorno;
    }

    public void setValorRetorno(Instruccion valorRetorno) {
        this.valorRetorno = valorRetorno;
    }
}
