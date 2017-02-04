#include "FileReader.h"
#include "FileWriter.h"
#include "Input.h"
#include "PlaceholderLogic.h"
#include <string>
#include <map>

int main(){
	FileReader fileReader;
	PlaceholderLogic pLogic;
	Input input;
	FileWriter fileWriter;

	std::string toAddressFile = fileReader.readFile("toAddress.txt");
	std::string bodyFile = fileReader.readFile("body.txt");
	std::string signatureFile = fileReader.readFile("signature.txt");
	std::string totalMessage = toAddressFile + bodyFile + signatureFile;
	
	std::cout<< totalMessage << std::endl;
	
	std::vector<std::string> placeholders;
	placeholders = pLogic.markPlaceholders(totalMessage);
	
	std::map<std::string, std::string> inputResults = input.retrieveInput(placeholders);
	
	std::string finalMessage = pLogic.implementPlaceholder(inputResults, totalMessage);
	
	std::cout << finalMessage << std::endl;
	std::cout << "MESSAGE ALSO SAVED IN offer.txt" << std::endl;
	
	fileWriter.writeFile("offer.txt", finalMessage);
	
	//system("PAUSE");
}
