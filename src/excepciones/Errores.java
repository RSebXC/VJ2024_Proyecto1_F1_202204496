/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rodri
 */
public class Errores {
     private String tipo;
    private String desc;
    private int linea;
    private int columna;

    public Errores(String tipo, String desc, int linea, int columna) {
        this.tipo = tipo;
        this.desc = desc;
        this.linea = linea;
        this.columna = columna;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "Errores{" + "tipo=" + tipo + ", desc=" + desc + ", linea=" + linea + ", columna=" + columna + '}';
    }

 public static String generateHtml(List<Errores> erroresList) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<title>Errores</title>");
        html.append("</head>");
        html.append("<body>");
        html.append("<h1>Listado de Errores</h1>");
        html.append("<table border='1'>");
        html.append("<tr>");
        html.append("<th>Tipo</th>");
        html.append("<th>Descripción</th>");
        html.append("<th>Línea</th>");
        html.append("<th>Columna</th>");
        html.append("</tr>");

        for (Errores error : erroresList) {
            html.append("<tr>");
            html.append("<td>").append(error.getTipo()).append("</td>");
            html.append("<td>").append(error.getDesc()).append("</td>");
            html.append("<td>").append(error.getLinea()).append("</td>");
            html.append("<td>").append(error.getColumna()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table>");
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }
}
