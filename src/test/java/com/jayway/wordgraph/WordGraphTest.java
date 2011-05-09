package com.jayway.wordgraph;

import static com.jayway.wordgraph.Collections3Test.assertThatIterablesAreEqual;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class WordGraphTest {
    @Test
    public void dummyTest() {
    }

    // @BEGIN_VERSION GATHER_WORDS_WHITESPACE
    // (describe gather-words
    //   (it "splits words on whitespace"
    //     (= ["mary" "had" "a" "little" "lamb"]
    //        (gather-words "   mary had a\tlittle\n   lamb    ")))
    // @BEGIN_VERSION GATHER_WORDS_PUNCTUATION
    //   (it "removes punctuation"
    //     (= ["mary" "had" "a" "little" "lamb"]
    //        (gather-words "., mary, had... a little; lamb!")))
    // @END_VERSION GATHER_WORDS_PUNCTUATION
    // @BEGIN_VERSION GATHER_WORDS_LOWERCASE
    //   (it "converts words to lower case"
    //     (= ["mary" "had" "a" "little" "lamb"]
    //        (gather-words "., MaRy, hAd... A liTTle; lAmb!"))))
    // @END_VERSION GATHER_WORDS_LOWERCASE
    @Test
    public void testGatherWords() {
        Collection<String> expected = Arrays.asList("mary", "had", "a", "little", "lamb");
        assertThatIterablesAreEqual(WordGraph.gatherWords("   mary had a\tlittle\n   lamb    "), expected);

        // @BEGIN_VERSION GATHER_WORDS_PUNCTUATION
        assertThatIterablesAreEqual(WordGraph.gatherWords("., mary, had... a little; lamb!"), expected);
        // @END_VERSION GATHER_WORDS_PUNCTUATION

        // @BEGIN_VERSION GATHER_WORDS_LOWERCASE
        assertThatIterablesAreEqual(WordGraph.gatherWords("., MaRy, hAd... A liTTle; lAmb!"), expected);
        // @END_VERSION GATHER_WORDS_LOWERCASE
    }

    // @END_VERSION GATHER_WORDS_WHITESPACE

    // @BEGIN_VERSION COUNT_WORDS
    // (describe count-words
    //   (it "counts words into a map"
    //     (= {"mary" 2 "why" 3 } (count-words ["why" "mary" "why" "mary" "why"]))))
    @Test
    public void testCountWords() {
        Map<String, Integer> expected = new ImmutableMap.Builder<String, Integer>()
            .put("mary", 2)
            .put("why", 3)
            .build();

        assertThat(WordGraph.countWords(Arrays.asList("why", "mary", "why", "mary", "why")), is(expected));
    }

    // @END_VERSION COUNT_WORDS

    // @BEGIN_VERSION SORT_COUNTED_WORDS
    // (describe sort-counted-words
    //   (it "sorts and returns a list of word/count pairs"
    //     (= [["a" 1] ["c" 2] ["b" 3]] (sort-counted-words {"b" 3 "c" 2 "a" 1}))))
    @Test
    public void testSortCountedWords() {
        ImmutableMap<String, Integer> input = new ImmutableMap.Builder<String, Integer>()
            .put("b", 3)
            .put("c", 2)
            .put("a", 1)
            .build();
        Collection<WordCountPair> expected = new ImmutableList.Builder<WordCountPair>()
        .add(new WordCountPair("a", 1))
        .add(new WordCountPair("c", 2))
        .add(new WordCountPair("b", 3))
        .build();
        assertThat(WordGraph.sortCountedWords(input), is(expected));
    }
    // @END_VERSION SORT_COUNTED_WORDS

    // @BEGIN_VERSION REPEAT_STR
    // (describe repeat-str
    //   (it "returns the empty string if count is zero"
    //     (= "" (repeat-str "*" 0)))
    //   (it "repeats the input string n times"
    //     (= "xxxxx" (repeat-str "x" 5))))
    @Test
    public void testRepeatStr() {
        assertThat(WordGraph.repeatStr("*", 0), is(""));
        assertThat(WordGraph.repeatStr("x", 5), is("xxxxx"));
    }
    // @END_VERSION REPEAT_STR

    // @BEGIN_VERSION HISTOGRAM_ENTRY
    // (describe histogram-entry
    //   (it "can generate a single histogram entry"
    //     (= "betty   ######" (histogram-entry ["betty" 6] 7))))
    @Test
    public void testHistogramEntry() {
        assertThat(WordGraph.histogramEntry(new WordCountPair("betty", 6), 7), is("betty   ######"));
    }
    // @END_VERSION HISTOGRAM_ENTRY

    // @BEGIN_VERSION HISTOGRAM
    // (describe histogram
    //   (it "can generate a histogram from word counts"
    //     (= "mary ##\nwhy  ###\n" (histogram [["mary" 2] ["why" 3]]))))
    @Test
    public void testHistogram() {
        List<WordCountPair> input = new ImmutableList.Builder<WordCountPair>()
                .add(new WordCountPair("mary", 2))
                .add(new WordCountPair("why", 3))
                .build();
        assertThat(WordGraph.histogram(input), is("mary ##\nwhy  ###\n"));
    }
    // @END_VERSION HISTOGRAM
}
