package com.jayway.wordgraph;

import java.util.Iterator;

public class Collections3 {
    // @BEGIN_VERSION REDUCE
    // TODO pmap impl here
    // @END_VERSION REDUCE

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
