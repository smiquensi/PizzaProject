package com.proyecto.hellofx;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class PrimaryController implements Initializable {

    Map<String, Double> listadoPrecios = new HashMap<>();
    List<String> pedidosImp = new ArrayList<>();

    private double precioMasa;
    private double precioTipoPizza;
    private double precioIngrediente;
    private double precioTamanyo;
    private double precioTotal;
    private String masaElegida;
    private String tipoPizzaElegida;
    private Set<String> ingredienteElegido = new HashSet<>();
    private String tamanyoElegido;

    private Label etiqueta;
    @FXML
    private Pane pane;
    @FXML
    private ImageView backgroundIMG;
    @FXML
    private GridPane grid1;
    @FXML
    private Label lMasa;
    @FXML
    private Label ltipo;
    @FXML
    private Label lIngrediente;
    @FXML
    private Label lTamanyo;
    @FXML
    private RadioButton integral;
    @FXML
    private ToggleGroup tipoMasa;
    @FXML
    private RadioButton normal;
    @FXML
    private ListView<String> ingredientesExtra;
    @FXML
    private Spinner<String> tamanyoP;
    @FXML
    private ComboBox<String> tipoP;
    @FXML
    private Button pedido;
    @FXML
    private GridPane grid2;
    @FXML
    private Label pedidoTittle;
    @FXML
    private Label PedidoResultado;
    @FXML
    private Label resultado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rellenarApp();
    }

    private void rellenarApp() {

        // Añadir diccionario opciones\precios
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

        // Definir el button radio por defecto (normal)
        normal.setSelected(true);

        // Crear la lista visible de Tipos de pizza y darle por defecto la basica
        ObservableList<String> listaTiposPizza = FXCollections.observableArrayList("Básica",
                "Cuatro quesos", "Barbacoa", "Mexicana");
        tipoP.setValue("Básica");
        tipoP.setItems(listaTiposPizza);

        // Crear la lista de Ingredientes extra        
        ObservableList<String> listaIngredientes = FXCollections.observableArrayList("Jamón",
                "Queso", "Tomate", "Cebolla", "Olivas");
        ingredientesExtra.setItems(listaIngredientes);
        ingredientesExtra.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Crear una lista visible de tamaños de pizza
        ObservableList<String> tamanyoPizza = FXCollections.observableArrayList("Normal",
                "Mediana", "Familiar");
        SpinnerValueFactory.ListSpinnerValueFactory<String> tamanyos = new SpinnerValueFactory.ListSpinnerValueFactory(tamanyoPizza);
        tamanyoP.setValueFactory(tamanyos);

    }

    private void tipoMasa() {
        masaElegida = ((RadioButton) tipoMasa.getSelectedToggle()).getText();
    }

    private void ingredientes() {
        ingredienteElegido.clear();

        for (String ingredienteAnyadido : ingredientesExtra.getSelectionModel().getSelectedItems()) {
            if (ingredienteAnyadido != null) {
                ingredienteElegido.add(ingredienteAnyadido);
            }
        }
    }

    private void tamanyo() {
        tamanyoElegido = tamanyoP.getValue();
    }

    private void tipo() {
        tipoPizzaElegida = tipoP.getValue();
    }
    
    private String composicion(){
        String cadena =  "";
        precioMasa = listadoPrecios.get(masaElegida);
        precioTipoPizza = listadoPrecios.get(tipoPizzaElegida);
        
        precioTamanyo = listadoPrecios.get(tamanyoElegido);
        
        for (String ingredienteSeleccionado : ingredienteElegido) {
            precioIngrediente += listadoPrecios.get(ingredienteSeleccionado);
        }
        
        cadena += "MASA: " + masaElegida + " -> " + precioMasa+ "€\n";
        cadena += "TIPO : " + tipoPizzaElegida + " -> " + precioTipoPizza + "€\n";
        if (!ingredienteElegido.isEmpty()) {
            cadena += "INGREDIENTES EXTRA: " + ingredienteElegido + " -> " + precioIngrediente + "€";  
            cadena +="\n";
        }
        cadena += "TAMAÑO:  " + tamanyoElegido + " -> " +precioTamanyo + "%";
        precioTotal = (precioMasa + precioTipoPizza + precioIngrediente);
        precioTotal += (precioTotal * (precioTamanyo/100));
        
        return cadena;
       
    }
       @FXML
    private void calcularPedido(ActionEvent event) {
        tipoMasa();
        ingredientes();
        tamanyo();
        tipo();
        
        PedidoResultado.setText(composicion());
        resultado.setText(String.format("PRECIO TOTAL %.2f€", precioTotal));
        
        
    }

}
