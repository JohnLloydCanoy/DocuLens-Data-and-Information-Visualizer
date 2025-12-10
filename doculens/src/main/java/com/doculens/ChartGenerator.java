package com.doculens;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.chart.*;
import java.util.Map;

public class ChartGenerator {
    // Funtion to create a Pie Chart from data
    public Node createPieChart(Map<String, Integer> data) {
        // 1. Calculate the TOTAL sum first (so we can do the math)
        double total = 0;
        for (int value : data.values()) {
            total += value;
        }

        // 2. Create the Chart
        PieChart chart = new PieChart();
        chart.setTitle("Demographic Breakdown");

        // 3. Add Data with Percentage Labels
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            String category = entry.getKey();
            int count = entry.getValue();

            // Calculate percentage
            double percentage = (count / total) * 100;

            // Create a smart label: "Java (60%)"
            String label = String.format("%s (%.1f%%)", category, percentage);

            // Add to chart
            chart.getData().add(new PieChart.Data(label, count));
        }
        
        // Optional: Show labels on the chart itself, not just the legend
        chart.setLabelsVisible(true); 

        return chart;
    }

    public Node createLineChart(Map<String, Integer> data) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Trend Over Time");
        data.forEach((key, value) -> series.getData().add(new XYChart.Data<>(key, value)));
        
        chart.getData().add(series);
        chart.setTitle("Timeline Analysis");
        return chart;
    }

    public Node createBarChart(Map<String, Integer> data) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Frequency");
        data.forEach((key, value) -> series.getData().add(new XYChart.Data<>(key, value)));
        
        chart.getData().add(series);
        chart.setTitle("Word Frequency");
        return chart;
    }
}
