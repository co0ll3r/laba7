#include "CompHeader.h"

void swap(workComputers& first, workComputers& second){
	std::swap(first.size, second.size);
	std::swap(first.CapabilitiesComp, second.CapabilitiesComp);
}
workComputers::workComputers(const workComputers& copy) : workComputers(copy.size){
	std::copy(copy.CapabilitiesComp, copy.CapabilitiesComp + size, CapabilitiesComp);
}

workComputers& workComputers::operator=(workComputers copy){
	if(&copy != this)
		swap(*this, copy);
	return *this;
}

void workComputers::testCopyOperator(){
	workComputers eg;
	if (true){
		workComputers eg2;
		eg2 = *this;
		std::cout << "второй объект присваиваем первому:\n";
		eg = eg2;
		std::cout << "вывод второго объекта:\n";
		std::cout << eg2;
	}
	std::cout << "очистка второго объекта\n";
	eg = *this;
	std::cout << "вывод первого объекта:\n";
	std::cout << eg;
}

void workComputers::testCopyConstructor(){
	workComputers eg(*this);
	std::cout << "вывод первого объекта:\n";
	std::cout << eg;
	std::cout << "создаем второй объект, с помощью конструтора:\n";
	workComputers eg2(eg);
	std::cout << "вывод второго объекта:\n";
	std::cout << eg2;
}

void workComputers::Add_comp(){
	RECORD* CopyCapComp = new RECORD[size + 1];
	if (CopyCapComp == nullptr)
	{
	    throw MemoryErr("Ошибка выделения памяти при добавлении компьютера!\n");
	}
	for (int j = 0; j < size; j++)
		if (CapabilitiesComp != NULL)
			CopyCapComp[j] = CapabilitiesComp[j];
	std::cin >> CopyCapComp[size];
	if (CapabilitiesComp != NULL)
		delete [] CapabilitiesComp;
	CapabilitiesComp = CopyCapComp;
	size++;
	CopyCapComp = nullptr;
}

void workComputers::Delete_comp(){
	if (CapabilitiesComp == nullptr)
		throw EmptyMassive("удалении строки.");
	int n;
	char ch;
	std::cout << *this;
	std::cout << "Введите номер строки для удаления: ";
	std::cin >> n;
	if (n < 1 || n > size)
		throw InputErr(" Нет такой строки\n");
	n--;
	std::cout << " удалить строку(press y):";
	std::cin >> ch;
	if (ch == 'y' || ch == 'Y'){
		RECORD* CopyCapComp = new RECORD[size - 1];
		int i, j;
		for (i = 0; i < n; i++)
			CopyCapComp[i] = CapabilitiesComp[i];
		for (i = n + 1, j = n; i < size; i++, j++)
			CopyCapComp[j] = CapabilitiesComp[i];
		if (CapabilitiesComp != nullptr)
			delete [] CapabilitiesComp;
		CapabilitiesComp = CopyCapComp;
		CopyCapComp = nullptr;
		size--;
	}
}

void workComputers::swapElementsInMassive(unsigned index){
	RECORD temp = CapabilitiesComp[index];
	CapabilitiesComp[index] = CapabilitiesComp[index - 1];
	CapabilitiesComp[index - 1] = temp;
}

void workComputers::SortProcTypeAndClock(){
	std::cout << "Сортировка по типу процессора и частоте: \n";
	unsigned n = size; 
	bool flag;
	do{
		flag = false;
		for (unsigned i = 1; i < n; i++)
		{
			if (CapabilitiesComp[i].CompInfo < CapabilitiesComp[i - 1].CompInfo){
				swapElementsInMassive(i);
				flag = true;
			}
		}
		n--;
	} while (flag);
	std::cout << *this;
}

void workComputers::SortProcName(){
	std::cout << "Сортировка по названию процессора: \n";
	unsigned n = size; 
	bool flag;
	do{
		flag = false;
		for (unsigned i = 1; i < n; i++)
		{
			if (CapabilitiesComp[i] < CapabilitiesComp[i - 1])
			{
				swapElementsInMassive(i);
				flag = true;
			}
		}
		n--;
	} while (flag);
	std::cout << *this;
}

void workComputers::SortPrice(){
	std::cout << "Сортировка по цене: \n";
	bool flag;
	unsigned n = size; 
	do{
		flag = false;
		for (unsigned i = 1; i < n; i++)
		{
			if (CapabilitiesComp[i - 1].CompCost > CapabilitiesComp[i].CompCost){
				swapElementsInMassive(i);
				flag = true;
			}
		}
		n--;
	} while(flag);
	std::cout << *this;
}
