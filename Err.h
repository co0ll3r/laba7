#include "CompHeader.h"

/**
 * Abstract or pure class that has only virtual methods
 */
class Err {
protected:
    std::string message;
public:
    Err(std::string inp) : message(inp) {}

    virtual void outputErr() = 0;
};

class InputErr : Err {
public:
    InputErr(std::string inp) : Err(inp) {}

    void outputErr() {
        std::cerr << "Возникла ошибка ввода! " << message << std::endl;
    }

    void inputAgainMessage(int i) {
        switch (i) {
            case 5:
                break;
            case 15:
                break;
            case 20:
                break;
            case 100:
                for (int i = 0; i < 1e5; i++) std::cout << "Твоя взяла!";
                break;
            default:
                std::cerr << "Введите снова номер пункта (номер - это целое число!)\n";
                break;

        }

    }
};

class MemoryErr : Err {
public:
    MemoryErr(std::string inp) : Err(inp) {}

    void outputErr() {
        std::cerr << "Возникла ошибка выделения памяти! " << message << std::endl;
    }
};

class OpenFileErr : Err {
private:
    bool actionOpen;
public:
    OpenFileErr(std::string inp, bool a) : Err(inp), actionOpen(a) {}

    void outputErr() {
        std::cerr << "Возникла ошибка при " << (actionOpen ? "открытии " : "закрытии ") << "файла! " << message
                  << std::endl;
    }
};

class EmptyMassive : Err {
public:
    EmptyMassive(std::string err) : Err(err) {
    }

    void outputErr(){
        std::cerr << "Возникла ошибка при " << message << " Загрузите массив, т.к. он пуст.";
    }
};
