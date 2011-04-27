package com.jayway.wordgraph;

import static java.util.Collections.singletonMap;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matcher;
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
        String[] expected = new String[] {"mary", "had", "a", "little", "lamb"};
        assertThatCollection(WordGraph.gatherWords("   mary had a\tlittle\n   lamb    "), is(expected));

        // @BEGIN_VERSION GATHER_WORDS_PUNCTUATION
        assertThatCollection(WordGraph.gatherWords("., mary, had... a little; lamb!"), is(expected));
        // @END_VERSION GATHER_WORDS_PUNCTUATION

        // @BEGIN_VERSION GATHER_WORDS_LOWERCASE
        assertThatCollection(WordGraph.gatherWords("., MaRy, hAd... A liTTle; lAmb!"), is(expected));
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
        Collection<Map<String, Integer>> expected = new ImmutableList.Builder<Map<String, Integer>>()
            .add(singletonMap("a", 1))
            .add(singletonMap("c", 2))
            .add(singletonMap("b", 3))
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
        assertThat(WordGraph.histogramEntry(singletonMap("betty", 6), 7), is("betty   ######"));
    }
    // @END_VERSION HISTOGRAM_ENTRY

    // @BEGIN_VERSION HISTOGRAM
    // (describe histogram
    //   (it "can generate a histogram from word counts"
    //     (= "mary ##\nwhy  ###\n" (histogram [["mary" 2] ["why" 3]]))))
    @Test
    public void testHistogram() {
        List<Map<String, Integer>> input = new ImmutableList.Builder<Map<String, Integer>>()
                .add(singletonMap("mary", 2))
                .add(singletonMap("why", 3))
                .build();
        assertThat(WordGraph.histogram(input), is("mary ##\nwhy  ###\n"));
    }
    // @END_VERSION HISTOGRAM

    // had trouble getting assertThat to compare a transformed collection with a list or array
    private void assertThatCollection(Collection<String> actual, Matcher<String[]> matcher) {
        assertThat(actual.toArray(new String[0]), matcher);
    }
}
