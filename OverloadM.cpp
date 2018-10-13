#include "CompHeader.h"
//Перегрузки для структур RECORD & COMP
//ввод из консоли
std::istream& operator>>(std::istream& in, COMP& a){
	std::cout << "Введите марку процессора: ";
	in >> a.ProcName;
	std::cout << "Введите тип процессора(хар-ки): ";
	in >> a.ProcType; 
	std::cout << "Введите частоту процессора: ";
	in >> a.ClockSpeed; 
	std::cout << "Введите название видеокарты: ";
	in >> a.Graphics;
	std::cout << "Введите объем видеокарты: ";
	in >> a.GraphicVolume;
	std::cout << "Введите объем ОЗУ: ";
	in >> a.RAM; 
	std::cout << "Введите объем жесткого диска: "; 
	in >> a.Storage; 
	return in;
}
std::istream& operator>>(std::istream& in, RECORD& a){
	std::cin >> a.CompInfo;
	std::cout << "Введите цену: ";
	in >> a.CompCost;
	std::cout << "Введите количество компьютеров на складе: ";
	in >> a.CompInStock;
	return in;
}

//ввод из файла
std::ifstream& operator>>(std::ifstream& in, COMP& a){
	in >> a.ProcName >> a.ProcType  >> a.ClockSpeed >> a.Graphics >> a.GraphicVolume >> a.RAM >> a.Storage;
	return in;
}
std::ifstream& operator>>(std::ifstream& in, RECORD& a){
	in >> a.CompCost >> a.CompInStock;
	in >> a.CompInfo;
	return in;
}

//вывод в консоль

std::ostream& operator<<(std::ostream& out, COMP a){
	out << std::setw(16) << a.ProcName << "|" << std::setw(19) <<
   		a.ProcType << "|" << std::setw(9) << a.ClockSpeed <<
	       	"|" << std::setw(24) << a.Graphics << "|" <<
	       	std::setw(10) << a.GraphicVolume << "|" << 
		std::setw(5) <<	a.RAM << "|" << std::setw(17) << 
		a.Storage << "|\n";
	return out;
}
std::ostream& operator<<(std::ostream& out, RECORD a){
	out << std::setw(6) <<a.CompCost << "|" << std::setw(6) << a.CompInStock<< "|" << a.CompInfo;
	return out;
}
//вывод в файл
std::ofstream& operator<<(std::ofstream& out, COMP a){
	out << std::setw(16) << a.ProcName << "|" << std::setw(19) << a.ProcType << "|" << std::setw(9) << a.ClockSpeed << "|" << std::setw(24) << a.Graphics << "|" << std::setw(10) << a.GraphicVolume << "|" << std::setw(5) << a.RAM << "|" << std::setw(17) << a.Storage << "|\n";
	return out;
}

std::ofstream& operator<<(std::ofstream& out, RECORD a){
	out << std::setw(6) <<a.CompCost << "|" << std::setw(6) << a.CompInStock << "|"; 
	out << a.CompInfo;
	return out;
}

// ПЕРЕГРУЗКИ операторов сравнения
bool operator<(COMP& a, COMP& b){
	if (a.ProcType< b.ProcType)
		return true;
	else if (a.ProcType == b.ProcType && a.ClockSpeed < b.ClockSpeed)
		return true;
	return false;
}

bool operator==(COMP& a, COMP& b){
	return a.ProcName == b.ProcName;
}

bool operator<(RECORD& a, RECORD& b){
	if (a.CompInfo.ProcName < b.CompInfo.ProcName)
		return true;
	else if (a.CompInfo.ProcName == b.CompInfo.ProcName &&
			a.CompInfo.ProcType < b.CompInfo.ProcType)
		return true;
	return false;
}

std::istream& operator>>(std::istream& in, workComputers& a){
	RECORD* CopyCapComp = new RECORD[a.size + 1];
	if (CopyCapComp == NULL)
	{
		std::cout << "Ошибка выделения памяти при добавлении компьютера!\n";
		return in;//exit(-1);
	}
	for (int j = 0; j < a.size; j++)
		if (a.CapabilitiesComp != NULL)
			CopyCapComp[j] = a.CapabilitiesComp[j];
		in >> CopyCapComp[a.size];
	if (a.CapabilitiesComp != NULL)
		delete [] a.CapabilitiesComp;
	a.CapabilitiesComp = CopyCapComp;
	a.size++;
	CopyCapComp = NULL;
	return in;
}

