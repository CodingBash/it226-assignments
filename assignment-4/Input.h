#ifndef GUARD_INPUT_H
#define GUARD_INPUT_H
#include <iostream>
#include <map>
#include <vector>
#include <string>

struct Input{
public:
	std::map<std::string, std::string> retrieveInput(std::vector<std::string> &replacementList);
};
#endif 
