package org.word.count.test.statisticsgeneration;

import org.word.count.test.models.FileWordStatistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounter {

    private WordCountStatisticsGenerator wordCountStatisticsGenerator;

    public WordCounter(WordCountStatisticsGenerator wordCountStatisticsGenerator){
        this.wordCountStatisticsGenerator = wordCountStatisticsGenerator;
    }

    public FileWordStatistics count(String filePath){

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {

            List<String> wordsList = stream.parallel()
                    //Remove Grouped Punctuation
                    .map(line -> line.replaceAll("[^a-zA-Z\\d\\s\\/&\\-.]", ""))
                    //Reduce whitespace preceeding punctation to a " "
                    .map(line -> line.replaceAll("[^a-zA-Z\\d\\s&] +", " "))
                    //Reduce whitespace proceeding punctation to a " "
                    .map(line -> line.replaceAll(" +[^a-zA-Z\\d\\s&]", " "))
                    .map(line -> line.split(" "))
                    .map(words -> Arrays.stream(words).collect(Collectors.toList()))
                    .flatMap(list -> list.stream().map(integer -> integer))
                    .collect(Collectors.toList());

            List<Integer> wordLengths = wordCountStatisticsGenerator.wordLengthMapper(wordsList);

            Double averageLength = wordCountStatisticsGenerator.averageLength(wordLengths);
            Long wordCount = wordCountStatisticsGenerator.wordCount(wordLengths);
            Map<Integer, Long> countMap = wordCountStatisticsGenerator.wordLengthFrequency(wordLengths);

            long highestFrequency = wordCountStatisticsGenerator.highestFrequency(countMap);
            List<Integer> lengthWithHighestFrequency = wordCountStatisticsGenerator.lengthWithHighestFrequecy(countMap, highestFrequency);

            FileWordStatistics fileWordStatistics =
                    new FileWordStatistics(filePath, averageLength, wordCount, countMap, new HashSet<>(lengthWithHighestFrequency));

            return fileWordStatistics;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
