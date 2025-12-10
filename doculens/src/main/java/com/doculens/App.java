package com.doculens;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import java.io.File;
import java.util.List;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private File storedPdfFile;

    


    // This is where the application starts, Let the primary stage show the primary.fxml
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 740, 580);
        Image icon = new Image(getClass().getResourceAsStream("/assets/docuLensLogo.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Doculens");// Title
        stage.setScene(scene);
        stage.show();
    }
    // This is the method that changes the scene to the fxml file passed as parameter
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    // This is the method that loads the fxml file passed as parameter
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader
        .load();
    }
    @FXML
    void handleDragOver(DragEvent event) {
        // Check if the user is dragging a file
        if (event.getDragboard().hasFiles()) {
            // Allow the copy transfer (changes cursor to 'Copy' icon)
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    // 2. CATCH: This runs when the user DROPS the file
    @FXML
    void handleDragDropped(DragEvent event) {
        List<File> files = event.getDragboard().getFiles();

        if (files != null && !files.isEmpty()) {
            File droppedFile = files.get(0); // Get the first file

            // Check if it is a PDF
            if (droppedFile.getName().toLowerCase().endsWith(".pdf")) {
                System.out.println("File dropped: " + droppedFile.getAbsolutePath());
                
                // STORE IT just like the button method does
                this.storedPdfFile = droppedFile;
                
                // Optional: Change the button text to show success
                // primaryButton.setText("Loaded: " + droppedFile.getName());
            } else {
                System.out.println("Error: Only PDFs are allowed.");
            }
        }
        event.setDropCompleted(true);
        event.consume();
    }// Must match the fx:id exactly!
    // This is the main method that launches the application
    public static void main(String[] args) {
        launch();
    }

}