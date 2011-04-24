package com.jayway.wordgraph;

import static com.jayway.wordgraph.Collections3.reduce;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class WordGraphTest
{
    //@BEGIN_VERSION 0
	@Test
	public void reduceWithPlusShouldAddAllNumbers() {
		Function2<Integer, Integer> plus = new Function2<Integer, Integer>() {
			public Integer apply(Integer accum, Integer next) {
				return accum + next;
			}
		};
		assertThat(reduce(plus, 0, Arrays.asList(1, 2, 3, 4)), is(10));
	}

	@Test
	public void reduceWithTimesShouldMultiplyAllNumbers() {
		Function2<Integer, Integer> times = new Function2<Integer, Integer>() {
			public Integer apply(Integer accum, Integer next) {
				return accum * next;
			}
		};
		assertThat(reduce(times, 1, Arrays.asList(1, 2, 3, 4)), is(24));
	}
	
	@Test
	public void reduceWithAddFirstShouldReverse() {
		Function2<Deque<Integer>, Integer> reverse = new Function2<Deque<Integer>, Integer>() {
            public Deque<Integer> apply(Deque<Integer> accum, Integer next) {
                accum.addFirst(next);
                return accum;
            }
        };
        Deque<Integer> expected = new LinkedList<Integer>();
        expected.add(3);
        expected.add(2);
        expected.add(1);
        assertThat(reduce(reverse, new LinkedList<Integer>(), Arrays.asList(1, 2, 3)), is(expected));
    }
    //@END_VERSION 0
    
    //@BEGIN_VERSION 1
	//    (describe gather-words
	//    		  (it "splits words on whitespace"
	//    		    (= ["mary" "had" "a" "little" "lamb"] (gather-words "   mary had a\tlittle\n   lamb    ")))
	//    		  (it "removes punctuation"
	//    		    (= ["mary" "had" "a" "little" "lamb"] (gather-words "., mary, had... a little; lamb!")))
	//    		  (it "converts words to lower case"
	//    		    (= ["mary" "had" "a" "little" "lamb"] (gather-words "., MaRy, hAd... A liTTle; lAmb!"))))
    @Test
    public void testGatherWords() {
        Collection<String> actual;
        Collection<String> expected = Arrays.asList("mary", "had", "a", "little", "lamb");
        
        actual = WordGraph.gatherWords("   mary had a\tlittle\n   lamb    ");
        assertCollectionsEqual(expected, actual);
        
    //@BEGIN_VERSION 2
        actual = WordGraph.gatherWords("., mary, had... a little; lamb!");
        assertCollectionsEqual(expected, actual);
    //@END_VERSION 2

    //@BEGIN_VERSION 3
        actual = WordGraph.gatherWords("., MaRy, hAd... A liTTle; lAmb!");
        assertCollectionsEqual(expected, actual);
    //@END_VERSION 3
    }
    //@END_VERSION 1

    //@BEGIN_VERSION 4
	//    (describe count-words
	//    		  (it "counts words into a map"
	//    		    (= {"mary" 2 "why" 3 } (count-words ["why" "mary" "why" "mary" "why"]))))
    @Test
    public void testCountWords() {
        Map<String, Integer> expected = new ImmutableMap.Builder<String, Integer>()
	        .put("mary", 2)
	        .put("why", 3)
	        .build();
        
        Map<String, Integer> actual = WordGraph.countWords(Arrays.asList("why", "mary", "why", "mary", "why"));
        assertThat(actual, is(expected));
    }
    //@END_VERSION 4

    //@BEGIN_VERSION 5
	//    (describe sort-counted-words
	//    		  (it "sorts and returns a list of word/count pairs"
	//    		    (= [["a" 1] ["c" 2] ["b" 3]] (sort-counted-words {"b" 3 "c" 2 "a" 1}))))
    @Test
    public void testSortCountedWords() {
        ImmutableMap<String, Integer> input = new ImmutableMap.Builder<String, Integer>()
	        .put("b", 3)
	        .put("c", 2)
	        .put("a", 1)
	        .build();
        Collection<Map<String,Integer>> expected = new ImmutableList.Builder<Map<String,Integer>>()
	    	.add(Collections.singletonMap("a", 1))
	    	.add(Collections.singletonMap("c", 2))
	    	.add(Collections.singletonMap("b", 3))
	    	.build();
        assertThat(WordGraph.sortCountedWords(input), is(expected));
    }
    //@END_VERSION 5

    //@BEGIN_VERSION 6
	//    (describe repeat-str
	//    		  (it "returns the empty string if count is zero"
	//    		    (= "" (repeat-str "*" 0)))
	//    		  (it "repeats the input string n times"
	//    		    (= "xxxxx" (repeat-str "x" 5))))
    @Test
    public void testRepeatStr() {
    	assertThat(WordGraph.repeatStr("*", 0), is(""));
    	assertThat(WordGraph.repeatStr("x", 5), is("xxxxx"));
    }
    //@END_VERSION 6

    //@BEGIN_VERSION 7
	//    (describe histogram-entry
	//    		  (it "can generate a single histogram entry"
	//    		    (= "betty   ######" (histogram-entry ["betty" 6] 7))))
    @Test
    public void testHistogramEntry() {
    	assertThat(WordGraph.histogramEntry(Collections.singletonMap("betty", 6), 7), is("betty   ######"));
    }
    //@END_VERSION 7

    //@BEGIN_VERSION 8
	//    (describe histogram
	//    		  (it "can generate a histogram from word counts"
	//    		    (= "mary ##\nwhy  ###\n" (histogram [["mary" 2] ["why" 3]]))))
    @Test
    public void testHistogram() {
        List<Map<String,Integer>> input = new ImmutableList.Builder<Map<String,Integer>>()
	    	.add(Collections.singletonMap("mary", 2))
	    	.add(Collections.singletonMap("why", 3))
	    	.build();
    	assertThat(WordGraph.histogram(input), is("mary ##\nwhy  ###\n"));
    }
    //@END_VERSION 8

    //@BEGIN_VERSION 1
    private void assertCollectionsEqual(Collection<?> expected, Collection<String> actual) {
        assertThat("Collections are not of same length,", actual.size(), is(expected.size()));
        assertTrue("Collections are not equal", Arrays.equals(expected.toArray(), actual.toArray()));
    }
    //@END_VERSION 1
}
