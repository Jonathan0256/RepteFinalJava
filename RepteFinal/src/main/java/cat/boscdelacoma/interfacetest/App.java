package cat.boscdelacoma.interfacetest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import javafx.scene.image.Image;
import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        try{
        scene = new Scene(loadFXML("MainScreen"), 780, 480);
        stage.setScene(scene);
      //  stage.getIcons().add(new Image("./dinosaur.png"));
        stage.setTitle("Parc de Dinosaures");
        stage.show();
        }catch(IOException ex){
            ex.getMessage();
        }
    }

    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}