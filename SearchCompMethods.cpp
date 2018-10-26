#include "CompHeader.h"

void swap(SearchComp& first, SearchComp& second){
	std::swap(first.size, second.size);
	std::swap(first.SearchResult, second.SearchResult);
}

SearchComp::SearchComp(const SearchComp& copy) : SearchComp(copy.size){
	std::copy(copy.SearchResult, copy.SearchResult + size, SearchResult);
}

SearchComp& SearchComp::operator=(SearchComp copy){
	if(&copy != this)
		swap(*this, copy);
	return *this;
}
void SearchComp::testCopyOperator(){
	SearchComp eg2;
	if (true){
		SearchComp eg;
		eg = *this;
		std::cout << "вывод первого объекта:\n";
		std::cout << eg;
		std::cout << "копирование объекта через оператор присваивания\n";
		eg2 = eg;
	}
	std::cout << "Очистка первого объекта.\nвывод второго объекта:\n";
	std::cout << eg2;
}
void SearchComp::testCopyConstructor(){
	SearchComp eg(*this);
	std::cout << "вывод первого объекта:\n";
	std::cout << eg;
	std::cout << "Копирование первого объекта через конструктор:\n";
	SearchComp eg2(eg);
	std::cout << "вывод второго объекта:\n";
	std::cout << eg2;
}
void SearchComp::OutputInFile(){
	std::string file;
	std::cout << "Введите имя файла для сохранения\n";
	std::cin >> file;
	std::ofstream fout;
	fout.open(file);
	if (fout.fail())
		throw OpenFileErr("Не удалось сохранить файл под именем: " + file, false);

	fout << std::setfill('-') << std::setw(129) << "\n" <<
	     "|Номер| Цена | Кол. |                 Процессор                    |              Видеокарта           | ОЗУ | Размер жесткого |\n" <<
             "|     |      |      |----------------------------------------------|-----------------------------------|     |                 |\n" << 
	     "|     |(Руб.)|(штук)|    Название    |        Тип        | Частота |        Название        | Объём Гб |  Гб |      (Гб)       |\n" <<
	             std::setw(129) << "\n"; 
	for (int i = 0; i < size; i++){
		fout << std::setfill(' ') <<
		   "|" << std::setw(5) << i + 1 << "|";
	        fout << SearchResult[i];
		fout << std::setfill('-') << std::setw(129) << "\n"; 
	}
}

void SearchComp::swapElementsInSearch(unsigned index){
	RECORD temp = SearchResult[index];
	SearchResult[index] = SearchResult[index - 1];
	SearchResult[index - 1] = temp;
}
void SearchComp::SortProcTypeAndClock(){
	std::cout << "Сортировка по типу процессора и частоте: \n";
	unsigned n = size;
	bool flag;
	do{
		flag = false;
		for (unsigned i = 1; i < n; i++)
		{
			if (SearchResult[i].CompInfo < SearchResult[i - 1].CompInfo)
			{
				swapElementsInSearch(i);
				flag = true;
			}
		}
		n--;
	} while (flag);
	std::cout << *this;
}

void SearchComp::SortRAM(){
	std::cout << "Сортировка по ОЗУ: \n";
	bool flag;
	unsigned n = size;
	do{
		flag = false;
		for (unsigned i = 1; i < n; i++)
		{
			if (SearchResult[i - 1].CompInfo.RAM> SearchResult[i].CompInfo.RAM){
				swapElementsInSearch(i);
				flag = true;
			}
		}
		n--;
	} while(flag);
	std::cout << *this;
}

void SearchComp::SearchPrice(){
	double BottomBorder, TopBorder;
	std::cout << "Введите нижнюю границу цены(нестрогое): ";
	std::cin >> BottomBorder;
	std::cout << "Введите верхнюю границу цены(нестрогое): ";
	std::cin >> TopBorder;
	std::vector<int> CollectIndexes; // Второй вариант со сбором индеков
	for (int i = 0; i < workComputers::size; i++)
	{
		if (BottomBorder <= CapabilitiesComp[i].CompCost && CapabilitiesComp[i].CompCost <= TopBorder)
		{
			CollectIndexes.push_back(i);
		}	
	}
	// Есть два варианта: 1) вектор собирающий индексы; 2) перераспределение памяти каждыый раз

	if (CollectIndexes.empty())
	{
		std::cout << "Не найдено!\n";
		return;
	}
	if (SearchResult != nullptr)
		delete [] SearchResult;
	size = CollectIndexes.size();
	std::cout << size<< " size\n";
	SearchResult= new RECORD[size];
	for (int i = 0; i < size; i++)
		SearchResult[i] = CapabilitiesComp[CollectIndexes[i]];
	SearchComp::SortProcTypeAndClock();
	char ch;
	std::cout << "Желаете сохранить результаты поиска в файл?(y/n)\n";
	std::cin >> ch;
	if (ch == 'y' || ch == 'Y')
		SearchComp::OutputInFile();
}


