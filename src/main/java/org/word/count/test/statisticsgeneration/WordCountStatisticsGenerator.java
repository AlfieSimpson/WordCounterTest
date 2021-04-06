package org.word.count.test.statisticsgeneration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordCountStatisticsGenerator {

    public List<Integer> wordLengthMapper(List<String> wordsList){

        List<Integer> wordLengths = wordsList.parallelStream()
                .map(word -> {
                    return word.length();
                })
                .mapToInt(string -> Integer.valueOf(string))
                .boxed()
                .filter(length -> length != 0)
                .collect(Collectors.toList());
        return wordLengths;
    }

    public Double averageLength(List<Integer> wordLengths){
        Double averageCharCount = wordLengths.stream().parallel()
                .mapToInt(integer -> integer)
                .average().getAsDouble();
        return averageCharCount;
    }

    public Long wordCount(List<Integer> wordLengths){
        return wordLengths.stream().parallel()
                .mapToInt(integer -> integer)
                .count();
    }

    public Map<Integer, Long> wordLengthFrequency(List<Integer> wordLengths){
        return wordLengths.stream().parallel()
                .mapToInt(integer -> integer)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Long highestFrequency(Map<Integer, Long> countMap){
        return countMap.entrySet()
                .stream().max(Map.Entry.comparingByValue()).get().getValue();

    }

    public List<Integer> lengthWithHighestFrequecy(Map<Integer, Long> countMap, Long highestFrequency){
        return countMap
                .keySet()
                .stream()
                .collect(Collectors.partitioningBy(e -> {
                            return countMap.get(e).equals(highestFrequency);
                        })
                )
                .get(true);
    }

}
