package com.example;

import java.io.File;
import java.io.IOException;


import com.example.Controller.PrimaryController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class App extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        try {
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("AddressApp");
            this.primaryStage.getIcons().add(
                new Image(getClass().getResource("/com/example/images/icon.png").toExternalForm())
            );

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/Primary.fxml"));
            BorderPane root = loader.load();
            Scene scene = new Scene(root);

            // Collega controller
            PrimaryController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);

            this.primaryStage.setScene(scene);
            this.primaryStage.show();
            this.primaryStage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
        launch(args);
    }
}
