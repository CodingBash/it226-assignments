CC=g++
CFLAGS=-std=c++11 -g -Wall

all: main

main: Main.o FileReader.o FileWriter.o Input.o PlaceholderLogic.o
	${CC} ${CFLAGS} -o main Main.o FileReader.o FileWriter.o Input.o PlaceholderLogic.o

Main.o: Main.cpp
	${CC} ${CFLAGS} -c Main.cpp

FileReader.o: FileReader.cpp FileReader.h
	${CC} ${CFLAGS} -c FileReader.cpp

FileWriter.o: FileWriter.cpp FileWriter.h
	${CC} ${CFLAGS} -c FileWriter.cpp

Input.o: Input.cpp Input.h
	${CC} ${CFLAGS} -c Input.cpp

PlaceholderLogic.o: PlaceholderLogic.cpp PlaceholderLogic.h
	${CC} ${CFLAGS} -c PlaceholderLogic.cpp

clean:
	rm -f *.o *.out main
