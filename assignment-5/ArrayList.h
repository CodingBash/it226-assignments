#ifndef GUARD_ARRAYLIST_H
#define GUARD_ARRAYLIST_H

#include <cstddef>
#include <string>

template <class T>
class ArrayList {
public:
	int trueSize;
	int usedSize;
	T* dataArray;
	ArrayList();
	T& operator[](unsigned int index);
	void push_back(T m);
	void erase(T m);
	int size();
	std::string toString();
	~ArrayList();
private:
	T& get(unsigned int index);
	void copy(T* destination, T*src, int size);
};



#endif
