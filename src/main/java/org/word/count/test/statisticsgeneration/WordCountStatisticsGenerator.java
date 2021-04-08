package org.word.count.test.statisticsgeneration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordCountStatisticsGenerator {

    /**
     * Given a list of Strings, map those string onto a list of their lengths
     * @param wordsList     - a list of preferably
     * @return
     */
    public List<Integer> wordLengthMapper(List<String> wordsList){

        return wordsList.parallelStream()
                .map(String::length)
                .mapToInt(Integer::valueOf)
                .boxed()
                .filter(length -> length != 0)
                .collect(Collectors.toList());
    }

    /**
     * Given a list of integers, return the average value
     * @param wordLengths     - a list of wordLengths
     * @return                  - The average length
     */
    public Double averageLength(List<Integer> wordLengths){
        return wordLengths.stream().parallel()
                .mapToInt(integer -> integer)
                .average()
                .getAsDouble();
    }

    /**
     * Count the amount of Lengths given; needed as a large file could easily go above Integer.MAX_VALUE which is the
     * max returned from wordLengths.size()
     * @param wordLengths   - the given wordLength
     * @return              - the amount of lengths within the given list
     */
    public Long wordCount(List<Integer> wordLengths){
        return wordLengths.stream().parallel()
                .mapToInt(integer -> integer)
                .count();
    }

    /**
     * Makes a distribution mapping given a List of word lengths in the form Length:Amount Found
     * @param wordLengths   - A list of word lengths
     * @return              - Distribution of Lengths to Frequency
     */
    public Map<Integer, Long> wordLengthFrequency(List<Integer> wordLengths){
        return wordLengths.stream().parallel()
                .mapToInt(integer -> integer)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    /**
     * Finds the most frequent word length
     * @param countMap  - A word count distribution map
     * @return          - The most frequent length
     */
    public Long highestFrequency(Map<Integer, Long> countMap){
        return countMap.entrySet()
                .stream().max(Map.Entry.comparingByValue()).get().getValue();
    }

    /**
     * Given a distribution map and the highest value within that map, return all keys with the same value
     * @param countMap      - A Length : Count distribution map
     * @param highestFrequency  - The highest frequency to look for
     * @return                  - The Lengths with a matching frequency
     */
    public List<Integer> lengthWithHighestFrequency(Map<Integer, Long> countMap, Long highestFrequency){
        return countMap
                .keySet()
                .stream()
                .collect(Collectors.partitioningBy(e -> countMap.get(e).equals(highestFrequency))
                )
                .get(true);
    }
}
