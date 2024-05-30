package cat.boscdelacoma.interfacetest;

import cat.boscdelacoma.interfacetest.parc.Dinosaure;
import cat.boscdelacoma.interfacetest.parc.Parc;
import cat.boscdelacoma.interfacetest.parc.Aeris.Microraptor;
import cat.boscdelacoma.interfacetest.parc.Aeris.Pterodactil;
import cat.boscdelacoma.interfacetest.parc.Aquatics.Mosasaurus;
import cat.boscdelacoma.interfacetest.parc.Aquatics.Plesiosaurus;
import cat.boscdelacoma.interfacetest.parc.Terrestres.TRex;
import cat.boscdelacoma.interfacetest.parc.Terrestres.Velociraptor;
import cat.boscdelacoma.interfacetest.helpers.DBConnect;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;

public class Screen2Controller implements Initializable {
    @FXML
    private TextField nameField; 
    @FXML
    private TextField ageField; 
    @FXML
    private TextField typeField; 
    @FXML
    private TextField weightField; 
    @FXML
    private Button addButton; // Botó per afegir
    private Parc parcDinosaures; 
    private Map<String, Function<DinosaureParams, Dinosaure>> tipusMap;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        parcDinosaures = new Parc("Jurassic Park");

        tipusMap = new HashMap<>();
        tipusMap.put("velociraptor", params -> new Velociraptor(params.id, params.nom, params.edat, params.tipus, params.pes, params.parcId));
        tipusMap.put("trex", params -> new TRex(params.id, params.nom, params.edat, params.tipus, params.pes, params.parcId));
        tipusMap.put("microraptor", params -> new Microraptor(params.id, params.nom, params.edat, params.tipus, params.pes, params.parcId));
        tipusMap.put("pterodactil", params -> new Pterodactil(params.id, params.nom, params.edat, params.tipus, params.pes, params.parcId));
        tipusMap.put("mosasaurus", params -> new Mosasaurus(params.id, params.nom, params.edat, params.tipus, params.pes, params.parcId));
        tipusMap.put("plesiosaurus", params -> new Plesiosaurus(params.id, params.nom, params.edat, params.tipus, params.pes, params.parcId));
        
        // Assignar acció al botó
        addButton.setOnAction(event -> afegirDinosaure());
    }

    private void afegirDinosaure() {
        try {
            // Agafar les dades dels camps de text
           String nom = nameField.getText();
            int edat = Integer.parseInt(ageField.getText());
            String tipus = typeField.getText().toLowerCase();
            double pes = Double.parseDouble(weightField.getText());

            // Comprovar que els camps no estan buits
            if (nom.isEmpty() || tipus.isEmpty()) {
                showAlert(AlertType.ERROR, "ERROR!", "Els camps no poden estar buits");
                return;
            }

            // Agafar la funció que construeix el mapa 
          Function<DinosaureParams, Dinosaure> constructor = tipusMap.get(tipus);

            // Si el tipus no és vàlid, mostrar error
            if (constructor == null) {
              showAlert(AlertType.ERROR, "ERROR!", "Aquest tipus no existeix a Jurassic Park!");
                return;
            }

            // Crear els paràmetres i el dinosaure
           DinosaureParams params = new DinosaureParams(0, nom, edat, tipus, pes, 0); //L'id sera 0 ja que es genera automàticament
            Dinosaure dino = constructor.apply(params);

            parcDinosaures.afegirDinosaure(dino);
            insertarDinoEnDB(dino);
            
            //Mostrar el missatge 
            showAlert(AlertType.INFORMATION, "PERFECTE", "S'ha afegit el dinosaure: " + dino.getNom() + " de tipus: " + tipus);
            netejarCamps();

        } catch (Exception e) {
            showAlert(AlertType.ERROR, "ERROR!", "El pes ha de ser double i l'edat enter");
        }
    }

    // Funció per inserir un dinosaure a la base de dades
    private void insertarDinoEnDB(Dinosaure dino) {
        String insertSQL = "INSERT INTO dinos (nom, edat, tipus, pes) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnect.getConnect();
             PreparedStatement afegir = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
            
              afegir.setString(1, dino.getNom());
             afegir.setInt(2, dino.getEdat());
             afegir.setString(3, dino.getTipus());
            afegir.setDouble(4, dino.getPes());

            if (afegir.executeUpdate() > 0) {
                var rstKeys = afegir.getGeneratedKeys();
                if (rstKeys.next()) {
                    dino.setId(rstKeys.getInt(1)); 
                }
            }
            
        } catch (SQLException ex) {
            showAlert(AlertType.ERROR, "Error", "Excepció " + ex.getMessage());
        }
    }

    // Funció per mostrar alertes
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Funció per netejar els camps
    private void netejarCamps() {
        nameField.clear();
        ageField.clear();
        typeField.clear();
        weightField.clear();
    }

    // Classe per agrupar els paràmetres dels dinosaures
    private static class DinosaureParams {
        int id;
        String nom;
        int edat;
        String tipus;
        double pes;
        int parcId;
        DinosaureParams(int id, String nom, int edat, String tipus, double pes, int parcId) {
            this.id = id;
            this.nom = nom;
            this.edat = edat;
            this.tipus = tipus;
            this.pes = pes;
            this.parcId = parcId;
        }
    }
}
