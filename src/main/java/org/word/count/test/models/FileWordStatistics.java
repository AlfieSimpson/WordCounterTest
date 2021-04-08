package org.word.count.test.models;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.Map;
import java.util.Set;

public class FileWordStatistics {

    private String id;
    private Double averageLength;
    private Long wordCount;
    private Map<Integer, Long> lengthDistribution;
    private Set<Integer> mostFrequent;

    public FileWordStatistics(String id,
                              Double averageLength,
                              Long wordCount,
                              Map<Integer, Long> lengthDistribution,
                              Set<Integer> mostFrequent) {
        this.id = id;
        this.averageLength = averageLength;
        this.wordCount = wordCount;
        this.lengthDistribution = lengthDistribution;
        this.mostFrequent = mostFrequent;
    }


    public Double getAverageLength() {
        return averageLength;
    }

    public void setAverageLength(Double averageLength) {
        this.averageLength = averageLength;
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

    public Set<Integer> getMostFrequent() {
        return mostFrequent;
    }

    public void setMostFrequent(Set<Integer> mostFrequent) {
        this.mostFrequent = mostFrequent;
    }

    @Override
    public boolean equals(Object o){
        return EqualsBuilder.reflectionEquals(this,o);
    }

}

