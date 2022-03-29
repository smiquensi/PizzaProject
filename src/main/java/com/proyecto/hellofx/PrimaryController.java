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
import javafx.scene.control.CheckBox;
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
import modelo.Pizza;
import modelo.Precios;

public class PrimaryController implements Initializable {

    private Pizza pizza1;
    private Precios precios1;

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
    private GridPane grid2;
    @FXML
    private Label pedidoTittle;
    @FXML
    private Label pedidoResultado;
    @FXML
    private Label resultado;
    @FXML
    private Label lIngrediente1;
    @FXML
    private CheckBox bebida;
    @FXML
    private CheckBox gratinado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rellenarApp();
        calcularPedido();

    }

    private void rellenarApp() {
        pizza1 = new Pizza();
        precios1 = new Precios();
        pizza1.setPrecios(precios1);

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
        pizza1.setMasa(((RadioButton) tipoMasa.getSelectedToggle()).getText());
    }

    private void ingredientes() {
        Set<String> ingredientesElegidos = new HashSet<>();
        for (String ingredienteAnyadido : ingredientesExtra.getSelectionModel().getSelectedItems()) {
            if (ingredienteAnyadido != null) {
                ingredientesElegidos.add(ingredienteAnyadido);
            }
        }
        pizza1.setConjuntoIngredientes(ingredientesElegidos);
    }

    private void tamanyo() {
        pizza1.setTamanyo(tamanyoP.getValue());
    }

    private void tipo() {
        pizza1.setTipo(tipoP.getValue());
    }
    
    private void bebida(){       
        if (bebida.isSelected()) {
            pizza1.setBebida(bebida.getText());
        }else{
            pizza1.setBebida(null);
        }        
    }
    
    private void gratinado(){         
        if (gratinado.isSelected()) {
            pizza1.setGratinado(gratinado.getText());               
        }else{
            pizza1.setGratinado(null);
        }     
    }
    
    private void calcularPedido() {
        tipoMasa();
        ingredientes();
        tamanyo();
        tipo();
        bebida();
        gratinado();        
        pedidoResultado.setText(pizza1.composicion());
        resultado.setText(String.format("PRECIO TOTAL %.2f€", pizza1.getPrecioTotal()));
    }



    @FXML
    private void eventoMouse(ActionEvent event) {
        calcularPedido();
    }

    @FXML
    private void eventoMouseClick(MouseEvent event) {
        calcularPedido();
    }

}
