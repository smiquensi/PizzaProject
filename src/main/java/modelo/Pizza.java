/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author santimiquel
 */
public class Pizza {

    private String masa;
    private String tipo;
    private Set<String> conjuntoIngredientes;
    private String tamanyo;
    private Precios listaPrecios;
    private double precioTotal;

    public Pizza() {

    }

    public Pizza(String masa, String tipo, Set<String> ingredienteExtra, String tamanyo) {
        this.masa = masa;
        this.tipo = tipo;
        this.conjuntoIngredientes = ingredienteExtra;
        this.tamanyo = tamanyo;

    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setConjuntoIngredientes(Set<String> ingredienteExtra) {

        this.conjuntoIngredientes = ingredienteExtra;
    }

    public void limpiaConjuntoIngredientes() {
        conjuntoIngredientes.clear();
    }

    public void setTamanyo(String tamanyo) {
        this.tamanyo = tamanyo;
    }

    public void setPrecios(Precios precios) {
        this.listaPrecios = precios;
    }

    public Precios getPrecios() {
        return listaPrecios;
    }

    public String getMasa() {
        return masa;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTamanyo() {
        return tamanyo;
    }

    public Set<String> getConjuntoIngredientes() {
        return conjuntoIngredientes;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public String composicion() {
        String cadena = "";
        double precioMasa;
        double precioTipoPizza;
        double precioIngrediente = 0.;
        double precioTamanyo;
        precioMasa = listaPrecios.getPrecio(masa);
        precioTipoPizza = listaPrecios.getPrecio(tipo);
        precioTamanyo = listaPrecios.getPrecio(tamanyo);

        for (String ingredienteSeleccionado : conjuntoIngredientes) {
            precioIngrediente += listaPrecios.getPrecio(ingredienteSeleccionado);
        }

        cadena += "MASA: " + masa + " -> " + precioMasa + "€\n";
        cadena += "TIPO : " + tipo + " -> " + precioTipoPizza + "€\n";
        if (!conjuntoIngredientes.isEmpty()) {
            cadena += "INGREDIENTES EXTRA: " + conjuntoIngredientes + " -> " + precioIngrediente + "€";
            cadena += "\n";
        }
        cadena += "TAMAÑO:  " + tamanyo + " -> " + precioTamanyo + "%";
        precioTotal = (precioMasa + precioTipoPizza + precioIngrediente);
        precioTotal += (precioTotal * (precioTamanyo / 100));

        return cadena;

    }
}
