package com.jayway.wordgraph;

// @BEGIN_VERSION FOLD
import static com.jayway.wordgraph.Collections3.fold;

// @END_VERSION FOLD
// @BEGIN_VERSION REDUCE
import static com.jayway.wordgraph.Collections3.reduce;
// @END_VERSION REDUCE
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

public class Collections3Test {
    @Test
    public void dummyTest() {
    }

    // @BEGIN_VERSION TO_BACKGROUND_FUNCTION
    private static final Function<Integer, Integer> timeConsumingCalculation = new Function<Integer, Integer>() {
        public Integer apply(Integer from) {
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
            }
            return from * 2;
        }
    };
    
    @Test
    public void backgroundFunctionShouldHaveSameResult() throws Exception {
        String expected = "hello";
        Future<String> future = Collections3.toBackgroundFunction(Functions.constant(expected)).apply("world");
        assertThat(future.get(1, TimeUnit.SECONDS), equalTo(expected));
    }
    
    @Test
    public void backgroundFunctionShouldReturnFast() throws Exception {
        long before = System.currentTimeMillis();
        Future<Integer> future = Collections3.toBackgroundFunction(timeConsumingCalculation).apply(1);
        long after = System.currentTimeMillis();
        assertThat(after-before, lessThan(50L));
        assertThat(future.get(2, TimeUnit.SECONDS), equalTo(2));
    }
    // @END_VERSION TO_BACKGROUND_FUNCTION
    
    // @BEGIN_VERSION BACKGROUND_TRANSFORM
    @Test
    public void backgroundTransformShouldHaveSameResult() throws Exception {
        String expected = "hello";
        Collection<Future<String>> futures = Collections3.transformInBackground(Collections.<Object>singleton("world"), Functions.constant(expected));
        assertThat(Iterables.getOnlyElement(futures).get(1, TimeUnit.SECONDS), equalTo(expected));
    }
    
    @Test
    public void backgroundTransformShouldPerformFunctionOnlyOnce() throws Exception {
        Function<Object, Integer> countingFunction = new Function<Object, Integer>() {
            private int callCount = 0;
            public Integer apply(Object from) {
                return ++callCount;
            }
        };
        Collection<Future<Integer>> futures = Collections3.transformInBackground(Collections.<Object>singleton("world"), countingFunction);
        assertThat(Iterables.getOnlyElement(futures).get(1, TimeUnit.SECONDS), equalTo(1));
        assertThat(Iterables.getOnlyElement(futures).get(1, TimeUnit.SECONDS), equalTo(1)); // check twice to force second iteration
        assertThat(countingFunction.apply("qwe"), equalTo(2));
    }
    // @END_VERSION BACKGROUND_TRANSFORM

    // @BEGIN_VERSION FROM_FUTURE_FUNCTION
    @SuppressWarnings("unchecked")
    @Test
    public void fromFutureMustUseTimeout() throws Exception {
        Collections3.setTimeout(1000L);
        Future future = mock(Future.class);
        Collections3.fromFuture().apply(future);
        verify(future).get(1000L, TimeUnit.MILLISECONDS);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void fromFutureShouldGetValue() throws Exception {
        Future future = Mockito.mock(Future.class);
        Object expected = "result";
        when(future.get(anyLong(), (TimeUnit)anyObject())).thenReturn(expected);
        assertThat(Collections3.fromFuture().apply(future), equalTo(expected));
    }

    // @END_VERSION FROM_FUTURE_FUNCTION

    // @BEGIN_VERSION REGULAR_TRANSFORM
    @Test
    public void parallelMapShouldRunQuicker() {
        long before = System.currentTimeMillis();
        Collection<Integer> result;
        // @BEGIN_VERSION_ONLY REGULAR_TRANSFORM
        result = Collections2.transform(Arrays.asList(1, 2, 3, 4, 5), timeConsumingCalculation);
        // @END_VERSION_ONLY REGULAR_TRANSFORM
        // @BEGIN_VERSION_ONLY PARALLEL_TRANSFORM
        result = Collections3.parallelTransform(Arrays.asList(1, 2, 3, 4, 5), timeConsumingCalculation);
        // @END_VERSION_ONLY PARALLEL_TRANSFORM
        assertThatCollection(result, is(new Integer[] {2, 4, 6, 8, 10}));
        long after = System.currentTimeMillis();
        System.out.println("Transformation took " + (after-before) + " milliseconds");
        assertThat("Transform is running waaaay too slow!", after-before, is(lessThan(1200L)));
    }
    // @END_VERSION REGULAR_TRANSFORM

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

    // had trouble getting assertThat to compare a transformed collection with a list or array
    public static void assertThatCollection(Collection<Integer> actual, Matcher<Integer[]> matcher) {
        assertThat(actual.toArray(new Integer[actual.size()]), matcher);
    }
}
