package org.word.count.test.statisticsgenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.word.count.test.models.FileWordStatistics;
import org.word.count.test.statisticsgeneration.WordCountStatisticsGenerator;
import org.word.count.test.statisticsgeneration.WordCounter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class WordCounterFunctionalTest {

    private WordCounter wordCounter;

    private static final String FILE_NAME = "world.txt";

    @TempDir
    Path directory;

    private static final String AMPERSAND = "&";
    private static final FileWordStatistics EXPECTED_AMPERSAND_STATS = new FileWordStatistics(FILE_NAME,
            1d,
            1L,
            Map.of(1,1L),
            Set.of(1)
            );

    private static final String DECIMAL = "11.11";
    private static final FileWordStatistics EXPECTED_DECIMAL_STATS = new FileWordStatistics(FILE_NAME,
            5d,
            1L,
            Map.of(5,1L),
            Set.of(5)
    );
    private static final String COMMA_DECIMAL = "11,11";
    private static final FileWordStatistics EXPECTED_COMMA_DECIMAL_STATS = new FileWordStatistics(FILE_NAME,
            5d,
            1L,
            Map.of(5,1L),
            Set.of(5)
    );
    private static final String SHORT_DATE = "11/11/11";
    private static final FileWordStatistics EXPECTED_SHORT_DATE_STATS = new FileWordStatistics(FILE_NAME,
            8d,
            1L,
            Map.of(8,1L),
            Set.of(8)
    );
    private static final String LONG_DATE = "11/11/1111";
    private static final FileWordStatistics EXPECTED_LONG_DATE_STATS = new FileWordStatistics(FILE_NAME,
            10d,
            1L,
            Map.of(10, 1L),
            Set.of(10)
    );
    private static final String DECIMAL_UNITS_P = "11.11mps";
    private static final FileWordStatistics EXPECTED_DECIMAL_UNITS_P_DECIMAL_STATS = new FileWordStatistics(FILE_NAME,
            8d,
            1L,
            Map.of(8, 1L),
            Set.of(8)
    );
    private static final String DECIMAL_UNITS_SLASH = "11.11m/s";
    private static final FileWordStatistics EXPECTED_DECIMAL_UNITS_SLASH_DECIMAL_STATS = new FileWordStatistics(FILE_NAME,
            8d,
            1L,
            Map.of(8,1L),
            Set.of(8)
    );
    private static final String HYPHENATED_CHARS = "hello-world";
    private static final FileWordStatistics EXPECTED_HYPHENATED_CHARS_STATS = new FileWordStatistics(FILE_NAME,
            11d,
            1L,
            Map.of(11,1L),
            Set.of(11)
    );
    private static final String NORMAL_CHARS = "hello";
    private static final FileWordStatistics EXPECTED_NORMAL_CHARS_STATS = new FileWordStatistics(FILE_NAME,
            5d,
            1L,
            Map.of(5,1L),
            Set.of(5)
    );

    private static final FileWordStatistics EXPECTED_NEGATIVE_STATS = new FileWordStatistics(FILE_NAME,
            0d,
            0L,
            Map.of(0,0L),
            Set.of(0)
    );

    private static final List<String> NEGATIVE_TESTS = List.of("hello-",
            "-",
            "1.11.11",
            "1.11.11npnmpmppnpnpnppnppn",
            "",
            "    ",
            "1212aasasad1231");



    @BeforeEach
    public void setup(){

        wordCounter = new WordCounter(new WordCountStatisticsGenerator());

    }

    @Test
    public void normalCharsTest() throws IOException {

        Path fileToUse = directory.resolve(FILE_NAME);
        Files.createFile(fileToUse);
        Files.write(fileToUse, NORMAL_CHARS.getBytes());

        FileWordStatistics fileWordStatistics = wordCounter.count(fileToUse.toAbsolutePath().toString());

        assertTrue(statisticsAreEqual(fileWordStatistics, EXPECTED_NORMAL_CHARS_STATS));
    }

    @Test
    public void hyphenatedCharsTest() throws IOException {

        Path fileToUse = directory.resolve(FILE_NAME);
        Files.createFile(fileToUse);
        Files.write(fileToUse, HYPHENATED_CHARS.getBytes());

        FileWordStatistics fileWordStatistics = wordCounter.count(fileToUse.toAbsolutePath().toString());

        assertTrue(statisticsAreEqual(fileWordStatistics, EXPECTED_HYPHENATED_CHARS_STATS));
    }

    @Test
    public void decimalsUnitsSlashTest() throws IOException {

        Path fileToUse = directory.resolve(FILE_NAME);
        Files.createFile(fileToUse);
        Files.write(fileToUse, DECIMAL_UNITS_SLASH.getBytes());

        FileWordStatistics fileWordStatistics = wordCounter.count(fileToUse.toAbsolutePath().toString());

        assertTrue(statisticsAreEqual(fileWordStatistics, EXPECTED_DECIMAL_UNITS_SLASH_DECIMAL_STATS));
    }

    @Test
    public void decimalsUnitsPTest() throws IOException {

        Path fileToUse = directory.resolve(FILE_NAME);
        Files.createFile(fileToUse);
        Files.write(fileToUse, DECIMAL_UNITS_P.getBytes());

        FileWordStatistics fileWordStatistics = wordCounter.count(fileToUse.toAbsolutePath().toString());

        assertTrue(statisticsAreEqual(fileWordStatistics, EXPECTED_DECIMAL_UNITS_P_DECIMAL_STATS));
    }

    @Test
    public void longDateTest() throws IOException {

        Path fileToUse = directory.resolve(FILE_NAME);
        Files.createFile(fileToUse);
        Files.write(fileToUse, LONG_DATE.getBytes());

        FileWordStatistics fileWordStatistics = wordCounter.count(fileToUse.toAbsolutePath().toString());

        assertTrue(statisticsAreEqual(fileWordStatistics, EXPECTED_LONG_DATE_STATS));
    }

    @Test
    public void shortDateTest() throws IOException {

        Path fileToUse = directory.resolve(FILE_NAME);
        Files.createFile(fileToUse);
        Files.write(fileToUse, SHORT_DATE.getBytes());

        FileWordStatistics fileWordStatistics = wordCounter.count(fileToUse.toAbsolutePath().toString());

        assertTrue(statisticsAreEqual(fileWordStatistics, EXPECTED_SHORT_DATE_STATS));
    }

    @Test
    public void europeanDecimalTest() throws IOException {

        Path fileToUse = directory.resolve(FILE_NAME);
        Files.createFile(fileToUse);
        Files.write(fileToUse, COMMA_DECIMAL.getBytes());

        FileWordStatistics fileWordStatistics = wordCounter.count(fileToUse.toAbsolutePath().toString());

        assertTrue(statisticsAreEqual(fileWordStatistics, EXPECTED_COMMA_DECIMAL_STATS));
    }

    @Test
    public void decimalTest() throws IOException {

        Path fileToUse = directory.resolve(FILE_NAME);
        Files.createFile(fileToUse);
        Files.write(fileToUse, DECIMAL.getBytes());

        FileWordStatistics fileWordStatistics = wordCounter.count(fileToUse.toAbsolutePath().toString());

        assertTrue(statisticsAreEqual(fileWordStatistics, EXPECTED_DECIMAL_STATS));
    }

    @Test
    public void negativeTests() throws IOException {

        Path fileToUse = directory.resolve(FILE_NAME);
        for (String negativeTest: NEGATIVE_TESTS){
            Files.createFile(fileToUse);
            Files.write(fileToUse, negativeTest.getBytes());

            FileWordStatistics fileWordStatistics = wordCounter.count(fileToUse.toAbsolutePath().toString());

            assertTrue(statisticsAreEqual(fileWordStatistics, EXPECTED_NEGATIVE_STATS),
                    "Asserting that "+negativeTest+" comes out with empty statistics");

            Files.delete(fileToUse);
        }


    }

    @Test
    public void ampersandTest() throws IOException {

        Path fileToUse = directory.resolve(FILE_NAME);
        Files.createFile(fileToUse);
        Files.write(fileToUse, AMPERSAND.getBytes());

        FileWordStatistics fileWordStatistics = wordCounter.count(fileToUse.toAbsolutePath().toString());

        assertTrue(statisticsAreEqual(fileWordStatistics, EXPECTED_AMPERSAND_STATS));
    }

    private boolean statisticsAreEqual(FileWordStatistics fileWordStatistics, FileWordStatistics fileWordStatisticsOther){
        return (fileWordStatistics.getMostFrequent().equals(fileWordStatisticsOther.getMostFrequent()) &&
                fileWordStatistics.getWordCount().equals(fileWordStatisticsOther.getWordCount()) &&
                fileWordStatistics.getLengthDistribution().equals(fileWordStatisticsOther.getLengthDistribution()) &&
                fileWordStatistics.getAverageLength().equals(fileWordStatisticsOther.getAverageLength()));
    }

}
