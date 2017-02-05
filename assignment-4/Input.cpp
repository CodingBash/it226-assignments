#include "Input.h"
#include <iostream>

std::map<std::string, std::string> Input::retrieveInput(std::vector<std::string> &replacementList) {
	std::map<std::string, std::string> placeholderMap;
	for (const std::string &element : replacementList) {
		std::string entry;
		std::cout << "Placeholder for " << element << ": ";
		std::getline(std::cin, entry);
		std::cout << "You entered " << entry << std::endl;
		placeholderMap.insert(std::pair<std::string, std::string>(element, entry));
	}
	return placeholderMap;
}
