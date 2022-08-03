package com.tlglearning.wordcount;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordCounter {

  public static final String[] BORING_WORDS = {"and", "of", "the", "in", "i", "on", "then", "than",
      "out", "a", "to", "that", "it", "he", "you", "was", "his", "is", "have", "had"};
  private final Map<String, Integer> counts = new HashMap<>();

  private int totalWords;

  public Set<String> words() {
    return counts.keySet();
  }

  public int get(String word) {
    return counts.getOrDefault(word, 0);
  }

  public Map<String, Integer> getCounts() {
    return Collections.unmodifiableMap(counts);
  }

  public void add(String text) {
    String trimmedLine = text.trim();
    if (!trimmedLine.isEmpty()) {
      String[] words = splitWords(trimmedLine);
      countWords(words);
    }
  }

  public int size() {
    return counts.size();
  }

  public int total() {
    return totalWords;
  }

  @Override
  public String toString() {
    return counts.toString();
  }

  String[] splitWords(String text) {
    return text
        .toLowerCase()
        .split("[\\W_]+");
  }

  void countWords(String[] words) {

    Arrays.stream(words)
        .map((s) -> s.trim())     //.map lets you take a value in the map and change it/replace it
        .filter((s) -> !s.isEmpty())
        .filter((s) -> s.length() > 14)
//        .filter(Predicate.not(s -> s.isEmpty()))
        .forEach((word) -> counts.put(word, 1 + counts.getOrDefault(word, 0)));
  }

}
