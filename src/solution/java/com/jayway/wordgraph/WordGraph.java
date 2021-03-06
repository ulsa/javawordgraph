package com.jayway.wordgraph;


import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newLinkedList;
// @BEGIN_VERSION FOLD
import static com.jayway.wordgraph.Collections3.fold;
// @END_VERSION FOLD
import static java.util.Collections.max;
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
    // @BEGIN_VERSION GATHER_WORDS_LOWERCASE
    private static final Function<String, String> LOWER = new Function<String, String>() {
        public String apply(String from) {
            return from.toLowerCase();
        }
    };
    // @END_VERSION GATHER_WORDS_LOWERCASE

    // @BEGIN_VERSION GATHER_WORDS_WHITESPACE
    private static final Predicate<String> NO_EMPTY = new Predicate<String>() {
        public boolean apply(String input) {
            return input != null && input.length() > 0;
        }
    };
    // @END_VERSION GATHER_WORDS_WHITESPACE

    // @BEGIN_VERSION SORT_COUNTED_WORDS
    private static final Function<Entry<String, Integer>, WordCountPair> ENTRY_TO_PAIR = new Function<Entry<String, Integer>, WordCountPair>() {
        public WordCountPair apply(Entry<String, Integer> from) {
            return new WordCountPair(from.getKey(), from.getValue());
        }
    };

    private static final Comparator<WordCountPair> COMPARE_BY_COUNT = new Comparator<WordCountPair>() {
        public int compare(WordCountPair o1, WordCountPair o2) {
            return Integer.valueOf(o1.getCount()).compareTo(o2.getCount());
        }
    };
    // @END_VERSION SORT_COUNTED_WORDS

    // @BEGIN_VERSION HISTOGRAM
    private static final Comparator<WordCountPair> COMPARE_BY_WORD_LENGTH = new Comparator<WordCountPair>() {
        public int compare(WordCountPair o1, WordCountPair o2) {
            Integer lengthFirst = o1.getWord().length();
            Integer lengthSecond = o2.getWord().length();
            return lengthFirst.compareTo(lengthSecond);
        }
    };
    // @END_VERSION HISTOGRAM

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

    // @BEGIN_VERSION GATHER_WORDS_WHITESPACE
    public static Collection<String> gatherWords(String s) {
        String[] words;
        Collection<String> result;
        // @BEGIN_VERSION_ONLY GATHER_WORDS_WHITESPACE
        // split on whitespace
        words = Pattern.compile("\\s+").split(s);
        // filter non-empty elements
        result = filter(Arrays.asList(words), NO_EMPTY);
        // @END_VERSION_ONLY GATHER_WORDS_WHITESPACE
        // @BEGIN_VERSION_ONLY GATHER_WORDS_PUNCTUATION
        // split on non-word characters
        words = Pattern.compile("\\W+").split(s);
        // filter non-empty elements
        result = filter(Arrays.asList(words), NO_EMPTY);
        // @END_VERSION_ONLY GATHER_WORDS_PUNCTUATION
        // @BEGIN_VERSION GATHER_WORDS_LOWERCASE
        // split on non-word characters
        words = Pattern.compile("\\W+").split(s);
        // filter non-empty elements and transform to lowercase
        result = transform(filter(Arrays.asList(words), NO_EMPTY), LOWER);
        // @END_VERSION GATHER_WORDS_LOWERCASE
        return result;
    }
    // @END_VERSION GATHER_WORDS_WHITESPACE

    // @BEGIN_VERSION COUNT_WORDS
    public static Map<String, Integer> countWords(Collection<String> words) {
        Function2<Map<String, Integer>, String, Map<String, Integer>> count = new Function2<Map<String, Integer>, String, Map<String, Integer>>() {
            public Map<String, Integer> apply(Map<String, Integer> accum, String next) {
                Integer value = accum.get(next);
                if (value == null) {
                    value = 0;
                }
                accum.put(next, value + 1);
                return accum;
            }
        };
        return fold(words, new HashMap<String, Integer>(), count);
    }
    // @END_VERSION COUNT_WORDS

    // @BEGIN_VERSION SORT_COUNTED_WORDS
    public static Collection<WordCountPair> sortCountedWords(Map<String, Integer> words) {
        List<WordCountPair> maps = newLinkedList(transform(words.entrySet(), ENTRY_TO_PAIR));
        sort(maps, COMPARE_BY_COUNT);
        return maps;
    }
    // @END_VERSION SORT_COUNTED_WORDS

    // @BEGIN_VERSION REPEAT_STR
    public static String repeatStr(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }
    // @END_VERSION REPEAT_STR

    // @BEGIN_VERSION HISTOGRAM_ENTRY
    public static String histogramEntry(WordCountPair wordCount, int width) {
        String word = wordCount.getWord();
        Integer count = wordCount.getCount();
        int blanks = width - word.length();
        return String.format("%s%s %s", word, repeatStr(" ", blanks), repeatStr("#", count));
    }
    // @END_VERSION HISTOGRAM_ENTRY

    // @BEGIN_VERSION HISTOGRAM
    public static String histogram(Collection<WordCountPair> input) {
        int maxWidth = max(input, COMPARE_BY_WORD_LENGTH).getWord().length();
        StringBuilder sb = new StringBuilder();
        String newline = System.getProperty("line.separator");
        for (WordCountPair wordCount : input) {
            sb.append(histogramEntry(wordCount, maxWidth));
            sb.append(newline);
        }
        return sb.toString();
    }
    // @END_VERSION HISTOGRAM
}
