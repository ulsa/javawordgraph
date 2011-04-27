package com.jayway.wordgraph;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class Collections3 {
    // @BEGIN_VERSION PARALLEL_TRANSFORM
    public static <A> Collection<A> parallelTransform(Collection<A> fromCollection, final Function<? super A, A> function) {
        // @BEGIN_VERSION REDUCE
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
        Collection<Future<A>> futures = Lists.newArrayList();
        for (final A task : fromCollection) {
            Callable<A> callable = new Callable<A>() {
                public A call() throws Exception {
                    return function.apply(task);
                }
            };
            futures.add(newFixedThreadPool.submit(callable));
        }
        Collection<A> results = Lists.newArrayList();
        for (final Future<A> future : futures) {
            try {
                results.add(future.get(2, TimeUnit.MINUTES));
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return results;
        // @END_VERSION REDUCE
    }
    // @END_VERSION PARALLEL_TRANSFORM

    // @BEGIN_VERSION REDUCE
    public static <A> A reduce(Function2<A, A, A> f, Iterable<A> coll) {
        // @BEGIN_VERSION FOLD
        Iterator<A> iterator = coll.iterator();
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("coll is not allowed to be empty");
        }
        A result = iterator.next();
        for (; iterator.hasNext();) {
            A next = iterator.next();
            result = f.apply(result, next);
        }
        return result;
        // @END_VERSION FOLD
    }
    // @END_VERSION REDUCE

    // @BEGIN_VERSION FOLD
    public static <A, B> A fold(Function2<A, B, A> f, A val, Iterable<B> coll) {
        // @BEGIN_VERSION GATHER_WORDS_WHITESPACE
        A result = val;
        for (B next : coll) {
            result = f.apply(result, next);
        }
        return result;
        // @END_VERSION GATHER_WORDS_WHITESPACE
    }
    // @END_VERSION FOLD
}
