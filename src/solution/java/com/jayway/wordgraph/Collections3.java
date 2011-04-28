package com.jayway.wordgraph;


import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.ImmutableList.copyOf;
import static com.jayway.wordgraph.Sneak.sneakyThrow;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Function;

public class Collections3 {
    // @BEGIN_VERSION PARALLEL_TRANSFORM
	private static ExecutorService threadPool = Executors.newFixedThreadPool(10);
	private static long timeout = 1000*60;
	
	public static <A> Collection<A> parallelTransform(Collection<A> fromCollection, final Function<? super A, A> function) {
		return getAll(transformInBackground(fromCollection, function));
    }
	
	public static <A> Function<A, Future<A>> toBackgroundFunction(final Function<? super A, A> function) {
		return new Function<A, Future<A>>() {
			public Future<A> apply(final A from) {
				return threadPool.submit(new Callable<A>() {
					public A call() throws Exception {
						return function.apply(from);
					}
				});
			}
		};
	};

	public static <A> Collection<A> getAll(Collection<Future<A>> col) {
		Function<Future<A>, A> fromFuture = new Function<Future<A>, A>() {
			public A apply(Future<A> from) {
				try {
					return from.get(timeout, TimeUnit.MILLISECONDS);
				} catch (Exception e) {
					throw sneakyThrow(e);
				}
			}
		};
		return copyOf(transform(col, fromFuture));
	}

	public static <A> Collection<Future<A>> transformInBackground(Collection<A> fromCollection, final Function<? super A, A> function) {
		return copyOf(transform(fromCollection, toBackgroundFunction(function)));
	}
    // @END_VERSION PARALLEL_TRANSFORM

    // @BEGIN_VERSION REDUCE
    public static <A> A reduce(Function2<A, A, A> f, Iterable<A> coll) {
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
    }
    // @END_VERSION REDUCE

    // @BEGIN_VERSION FOLD
    public static <A, B> A fold(Function2<A, B, A> f, A val, Iterable<B> coll) {
        A result = val;
        for (B next : coll) {
            result = f.apply(result, next);
        }
        return result;
    }
    // @END_VERSION FOLD
}
