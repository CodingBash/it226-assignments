#include "FileReader.h"
#include <iostream>
#include <fstream>

std::string FileReader::readFile(const std::string filename){
	std::string content;
	std::ifstream file(filename.c_str());
	if(file.is_open()){
		std::string line;
		while(std::getline(file, line))
		{
			content += line + '\n';
		}
		file.close();
	} else {
		std::cerr << "Unable to open file" << std::endl;
	}
	return content;
}
