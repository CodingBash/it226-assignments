CC=g++
CFLAGS=-std=c++11 -g -Wall

all: main

main: Assignment5.o ArrayList.o
	${CC} ${CFLAGS} -o main Assignment5.o ArrayList.o

Main.o: Main.cpp
	${CC} ${CFLAGS} -c Assignment5.cpp

ArrayList.o: ArrayList.cpp ArrayList.h
	${CC} ${CFLAGS} -c ArrayList.cpp

clean:
	rm -f *.o *.out main
