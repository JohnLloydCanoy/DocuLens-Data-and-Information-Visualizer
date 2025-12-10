package com.doculens;
// The Collections Framework
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

// The Regex Engine
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DataExtractor {
    // Method to extract data points from text using regex patterns or Regular Expressions
    private static final Pattern PERCENTAGE_PATTERN = Pattern.compile("(\\d+)%\\s+of\\s+([\\w\\s]+)");
    // Case insensitive match for years like "in 2020 the value was 500"
    private static final Pattern YEAR_VALUE_PATTERN = Pattern.compile("(?i)in\\s+(19|20)\\d{2}.*?(\\d+)");

    public enum DataType {
        PIE_DATA, LINE_DATA, WORD_COUNT, NONE
    }

    // functions decide which chart to generate based on extracted data
    public DataType determineBestChartType(String text){
        if (PERCENTAGE_PATTERN.matcher(text).find()) return DataType.PIE_DATA;
        if (YEAR_VALUE_PATTERN.matcher(text).find()) return DataType.LINE_DATA;
        return DataType.WORD_COUNT;
    }
    // Extracting the daya for that type
    public Map<String, Integer> extractPieData(String text) {
        Map<String, Integer> data = new HashMap<>();
        Matcher matcher = PERCENTAGE_PATTERN.matcher(text);
        while (matcher.find()) {
            data.put(matcher.group(2).trim(), Integer.parseInt(matcher.group(1)));
        }
        return data;
    }

    public Map<String, Integer> extractLineData(String text) {
        Map<String, Integer> data = new TreeMap<>();
        Matcher matcher = YEAR_VALUE_PATTERN.matcher(text);
        while (matcher.find()) {
            // Simplified logic for demo
            String match = matcher.group(0);
            String year = match.replaceAll("[^0-9]", "").substring(0, 4); 
            String val = match.replaceAll("[^0-9]", "").substring(4);
            data.put(year, Integer.parseInt(val));
        }
        return data;
    }
}
