This file contains hints that don't give you the solution, but points you in the right direction.

Reload this file after moving to the next step, for further hints.

// @BEGIN_VERSION_ONLY TO_BACKGROUND_FUNCTION
Step: To Background Function
----------------------------
Look into java.util.concurrent.ExecutorService.submit.
// @END_VERSION_ONLY TO_BACKGROUND_FUNCTION

// @BEGIN_VERSION_ONLY BACKGROUND_TRANSFORM
Step: Background Transform
--------------------------
Look into ImmutableList.copyOf.
// @END_VERSION_ONLY BACKGROUND_TRANSFORM

// @BEGIN_VERSION_ONLY FROM_FUTURE_FUNCTION
Step: From Future Function
--------------------------
No hints yet.
// @END_VERSION_ONLY FROM_FUTURE_FUNCTION

// @BEGIN_VERSION_ONLY REGULAR_TRANSFORM
Step: Regular Transform
-----------------------
Nothing to do in this step, just run the test.
// @END_VERSION_ONLY REGULAR_TRANSFORM

// @BEGIN_VERSION_ONLY PARALLEL_TRANSFORM
Step: Parallel Transform
------------------------
Transform the collection into a collection of Futures. Transform that back into a collection. It's lazy, so you must copy the result into something. An immutable something perhaps?

Note Java's crappy type inference:
	return copyOf(transform(coll, Collections3.fromFuture())); // does not work
	return copyOf(transform(coll, fromFuture())); // does not work

You must write:
	return copyOf(transform(coll, Collections3.<A>fromFuture()));
// @END_VERSION_ONLY PARALLEL_TRANSFORM

// @BEGIN_VERSION_ONLY REDUCE
Step: Reduce
------------
No hints yet.
// @END_VERSION_ONLY REDUCE

// @BEGIN_VERSION_ONLY FOLD
Step: Fold
----------
No hints yet.
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
(reduce (fn [counts x]
          (assoc counts x (inc (get counts x 0))))
        coll))
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
