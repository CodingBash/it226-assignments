#include "PlaceholderLogic.h"

bool checkUnique(std::vector<std::string> &placeHolderList, std::string word);

std::vector<std::string> PlaceholderLogic::markPlaceholders(std::string &content){
	std::vector<std::string> placeHolderList;
	std::vector<std::string> strWords;
	std::string current;
	for(short i = 0;i < content.length();i++){
		if((content[i] >=0 && content[i] <= 255) && !isalnum(content[i]) && !current.empty()){
			strWords.push_back(current);
			current.clear();
		}
		else if ((content[i] >=0 && content[i] <= 255) && !isspace(content[i])) {
			current += content[i];
		}
	}
	for(std::string &word : strWords){
		if(word[0] == '_' && checkUnique(placeHolderList, word)){
			placeHolderList.push_back(word);
		}
	}


	return placeHolderList;
}

bool checkUnique(std::vector<std::string> &placeHolderList, std::string word){
	for(std::string &placeholder : placeHolderList){
		if(placeholder == word){
			return false;
		}
	}
	return true;
}

std::string PlaceholderLogic::implementPlaceholder(std::map<std::string, std::string> &placeholders, std::string &content){
	typedef std::map<std::string, std::string>::iterator it_type;
	for(it_type iterator = placeholders.begin(); iterator != placeholders.end(); iterator++)
	{
		 for(std::string::size_type i = 0; (i = content.find(iterator->first, i)) != std::string::npos;)
    {
        content.replace(i, iterator->first.length(), iterator->second);
        i += iterator->second.length();
    }
	}

    return content;
}
