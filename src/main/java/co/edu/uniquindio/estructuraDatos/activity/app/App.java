package co.edu.uniquindio.estructuraDatos.activity.app;

import co.edu.uniquindio.estructuraDatos.activity.viewControllers.InicioViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("InicioView.fxml"));
        Parent AnchorPane= loader.load();
        Scene scene= new Scene(AnchorPane);
        primaryStage.setScene(scene);
        InicioViewController controller= loader.getController();
        controller.setStage(primaryStage);
        primaryStage.show();
    }
}
