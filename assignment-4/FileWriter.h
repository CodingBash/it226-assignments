#ifndef GUARD_FILEWRITER_H
#define GUARD_FILEWRITER_H
#include <iostream>
#include <string>

struct FileWriter{
public:
	bool writeFile(const std::string &fileName, const std::string &content);
};
#endif
