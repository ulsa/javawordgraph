package com.jayway.wordgraph;

import static com.jayway.wordgraph.Collections3.fold;
import static com.jayway.wordgraph.Collections3.reduce;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import org.junit.Test;

public class Collections3Test {
    @Test
    public void dummyTest() {
    }

    // @BEGIN_VERSION PMAP
    @Test
    public void parallelMapShouldRunQuicker() {
        
    }
    // @END_VERSION PMAP

    // @BEGIN_VERSION REDUCE
    @Test
    public void reduceWithPlusShouldAddAllNumbers() {
        Function2<Integer, Integer, Integer> plus = new Function2<Integer, Integer, Integer>() {
            public Integer apply(Integer accum, Integer next) {
                return accum + next;
            }
        };
        assertThat(reduce(plus, Arrays.asList(1, 2, 3, 4)), is(10));
    }

    @Test
    public void reduceWithTimesShouldMultiplyAllNumbers() {
        Function2<Integer, Integer, Integer> times = new Function2<Integer, Integer, Integer>() {
            public Integer apply(Integer accum, Integer next) {
                return accum * next;
            }
        };
        assertThat(reduce(times, Arrays.asList(1, 2, 3, 4)), is(24));
    }
    // @END_VERSION REDUCE

    // @BEGIN_VERSION FOLD
    @Test
    public void foldWithTimesShouldMultiplyInitialValueWithAllNumbers() {
        Function2<Integer, Integer, Integer> times = new Function2<Integer, Integer, Integer>() {
            public Integer apply(Integer accum, Integer next) {
                return accum * next;
            }
        };
        assertThat(fold(times, 2, Arrays.asList(1, 2, 3, 4)), is(48));
    }
    
    @Test
    public void foldWithAddFirstShouldReverseListIntoEmptyList() {
        Function2<Deque<Integer>, Integer, Deque<Integer>> reverse = new Function2<Deque<Integer>, Integer, Deque<Integer>>() {
            public Deque<Integer> apply(Deque<Integer> accum, Integer next) {
                accum.addFirst(next);
                return accum;
            }
        };
        Deque<Integer> expected = new LinkedList<Integer>();
        expected.add(3);
        expected.add(2);
        expected.add(1);
        assertThat(fold(reverse, new LinkedList<Integer>(), Arrays.asList(1, 2, 3)), is(expected));
    }
    // @END_VERSION FOLD
}