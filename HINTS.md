This file contains hints that don't give you the solution, but point you in the right direction.

Reload this file after moving to the next step, for further hints.

// @BEGIN_VERSION_ONLY TO_BACKGROUND_FUNCTION
Step: To Background Function
----------------------------
Look into java.util.concurrent.ExecutorService.submit. Create a Function that creates a Callable that performs whatever the original Function does.
// @END_VERSION_ONLY TO_BACKGROUND_FUNCTION

// @BEGIN_VERSION_ONLY BACKGROUND_TRANSFORM
Step: Background Transform
--------------------------
Apply the background function you just implemented in a transform. The resulting collection is lazy. Look into ImmutableList.copyOf to go non-lazy.
// @END_VERSION_ONLY BACKGROUND_TRANSFORM

// @BEGIN_VERSION_ONLY FROM_FUTURE_FUNCTION
Step: From Future Function
--------------------------
Do you 'get' it?
// @END_VERSION_ONLY FROM_FUTURE_FUNCTION

// @BEGIN_VERSION_ONLY REGULAR_TRANSFORM
Step: Regular Transform
-----------------------
Nothing to do in this step, just run the test.
// @END_VERSION_ONLY REGULAR_TRANSFORM

// @BEGIN_VERSION_ONLY GET_ALL
Step: Get All
-------------
Note Java's crappy type inference:
	return copyOf(transform(coll, Collections3.fromFuture())); // does not work
	return copyOf(transform(coll, fromFuture())); // does not work

You must write something like:
	return copyOf(transform(coll, Collections3.<A>fromFuture()));
// @END_VERSION_ONLY GET_ALL

// @BEGIN_VERSION_ONLY PARALLEL_TRANSFORM
Step: Parallel Transform
------------------------
Get all the results that have been transformed in the background.
// @END_VERSION_ONLY PARALLEL_TRANSFORM

// @BEGIN_VERSION_ONLY REDUCE
Step: Reduce
------------
Use an iterator to check if no values, to get the first value, etc.
// @END_VERSION_ONLY REDUCE

// @BEGIN_VERSION_ONLY FOLD
Step: Fold
----------
Vanilla foreach.
// @END_VERSION_ONLY FOLD
	
// @BEGIN_VERSION_ONLY GATHER_WORDS_WHITESPACE
Step Gather Words, Whitespace
-----------------------------
No hints yet.
// @END_VERSION_ONLY GATHER_WORDS_WHITESPACE

// @BEGIN_VERSION_ONLY GATHER_WORDS_PUNCTUATION
Step Gather Words, Remove Punctuation
-------------------------------------
No hints yet.
// @END_VERSION_ONLY GATHER_WORDS_PUNCTUATION

// @BEGIN_VERSION_ONLY GATHER_WORDS_LOWERCASE
Step Gather Words, Ignore Case
------------------------------
No hints yet.
// @END_VERSION_ONLY GATHER_WORDS_LOWERCASE

// @BEGIN_VERSION_ONLY COUNT_WORDS
Step Count Words
----------------
This is the pseudo-code for counting stuff in a collection:

    (fold (function [counts x]
      	        (assoc counts x (inc (get counts x 0))))
          {}
          coll))

'counts' is a Map that accumulates the counts.
'x' will be each element in 'coll'.
{} is an empty Map used as the initial value.

Get the value of x from the Map counts, or zero if no x in counts:
	(get counts x 0)

Increase it by one:
	(inc (get counts x 0))

Associate to the key 'x', in the Map 'counts', the above number as the value:
    (assoc counts x (inc (get counts x 0)))
// @END_VERSION_ONLY COUNT_WORDS

// @BEGIN_VERSION_ONLY SORT_COUNTED_WORDS
Step Sort Counted Words
-----------------------
Transform the Map to a collection of Maps, then sort.
// @END_VERSION_ONLY SORT_COUNTED_WORDS

// @BEGIN_VERSION_ONLY REPEAT_STR
Step Repeat String
------------------
StringBuilder
// @END_VERSION_ONLY REPEAT_STR

// @BEGIN_VERSION_ONLY HISTOGRAM_ENTRY
Step Histogram Entry
--------------------
sprintf("%-7.7s %d", word, count)
// @END_VERSION_ONLY HISTOGRAM_ENTRY

// @BEGIN_VERSION_ONLY HISTOGRAM
Step Histogram
--------------
No hints yet.
// @END_VERSION_ONLY HISTOGRAM
