/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.proyecto.hellofx.PrimaryController;
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
    private String gratinado;
    private String bebida;
    private String tamanyo;
    private Precios listaPrecios;
    private Double precioTotal;

    public Pizza() {

    }

    public Pizza(String masa, String tipo, Set<String> ingredienteExtra, String tamanyo, String gratinado, String bebida) {
        this.masa = masa;
        this.tipo = tipo;
        this.conjuntoIngredientes = ingredienteExtra;
        this.tamanyo = tamanyo;
        this.gratinado = gratinado;
        this.bebida = bebida;

    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setGratinado(String gratinado) {
        this.gratinado = gratinado;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public void setConjuntoIngredientes(Set<String> ingredienteExtra) {

        this.conjuntoIngredientes = ingredienteExtra;
    }

//    public void limpiaConjuntoIngredientes() {
//        conjuntoIngredientes.clear();
//    }
    public void setTamanyo(String tamanyo) {
        this.tamanyo = tamanyo;
    }

    public void setPrecios(Precios precios) {
        this.listaPrecios = precios;
    }

    public String getGratinado() {
        return gratinado;
    }

    public String getBebida() {
        return bebida;
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

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public String composicion() {
        String cadena = "";
        Double precioMasa;
        Double precioTipoPizza;
        Double precioIngrediente = 0.;
        Double precioTamanyo;
        Double precioBebida = 0.;
        Double precioGratinado = 0.;

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
        cadena += "TAMAÑO:  " + tamanyo + " -> " + precioTamanyo + "%\n";
        if (bebida != null) {
            precioBebida = listaPrecios.getPrecio(bebida);
            cadena += "COMPLEMENTOS:  " + bebida + " -> " + precioBebida + "€\n";
        }
        if (gratinado != null) {
            precioGratinado = listaPrecios.getPrecio(gratinado);
            cadena += "COMPLEMENTOS:  " + gratinado + " -> " + precioGratinado + "%";
        }

        precioTotal = (precioMasa + precioTipoPizza + precioIngrediente + precioBebida);
        precioTotal += (precioTotal * (precioTamanyo / 100));
        precioTotal += (precioTotal * (precioGratinado / 100));
        return cadena;

    }

}
