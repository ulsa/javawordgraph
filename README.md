This program, wordgraph, will read a text file, count word occurrences and produce a histogram of the results.

Introduction
------------
This lab is about Functional Programming in Java, using mainly Google Collections. The purpose is to build a little utility that shows a histogram graph of the number of occurrences of each word in a text file. Like this:

	% java ...WordGraph "README.md"
	etc            #
	particular     #
	...
	lab            #############
	step           ################
	a              #########################
	the            #############################

In the process, you will learn about map, filter and reduce, or as they are called in Google Collections: transform, filter, and ... well, there is no reduce in Google Collections. Hmmm. What shall we do about that?

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

Restore all files to the state that was before the lab:init:

	mvn lab:reset

To change to a particular step of the lab:

	mvn lab:setStep -DlabStep=n

Step 0: Reduce
--------------
Google Collections has map and filter, but no reduce. In this exercise, you will implement reduce. And fold. Because they're different. Fold takes an initial value, but reduce doesn't.

Fold takes a function f, an initial value val, and a collection coll. f should be a function of 2 arguments. Reduce returns the result of applying f to val and the first item in coll, then applying f to that result and the 2nd item, etc.

Reduce takes a function f, and a collection coll. f should be a function of 2 arguments. Reduce returns the result of applying f to the first item in coll and the 2nd item, then applying f to that result and the 3rd item, etc.

We provide an interface for the function that both reduce and fold take:

    public interface Function2<A1, A2, R> {
        public R apply(A1 accum, A2 next);
    }

We provide a skeleton for the reduce function:

    public class Collections3 {
        public static <A> A reduce(Function2<A, A, A> f, A initial, Iterable<A> coll) {
    	    ...
        }
    }

Here is an example of using reduce:

    Function2<Integer, Integer, Integer> plus = new Function2<Integer, Integer, Integer>() {
        public Integer apply(Integer accum, Integer next) {
            return accum + next;
        }
    };
    Collections3.reduce(plus, Arrays.asList(1, 2, 3, 4))

We also provide a skeleton for the fold function:

    public class Collections3 {
        public static <A,B> A fold(Function2<A, B, A> f, A initial, Iterable<B> coll) {
    	    ...
        }
    }

And here is an example of using fold (note the initial value 2):

    Function2<Integer, Integer, Integer> times = new Function2<Integer, Integer, Integer>() {
        public Integer apply(Integer accum, Integer next) {
	        return accum * next;
	    }
	};
    Collections3.fold(times, 2, Arrays.asList(1, 2, 3, 4))
	
Step 1: Gather Words
--------------------
Back to the real business problem. In comments for the test methods you can see the Clojure equivalents of the tests. I have left them there because I think that they are more readable than the Java test code, and that they convey the essence of the tests quite clearly.

Write a function that extracts words from a string, splitting words on whitespace. Note that there is a main method that you can run on any text file of choice, like this README.md file for example.

Step 2: Gather Words, Remove Punctuation
----------------------------------------
Remove also the punctuation. Run main.

Step 3: Gather Words, Ignore Case
---------------------------------
Ignore the case of the words by converting them to lowercase. Run main.

Step 4: Count Words
-------------------
Count the words into a map from words to count. Perhaps you'll get some use of that reduce/fold implementation now?

Step 5: Sort Counted Words
--------------------------
Sort the map by count and return a list of word/count pairs. What's the simplest implementation of a pair? I don't know, but we chose Map, so that's what you'll use.

Step 6: Repeat String
---------------------
Repeat a string a given number of times. Like 'repeatStr "#" 3' becomes "###".

Step 7: Histogram Entry
-----------------------
Produce a single entry in the histogram.

Step 8: Histogram
-----------------
Produce a complete histogram.

