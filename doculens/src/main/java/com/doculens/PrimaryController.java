package com.doculens;

import java.io.IOException;
import java.io.File;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button; 
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PrimaryController {

    private File storedPdfFile;

    @FXML
    private HBox primaryButton; // Container (VBox/StackPane)
    
    @FXML
    private Button statusLabel; // Your "Attach PDF" Button
    
    // --- 1. THE HELPER METHOD (The Key Fix) ---
    // This handles switching screens AND passing the file data
    private void goToSecondaryScreen(File pdfFile, Node sourceNode) {
        try {
            // A. Load the FXML manually so we can get the controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
            Parent root = loader.load();

            // B. Get the Controller and PASS THE FILE
            SecondaryController secondaryController = loader.getController();
            secondaryController.setPdfFile(pdfFile);

            // C. Switch the Scene
            Stage stage = (Stage) sourceNode.getScene().getWindow();
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading secondary.fxml: " + e.getMessage());
        }
    }

    // --- 2. UPDATED OPEN PDF (Now switches screen) ---
    @FXML
    void openPDF(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open PDF File");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
        );
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            this.storedPdfFile = selectedFile; 
            System.out.println("File selected: " + this.storedPdfFile.getAbsolutePath());
            
            // CALL THE HELPER to switch scenes
            goToSecondaryScreen(this.storedPdfFile, (Node)event.getSource());
        } else {
            System.out.println("File selection cancelled.");
        }
    }

    // --- 3. UPDATED DRAG DROPPED (Now passes data correctly) ---
    @FXML
    void handleDragDropped(DragEvent event) {
        List<File> files = event.getDragboard().getFiles();
        if (files != null && !files.isEmpty()) {
            File droppedFile = files.get(0);
            if (droppedFile.getName().toLowerCase().endsWith(".pdf")) {
                this.storedPdfFile = droppedFile; // Store it
                
                // CALL THE HELPER to switch scenes
                // We use 'statusLabel' (your button) as the source node to find the Stage
                goToSecondaryScreen(droppedFile, statusLabel); 
            } else {
                showErrorDialog();
            }
        }
        event.setDropCompleted(true);
        event.consume();
    }

    // --- 4. DRAG OVER (Visual Feedback) ---
    @FXML
    void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
            statusLabel.setText("Drop PDF file here!");
            statusLabel.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;"); 
        }
        event.consume();
    }
    
    // --- 5. DRAG EXITED (Reset Text) ---
    // You need to link this in Scene Builder -> Code -> On Drag Exited
    @FXML
    void handleDragExited(DragEvent event) {
        statusLabel.setText("ATTACH PDF HERE!"); // Reset to original text
        statusLabel.setStyle(""); // Reset color
        event.consume();
    }
    // encapsulated error dialog method
    private void showErrorDialog() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Incorrect File");
        alert.setHeaderText("Invalid File Type");
        alert.setContentText("Please drop a valid PDF file.");
        alert.showAndWait();
    }
    
    // Unused method for now, but kept for future use
    private void readAndGraph(File pdfFile) {
        System.out.println("Reading PDF: " + pdfFile.getAbsolutePath());
    }
}