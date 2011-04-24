This program, wordgraph, will read a text file, count word occurrences and produce
a histogram of the results.

Quick Start
-----------
Check out this lab from github.com:

	git clone git@github.com:ulsa/javawordgraph.git

The lab is constructed using [maven-lab-plugin](https://github.com/jayway/maven-lab-plugin). Initialize the lab to step 0 by running:

	mvn lab:init

Move to the next step when you have solved the problem, compiled the code and ran the tests. You can also skip to the next step if you give up and want to see a solution to this step, and take on the next.

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
You sure have been thrown into the deep end first. Google Collections has map and filter, but not reduce. In this exercise, you will implement reduce. Reduce takes a function f, an initial value val, and a collection coll. f should be a function of 2 arguments. If coll has only 1 item, it is returned and f is not called. Reduce returns the result of applying f to val and the first item in coll, then applying f to that result and the 2nd item, etc.

We provide an interface that should be used for the function that reduce takes:

    public interface Function2<A, T> {
        public A apply(A accum, T next);
    }
	
We also provide a skeleton for the reduce function:

    public class Collections3<A, T> {
        public static <A, T> A reduce(Function2<A, T> f, A initial, Iterable<T> coll) {
    	    ...
        }
    }

An example of using reduce:

    Function2<Integer, Integer> plus = new Function2<Integer, Integer>() {
        public Integer apply(Integer accum, Integer next) {
            return accum + next;
        }
    };
    reduce(plus, 0, Arrays.asList(1, 2, 3, 4))
	
Step 1: Gather Words
--------------------
Back to the real business problem. In comments for the test methods you can see the Clojure equivalents of the tests. I have left them there because I think that they are more readable than the Java test code, and that they convey the essence of the tests quite clearly.

Write a function that extracts words from a string, splitting words on whitespace. Note that there is a main method that you can run on any text file of choice.

Step 2: Gather Words, Remove Punctuation
----------------------------------------
Remove the punctuation. Run main if you want.

Step 3: Gather Words, Ignore Case
---------------------------------
Ignore the case. Run main if you want.

Step 4: Count Words
-------------------
Count words into a map. Perhaps you'll get some use of that reduce implementation now?

Step 5: Sort Counted Words
--------------------------
Sort by count and return a list of word/count pairs.

Step 6: Repeat String
---------------------
Repeat a string a given number of times.

Step 7: Histogram Entry
-----------------------
Produce a single entry in the histogram.

Step 8: Histogram
-----------------
Produce a complete histogram.

