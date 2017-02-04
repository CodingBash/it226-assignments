#ifndef GUARD_FILEREADER_H
#define GUARD_FILEREADER_H
#include <iostream>
#include <string>

struct FileReader{
public:
	std::string readFile(const std::string fileName);
};
#endif
