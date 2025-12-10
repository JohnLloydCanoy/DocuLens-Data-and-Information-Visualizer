# DocuLens: A PDF Data Visualization System
## Technical Documentation and Object-Oriented Programming Analysis

**Date:** December 10, 2025  
**Version:** 2.0  
**Technology Stack:** Java 11, JavaFX 19, Apache PDFBox 2.0.29

---

## Table of Contents
1. [Executive Summary](#executive-summary)
2. [System Overview](#system-overview)
3. [Architecture and Design](#architecture-and-design)
4. [Object-Oriented Programming Principles](#object-oriented-programming-principles)
5. [File Handling Implementation](#file-handling-implementation)
6. [Component Analysis](#component-analysis)
7. [Workflow and Data Flow](#workflow-and-data-flow)
8. [Technical Features](#technical-features)
9. [Conclusion](#conclusion)

---

## 1. Executive Summary

DocuLens is a comprehensive PDF data visualization application built using Java and JavaFX. The system demonstrates robust implementation of Object-Oriented Programming (OOP) principles while providing intuitive document analysis and visualization capabilities. This paper examines the architectural design, OOP implementation, and technical features that make DocuLens an exemplary educational and practical application.

### Key Achievements:
- **Multi-screen JavaFX application** with seamless navigation
- **PDF processing** with text extraction and thumbnail generation
- **Automated data extraction** using regex pattern matching
- **Dynamic visualization** with multiple chart types (Pie, Line, Bar)
- **Export functionality** for saving visualizations as PNG images
- **Strong OOP design** with clear separation of concerns

---

## 2. System Overview

### 2.1 Purpose and Functionality

DocuLens transforms static PDF documents into interactive visual insights. The application extracts textual data from PDF files, analyzes content patterns, and generates appropriate visualizations based on the detected data types.

### 2.2 Core Capabilities

1. **Document Upload**: Drag-and-drop or file browser interface
2. **PDF Preview**: Thumbnail generation for document verification
3. **Automated Analysis**: Intelligent detection of data patterns
4. **Visualization**: Dynamic chart generation (Pie, Line, Bar charts)
5. **Export**: Save visualizations as PNG images

### 2.3 User Workflow

```
[Upload PDF] → [Preview & Confirm] → [Analyze & Visualize] → [Export Results]
```

---

## 3. Architecture and Design

### 3.1 System Architecture

DocuLens follows the **Model-View-Controller (MVC)** architectural pattern, enhanced with service-layer components for business logic separation.

```
┌─────────────────────────────────────────────────────────────┐
│                    PRESENTATION LAYER                        │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐      │
│  │   Primary    │  │  Secondary   │  │Visualization │      │
│  │  Controller  │→ │  Controller  │→ │  Controller  │      │
│  │  (Upload)    │  │  (Preview)   │  │  (Charts)    │      │
│  └──────────────┘  └──────────────┘  └──────────────┘      │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                     BUSINESS LOGIC LAYER                     │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐      │
│  │    Data      │  │    Chart     │  │     Text     │      │
│  │  Extractor   │  │  Generator   │  │   Analyzer   │      │
│  └──────────────┘  └──────────────┘  └──────────────┘      │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                      DATA ACCESS LAYER                       │
│           Apache PDFBox, File I/O, JavaFX Charts            │
└─────────────────────────────────────────────────────────────┘
```

### 3.2 Class Diagram

```
                    ┌──────────────┐
                    │     App      │
                    │  (extends    │
                    │ Application) │
                    └──────┬───────┘
                           │ launches
              ┌────────────┼────────────┐
              │            │            │
     ┌────────▼────────┐   │   ┌───────▼────────┐
     │   Primary       │   │   │  Secondary     │
     │  Controller     │───┼──→│  Controller    │
     └────────┬────────┘   │   └───────┬────────┘
              │            │           │
              └────────────┼───────────┘
                           │
                  ┌────────▼───────────┐
                  │  Visualization     │
                  │   Controller       │
                  └─────────┬──────────┘
                            │ uses
              ┌─────────────┼─────────────┐
              │             │             │
      ┌───────▼───────┐ ┌──▼──────┐ ┌────▼────────┐
      │     Data      │ │  Chart  │ │    Text     │
      │  Extractor    │ │Generator│ │  Analyzer   │
      └───────────────┘ └─────────┘ └─────────────┘
```

### 3.3 Module Structure

```
com.doculens/
├── App.java                          // Application entry point
├── PrimaryController.java            // File upload screen
├── SecondaryController.java          // PDF preview screen
├── VisualizationController.java      // Chart display screen
├── DataExtractor.java                // Pattern detection service
├── ChartGenerator.java               // Chart creation service
└── TextAnalyzer.java                 // Text processing service
```

---

## 4. Object-Oriented Programming Principles

### 4.1 Encapsulation

**Definition**: Bundling data and methods that operate on that data within a single unit, while restricting direct access to internal state.

#### Implementation Examples:

**Example 1: PrimaryController.java**
```java
public class PrimaryController {
    // Private field - data hidden from external access
    private File storedPdfFile;
    
    @FXML
    private Button statusLabel;
    
    // Public method provides controlled access
    private void goToSecondaryScreen(File pdfFile, Node sourceNode) {
        // Encapsulated logic for screen transition
    }
}
```

**Benefits Demonstrated:**
- Private fields prevent unauthorized access
- Public methods provide controlled interfaces
- Internal state changes are managed safely

**Example 2: SecondaryController.java**
```java
public class SecondaryController {
    private File currentPdfFile;  // Encapsulated state
    
    // Controlled access through setter
    public void setPdfFile(File file) {
        this.currentPdfFile = file;
        if (fileNameLabel != null && file != null) {
            fileNameLabel.setText(file.getName());
            generateThumbnail(file);  // Internal method call
        }
    }
    
    // Private helper - implementation detail hidden
    private void generateThumbnail(File file) {
        // Complex PDFBox operations hidden from caller
    }
}
```

**Example 3: DataExtractor.java**
```java
public class DataExtractor {
    // Private constants - implementation details
    private static final Pattern PERCENTAGE_PATTERN = 
        Pattern.compile("(\\d+)%\\s+of\\s+([\\w\\s]+)");
    
    // Public interface for data extraction
    public Map<String, Integer> extractPieData(String text) {
        // Internal pattern matching logic
    }
}
```

**Key Encapsulation Features:**
1. **Data Hiding**: 7+ private fields across controllers
2. **Access Control**: Public methods with private helpers
3. **State Management**: Controlled mutation through setters
4. **Implementation Hiding**: Complex operations abstracted

---

### 4.2 Abstraction

**Definition**: Hiding complex implementation details and showing only essential features of an object.

#### Implementation Examples:

**Example 1: App.java - Scene Management**
```java
public class App extends Application {
    // High-level interface hides complexity
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    // Abstract FXML loading details
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
            App.class.getResource(fxml + ".fxml")
        );
        return fxmlLoader.load();
    }
}
```

**Abstraction Benefits:**
- Controllers call `App.setRoot("secondary")` without knowing FXMLLoader details
- Scene transition logic centralized
- Changes to loading mechanism don't affect callers

**Example 2: ChartGenerator.java - Chart Creation Abstraction**
```java
public class ChartGenerator {
    // Abstract chart creation - caller doesn't need JavaFX knowledge
    public Node createPieChart(Map<String, Integer> data) {
        PieChart chart = new PieChart();
        data.forEach((key, value) -> 
            chart.getData().add(new PieChart.Data(key, value))
        );
        chart.setTitle("Demographic Breakdown");
        return chart;
    }
    
    public Node createBarChart(Map<String, Integer> data) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        // Complex JavaFX setup hidden from caller
        return chart;
    }
}
```

**Abstraction Advantages:**
- **Simple Interface**: Pass data, get chart
- **Technology Hiding**: JavaFX implementation details hidden
- **Flexibility**: Can change chart library without affecting callers

**Example 3: TextAnalyzer.java - Text Processing Abstraction**
```java
public class TextAnalyzer {
    private static final Set<String> STOP_WORDS = Set.of(
        "the", "and", "is", "of", "to", "a", "in", // ... more words
    );
    
    // High-level interface
    public Map<String, Integer> getWordFrequency(String text) {
        // Complex operations abstracted:
        // 1. Text cleaning
        // 2. Stop word filtering
        // 3. Frequency counting
        // 4. Sorting and limiting
        return processedData;
    }
}
```

**Abstraction Layers:**
1. **UI Layer**: Controllers abstract user interactions
2. **Business Logic**: Services abstract data processing
3. **Data Access**: PDFBox operations abstracted by methods

---

### 4.3 Inheritance

**Definition**: Mechanism where a new class derives properties and behaviors from an existing class.

#### Implementation Examples:

**Example 1: Application Framework Inheritance**
```java
public class App extends Application {
    // Inherits from JavaFX Application class
    
    @Override
    public void start(Stage stage) throws IOException {
        // Override abstract method from parent
        scene = new Scene(loadFXML("primary"), 740, 580);
        stage.setTitle("Doculens");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();  // Inherited method from Application
    }
}
```

**Inherited Capabilities:**
- Lifecycle management (init, start, stop)
- Platform integration
- Thread management
- Resource handling

**Example 2: Controller Inheritance (Implicit)**
```java
// All controllers implicitly inherit JavaFX controller behaviors
public class PrimaryController {
    @FXML  // Annotation handling inherited from framework
    private Button statusLabel;
    
    @FXML  // Framework-provided event handling
    void openPDF(ActionEvent event) {
        // Event system inherited from JavaFX
    }
}
```

**Inheritance Benefits:**
1. **Code Reuse**: Don't reimplement application lifecycle
2. **Framework Integration**: Automatic JavaFX features
3. **Standardization**: Consistent behavior across components

**Inheritance Hierarchy:**
```
java.lang.Object
    ↓
javafx.application.Application
    ↓
com.doculens.App
```

---

### 4.4 Polymorphism

**Definition**: The ability of objects to take multiple forms, allowing methods to process objects differently based on their type.

#### Implementation Examples:

**Example 1: Interface Polymorphism (Chart Types)**
```java
public class VisualizationController {
    public void startAnalysis(File pdfFile) {
        // Polymorphic reference - can hold any chart type
        Node chart = null;
        
        DataExtractor.DataType type = extractor.determineBestChartType(text);
        
        switch (type) {
            case PIE_DATA:
                chart = factory.createPieChart(pieData);  // Returns PieChart
                break;
            case LINE_DATA:
                chart = factory.createLineChart(lineData);  // Returns LineChart
                break;
            default:
                chart = factory.createBarChart(wordData);  // Returns BarChart
        }
        
        // Polymorphic behavior - all treated as Node
        if (chart != null) {
            chartContainer.getChildren().add(chart);
        }
    }
}
```

**Polymorphic Behavior:**
- Single variable (`Node chart`) holds different chart types
- All charts implement Node interface
- UI treats all charts uniformly

**Example 2: Collection Framework Polymorphism**
```java
public class DataExtractor {
    // Interface reference, HashMap implementation
    public Map<String, Integer> extractPieData(String text) {
        Map<String, Integer> data = new HashMap<>();
        // ... processing
        return data;
    }
    
    // Interface reference, TreeMap implementation (sorted)
    public Map<String, Integer> extractLineData(String text) {
        Map<String, Integer> data = new TreeMap<>();
        // ... processing
        return data;
    }
}
```

**Polymorphism Advantages:**
1. **Flexibility**: Easy to add new chart types
2. **Unified Interface**: All charts treated consistently
3. **Runtime Decisions**: Chart type determined dynamically

**Polymorphic Relationships:**
```
Node (Interface)
    ↓
├── PieChart
├── LineChart
└── BarChart

Map (Interface)
    ↓
├── HashMap
└── TreeMap
```

---

### 4.5 Single Responsibility Principle (SRP)

**Definition**: Each class should have only one reason to change, focusing on a single, well-defined responsibility.

#### Implementation Analysis:

| Class | Single Responsibility | Lines of Code | Key Methods |
|-------|----------------------|---------------|-------------|
| **App** | Application lifecycle & scene management | ~90 | `start()`, `setRoot()`, `loadFXML()` |
| **PrimaryController** | File upload user interface | ~132 | `openPDF()`, `handleDragDropped()` |
| **SecondaryController** | PDF preview & confirmation | ~104 | `setPdfFile()`, `generateThumbnail()` |
| **VisualizationController** | Chart display & export | ~93 | `startAnalysis()`, `handleDownload()` |
| **DataExtractor** | Pattern detection & data extraction | ~50 | `determineBestChartType()`, `extractPieData()` |
| **ChartGenerator** | JavaFX chart creation | ~42 | `createPieChart()`, `createLineChart()` |
| **TextAnalyzer** | Text processing & frequency analysis | ~65 | `getWordFrequency()` |

**SRP Benefits:**
1. **Maintainability**: Changes to charts don't affect file upload
2. **Testability**: Each component tested independently
3. **Reusability**: ChartGenerator usable in other projects
4. **Clarity**: Each class has clear, focused purpose

**Example: Clear Responsibility Separation**
```java
// ❌ VIOLATION (Everything in one class):
class MonolithicController {
    void uploadFile() { }
    void renderPDF() { }
    void extractText() { }
    void analyzeData() { }
    void createChart() { }
    void exportImage() { }
}

// ✅ PROPER SEPARATION (DocuLens approach):
class PrimaryController {
    void uploadFile() { }  // ONLY file upload
}

class DataExtractor {
    void extractData() { }  // ONLY data extraction
}

class ChartGenerator {
    void createChart() { }  // ONLY chart creation
}
```

---

## 5. File Handling Implementation

### 5.1 Input File Operations

#### 5.1.1 PDF File Selection (FileChooser)

**Location:** `PrimaryController.java` (Lines 55-71)

```java
@FXML
void openPDF(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open PDF File");
    
    // File type filtering
    fileChooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
    );
    
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    File selectedFile = fileChooser.showOpenDialog(stage);
    
    if (selectedFile != null) {
        this.storedPdfFile = selectedFile;
        System.out.println("File selected: " + selectedFile.getAbsolutePath());
        goToSecondaryScreen(selectedFile, (Node)event.getSource());
    }
}
```

**Key Features:**
- Native OS file dialog integration
- Extension filtering (`.pdf` only)
- Null-safety checking
- Absolute path access

---

#### 5.1.2 Drag-and-Drop File Handling

**Location:** `PrimaryController.java` (Lines 75-95)

```java
@FXML
void handleDragDropped(DragEvent event) {
    List<File> files = event.getDragboard().getFiles();
    
    if (files != null && !files.isEmpty()) {
        File droppedFile = files.get(0);
        
        // File validation
        if (droppedFile.getName().toLowerCase().endsWith(".pdf")) {
            this.storedPdfFile = droppedFile;
            goToSecondaryScreen(droppedFile, statusLabel);
        } else {
            showErrorDialog();  // User feedback
        }
    }
    event.setDropCompleted(true);
    event.consume();
}
```

**Implementation Details:**
- System clipboard integration
- File type validation (`.pdf` extension)
- Multiple file handling (takes first)
- Event consumption for proper UI behavior

---

#### 5.1.3 PDF Document Loading

**Location:** `SecondaryController.java` (Lines 42-60)

```java
private void generateThumbnail(File file) {
    try {
        // Auto-closing resource (try-with-resources)
        try (PDDocument document = PDDocument.load(file)) {
            PDFRenderer renderer = new PDFRenderer(document);
            
            // Render first page at 72 DPI
            BufferedImage bufferedImage = 
                renderer.renderImageWithDPI(0, 72, ImageType.RGB);
            
            // Convert AWT image to JavaFX image
            Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);
            
            if (pdfThumbnail != null) {
                pdfThumbnail.setImage(fxImage);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Could not generate thumbnail.");
    }
}
```

**File Operations:**
1. **Document Loading**: `PDDocument.load(file)`
2. **Resource Management**: Try-with-resources ensures closure
3. **Binary Reading**: PDFBox handles binary PDF format
4. **Error Handling**: IOException catching
5. **Image Conversion**: AWT → JavaFX bridge

---

#### 5.1.4 PDF Text Extraction

**Location:** `VisualizationController.java` (Lines 27-35)

```java
public void startAnalysis(File pdfFile) {
    chartContainer.getChildren().clear();
    
    try (PDDocument document = PDDocument.load(pdfFile)) {
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        
        // Process extracted text
        DataExtractor.DataType type = extractor.determineBestChartType(text);
        // ... chart generation
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

**Text Extraction Process:**
1. Load PDF document
2. Initialize text stripper
3. Extract all text content
4. Process for analysis
5. Clean up resources automatically

---

### 5.2 Output File Operations

#### 5.2.1 Image Export (PNG)

**Location:** `VisualizationController.java` (Lines 64-82)

```java
@FXML
private void handleDownload() {
    // 1. Capture current visualization
    WritableImage image = chartContainer.snapshot(
        new SnapshotParameters(), 
        null
    );
    
    // 2. File save dialog
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save Visualization");
    fileChooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("PNG Image", "*.png")
    );
    File file = fileChooser.showSaveDialog(
        chartContainer.getScene().getWindow()
    );
    
    // 3. Write to disk
    if (file != null) {
        try {
            ImageIO.write(
                SwingFXUtils.fromFXImage(image, null), 
                "png", 
                file
            );
            System.out.println("Saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save image: " + e.getMessage());
        }
    }
}
```

**File Writing Steps:**
1. **Screen Capture**: Snapshot JavaFX node tree
2. **Format Conversion**: JavaFX Image → BufferedImage
3. **File Dialog**: User selects save location
4. **Binary Write**: ImageIO writes PNG format
5. **Error Handling**: IOException management

---

#### 5.2.2 Resource File Loading

**Location:** `App.java` (Line 32)

```java
Image icon = new Image(
    getClass().getResourceAsStream("/assets/docuLensLogo.png")
);
stage.getIcons().add(icon);
```

**Resource Management:**
- Classpath resource access
- Stream-based reading
- Embedded resource loading
- Cross-platform path handling

---

### 5.3 File Handling Best Practices Demonstrated

| Practice | Implementation | Location |
|----------|---------------|----------|
| **Try-with-resources** | Auto-closing PDDocument | SecondaryController:45, VisualizationController:29 |
| **Exception Handling** | IOException catching | All file operations |
| **Null Checking** | File existence validation | PrimaryController:66, VisualizationController:73 |
| **Path Handling** | Absolute path usage | PrimaryController:68 |
| **Extension Filtering** | FileChooser filters | PrimaryController:59, VisualizationController:71 |
| **User Feedback** | Error dialogs | PrimaryController:125 |

---

## 6. Component Analysis

### 6.1 App.java - Application Core

**Responsibility:** Application lifecycle management and scene orchestration

**Key Methods:**

```java
@Override
public void start(Stage stage) throws IOException {
    scene = new Scene(loadFXML("primary"), 740, 580);
    Image icon = new Image(getClass().getResourceAsStream("/assets/docuLensLogo.png"));
    stage.getIcons().add(icon);
    stage.setTitle("Doculens");
    stage.setScene(scene);
    stage.show();
}

static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
}
```

**Design Patterns:**
- **Singleton Pattern**: Static `scene` instance
- **Factory Pattern**: `loadFXML()` creates views
- **Template Method**: Overrides `start()` from Application

---

### 6.2 PrimaryController.java - Upload Interface

**Responsibility:** File upload through drag-and-drop or file browser

**State Management:**
```java
private File storedPdfFile;  // Encapsulated state
```

**Key Features:**
1. Dual upload methods (browse + drag-drop)
2. File validation
3. Visual feedback during drag operations
4. Seamless transition to preview screen

**Event Handling:**
- `openPDF()`: File browser upload
- `handleDragOver()`: Drag visual feedback
- `handleDragDropped()`: Drop processing
- `handleDragExited()`: UI reset

---

### 6.3 SecondaryController.java - Preview Screen

**Responsibility:** PDF verification and visualization initiation

**Core Functionality:**

```java
public void setPdfFile(File file) {
    this.currentPdfFile = file;
    fileNameLabel.setText(file.getName());
    generateThumbnail(file);
}

@FXML
private void handleVisualize() {
    FXMLLoader loader = new FXMLLoader(
        getClass().getResource("VisualizationController.fxml")
    );
    Parent root = loader.load();
    VisualizationController visController = loader.getController();
    visController.startAnalysis(currentPdfFile);
    // Switch scene
}
```

**Design Highlights:**
- **Data Transfer**: File passed between controllers
- **Preview Generation**: Real-time PDF rendering
- **User Confirmation**: "Visualize" button workflow

---

### 6.4 VisualizationController.java - Analysis Engine

**Responsibility:** Data analysis, chart generation, and export

**Architecture:**

```java
private final DataExtractor extractor = new DataExtractor();
private final ChartGenerator factory = new ChartGenerator();
private final TextAnalyzer textAnalyzer = new TextAnalyzer();
```

**Analysis Pipeline:**

```
PDF File → Text Extraction → Pattern Detection → Chart Generation → Display
```

**Implementation:**

```java
public void startAnalysis(File pdfFile) {
    try (PDDocument document = PDDocument.load(pdfFile)) {
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        
        // Intelligent chart type selection
        DataExtractor.DataType type = extractor.determineBestChartType(text);
        Node chart = null;
        
        switch (type) {
            case PIE_DATA:
                chart = factory.createPieChart(extractor.extractPieData(text));
                break;
            case LINE_DATA:
                chart = factory.createLineChart(extractor.extractLineData(text));
                break;
            default:
                chart = factory.createBarChart(textAnalyzer.getWordFrequency(text));
        }
        
        chartContainer.getChildren().add(chart);
    }
}
```

---

### 6.5 DataExtractor.java - Pattern Recognition Service

**Responsibility:** Identify and extract structured data from text

**Regex Patterns:**

```java
private static final Pattern PERCENTAGE_PATTERN = 
    Pattern.compile("(\\d+)%\\s+of\\s+([\\w\\s]+)");

private static final Pattern YEAR_VALUE_PATTERN = 
    Pattern.compile("(?i)in\\s+(19|20)\\d{2}.*?(\\d+)");
```

**Intelligent Detection:**

```java
public DataType determineBestChartType(String text) {
    if (PERCENTAGE_PATTERN.matcher(text).find()) 
        return DataType.PIE_DATA;
    if (YEAR_VALUE_PATTERN.matcher(text).find()) 
        return DataType.LINE_DATA;
    return DataType.WORD_COUNT;
}
```

**Enum Design:**
```java
public enum DataType {
    PIE_DATA,      // Percentage data
    LINE_DATA,     // Time-series data
    WORD_COUNT,    // Frequency data
    NONE
}
```

---

### 6.6 ChartGenerator.java - Visualization Factory

**Responsibility:** Create JavaFX chart components

**Factory Methods:**

```java
public Node createPieChart(Map<String, Integer> data) {
    PieChart chart = new PieChart();
    data.forEach((key, value) -> 
        chart.getData().add(new PieChart.Data(key, value))
    );
    chart.setTitle("Demographic Breakdown");
    return chart;
}

public Node createLineChart(Map<String, Integer> data) {
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
    
    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("Trend Over Time");
    data.forEach((key, value) -> 
        series.getData().add(new XYChart.Data<>(key, value))
    );
    
    chart.getData().add(series);
    return chart;
}
```

**Benefits:**
- Centralized chart creation
- Consistent styling
- Easy to add new chart types
- Returns polymorphic Node interface

---

### 6.7 TextAnalyzer.java - NLP Service

**Responsibility:** Text processing and frequency analysis

**Stop Word Filtering:**

```java
private static final Set<String> STOP_WORDS = Set.of(
    "the", "and", "is", "of", "to", "a", "in", "that", "it", "for"
    // ... 38 total stop words
);
```

**Frequency Analysis Algorithm:**

```java
public Map<String, Integer> getWordFrequency(String text) {
    Map<String, Integer> frequencyMap = new HashMap<>();
    
    // 1. Clean text
    String cleanText = text.replaceAll("[^a-zA-Z\\s]", "").toLowerCase();
    
    // 2. Split into words
    String[] words = cleanText.split("\\s+");
    
    // 3. Count with filtering
    for (String word : words) {
        if (!word.isEmpty() && 
            !STOP_WORDS.contains(word) && 
            word.length() > 2) {
            frequencyMap.put(word, 
                frequencyMap.getOrDefault(word, 0) + 1);
        }
    }
    
    // 4. Sort and limit to top 10
    return frequencyMap.entrySet().stream()
        .sorted((k1, k2) -> k2.getValue().compareTo(k1.getValue()))
        .limit(10)
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (e1, e2) -> e1,
            LinkedHashMap::new
        ));
}
```

**Processing Steps:**
1. **Text Cleaning**: Remove punctuation and numbers
2. **Normalization**: Convert to lowercase
3. **Tokenization**: Split into words
4. **Filtering**: Remove stop words and short words
5. **Counting**: Build frequency map
6. **Sorting**: Descending by frequency
7. **Limiting**: Top 10 results

---

## 7. Workflow and Data Flow

### 7.1 Complete User Journey

```
┌─────────────────────────────────────────────────────────────────┐
│                      STEP 1: FILE UPLOAD                        │
│                                                                  │
│  User Action: Drag PDF or Click Browse                          │
│  System: PrimaryController.openPDF() or .handleDragDropped()    │
│  Result: File stored in memory                                  │
└────────────────────────────┬────────────────────────────────────┘
                             │
                             ↓
┌─────────────────────────────────────────────────────────────────┐
│                      STEP 2: PREVIEW                             │
│                                                                  │
│  User Action: Automatic transition                              │
│  System: SecondaryController.setPdfFile()                       │
│  Process: generateThumbnail() → PDF rendered at 72 DPI          │
│  Display: Filename + thumbnail image                            │
└────────────────────────────┬────────────────────────────────────┘
                             │
                             ↓
┌─────────────────────────────────────────────────────────────────┐
│                   STEP 3: VISUALIZATION                          │
│                                                                  │
│  User Action: Click "Visualize" button                          │
│  System: VisualizationController.startAnalysis()                │
│  Process:                                                        │
│    1. PDDocument.load(file) → Open PDF                          │
│    2. PDFTextStripper.getText() → Extract text                  │
│    3. DataExtractor.determineBestChartType() → Detect pattern   │
│    4. ChartGenerator.createChart() → Build visualization        │
│    5. chartContainer.add(chart) → Display                       │
└────────────────────────────┬────────────────────────────────────┘
                             │
                             ↓
┌─────────────────────────────────────────────────────────────────┐
│                      STEP 4: EXPORT                              │
│                                                                  │
│  User Action: Click "Download" button                           │
│  System: VisualizationController.handleDownload()               │
│  Process:                                                        │
│    1. chartContainer.snapshot() → Capture screen                │
│    2. FileChooser.showSaveDialog() → User selects location      │
│    3. ImageIO.write() → Save as PNG                             │
│  Result: PNG file saved to disk                                 │
└─────────────────────────────────────────────────────────────────┘
```

### 7.2 Data Flow Diagram

```
┌──────────┐
│   User   │
└────┬─────┘
     │ Upload PDF
     ↓
┌─────────────────┐
│ PrimaryControl  │ ─────→ File Reference
└────┬────────────┘
     │ Pass File
     ↓
┌──────────────────┐
│ SecondaryControl │ ─────→ Thumbnail Image
└────┬─────────────┘        (PDFRenderer)
     │ Pass File
     ↓
┌───────────────────────┐
│ VisualizationControl  │
└──────┬────────────────┘
       │
       ├─────→ PDFTextStripper ─────→ Raw Text
       │
       ├─────→ DataExtractor ─────────→ Structured Data
       │       (Regex Patterns)
       │
       ├─────→ TextAnalyzer ──────────→ Word Frequencies
       │       (Stop Word Filter)
       │
       └─────→ ChartGenerator ────────→ JavaFX Chart
               (Factory Methods)
                     │
                     ↓
               ┌──────────┐
               │ Display  │
               └──────────┘
                     │
                     ↓
               ┌──────────┐
               │  Export  │ ─────→ PNG File
               └──────────┘
```

### 7.3 Controller Communication Pattern

```java
// Pattern: Manual Controller Injection

// Step 1: Load FXML and get controller reference
FXMLLoader loader = new FXMLLoader(
    getClass().getResource("secondary.fxml")
);
Parent root = loader.load();
SecondaryController controller = loader.getController();

// Step 2: Pass data to new controller
controller.setPdfFile(pdfFile);

// Step 3: Switch scene
Stage stage = (Stage) sourceNode.getScene().getWindow();
stage.getScene().setRoot(root);
```

**Benefits:**
- Type-safe data passing
- No global state required
- Clear data ownership
- Testable components

---

## 8. Technical Features

### 8.1 JavaFX Integration

**FXML Files:**
- `primary.fxml` - Upload interface
- `secondary.fxml` - Preview screen
- `VisualizationController.fxml` - Chart display
- `NavBar.fxml` - Reusable navigation component

**Scene Builder Support:**
- Visual FXML editing
- Task configuration for quick launching
- fx:id binding to controller fields

### 8.2 Apache PDFBox Integration

**Operations:**
1. **Document Loading**: Binary PDF parsing
2. **Text Extraction**: Full document text retrieval
3. **Rendering**: Page-to-image conversion (72 DPI)
4. **Resource Management**: Auto-closing documents

**Module Configuration:**
```java
module com.doculens {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires org.apache.pdfbox;  // PDF operations
    requires java.desktop;       // AWT/Swing bridge
}
```

### 8.3 Regular Expression Patterns

**Percentage Detection:**
```regex
(\d+)%\s+of\s+([\w\s]+)
```
*Matches: "25% of users", "80% of revenue"*

**Year-Value Detection:**
```regex
(?i)in\s+(19|20)\d{2}.*?(\d+)
```
*Matches: "In 2020 sales were 500", "in 1998 the value was 250"*

### 8.4 Chart Visualizations

| Chart Type | Use Case | Data Source |
|------------|----------|-------------|
| **Pie Chart** | Percentage breakdowns | Regex-extracted percentages |
| **Line Chart** | Time-series trends | Year-value pairs |
| **Bar Chart** | Frequency analysis | Word frequency counts |

### 8.5 Error Handling Strategy

**Layers of Safety:**
1. **File Validation**: Extension checking before processing
2. **Null Checks**: Defensive programming throughout
3. **Exception Handling**: Try-catch for I/O operations
4. **User Feedback**: Alert dialogs for errors
5. **Resource Management**: Try-with-resources for cleanup

---

## 9. Conclusion

### 9.1 OOP Implementation Summary

DocuLens demonstrates **strong practical implementation of OOP principles**:

| Principle | Score | Key Evidence |
|-----------|-------|--------------|
| **Encapsulation** | ⭐⭐⭐⭐⭐ | 7+ private fields, controlled access methods |
| **Abstraction** | ⭐⭐⭐⭐ | Multiple abstraction layers (UI, business logic, data) |
| **Inheritance** | ⭐⭐⭐ | JavaFX Application framework extension |
| **Polymorphism** | ⭐⭐ | Interface polymorphism (Node, Map) |
| **SRP** | ⭐⭐⭐⭐⭐ | 7 dedicated classes with clear responsibilities |

**Overall OOP Score: 18/25 (72%) - Solid B+**

### 9.2 System Strengths

1. **Clear Architecture**: Separation of concerns across layers
2. **Maintainability**: Single-responsibility classes
3. **Extensibility**: Easy to add new chart types or data extractors
4. **User Experience**: Intuitive drag-and-drop interface
5. **File Handling**: Comprehensive input/output operations
6. **Error Resilience**: Defensive programming throughout

### 9.3 Educational Value

DocuLens serves as an excellent educational example demonstrating:

- **MVC Pattern**: Clear separation of model, view, and controller
- **Service Layer**: Business logic decoupled from UI
- **Factory Pattern**: ChartGenerator creates objects
- **Dependency Injection**: Manual controller data passing
- **Resource Management**: Proper file handling with try-with-resources
- **Event-Driven Programming**: JavaFX event handling
- **Regular Expressions**: Pattern matching for data extraction
- **Collections Framework**: Maps, Sets, Streams

### 9.4 Real-World Applications

The techniques demonstrated in DocuLens apply to:

- Document management systems
- Business intelligence dashboards
- Data visualization tools
- Report generation applications
- PDF processing workflows
- Educational software development

### 9.5 Future Enhancement Opportunities

While the current system is robust, potential improvements include:

1. **Advanced Polymorphism**: Abstract base classes for controllers
2. **Dependency Injection Framework**: Replace manual injection
3. **Model Layer**: Dedicated data classes (PDFDocument, AnalysisResult)
4. **Interface Abstractions**: Define contracts (Visualizer, DataParser)
5. **Unit Testing**: JUnit test coverage
6. **Configuration Management**: External properties files
7. **Logging Framework**: Replace System.out with SLF4J
8. **Additional Chart Types**: Scatter plots, area charts
9. **Export Formats**: PDF, SVG, JSON
10. **Multi-page Analysis**: Process all PDF pages

### 9.6 Final Assessment

DocuLens successfully demonstrates that **Object-Oriented Programming principles are not just theoretical concepts**, but practical tools that lead to:

- **Better organized code**
- **Easier maintenance**
- **Improved scalability**
- **Enhanced team collaboration**
- **Professional software architecture**

The system achieves its primary goal: transforming static PDF documents into interactive visual insights while serving as a comprehensive example of OOP best practices in Java.

---

## References

- Oracle JavaFX Documentation: https://openjfx.io/
- Apache PDFBox Documentation: https://pdfbox.apache.org/
- Java Platform Module System (JPMS): https://openjdk.org/jeps/261
- Oracle Java SE Documentation: https://docs.oracle.com/en/java/
- Design Patterns: Elements of Reusable Object-Oriented Software (Gang of Four)
- Clean Code: A Handbook of Agile Software Craftsmanship (Robert C. Martin)

---

**Document Version:** 1.0  
**Last Updated:** December 10, 2025  
**Author:** DocuLens Development Team  
**License:** Educational Use

---

*This documentation was generated as part of the DocuLens project to demonstrate comprehensive understanding of Object-Oriented Programming principles and professional software development practices.*
