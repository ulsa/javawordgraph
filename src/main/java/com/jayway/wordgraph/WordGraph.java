package com.jayway.wordgraph;


import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newLinkedList;
import static java.util.Collections.max;
import static java.util.Collections.singletonMap;
import static java.util.Collections.sort;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

public class WordGraph {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: WordGraph <filename>");
            System.exit(1);
        }
        // @BEGIN_VERSION_ONLY GATHER_WORDS_WHITESPACE
        System.out.println(gatherWords(FileUtils.readFileToString(new File(args[0]))));
        // @END_VERSION_ONLY GATHER_WORDS_WHITESPACE
        // @BEGIN_VERSION_ONLY GATHER_WORDS_PUNCTUATION
        System.out.println(gatherWords(FileUtils.readFileToString(new File(args[0]))));
        // @END_VERSION_ONLY GATHER_WORDS_PUNCTUATION
        // @BEGIN_VERSION_ONLY GATHER_WORDS_LOWERCASE
        System.out.println(gatherWords(FileUtils.readFileToString(new File(args[0]))));
        // @END_VERSION_ONLY GATHER_WORDS_LOWERCASE
        // @BEGIN_VERSION_ONLY COUNT_WORDS
        System.out.println(countWords(gatherWords(FileUtils.readFileToString(new File(args[0])))));
        // @END_VERSION_ONLY COUNT_WORDS
        // @BEGIN_VERSION_ONLY SORT_COUNTED_WORDS
        System.out.println(sortCountedWords(countWords(gatherWords(FileUtils.readFileToString(new File(args[0]))))));
        // @END_VERSION_ONLY SORT_COUNTED_WORDS
        // @BEGIN_VERSION_ONLY REPEAT_STR
        System.out.println(sortCountedWords(countWords(gatherWords(FileUtils.readFileToString(new File(args[0]))))));
        // @END_VERSION_ONLY REPEAT_STR
        // @BEGIN_VERSION_ONLY HISTOGRAM_ENTRY
        System.out.println(sortCountedWords(countWords(gatherWords(FileUtils.readFileToString(new File(args[0]))))));
        // @END_VERSION_ONLY HISTOGRAM_ENTRY
        // @BEGIN_VERSION HISTOGRAM
        System.out.println(histogram(sortCountedWords(countWords(gatherWords(FileUtils.readFileToString(new File(
                args[0])))))));
        // @END_VERSION HISTOGRAM
    }
}
