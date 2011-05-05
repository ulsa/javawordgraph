package com.jayway.wordgraph;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class WordGraphMain {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: WordGraph <filename>");
            System.exit(1);
        }
        // @BEGIN_VERSION_ONLY GATHER_WORDS_WHITESPACE
        System.out.println(WordGraph.gatherWords(FileUtils.readFileToString(new File(args[0]))));
        // @END_VERSION_ONLY GATHER_WORDS_WHITESPACE
        // @BEGIN_VERSION_ONLY GATHER_WORDS_PUNCTUATION
        System.out.println(WordGraph.gatherWords(FileUtils.readFileToString(new File(args[0]))));
        // @END_VERSION_ONLY GATHER_WORDS_PUNCTUATION
        // @BEGIN_VERSION_ONLY GATHER_WORDS_LOWERCASE
        System.out.println(WordGraph.gatherWords(FileUtils.readFileToString(new File(args[0]))));
        // @END_VERSION_ONLY GATHER_WORDS_LOWERCASE
        // @BEGIN_VERSION_ONLY COUNT_WORDS
        System.out.println(WordGraph.countWords(WordGraph.gatherWords(FileUtils.readFileToString(new File(args[0])))));
        // @END_VERSION_ONLY COUNT_WORDS
        // @BEGIN_VERSION_ONLY SORT_COUNTED_WORDS
        System.out.println(WordGraph.sortCountedWords(WordGraph.countWords(WordGraph.gatherWords(FileUtils.readFileToString(new File(args[0]))))));
        // @END_VERSION_ONLY SORT_COUNTED_WORDS
        // @BEGIN_VERSION_ONLY REPEAT_STR
        System.out.println(WordGraph.sortCountedWords(WordGraph.countWords(WordGraph.gatherWords(FileUtils.readFileToString(new File(args[0]))))));
        // @END_VERSION_ONLY REPEAT_STR
        // @BEGIN_VERSION_ONLY HISTOGRAM_ENTRY
        System.out.println(WordGraph.sortCountedWords(WordGraph.countWords(WordGraph.gatherWords(FileUtils.readFileToString(new File(args[0]))))));
        // @END_VERSION_ONLY HISTOGRAM_ENTRY
        // @BEGIN_VERSION HISTOGRAM
        System.out.println(WordGraph.histogram(WordGraph.sortCountedWords(WordGraph.countWords(WordGraph.gatherWords(FileUtils.readFileToString(new File(
                args[0])))))));
        // @END_VERSION HISTOGRAM
    }
}
