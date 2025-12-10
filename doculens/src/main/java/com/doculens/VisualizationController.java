package com.doculens;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class VisualizationController {
    @FXML private VBox chartContainer; // The container holding all charts
    private File currentPdfFile;
    
    // Use our helper classes
    private final DataExtractor extractor = new DataExtractor();
    private final ChartGenerator factory = new ChartGenerator();
    private final TextAnalyzer textAnalyzer = new TextAnalyzer(); // Your old word counter


    public void startAnalysis(File pdfFile) {
        this.currentPdfFile = pdfFile;
        chartContainer.getChildren().clear(); // Wipe the slate clean

        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String fullText = stripper.getText(document);

            // SAFETY CHECK: Handle Scanned PDFs (Images)
            if (fullText.trim().isEmpty()) {
                javafx.scene.control.Label errorLabel = new javafx.scene.control.Label("⚠️ No text detected. This PDF might be a scanned image.");
                errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
                chartContainer.getChildren().add(errorLabel);
                return;
            }

            // --- 1. CHECK FOR PIE CHART DATA (Percentages) ---
            Map<String, Integer> pieData = extractor.extractPieData(fullText);
            if (!pieData.isEmpty()) {
                Node pieChart = factory.createPieChart(pieData);
                // Add a Separator/Title for clarity
                chartContainer.getChildren().add(createSectionTitle("Demographic Analysis"));
                chartContainer.getChildren().add(pieChart);
            }

            // --- 2. CHECK FOR LINE CHART DATA (Trends) ---
            Map<String, Integer> lineData = extractor.extractLineData(fullText);
            if (!lineData.isEmpty()) {
                Node lineChart = factory.createLineChart(lineData);
                chartContainer.getChildren().add(createSectionTitle("Timeline Trends"));
                chartContainer.getChildren().add(lineChart);
            }

            // --- 3. ALWAYS ADD WORD FREQUENCY (Bar Chart) ---
            // This is our fallback so the user always sees *something*
            Map<String, Integer> wordData = textAnalyzer.getWordFrequency(fullText);
            if (!wordData.isEmpty()) {
                Node barChart = factory.createBarChart(wordData);
                chartContainer.getChildren().add(createSectionTitle("Keyword Frequency"));
                chartContainer.getChildren().add(barChart);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Helper method to make nice titles above each chart
    private javafx.scene.control.Label createSectionTitle(String title) {
        javafx.scene.control.Label label = new javafx.scene.control.Label(title);
        label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #34495e; -fx-padding: 10 0 5 0;");
        return label;
    }
    // --- THE DOWNLOAD FEATURE ---
    @FXML
    private void handleDownload() {
        // 1. Take a "Snapshot" of the chart container
        WritableImage image = chartContainer.snapshot(new SnapshotParameters(), null);
        // 2. Open Save Dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Visualization");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Image", "*.png"));
        File file = fileChooser.showSaveDialog(chartContainer.getScene().getWindow());
        if (file != null) {
            try {
                // 3. Save the snapshot to the hard drive
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                System.out.println("Saved to: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Failed to save image: " + e.getMessage());
            }
        }
    }
    // --- THE  CSV Export Feature ---

    @FXML
    private void handleExportCSV(){
        // ASK users where to save the file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV File", "*.csv"));
        File file = fileChooser.showSaveDialog(chartContainer.getScene().getWindow());
        if (file != null) {
            try (java.io.PrintWriter writer = new java.io.PrintWriter(file)) {
                StringBuilder sb = new StringBuilder();
                
                // 2. Re-run extraction to get the data (or store it in a class variable earlier)
                // For simplicity, we re-run extraction here:
                try (PDDocument document = PDDocument.load(currentPdfFile)) { // Make sure you have 'currentPdfFile' stored!
                    PDFTextStripper stripper = new PDFTextStripper();
                    String fullText = stripper.getText(document);

                    // A. Check Pie Data
                    Map<String, Integer> pieData = extractor.extractPieData(fullText);
                    if (!pieData.isEmpty()) {
                        sb.append("--- Demographic Data ---\n");
                        sb.append("Category,Count\n");
                        for (Map.Entry<String, Integer> entry : pieData.entrySet()) {
                            sb.append(entry.getKey()).append(",").append(entry.getValue()).append("\n");
                        }
                        sb.append("\n");
                    }

                    // B. Check Line Data
                    Map<String, Integer> lineData = extractor.extractLineData(fullText);
                    if (!lineData.isEmpty()) {
                        sb.append("--- Trend Data ---\n");
                        sb.append("Year,Value\n");
                        for (Map.Entry<String, Integer> entry : lineData.entrySet()) {
                            sb.append(entry.getKey()).append(",").append(entry.getValue()).append("\n");
                        }
                        sb.append("\n");
                    }

                    // C. Word Frequency
                    Map<String, Integer> wordData = textAnalyzer.getWordFrequency(fullText);
                    if (!wordData.isEmpty()) {
                        sb.append("--- Keyword Frequency ---\n");
                        sb.append("Word,Count\n");
                        for (Map.Entry<String, Integer> entry : wordData.entrySet()) {
                            sb.append(entry.getKey()).append(",").append(entry.getValue()).append("\n");
                        }
                    }
                }

                // 3. Write to file
                writer.write(sb.toString());
                System.out.println("CSV Export successful: " + file.getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to export CSV.");
            }
        }
    }
    // --- THE BACK BUTTON ---
    
    @FXML
    private void handleBack() throws IOException {
        App.setRoot("primary");
    }
}

