package com.jayway.wordgraph;

import com.google.common.base.Function;

public class Hello {
    // @BEGIN_VERSION HELLO_FUNCTION
    public static Function<Integer, Integer> square = new Function<Integer, Integer>() {
        public Integer apply(Integer from) {
            return from * from;
        }
    };
    // @END_VERSION HELLO_FUNCTION
}
