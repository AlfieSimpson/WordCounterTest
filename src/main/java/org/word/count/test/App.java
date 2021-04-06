package org.word.count.test;

import org.word.count.test.models.FileWordStatistics;
import org.word.count.test.statisticsgeneration.WordCountStatisticsGenerator;
import org.word.count.test.statisticsgeneration.WordCounter;
import org.word.count.test.userio.CountOutputter;

import java.util.ArrayList;
import java.util.List;

public class App {

    private static String filePath = "/home/alfie/bible.txt";

    public static void main (String [] args){

        List<String> filenames = argumentsList("--file", args);

        WordCountStatisticsGenerator wordCountStatisticsGenerator = new WordCountStatisticsGenerator();
        if (!filePath.equals("")) {
            FileWordStatistics statistics = new WordCounter(wordCountStatisticsGenerator).count(filePath);
            if (statistics != null) new CountOutputter().output(statistics);
        }
        for (String filename : filenames){

            System.out.println("Attempting to count for id: \""+filename+"\"");
            FileWordStatistics statistics = new WordCounter(wordCountStatisticsGenerator).count(filename);
            if (statistics != null) new CountOutputter().output(statistics);
        }
    }

    private static List<String> argumentsList(String argumentId, String [] args){

        List<String> matches = new ArrayList<>();

        boolean lookingForMatch = false;
        for (String arg : args){

            if (arg.equals(argumentId)) lookingForMatch = true;
            else if (lookingForMatch){

                matches.add(arg);
                lookingForMatch = false;
            }
        }
        return matches;
    }
}
