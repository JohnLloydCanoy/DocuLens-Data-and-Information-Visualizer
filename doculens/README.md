# DocuLens 📊

A JavaFX-based PDF data visualization application that transforms static documents into interactive visual insights.

![Java](https://img.shields.io/badge/Java-11-orange)
![JavaFX](https://img.shields.io/badge/JavaFX-19-blue)
![License](https://img.shields.io/badge/License-MIT-green)

## 🎯 Features

- **PDF Upload**: Drag-and-drop or file browser interface
- **Intelligent Analysis**: Automatic data pattern detection
- **Dynamic Visualizations**: Pie charts, line charts, and bar charts
- **Export**: Save visualizations as PNG images
- **Thumbnail Preview**: Real-time PDF rendering

## 🚀 Technologies

- **Java 11** - Core programming language
- **JavaFX 19** - User interface framework
- **Apache PDFBox 2.0.29** - PDF processing
- **Maven** - Build and dependency management

## 📋 Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven 3.6+
- JavaFX SDK 19

## 🔧 Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/YOUR_USERNAME/doculens.git
   cd doculens
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn javafx:run
   ```

## 📖 Usage

1. **Upload PDF**: Drag and drop a PDF file or click "Browse" to select
2. **Preview**: View PDF thumbnail and filename
3. **Visualize**: Click "Visualize" to generate charts
4. **Export**: Download visualization as PNG image

## 🏗️ Architecture

DocuLens follows the **Model-View-Controller (MVC)** pattern with service layer components:

```
├── App.java                      # Application entry point
├── PrimaryController.java        # File upload controller
├── SecondaryController.java      # PDF preview controller
├── VisualizationController.java  # Chart display controller
├── DataExtractor.java            # Pattern detection service
├── ChartGenerator.java           # Chart creation service
└── TextAnalyzer.java             # Text processing service
```

## 📊 Supported Visualizations

| Chart Type | Trigger Pattern | Example |
|------------|----------------|---------|
| **Pie Chart** | Percentage data | "25% of users..." |
| **Line Chart** | Time-series data | "In 2020, sales were 500" |
| **Bar Chart** | Word frequency | Automatic fallback |

## 🎓 OOP Principles Demonstrated

- ✅ **Encapsulation**: Private fields with controlled access
- ✅ **Abstraction**: Layered architecture hiding complexity
- ✅ **Inheritance**: JavaFX Application framework
- ✅ **Polymorphism**: Interface-based chart handling
- ✅ **Single Responsibility**: Dedicated service classes

See [SYSTEM_DOCUMENTATION.md](SYSTEM_DOCUMENTATION.md) for detailed analysis.

## 📁 File Handling

- **Input**: PDF file selection (FileChooser, Drag & Drop)
- **Processing**: Text extraction and rendering
- **Output**: PNG image export

## 🛠️ Development

### Project Structure
```
doculens/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/doculens/
│   │   │       ├── App.java
│   │   │       ├── *Controller.java
│   │   │       └── *Service.java
│   │   └── resources/
│   │       ├── assets/
│   │       └── com/doculens/*.fxml
├── pom.xml
└── README.md
```

### Building from Source
```bash
# Clean and compile
mvn clean compile

# Run tests (if available)
mvn test

# Package application
mvn package

# Run application
mvn javafx:run
```

## 🐛 Troubleshooting

**Issue**: Module errors  
**Solution**: Ensure `module-info.java` includes all required modules:
```java
requires javafx.controls;
requires javafx.fxml;
requires javafx.swing;
requires org.apache.pdfbox;
requires java.desktop;
```

**Issue**: PDF not loading  
**Solution**: Verify PDF is not corrupted and has read permissions

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Author

Created as a demonstration of Object-Oriented Programming principles and JavaFX development.

## 🙏 Acknowledgments

- Apache PDFBox for PDF processing
- JavaFX for UI framework
- Scene Builder for FXML design

## 📚 Documentation

For comprehensive system documentation including OOP analysis and architecture details, see [SYSTEM_DOCUMENTATION.md](SYSTEM_DOCUMENTATION.md).

---

**Note**: This is an educational project demonstrating professional software development practices.
