module com.doculens {
    requires javafx.controls;
    requires javafx.fxml;

    // --- ADD THESE 3 LINES ---
    requires javafx.swing;      // Unlocks SwingFXUtils
    requires org.apache.pdfbox; // Unlocks PDFBox
    requires java.desktop;      // Unlocks AWT Images (Buffered Image) used by PDFBox
    // -------------------------

    opens com.doculens to javafx.fxml;
    exports com.doculens;
}