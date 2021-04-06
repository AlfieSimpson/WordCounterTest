package org.word.count.test.statisticsgenerator;

import org.word.count.test.models.FileWordStatistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.word.count.test.statisticsgeneration.WordCountStatisticsGenerator;
import org.word.count.test.statisticsgeneration.WordCounter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WordCounterUnitTest {

    private WordCounter wordCounter;
    @Mock
    private WordCountStatisticsGenerator wordCountStatisticsGenerator;

    private static final String EXAMPLE_FILENAME= "example.txt";

    private static final List<String> EXAMPLE_CONTENTS = List.of("Hello", "world", "&", "good", "morning", "The", "date", "is", "18/05/2016");

    private static final long EXPECTED_COUNT = 9;
    private static final Double EXPECTED_AVERAGE=4.556;
    private static final Map<Integer, Long> EXPECTED_FREQUENCIES = new HashMap<>() {{
        put(1,1l);
        put(2,1l);
        put(3,1l);
        put(4,2l);
        put(5,2l);
        put(7,1l);
        put(10,1l);
    }};
    private static final List<Integer> EXPECTED_MOST_FREQUENT = new ArrayList<>(){{
        add(4);
        add(5);
    }};
    private static final HashSet<Integer> EXPECTED_MOST_FREQUENT_IN_MODEL = new HashSet<>(){{
        add(4);
        add(5);
    }};

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        wordCounter = new WordCounter(wordCountStatisticsGenerator);
    }

    @TempDir
    Path directory;

    @Test
    public void count() throws IOException {

        when(wordCountStatisticsGenerator.wordCount(any())).thenReturn(EXPECTED_COUNT);
        when(wordCountStatisticsGenerator.averageLength(any())).thenReturn(EXPECTED_AVERAGE);
        when(wordCountStatisticsGenerator.lengthWithHighestFrequecy(any(), any())).thenReturn(EXPECTED_MOST_FREQUENT);
        when(wordCountStatisticsGenerator.highestFrequency(any())).thenReturn(2l);
        when(wordCountStatisticsGenerator.wordLengthFrequency(any())).thenReturn(EXPECTED_FREQUENCIES);

        Path fileToUse = directory.resolve(EXAMPLE_FILENAME);
        Files.createFile(fileToUse);
        Files.write(fileToUse, EXAMPLE_CONTENTS);

        FileWordStatistics fileWordStatistics = wordCounter.count(fileToUse.toString());

        assertEquals(EXPECTED_COUNT, (long) fileWordStatistics.getWordCount());
        assertEquals(EXPECTED_AVERAGE.toString(), String.format("%.3f",fileWordStatistics.getAveragelength()));
        assertEquals(EXPECTED_FREQUENCIES, fileWordStatistics.getLengthDistribution());
        assertEquals(EXPECTED_MOST_FREQUENT_IN_MODEL, fileWordStatistics.getMostFrequent());
    }
}