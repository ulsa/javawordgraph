This program, wordgraph, will read a text file, count word occurrences and produce
a histogram of the results.

Getting Started
---------------
Check out this lab from github.com:

	git clone git@github.com:ulsa/javawordgraph.git

The lab is constructed using [maven-lab-plugin](https://github.com/jayway/maven-lab-plugin). Initialize the lab to step 0 by running:

	mvn lab:init

Move to the next step when you have fixed the problems, compiled the code and ran
the tests. You can also skip to the next step if you give up and want to see a
solution to this step, and take on the next.

When you are ready for the next step, do this:

	mvn lab:next

Show the current step:

	mvn lab:currentStep

Restore all files that were affected by the lab:

	mvn lab:reset

To change to a particular step of the lab:

	mvn lab:setStep -DlabStep=nnn

Step 0: Reduce
--------------
You sure have been thrown into the deep end first. Google Collections does have map and filter, but not reduce. In this exercise, you will implement reduce. There are many ways to do it. How would you do it? Think about it for a while.

Some widget needs to take an initial value and a collection. It should go through the collection and do something with the initial value and the first element in the collection, then do something with the result of that and the second element of the collection, and so on.

We provide an interface called Reducer for the "do something" part:

	public interface Reducer<A, T>
	{
	    public A reduce(A accum, T next);
	}
	
We also provide a class called Reductor as the "Some widget". However, the implementation is not complete:

	public class Reductor<A, T> {
		private final Reducer<A, T> reducer;

		public Reductor(Reducer<A, T> reducer) {
			this.reducer = reducer;
		}

		public A reduce(A initial, Iterable<T> coll) {
			// add implementation here
		}
	}