void SearchComp::SearchHddVolume(){
	if (CapabilitiesComp == nullptr)
		throw EmptyMassive("поиске по объему жесткого диска.")
	double BottomBorder, TopBorder;
	std::cout << "Введите нижнюю границу размера памяти(нестрогое): ";
	std::cin >> BottomBorder;
	std::cout << "Введите верхнюю границу размера памяти(нестрогое): ";
	std::cin >> TopBorder;
	std::vector<int> CollectIndexes; // Второй вариант со сбором индеков
	for (int i = 0; i < workComputers::size; i++)
	{
		if (BottomBorder <= CapabilitiesComp[i].CompInfo.Storage && CapabilitiesComp[i].CompInfo.Storage <= TopBorder)
			CollectIndexes.push_back(i);
	}
	if (CollectIndexes.size() == 0)
	{
		std::cout << "Не найдено!\n";
		return;
	}
	if (SearchResult != NULL)
		delete [] SearchResult;
	size = CollectIndexes.size();
	std::cout << size<< " size\n";
	SearchResult= new RECORD[size];

	for (int i = 0; i < size; i++)
		SearchResult[i] = CapabilitiesComp[CollectIndexes[i]];
	SearchComp::SortRAM();
	char ch;
	std::cout << "Желаете сохранить результаты поиска в файл?(y/n)\n";
	std::cin >> ch;
	if (ch == 'y' || ch == 'Y')
		SearchComp::OutputInFile();
}

void SearchComp::SearchBrandTypeRamETC(){
	if (CapabilitiesComp == nullptr)
		throw EmptyMassive("поиске по всем полям.")
	std::string NameOfProc, TypeOfProc;
	double BottomBorder, TopBorder, BBRAM, TBRAM, BBVideo, TBVideo;
	std::cout << "Введите название марки процессора ";
	std::cin >> NameOfProc;
	std::cout << "Введите тип процессора ";
	std::cin >> TypeOfProc;
	std::cout << "Введите нижнюю границу размера памяти hdd(нестрогое): ";
	std::cin >> BottomBorder;
	std::cout << "Введите верхнюю границу размера памяти hdd(нестрогое): ";
	std::cin >> TopBorder;
	std::cout << "Введите нижнюю границу размера ОЗУ(нестрогое): ";
	std::cin >> BBRAM;
	std::cout << "Введите верхнюю границу размера ОЗУ(нестрогое): ";
	std::cin >> TBRAM;
	std::cout << "Введите нижнюю границу размера видеопамяти(нестрогое): ";
	std::cin >> BBVideo;
	std::cout << "Введите верхнюю границу размера видеопамяти(нестрогое): ";
	std::cin >> TBVideo;
	std::vector<int> CollectIndexes; // Второй вариант со сбором индеков
	for (int i = 0; i < workComputers::size; i++)
	{
		if (BottomBorder <= CapabilitiesComp[i].CompInfo.Storage && CapabilitiesComp[i].CompInfo.Storage <= TopBorder && 
			NameOfProc == CapabilitiesComp[i].CompInfo.ProcName && CapabilitiesComp[i].CompInfo.ProcType == TypeOfProc &&
		       	BBRAM <= CapabilitiesComp[i].CompInfo.RAM && CapabilitiesComp[i].CompInfo.RAM <= TBRAM &&
		      	BBVideo<= CapabilitiesComp[i].CompInfo.GraphicVolume && CapabilitiesComp[i].CompInfo.GraphicVolume <= TBVideo)
				CollectIndexes.push_back(i);
	}
	if (CollectIndexes.size() == 0)
	{
		std::cout << "Не найдено!\n";
		return;
	}
	if (SearchResult != NULL)
		delete [] SearchResult;
	size = CollectIndexes.size();
	std::cout << size<< " size\n";
	SearchResult= new RECORD[size];

	for (int i = 0; i < size; i++)
		SearchResult[i] = CapabilitiesComp[CollectIndexes[i]];
	std::cout << *this;
	char ch;
	std::cout << "Желаете сохранить результаты поиска в файл?(y/n)\n";
	std::cin >> ch;
	if (ch == 'y' || ch == 'Y')
		SearchComp::OutputInFile();
}
