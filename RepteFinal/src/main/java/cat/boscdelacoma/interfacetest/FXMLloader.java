package cat.boscdelacoma.interfacetest;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 * FXMLloader is responsible for loading FXML files and returning the corresponding Pane.
 *
 * Author: jonar
 */
public class FXMLloader {

    private Pane view;

    /**
     * Loads an FXML file and returns the corresponding Pane.
     *
     * @param fileName the name of the FXML file to load (without extension)
     * @return the loaded Pane, or null if an error occurs
     */
    public Pane getPage(String fileName) {
        try {
            URL fileUrl =App.class.getResource("/cat.boscdelacoma.interfacetest/" + fileName + ".fxml");
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("FXML file can't be found");
            }
            view = new FXMLLoader().load(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}