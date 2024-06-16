/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1_vj2024;

import abstracto.Instruccion;
import analisis.parser;
import analisis.scanner;
import excepciones.Errores;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.tablaSimbolos;
/**
 *
 * @author rodri
 */
public class Proyecto1_VJ2024 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Interfaz p = new Interfaz();
        p.setVisible(true);
    }
    
}

/* imprimir("Mi cadena"+1);
imprimir(2+2+2+2); 
imprimir(-1.33+3.33);
imprimir(5*9-1); 
imprimir(9/3);
imprimir(9**2);
imprimir(2==1+1);
imprimir(1<2);
imprimir(4+3>4);
imprimir(5!=6);
imprimir(4>=4);
imprimir(7-2<=6);
imprimir(3<1||1>2);
imprimir(5<6&&2>1);
imprimir(7>3||10<3);
imprimir(5<6);
imprimir(((int)56.5)+45.6);
int a = 5;
imprimir(a);
a = a + 6;
imprimir(a);
imprimir(a-1==11);
if (a>9) { imprimir("siiiiiuuuu");}
int i = 0;
while (i < 11) {
    if (i == 10) {
        imprimir("Terminamos con break");
        break;
    }
    imprimir("Iteracion " + i);
    i = i + 1;
} 
int i2 = 12;
while (i2 > 10) {
    if (i2 == 11) {
        println("Terminamos con break");
        break;
    }
int b = 4;
    println("Iteracion " + i2);
    i2 = i2--;
}*/