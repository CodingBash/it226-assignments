#ifndef GUARD_PLACEHOLDERLOGIC_H
#define GUARD_PLACEHOLDERLOGIC_H
#include <iostream>
#include <map>
#include <vector>
#include <string>

struct PlaceholderLogic {
public:
	std::vector<std::string> markPlaceholders(std::string &content);
	std::string implementPlaceholder(std::map<std::string, std::string> &placeholders, std::string &content);
};
#endif


