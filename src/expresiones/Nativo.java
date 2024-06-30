package expresiones;

import abstracto.Instruccion;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @autor rodri
 */
public class Nativo extends Instruccion {
    public Object valor;

    public Nativo(Object valor, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        return this.valor;
    }

    @Override
    public String toString() {
        return valor.toString();
    }

    public Object getValor() {
        return this.valor;
    }
    
    public static Nativo crearNativo(Object valor, int linea, int col) {
        Tipo tipo = new Tipo(determinarTipo(valor)); // Implementa determinarTipo para deducir el tipo de dato
        return new Nativo(valor, tipo, linea, col);
    }
    
    private static tipoDato determinarTipo(Object valor) {
        if (valor instanceof Integer) {
            return tipoDato.ENTERO;
        } else if (valor instanceof Double) {
            return tipoDato.DECIMAL;
        } else if (valor instanceof String) {
            return tipoDato.CADENA;
        } else if (valor instanceof Boolean) {
            return tipoDato.BOOLEANO;
        } else {
            return tipoDato.VOID; // O lanza una excepción si prefieres manejar tipos desconocidos
        }
    }
}
