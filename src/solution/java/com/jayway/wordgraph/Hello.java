package com.jayway.wordgraph;

import java.util.Collection;
import java.util.LinkedList;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class Hello {

    // @BEGIN_VERSION HELLO_GENERICS
    public static <T> Collection<T> repeat(T repeatThis, int times) {
        LinkedList<T> list = Lists.newLinkedList();
        for (int i = 0; i < times; i++) {
            list.add(repeatThis);
        }
        return list;
    }
    // @END_VERSION HELLO_GENERICS

    // @BEGIN_VERSION HELLO_FUNCTION
    public static Function<Integer, Integer> square = new Function<Integer, Integer>() {
        public Integer apply(Integer from) {
            return from * from;
        }
    };
    // @END_VERSION HELLO_FUNCTION

    // @BEGIN_VERSION HELLO_BINARY_FUNCTION
    public static Function2<Integer, Integer, Integer> add = new Function2<Integer, Integer, Integer>() {
        public Integer apply(Integer a1, Integer a2) {
            return a1 + a2;
        }
    };
    // @END_VERSION HELLO_BINARY_FUNCTION
}
