package com.jayway.wordgraph;

public class Reductor<A, T> {
	private Reducer<A, T> reducer;

	public Reductor(Reducer<A, T> reducer) {
		this.reducer = reducer;
	}

	public A reduce(A initial, Iterable<T> coll) {
	   //@BEGIN_VERSION 1
		A value = initial;
		for (T next : coll) {
			value = reducer.reduce(value, next);
		}
		return value;
	   //@END_VERSION 1
	}
}
