package cat.boscdelacoma.interfacetest;

import cat.boscdelacoma.interfacetest.helpers.DBConnect;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.Initializable;
import cat.boscdelacoma.interfacetest.parc.Dinosaure;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Screen3Controller implements Initializable {

    @FXML
    private Button boto_eliminar;
    @FXML
    private Button boto_modificar;
   
    @FXML
    private TableColumn<Dinosaure, Integer> col_edat;
    @FXML
    private TableColumn<Dinosaure, String> col_nom;
    @FXML
    private TableColumn<Dinosaure, Double> col_pes;
    @FXML
    private TableColumn<Dinosaure, String> col_tipus;
    @FXML
    private TableView<Dinosaure> table_dinos;
    
    @FXML
    private TextField nomField;
    @FXML
    private TextField edatField;
    @FXML
    private TextField tipusField;
    @FXML
    private TextField pesField;

    ObservableList<Dinosaure> listM;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listM = DBConnect.getDinos();
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_edat.setCellValueFactory(new PropertyValueFactory<>("edat"));
        col_tipus.setCellValueFactory(new PropertyValueFactory<>("tipus"));
        col_pes.setCellValueFactory(new PropertyValueFactory<>("pes"));

        table_dinos.setItems(listM);


        table_dinos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nomField.setText(newValue.getNom());
                edatField.setText(String.valueOf(newValue.getEdat()));
                tipusField.setText(newValue.getTipus());
                pesField.setText(String.valueOf(newValue.getPes()));
            }
        });

        boto_modificar.setOnAction(event -> modificarDino());
    }
     
    private void refrescarTaula() {
        listM = DBConnect.getDinos(); 
        table_dinos.setItems(listM); 
    }
    
    @FXML
    private void borrarDino() {
        Dinosaure selectedDino = table_dinos.getSelectionModel().getSelectedItem();
        if (selectedDino == null) {
            mostrarAlerta("Error", "Selecciona un dinosaure");
            return;
        }
        
        try {
            String id = selectedDino.getNom();
            DBConnect.deleteDino(id);
            refrescarTaula();
        } catch (SQLException e) {
            mostrarAlerta("SQL Exception", "An error occurred while deleting the dinosaur: " + e.getMessage());
        } catch (Exception e) {
            mostrarAlerta("Exception", "An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    private void modificarDino() {
        Dinosaure selectedDino = table_dinos.getSelectionModel().getSelectedItem();
        if (selectedDino == null) {
            mostrarAlerta("Error", "Selecciona un dinosaure primer");
            return;
        }

        String nom = nomField.getText();
        int edat;
        try {
            edat = Integer.parseInt(edatField.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "L'edat ha de ser un numero");
            return;
        }
        String tipus = tipusField.getText();
        double pes;
        try {
            pes = Double.parseDouble(pesField.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El pes ha de ser un número");
            return;
        }

        try {
            DBConnect.canviarDino(nom, edat, tipus, pes, selectedDino.getNom());
            refrescarTaula();
        } catch (SQLException e) {
            mostrarAlerta("SQL Exception", "Error al modificar el dinosaure:" + e.getMessage());
        } catch (Exception e) {
            mostrarAlerta("Exception", "Error:" + e.getMessage());
        }
    }

    private void mostrarAlerta(String tipus, String missatge){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(tipus);
        alert.setHeaderText(null);
        alert.setContentText(missatge);
        alert.showAndWait();
    }
}
