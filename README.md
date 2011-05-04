This program, wordgraph, will read a text file, count word occurrences and produce
a histogram of the results.

Pre-requisites
--------------
Java JDK 5+ [download here](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
Maven 2+ [download here](http://maven.apache.org/download.html)

Introduction
------------
This lab is about Functional Programming in Java, using mainly Google Collections.
The purpose is to build a little utility that shows a histogram graph of the number
of occurrences of each word in a text file. Like this:

	% java ...WordGraph "mary.txt"
	fleece     #
	but        #
	...
	lamb       ############
	mary       #############
	the        ##############

In the process, you will learn about map, filter and reduce, or as they are
called in Google Collections: transform, filter, and ... well, there is no
reduce in Google Collections. There is no parallel transform either. Let's
implement those first, before we get into the wordgraph business.

Quick Start
-----------
Check out this lab from github.com:

	git clone git@github.com:ulsa/javawordgraph.git

The lab is constructed using [maven-lab-plugin](https://github.com/jayway/maven-lab-plugin).
Initialize the lab to step 0 by running:

	mvn lab:init

Move to the next step when you have solved the problem, compiled the code and
ran the tests, or if otherwise instructed to move on. You can see solutions up
to the current step in src/solution/java. To move to the next step, do this:

	mvn lab:next

Other useful commands:
	mvn lab:currentStep
	mvn lab:reset
	mvn lab:setStep -DlabStep=n

Reload this file after moving to the next step, for further instructions.
Check HINTS.md file for hints.
Check src/solution/java for a solution.

// @BEGIN_VERSION HELLO_FUNCTION
Hello Function
--------------
Write a Function that squares an Integer.
// @END_VERSION HELLO_FUNCTION

// @BEGIN_VERSION TO_BACKGROUND_FUNCTION
To Background Function
----------------------
We're building a parallel version of transform. The idea is this: transform
already applies a Function f to all elements in a collection. So if we can
present a Function that takes f and executes it on a background thread, then
we can simply call transform with that new function. The new function will be
applied to each element in the collection, and execute f on the element but
do it in the background.

The first step towards a parallel transform is to write a Function that turns
an F into a Future<T>.
// @END_VERSION TO_BACKGROUND_FUNCTION

// @BEGIN_VERSION BACKGROUND_TRANSFORM
Background Transform
--------------------
OK, so the previous step produced a method that gives us a function that does
whatever f does, but in the background. 

The second step towards a parallel transform is to execute transform with a
collection and your new background function. However, you should consider the
consequences of laziness.
// @END_VERSION BACKGROUND_TRANSFORM

// @BEGIN_VERSION FROM_FUTURE_FUNCTION
From Future Function
--------------------
The previous step actually runs Function f in the background for all elements
in the collection. Now you need to get the result of the background calculation.
Start by writing a Function that turns a Future<A> back into an A.
// @END_VERSION FROM_FUTURE_FUNCTION

// @BEGIN_VERSION GET_ALL
Get All
-------
Apply the fromFuture function that you wrote in the previous step to a
collection of Futures. It's a regular transform, but again you should consider
the consequences of laziness.
// @END_VERSION GET_ALL

// @BEGIN_VERSION REGULAR_TRANSFORM
Regular Transform
-----------------
This step has a test that illustrates the problem with regular transform with a
function that takes a significant amount of time. Just run the test and watch
it fail. Ponder for a moment what can be done about it, but then move on to the
next step.

	mvn lab:next
	
// @END_VERSION REGULAR_TRANSFORM

// @BEGIN_VERSION PARALLEL_TRANSFORM
Parallel Transform
------------------
In this step, the test has been modified to use a parallel version of transform.
Stitch together the two larger building blocks you have produced so far.
// @END_VERSION PARALLEL_TRANSFORM

// @BEGIN_VERSION REDUCE
Reduce
------
Google Collections has map and filter, but no reduce. In this step, you will
implement reduce.

Reduce takes a collection coll, and a function f. f is a function of 2
arguments. Reduce returns the result of applying f to the first item in coll
and the 2nd item, then applying f to that result and the 3rd item, etc.

Here is an interface for the function f:

    public interface Function2<A1, A2, R> {
        public R apply(A1 a1, A2 a2);
    }

Here is a skeleton for reduce:

    public class Collections3 {
        public static <A> A reduce(Iterable<A> coll, Function2<A, A, A> f) {
    	    ...
        }
    }

Here is an example of using reduce:

    Function2<Integer, Integer, Integer> plus = new Function2<Integer, Integer, Integer>() {
        public Integer apply(Integer accum, Integer next) {
            return accum + next;
        }
    };
    Collections3.reduce(Arrays.asList(1, 2, 3, 4), plus);
// @END_VERSION REDUCE

// @BEGIN_VERSION FOLD
Fold
----
Google Collections doesn't have a fold either. In this step, you will implement
fold.

Fold takes an initial value, but reduce doesn't. Fold takes a collection coll,
an initial value val, and a function f. f is a function of 2 arguments. Fold
returns the result of applying f to val and the first item in coll, then
applying f to that result and the 2nd item, etc.

Here is a skeleton for fold:

    public class Collections3 {
        public static <A,B> A fold(Iterable<B> coll, A val, Function2<A, B, A> f) {
    	    ...
        }
    }

Note that fold takes a Function2, just as reduce did, although the 'next' argument
may be of a different type than 'accum'. Here is an example of using fold:

    Function2<Integer, Integer, Integer> times = new Function2<Integer, Integer, Integer>() {
        public Integer apply(Integer accum, Integer next) {
	        return accum * next;
	    }
	};
    Collections3.fold(Arrays.asList(1, 2, 3, 4), 2, times);

Note the initial value 2.
// @END_VERSION FOLD
	
// @BEGIN_VERSION GATHER_WORDS_WHITESPACE
Gather Words, Whitespace
------------------------
Back to the real business problem. In the test method comments, you can see
the Clojure equivalents of the tests. I have added them there because I think
that they convey the essence of the tests quite clearly.

Write a function that extracts words from a string, splitting words on
whitespace. Note that there is a main method that you can run on any text file
of choice, like for example the file mary.txt in the project root.
// @END_VERSION GATHER_WORDS_WHITESPACE

// @BEGIN_VERSION GATHER_WORDS_PUNCTUATION
Gather Words, Remove Punctuation
--------------------------------
Remove not only whitespace, but also any punctuation. Run main and watch the
output.
// @END_VERSION GATHER_WORDS_PUNCTUATION

// @BEGIN_VERSION GATHER_WORDS_LOWERCASE
Gather Words, Ignore Case
-------------------------
Ignore the case of the words by converting them to lowercase. Run main.
// @END_VERSION GATHER_WORDS_LOWERCASE

// @BEGIN_VERSION COUNT_WORDS
Count Words
-----------
Count the words into a map from words to count. Perhaps you'll get some use of
that fold implementation now?
// @END_VERSION COUNT_WORDS

// @BEGIN_VERSION SORT_COUNTED_WORDS
Sort Counted Words
------------------
Sort the map by count and return a list of word/count pairs. What's the simplest
implementation of a pair? I don't know, but we chose Map, so that's what you'll
use. It might look funny with a Collection of Map from String to Integer, but
remember that the Map is only representing a key-value pair.
// @END_VERSION SORT_COUNTED_WORDS

// @BEGIN_VERSION REPEAT_STR
Repeat String
-------------
Repeat a string a given number of times. Like 'repeatStr "#" 3' becomes "###".
// @END_VERSION REPEAT_STR

// @BEGIN_VERSION HISTOGRAM_ENTRY
Histogram Entry
---------------
Produce a single entry in the histogram. Calling histogramEntry with a word-count
pair of ["betty" 6] and a width of 7 should return the word "betty" left-justified
within a field of 7 characters, a blank, and then six hashes: "betty   ######".
// @END_VERSION HISTOGRAM_ENTRY

// @BEGIN_VERSION HISTOGRAM
Histogram
---------
Produce a complete histogram. It's just a bunch of histogram entries.
// @END_VERSION HISTOGRAM

// @BEGIN_VERSION DESIGN_QUESTIONS
Design Questions
----------------
The lab is now complete, but here are some questions that you should consider:
* Why are we using a static function rather than a field to create the function
  fromFuture? Could it be done differently? Pros and cons?
* Is Collections3 a good place to store all the functions? Other suggestions?
  (Compare with Google Collections)
* Both transformInBackground and fromFuture are stateful, but are used as
  static methods. Can you come up with a different design? There are at least
  two completely different approaches.
// @END_VERSION DESIGN_QUESTIONS