std::ostream& operator<<(std::ostream& out, workComputers a){
	std::cout << std::setfill('-') << std::setw(129) << "\n" <<
	     "|Номер| Цена | Кол. |                 Процессор                    |              Видеокарта           | ОЗУ | Размер жесткого |\n" <<
             "|     |      |      |----------------------------------------------|-----------------------------------|     |                 |\n" << 
	     "|     |(Руб.)|(штук)|    Название    |        Тип        | Частота |        Название        | Объём Гб |  Гб |      (Гб)       |\n" <<
	             std::setw(129) << "\n"; 
	for (int i = 0; i < a.size; i++){
		std::cout << std::setfill(' ') <<
		       	"|" << std::setw(5) << i + 1 << "|" 
			<< a.CapabilitiesComp[i];
			std::cout << std::setfill('-') << std::setw(129) << "\n"; 
	}
	return out;
}
std::ifstream& operator>>(std::ifstream& in, workComputers& a){
	std::string file;
	std::cout << "Введите имя файла для чтения\n";
	std::cin >> file;
	in.open(file);
	if (in.fail()){
		std::cout << file << " не удалось создать файл\n";
		return in;
	}
	RECORD ab;	
	RECORD* CopyCapComp;
	int i = -1;
	char end = '1';
	while(end != '\0'){
		if (in.fail()) 
			break;
		i++;
		in >> ab >> end;
		CopyCapComp = new RECORD[i + 1];
		for (int j = 0; j < i; j++)
			if (a.CapabilitiesComp != NULL)
				CopyCapComp[j] = a.CapabilitiesComp[j];
		CopyCapComp[i] = ab;
		if (a.CapabilitiesComp != NULL)
			delete [] a.CapabilitiesComp;
		a.CapabilitiesComp = CopyCapComp;
		CopyCapComp = NULL;
	}
	a.size = ++i;
	return in;
}
std::ofstream& operator<<(std::ofstream& out, workComputers a){
	std::string file;
	std::cout << "Введите имя файла для сохранения\n";
	std::cin >> file;
	out.open(file);
	if (out.fail()){
		std::cout << file << " не удалось открыть файл\n";
		return out;
	}
	out << std::setfill('-') << std::setw(129) << "\n" <<
	     "|Номер| Цена | Кол. |                 Процессор                    |              Видеокарта           | ОЗУ | Размер жесткого |\n" <<
             "|     |      |      |----------------------------------------------|-----------------------------------|     |                 |\n" << 
	     "|     |(Руб.)|(штук)|    Название    |        Тип        | Частота |        Название        | Объём Гб |  Гб |      (Гб)       |\n" <<
	             std::setw(129) << "\n"; 
	for (int i = 0; i < a.size; i++){
		out << std::setfill(' ') << "|" << std::setw(5) << i + 1 << "|";
 		out << a.CapabilitiesComp[i]; // перегруженный вывод в файл
		out << std::setfill('-') << std::setw(129) << "\n"; 
	}
	return out;
}
// Перегрузка ПОИСКА
std::ostream& operator<<(std::ostream& out, SearchComp a){
	std::cout << std::setfill('-') << std::setw(129) << "\n" <<
	     "|Номер| Цена | Кол. |                 Процессор                    |              Видеокарта           | ОЗУ | Размер жесткого |\n" <<
             "|     |      |      |----------------------------------------------|-----------------------------------|     |                 |\n" << 
	     "|     |(Руб.)|(штук)|    Название    |        Тип        | Частота |        Название        | Объём Гб |  Гб |      (Гб)       |\n" <<
	             std::setw(129) << "\n"; 
	for (int i = 0; i < a.size; i++){
		std::cout << std::setfill(' ') << "|" << std::setw(5)
		       	<< i + 1 << "|" << a.SearchResult[i];
		std::cout << std::setfill('-') << std::setw(129) << "\n"; 
	}

	return out;
}
std::ofstream& operator<<(std::ofstream& out, SearchComp a){
	std::string file;
	std::cout << "Введите имя файла для сохранения\n";
	std::cin >> file;
	out.open(file);
	if (out.fail()){
		std::cout << file << " не удалось открыть файл\n";
		return out;
	}
	out << std::setfill('-') << std::setw(129) << "\n" <<
	     "|Номер| Цена | Кол. |                 Процессор                    |              Видеокарта           | ОЗУ | Размер жесткого |\n" <<
             "|     |      |      |----------------------------------------------|-----------------------------------|     |                 |\n" << 
	     "|     |(Руб.)|(штук)|    Название    |        Тип        | Частота |        Название        | Объём Гб |  Гб |      (Гб)       |\n" <<
	             std::setw(129) << "\n"; 
	for (int i = 0; i < a.size; i++){
		out << std::setfill(' ') <<
		   "|" << std::setw(5) << i + 1 << "|";
	        out << a.SearchResult[i];
		out << std::setfill('-') << std::setw(129) << "\n"; 
	}
	return out;
}
