package com.doculens;

import java.util.*;
import java.util.stream.Collectors;

public class TextAnalyzer {

    // A list of "boring" words to ignore (Stop Words)
    private static final Set<String> STOP_WORDS = Set.of(
        "the", "and", "is", "of", "to", "a", "in", "that", "it", "for", 
        "on", "with", "as", "this", "was", "at", "by", "an", "be", "are", 
        "or", "from", "not", "but", "we", "can", "has", "have", "had", 
        "which", "their", "will", "its", "also", "into", "up", "out"
    );

    /**
     * Takes raw text and returns the Top 10 most frequent words.
     */
    public Map<String, Integer> getWordFrequency(String text) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        
        if (text == null || text.isEmpty()) {
            return frequencyMap;
        }

        // 1. Clean the text: Remove punctuation, numbers, and make lowercase
        // "[^a-zA-Z ]" means "Replace anything that is NOT a letter or space with empty string"
        String cleanText = text.replaceAll("[^a-zA-Z\\s]", "").toLowerCase();
        
        // 2. Split into words
        String[] words = cleanText.split("\\s+");

        // 3. Count meaningful words
        for (String word : words) {
            // Only count if it's not a stop word and has more than 2 letters
            if (!word.isEmpty() && !STOP_WORDS.contains(word) && word.length() > 2) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
        }

        // 4. Sort by frequency (High to Low) and take Top 10
        // This is a Java Stream chain to sort the map values
        return frequencyMap.entrySet().stream()
                .sorted((k1, k2) -> k2.getValue().compareTo(k1.getValue())) // Sort descending (Big to Small)
                .limit(10) // Only take the top 10 winners
                .collect(Collectors.toMap(
                        Map.Entry::getKey, 
                        Map.Entry::getValue, 
                        (e1, e2) -> e1, 
                        LinkedHashMap::new)); // LinkedHashMap keeps the sorted order
    }
    
    // Helper method to get basic text statistics
    public String getTextStats(String text) {
        if (text == null || text.isEmpty()) {
            return "No text found";
        }
        
        String[] words = text.split("\\s+");
        int wordCount = words.length;
        int charCount = text.length();
        
        return String.format("Words: %d | Characters: %d", wordCount, charCount);
    }
}