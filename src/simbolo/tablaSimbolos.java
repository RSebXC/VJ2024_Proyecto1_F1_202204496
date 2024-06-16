/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simbolo;

import java.util.HashMap;

/**
 *
 * @author rodri
 */
public class tablaSimbolos {
    
    private tablaSimbolos tablaAnterior;
    private HashMap<String, Object> tablaActual;
    private String nombre;

    public tablaSimbolos() {
        this.tablaActual = new HashMap<>();
        this.nombre = "";
    }

    public tablaSimbolos(tablaSimbolos tablaAnterior) {
        this.tablaAnterior = tablaAnterior;
        this.tablaActual = new HashMap<>();
        this.nombre = "";
    }

    public tablaSimbolos getTablaAnterior() {
        return tablaAnterior;
    }

    public void setTablaAnterior(tablaSimbolos tablaAnterior) {
        this.tablaAnterior = tablaAnterior;
    }

    public HashMap<String, Object> getTablaActual() {
        return tablaActual;
    }

    public void setTablaActual(HashMap<String, Object> tablaActual) {
        this.tablaActual = tablaActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean setVariable(Simbolo simbolo) {
    Simbolo busqueda = (Simbolo) this.tablaActual.get(simbolo.getId().toLowerCase());
    if (busqueda == null) {
        this.tablaActual.put(simbolo.getId().toLowerCase(), simbolo);
        System.out.println("Símbolo agregado: " + simbolo.getId());
        return true;
    }
    System.out.println("Símbolo ya existe: " + simbolo.getId());
    return false;
}

    public Simbolo getVariable(String id) {
        for (tablaSimbolos i = this; i != null; i = i.getTablaAnterior()) {
            Simbolo busqueda = (Simbolo) i.tablaActual.
                    get(id.toLowerCase());
            if (busqueda != null) {
                return busqueda;
            }
        }
        return null;
    }
    
    
     public boolean actualizarVariable(String id, Simbolo nuevoValor) {
        for (tablaSimbolos t = this; t != null; t = t.tablaAnterior) {
            if (t.tablaActual.containsKey(id)) {
                t.tablaActual.put(id, nuevoValor);
                return true;
            }
        }
        return false;
    }
    
    public String generateHtml() {
    StringBuilder html = new StringBuilder();
    html.append("<!DOCTYPE html>");
    html.append("<html>");
    html.append("<head>");
    html.append("<title>Tabla de Símbolos</title>");
    html.append("</head>");
    html.append("<body>");
    html.append("<h1>Tabla de Símbolos</h1>");
    html.append("<table border='1'>");
    html.append("<tr>");
    html.append("<th>Nombre</th>");
    html.append("<th>Tipo</th>");
    html.append("<th>Valor</th>");
    html.append("</tr>");

    for (String key : tablaActual.keySet()) {
        Simbolo simbolo = (Simbolo) tablaActual.get(key);
        html.append("<tr>");
        html.append("<td>").append(simbolo.getId()).append("</td>");
        html.append("<td>").append(simbolo.getTipo()).append("</td>");
        html.append("<td>").append(simbolo.getValor()).append("</td>");
        html.append("</tr>");
    }

    html.append("</table>");
    html.append("</body>");
    html.append("</html>");

    return html.toString();
}

    
}
