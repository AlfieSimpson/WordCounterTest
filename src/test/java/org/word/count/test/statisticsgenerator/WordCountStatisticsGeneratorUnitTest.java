package org.word.count.test.statisticsgenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.word.count.test.statisticsgeneration.WordCountStatisticsGenerator;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCountStatisticsGeneratorUnitTest {

    private WordCountStatisticsGenerator wordCountStatisticsGenerator;

    private static final List<String> EXAMPLE_CONTENTS = List.of("Hello", "world", "&", "good", "morning", "The", "date", "is", "18/05/2016");
    private static final List<Integer> EXPECTED_COUNT_MAPPING = List.of(5, 5, 1, 4, 7, 3, 4, 2, 10);

    private static final long EXPECTED_COUNT = 9;
    private static final Double EXPECTED_AVERAGE=4.555555555555555;
    private static final Map<Integer, Long> EXPECTED_FREQUENCIES = new HashMap<>() {{
        put(1,1L);
        put(2,1L);
        put(3,1L);
        put(4,2L);
        put(5,2L);
        put(7,1L);
        put(10,1L);
    }};
    private static final Long EXPECTED_MOST_FREQUENT_AMOUNT = 2L;
    private static final List<Integer> EXPECTED_MOST_FREQUENT = new ArrayList<>(){{
        add(4);
        add(5);
    }};

    @BeforeEach
    public void setup(){
        wordCountStatisticsGenerator = new WordCountStatisticsGenerator();
    }


    @Test
    public void wordLengths() {

        List<Integer> wordLengthsList = wordCountStatisticsGenerator.wordLengthMapper(EXAMPLE_CONTENTS);

        assertEquals(EXPECTED_COUNT_MAPPING, wordLengthsList);
    }

    @Test
    public void count() {

        long wordCount = wordCountStatisticsGenerator.wordCount(EXPECTED_COUNT_MAPPING);

        assertEquals(EXPECTED_COUNT, wordCount);
    }

    @Test
    public void average() {

        Double averageLength = wordCountStatisticsGenerator.averageLength(EXPECTED_COUNT_MAPPING);

        assertEquals(EXPECTED_AVERAGE, averageLength);
    }

    @Test
    public void frequencyMap() {

        Map<Integer, Long> frequencyMap = wordCountStatisticsGenerator.wordLengthFrequency(EXPECTED_COUNT_MAPPING);

        assertEquals(EXPECTED_FREQUENCIES, frequencyMap);
    }

    @Test
    public void mostFrequentAmount() {

        Long mostFrequentAmount = wordCountStatisticsGenerator.highestFrequency(EXPECTED_FREQUENCIES);

        assertEquals(EXPECTED_MOST_FREQUENT_AMOUNT, mostFrequentAmount);
    }

    @Test
    public void mostFrequentLengths() {

        List<Integer> mostFrequentAmount = wordCountStatisticsGenerator.lengthWithHighestFrequency(EXPECTED_FREQUENCIES, EXPECTED_MOST_FREQUENT_AMOUNT);

        assertEquals(EXPECTED_MOST_FREQUENT, mostFrequentAmount);
    }
}