package co.edu.uniquindio.estructuraDatos.activity.app;

import co.edu.uniquindio.estructuraDatos.activity.viewControllers.ClienteViewController;
import co.edu.uniquindio.estructuraDatos.activity.viewControllers.InicioViewController;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

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

        primaryStage.setTitle( "Tienda" );
        primaryStage.show();
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), AnchorPane);

        // Establecemos la opacidad inicial y final para la transición
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);

        // Iniciamos la transición
        fadeTransition.play();



    }



}
