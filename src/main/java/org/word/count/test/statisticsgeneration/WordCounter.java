package org.word.count.test.statisticsgeneration;

import org.word.count.test.models.FileWordStatistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounter {

    private final WordCountStatisticsGenerator WORDCOUNT_STATISTICS_GENERATOR;

    private static final String ALPHABET_POTENTIAL_HYPHEN_REGEX = "[a-zA-Z]+([-][a-zA-Z]+)*";
    private static final String DATE_FORMATTED_NUMBER_REGEX = "[0-9]{2}\\/[0-9]{2}\\/[0-9]{2}([0-9]{2})?";
    private static final String NUMBER_INCLUDING_DECIMAL_AND_UNITS_REGEX = "[0-9]+([.,][0-9]+)([a-zA-Z]{0,4}([p\\/][a-zA-Z]{0,4})?)?";
    private static final String AMPERSAND_REGEX = "&";

    public WordCounter(WordCountStatisticsGenerator wordCountStatisticsGenerator){
        this.WORDCOUNT_STATISTICS_GENERATOR = wordCountStatisticsGenerator;
    }

    public FileWordStatistics count(String filePath){

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {

            List<String> wordsList = stream.parallel()
                    //Remove Grouped Punctuation
                    .map(line -> line.split(" "))
                    .map(words -> Arrays.stream(words).collect(Collectors.toList()))
                    .flatMap(Collection::stream)
                    //Only raw words at this point - so filter to only words
                    .filter(word -> word.matches(ALPHABET_POTENTIAL_HYPHEN_REGEX+"|"
                            +DATE_FORMATTED_NUMBER_REGEX+"|"
                            +NUMBER_INCLUDING_DECIMAL_AND_UNITS_REGEX+"|"
                            +AMPERSAND_REGEX))
                    .collect(Collectors.toList());

            if (wordsList.size() == 0) return new FileWordStatistics(filePath,
                    0d,
                    0L,
                    Map.of(0,0L),
                    Set.of(0));

            List<Integer> wordLengths = WORDCOUNT_STATISTICS_GENERATOR.wordLengthMapper(wordsList);
            Double averageLength = WORDCOUNT_STATISTICS_GENERATOR.averageLength(wordLengths);
            Long wordCount = WORDCOUNT_STATISTICS_GENERATOR.wordCount(wordLengths);
            Map<Integer, Long> countMap = WORDCOUNT_STATISTICS_GENERATOR.wordLengthFrequency(wordLengths);

            long highestFrequency = WORDCOUNT_STATISTICS_GENERATOR.highestFrequency(countMap);
            List<Integer> lengthWithHighestFrequency = WORDCOUNT_STATISTICS_GENERATOR.lengthWithHighestFrequency(countMap, highestFrequency);

            return new FileWordStatistics(filePath, averageLength, wordCount, countMap, new HashSet<>(lengthWithHighestFrequency));

        } catch (IOException e) {
            System.out.println("There seems to be an issue counting the words in file: "+filePath);
            e.printStackTrace();
            return null;
        }
    }

}
