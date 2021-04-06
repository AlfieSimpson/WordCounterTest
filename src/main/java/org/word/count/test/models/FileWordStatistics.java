package org.word.count.test.models;

import java.util.HashSet;
import java.util.Map;

public class FileWordStatistics {

    private String id;
    private Double averagelength;
    private Long wordCount;
    private Map<Integer, Long> lengthDistribution;
    private HashSet<Integer> mostFrequent;

    public FileWordStatistics(String id,
                              Double averagelength,
                              Long wordCount,
                              Map<Integer, Long> lengthDistribution,
                              HashSet<Integer> mostFrequent) {
        this.id = id;
        this.averagelength = averagelength;
        this.wordCount = wordCount;
        this.lengthDistribution = lengthDistribution;
        this.mostFrequent = mostFrequent;
    }


    public Double getAveragelength() {
        return averagelength;
    }

    public void setAveragelength(Double averagelength) {
        this.averagelength = averagelength;
    }

    public Long getWordCount() {
        return wordCount;
    }

    public void setWordCount(Long wordCount) {
        this.wordCount = wordCount;
    }

    public Map<Integer, Long> getLengthDistribution() {
        return lengthDistribution;
    }

    public void setLengthDistribution(Map<Integer, Long> lengthDistribution) {
        this.lengthDistribution = lengthDistribution;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashSet<Integer> getMostFrequent() {
        return mostFrequent;
    }

    public void setMostFrequent(HashSet<Integer> mostFrequent) {
        this.mostFrequent = mostFrequent;
    }
}

