#include "CompHeader.h"

class Err{
	protected:
	std::string message;
	public:
//	Err() : message(NULL) {} 
	Err(std::string inp) : message(inp) {}
//	virtual void meth() = 0;
	virtual void outputErr() = 0;
	/*{
		std::cout << message << std::endl;
	}*/
};

class InputErr : Err{
//	protected:
//		std::string message;
	public:
		InputErr(std::string inp) : Err(inp) {} 
		
		void outputErr(){
			std::cout << "Возникла ошибка ввода! " << message << std::endl;
		}

		void inputAgainMessage(int i){
			switch(i){
				case 5: break;
				case 10: std::cout << R"(
##     ##  ######  ########    ##    ## ##     ## ##     ## ########  ######## ########   ######     ########  ##       ########    ###     ######  ######## #### #### #### 
##     ## ##    ## ##          ###   ## ##     ## ###   ### ##     ## ##       ##     ## ##    ##    ##     ## ##       ##         ## ##   ##    ## ##       #### #### #### 
##     ## ##       ##          ####  ## ##     ## #### #### ##     ## ##       ##     ## ##          ##     ## ##       ##        ##   ##  ##       ##       #### #### #### 
##     ##  ######  ######      ## ## ## ##     ## ## ### ## ########  ######   ########   ######     ########  ##       ######   ##     ##  ######  ######    ##   ##   ##  
##     ##       ## ##          ##  #### ##     ## ##     ## ##     ## ##       ##   ##         ##    ##        ##       ##       #########       ## ##                      
##     ## ##    ## ##          ##   ### ##     ## ##     ## ##     ## ##       ##    ##  ##    ##    ##        ##       ##       ##     ## ##    ## ##       #### #### #### 
 #######   ######  ########    ##    ##  #######  ##     ## ########  ######## ##     ##  ######     ##        ######## ######## ##     ##  ######  ######## #### #### #### 		
					 )" << "\n";break;
				case 15: break;
				case 20: break;
				case 100: for(int i = 0; i < 1e5; i++) std::cout << "Твоя взяла!"; break;
				default: std::cout << "Введите снова номер пункта (номер - это целое число!)\n"; break;
				
			}

		}
};

class MemoryErr : Err{
	public:
		MemoryErr(std::string inp) : Err(inp) {}
		void outputErr() {
			std::cout << "Возникла ошибка выделения памяти! " << message << std::endl;
		}
};

class OpenFileErr : Err{
	private: bool actionOpen;
	public:
		OpenFileErr(std::string inp, bool a) : Err(inp), actionOpen(a) {}
		void outputErr() {
			std::cout << "Возникла ошибка при " << (actionOpen? "открытии " : "закрытии ") << "файла! " << message << std::endl;
		}
};
