package com.doculens;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SecondaryController {

    @FXML
    private Label fileNameLabel;

    // --- FIX 1: Added @FXML annotation here ---
    @FXML 
    private ImageView pdfThumbnail; 

    private File currentPdfFile;

    public void setPdfFile(File file) {
        this.currentPdfFile = file;
        
        if (fileNameLabel != null && file != null) {
            fileNameLabel.setText(file.getName());
            
            // --- FIX 2: Actually call the thumbnail generator! ---
            generateThumbnail(file);
        }
    }

    private void generateThumbnail(File file) {
        try {
            try (PDDocument document = PDDocument.load(file)) {
                PDFRenderer renderer = new PDFRenderer(document);
                
                // Render page 0 at 72 DPI
                BufferedImage bufferedImage = renderer.renderImageWithDPI(0, 72, ImageType.RGB);
                Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);
                
                // Set the image
                if (pdfThumbnail != null) {
                    pdfThumbnail.setImage(fxImage);
                } else {
                    System.out.println("Error: pdfThumbnail is null. Check fx:id in Scene Builder!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not generate thumbnail.");
        }
    }

    @FXML
    private void handleVisualize() {
        if (currentPdfFile != null) {
            try {
                System.out.println("Switching to Visualization Dashboard...");

                // 1. Load the Visualization FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("VisualizationController.fxml"));
                Parent root = loader.load();

                // 2. Get the Controller for the new screen
                VisualizationController visController = loader.getController();

                // 3. PASS THE DATA (The Critical Step)
                // This triggers the analysis inside the next screen
                visController.startAnalysis(currentPdfFile);

                // 4. Switch the Scene
                // We use 'fileNameLabel' to find the current window (Stage)
                Stage stage = (Stage) fileNameLabel.getScene().getWindow();
                stage.getScene().setRoot(root);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error: Could not load visualization.fxml. Check if the file exists!");
            }
        } else {
            System.out.println("Error: No PDF file is currently selected.");
        }
    }

    @FXML
    private void handleNoCancel() {
        try {
            App.setRoot("primary");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}