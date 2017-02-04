#include "ArrayList.h"
#include <iterator>
#include <stdexcept>
#include <string>
#include <sstream>

template<class T>
ArrayList<T>::ArrayList(){
	trueSize = 1;
	usedSize = 0;
	dataArray = new T[trueSize];
	dataArray[0] = 0;
}

template <class T>
T& ArrayList<T>::operator[](unsigned int index){
		return get(index);
}

template <class T>
T& ArrayList<T>::get(unsigned int index) {
	if(index >= usedSize){
		throw std::out_of_range(index + " out of range of 0 to " + usedSize);
	}
	return *(dataArray + index);
}

template <class T>
void ArrayList<T>::copy(T* destination, T* src, int size){
	for(int i = 0; i < size; i++){
		*(destination+i) = *(src + i);
	} 
	delete[] src;
}

template <class T>
void ArrayList<T>::push_back(T m){
	if(usedSize < trueSize){
		*(dataArray + usedSize) = m;
		++usedSize;
	} else {
		int tempSize = trueSize;
		T* temp = dataArray;
		trueSize *= 2;
		dataArray = new T[trueSize];
		copy(dataArray, temp , tempSize);
		push_back(m);
	}
}

template <class T>
void ArrayList<T>::erase(T m){
	int eraseIndex = -1;
	for(int i = 0; i < usedSize; i++){
		if(*(dataArray + i) == m){
			eraseIndex = i;  
		}
	}
	if(eraseIndex != -1){
		for(int currIndex = eraseIndex; currIndex < usedSize - 1; currIndex++){
			dataArray[currIndex] = dataArray[currIndex+1];
		}
		--usedSize;
	}
}

template <class T>
int ArrayList<T>::size(){
	return usedSize;
}

template <class T>
std::string ArrayList<T>::toString(){
	std::string toString = "[";
	bool notFirstItem = false;
	for(int i = 0; i < usedSize; i++){
		if(notFirstItem){
			toString += ",";
		}
		T number = get(i);

		std::string str;          //The string
		std::ostringstream temp;  //temp as in temporary
		temp<<number;
		str=temp.str();
  
		toString += str;
		notFirstItem = true;
	}
	toString += "]";
	return toString;
}

template <class T>
ArrayList<T>::~ArrayList(){
	delete[] dataArray;
}
