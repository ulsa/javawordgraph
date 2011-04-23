package com.jayway.wordgraph;

public interface Reducer<A, T>
{
    public A reduce(A accum, T next);
}
