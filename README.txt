#A sample index file is included in the repository. The file is a self-contained index including the dictionary. Note there is no document dictionary.
##In the index file, Each posting is on a separate line in the file
###Every posting has three values: the term, the size of the posting list and the posting list itself. Each posting is of the form X\cY\mZ where X is the term, Y is the size of posting list and Z is the posting list
####The posting list itself is expressed as [a/b, c/d, e/f,...]. The square brackets denote the start and end of the list. Each entry of the form x/y means the term occurs 'y' times in document id 'x'.


#The Java program takes the index file as the first parameter when starting and writes corresponding results into a log file. 
##Second Parameter is the log file name that will contain the output. 
###The third parameter is an integer that will be used in the getTopK terms.  
####The last parameter is a file contains query terms. In this file, each line contains a set of query terms that are separated by blank spaces. 
An example query term file is as follows:

###############################################################################
query_term1 query_term2
query_term3 query_term4 query_term5
###############################################################################

#####Each set of query terms will trigger an execution of the getPostings, termAtATimeQueryAnd, termAtATimeQueryOr, docAtATimeQueryAnd and docAtATimeQueryOr once.

######Function getTopK is executed only once, no matter how many sets of query terms are given. 

#####In summary, your program will start running by executing the following example command:

java CSE535Assignment term.idx output.log 10 query_file.txt

