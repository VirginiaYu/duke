#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "~/duke-cpy/bin" ]
then
    mkdir ~/duke-cpy/bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ~/duke-cpy/src -Xlint:none -d ~/duke-cpy/bin ~/duke-cpy/src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ~/duke-cpy/bin Duke < input.txt > ACTUAL.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
