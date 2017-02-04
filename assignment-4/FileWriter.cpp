#include "FileWriter.h"
#include <iostream>
#include <fstream>

bool FileWriter::writeFile(const std::string &fileName, const std::string &content){
	std::ofstream file (fileName.c_str());
	if (file.is_open())
	{
		file << content;
		file.close();
		return true;
	}
	return false;
}

