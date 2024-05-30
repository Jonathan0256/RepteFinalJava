
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cat.boscdelacoma.interfacetest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
/**
 * FXML Classe controller
 *
 * @author jonar
 */
public class MainScreenController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private Pane panel;
    @FXML
    private BorderPane mainPane;
    @FXML
    private void handleButton1Action(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
            Pane view = loader.load();
            mainPane.setLeft(view);
        } catch (IOException e) {
            System.out.println("Excepcio: " + e.getMessage());
        }
    }

    
    @FXML
    private void handleButton2Action(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Screen2.fxml"));
            Pane view = loader.load();
            mainPane.setRight(view);
        } catch (IOException e) {
            System.out.println("Excepcio: " + e.getMessage());
        }
    }

    
    @FXML
    private void handleButton3Action(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Screen3.fxml"));
            Pane view = loader.load();
            mainPane.setRight(view);
        } catch (IOException e) {
            System.out.println("Excepcio: " + e.getMessage());
        }
    }
    
        @FXML
    private void handleButton4Action(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Screen3.fxml"));
            Pane view = loader.load();
            mainPane.setRight(view);
        } catch (IOException e) {
            System.out.println("Excepcio: " + e.getMessage());
        }
    }
        @FXML
    private void handleButton5Action(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Screen3.fxml"));
            Pane view = loader.load();
            mainPane.setRight(view);
        } catch (IOException e) {
            System.out.println("Excepcio: " + e.getMessage());
        }
    }
    

    /**
     * Inicialitza classe controller.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        
    }

}
