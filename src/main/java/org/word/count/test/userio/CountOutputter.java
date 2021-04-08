package org.word.count.test.userio;

import java.util.Map;
import java.util.Set;

import org.word.count.test.models.FileWordStatistics;

public class CountOutputter {

    private static final String FILENAME_OUTPUT_TEMPLATE = "For File: %s";
    private static final String AVERAGE_LENGTH_OUTPUT_TEMPLATE = "Average word length = %.3f";
    private static final String WORD_COUNT_OUTPUT_TEMPLATE = "Word count = %s";
    private static final String LENGTH_TO_FREQUENCY_OUTPUT_TEMPLATE = "Number of words of length %s";
    private static final String LENGTH_TO_FREQUENCY_INDIV_TEMPLATE = "%s is %s";
    private static final String MOST_FREQUENT_OUTPUT_TEMPLATE ="The most frequently occurring word length occurs %s times, for lengths %s";


    public void output(FileWordStatistics stats){


        formatAndPrint(FILENAME_OUTPUT_TEMPLATE, stats.getId());
        formatAndPrint(AVERAGE_LENGTH_OUTPUT_TEMPLATE, stats.getAverageLength());
        formatAndPrint(WORD_COUNT_OUTPUT_TEMPLATE, stats.getWordCount());
        for (Map.Entry<Integer, Long> lengthToCount : stats.getLengthDistribution().entrySet()) {
            String connectedKV = String.format(LENGTH_TO_FREQUENCY_INDIV_TEMPLATE, lengthToCount.getKey(), lengthToCount.getValue());
            formatAndPrint(LENGTH_TO_FREQUENCY_OUTPUT_TEMPLATE,connectedKV);
        }

        Set<Integer> mostFrequent = stats.getMostFrequent();
        StringBuilder mostFrequentStrBuilder = new StringBuilder();
        for (Integer equallyFrequentInt : mostFrequent) mostFrequentStrBuilder.append(equallyFrequentInt).append(" & ");
        String mostFrequentStr = mostFrequentStrBuilder.substring(0, mostFrequentStrBuilder.length()-3);
        String mostFrequentAmount = String.valueOf(stats.getLengthDistribution().get(mostFrequent.iterator().next()));
        formatAndPrint(MOST_FREQUENT_OUTPUT_TEMPLATE, mostFrequentAmount, mostFrequentStr);
    }

    private void formatAndPrint(String template, Object... information){
        String formattedOutput = String.format(template, information);
        System.out.println(formattedOutput);
    }
}
