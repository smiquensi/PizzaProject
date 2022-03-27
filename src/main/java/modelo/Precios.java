/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author santimiquel
 */
public class Precios {

    private Map<String,Double> listadoPrecios = new HashMap<>();

    public Precios() { 
        listadoPrecios.put("Normal", 3.00);
        listadoPrecios.put("Integral", 3.50);
        listadoPrecios.put("Básica", 3.00);
        listadoPrecios.put("Cuatro quesos", 5.00);
        listadoPrecios.put("Barbacoa", 7.00);
        listadoPrecios.put("Mexicana", 8.50);
        listadoPrecios.put("Jamón", 0.50);
        listadoPrecios.put("Queso", 0.75);
        listadoPrecios.put("Tomate", 1.50);
        listadoPrecios.put("Cebolla", 0.75);
        listadoPrecios.put("Olivas", 1.00);
        listadoPrecios.put("Normal", 0.00);
        listadoPrecios.put("Mediana", 15.);
        listadoPrecios.put("Familiar", 30.);
    }
    public void setProductoPrecio(String producto,Double precio){
        listadoPrecios.put(producto, precio);
    }

    public Double getPrecio(String nomProducto) {
        return listadoPrecios.get(nomProducto);

    }

}
