This program, wordgraph, will read a text file, count word occurrences and produce
a histogram of the results.

Pre-requisites
--------------
-  Java JDK 5+ [download here](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
-  Maven 2+ [download here](http://maven.apache.org/download.html)

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

Your workflow should be the following:
1.  See INSTRUCTIONS.txt for instructions
2.  Compile and run test cases to see what you need to do
3.  (if necessary) Check HINTS.txt file for hints.
4.  (if necessary) Check src/solution/java for a solution.
5.  Once the tests run successfully, mvn lab:next to get the next step

Notice: You will need to create classes and methods to fix compilation errors!

Good luck!
