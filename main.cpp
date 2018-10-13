#include "CompHeader.h"

void showMenu(){
	std::cout << "1. Загрузка файла\n2. Сохранение результатов обработки в файл\n" <<
		"3. Добавление записи\n4. Удаление записи\n" <<
		"5. Вывод на экран\n6. Сортировка по типу процессора и тактовой частоте\n" <<
		"7. Алфавитная сортировка по названию марки\n8. Числовая сортировка по цене\n" <<
		"9. Показать 1 перечень имен процессора\n10. Показать 2 перечень типов процессора\n" <<
		"11. Показать 3 перечень объемов видеокарты\n12. Загрузить первый перечень\n" <<
		"13. Загрузить второй перечень\n14. Загрузить третий перечень\n" <<
		"15. Сортировать перечень 1\n16. Сортировать перечень 2\n" <<
		"17. Сортировать перечень 3\n18. Сохранить перечни\n" <<
		"19. Поиск по цене\n20. Поиск по объему жесткого диска\n" <<
		"21. Поиск по названию марки, типу процессора ит.д.\n22. Сортировка результатов поиска\n" <<
		"23. Сохранение результатов поиска\n24. Тестирование конструктора копирования для 1 класса\n"<< 
		"25. Тестирование оператора присваивания для 1 класса\n26. Тестирование конструктора копирования для 2 класса\n" << 
		"27. Тестирование оператора присваивания для 2 класса\n28. Тестирование конструктора копирования для 3 класса\n" << 
		"29. Тестирование оператора присваивания для 3 класса\n30. Вывод результатов поиска\n31. Вывод перечней\n";
}

void fixSortProcTypeAndClock(workComputers a){
	a.SortProcTypeAndClock();
}

void GetMenu(Perechen& ExampleComputer, bool& exitFlag){
	workComputers& workComputer = ExampleComputer;
	SearchComp& searchComputer = ExampleComputer;
	workComputer = searchComputer;
	int SwitchChoose = 1;
	std::ifstream fin;
	std::ofstream fout;
	std::cout << "\033c"; // clear console 
//	std::cout << &ExampleComputer << "\n" << &workComputer << "\n" << &searchComputer << "\n";
	showMenu();
	while (SwitchChoose == 1){
		std::cout << "Введите пункт меню: ";
		try{
			std::cin >> SwitchChoose;
			if (std::cin.fail())
				throw Err("Обнаружена ошибка ввода! Возможно, вы ввели строковый символ.\n");
		}
		// Handle the error, that occurs when we input a char symbol
		catch (Err &e){
			int i = 0;
			while(std::cin.fail()){
				e.outputErr();
				std::cin.clear();
				std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n'); // cin.ignore() - очистить один символ из входного буфера, <streamsize>::max - говорит о том, что очищаем весь поток
				e.inputAgainMessage(++i);
				std::cin >> SwitchChoose;
			}
			std::cout << "\033c"; // clear console 
			showMenu();
		}
		std::cout << "\n";
		switch(SwitchChoose){
			case 1:  fin >> workComputer; break; //перегруженный ввод таблицы из файла
			case 2:  fout << workComputer; break; //перегруженный вывод таблицы в файл
			case 3:  std::cin >> workComputer; break; // перегруженное ввод записи в таблицу
			case 4:  workComputer.Delete_comp(); break;
//			case 5:  std::cout << (workComputers)ExampleComputer; break; // перегруженный вывод таблицы на экран
			case 5:  std::cout << workComputer; break;			
//			case 6:  ExampleComputer.workComputers::SortProcTypeAndClock(); break;
			case 6:  fixSortProcTypeAndClock(workComputer); break;
	//			 workComputer.SortProcTypeAndClock()
			case 7:  workComputer.SortProcName(); break; 	
			case 8:	 workComputer.SortPrice(); break;
			case 9:  ExampleComputer.showFirstPerech(); break;
			case 10: ExampleComputer.showSecondPerech(); break; 
			case 11: ExampleComputer.showThirdPerech();break; 
			case 12: makePerechen1(ExampleComputer, ExampleComputer); break; 
			case 13: makePerechen2(ExampleComputer, ExampleComputer);break; 
			case 14: makePerechen3(ExampleComputer, ExampleComputer);break; 
			case 15: ExampleComputer.sortProcTypeFirstPerech(); break;
			case 16: ExampleComputer.sortCountSecondPerech();break; 
			case 17: ExampleComputer.sortVideoVolumeThirdPerech(); break; 
			case 18: {
					 std::cout << "1. Сохранить 1\n 2. Сохранить 2\n3. Сохранить 3\n";
					 int j;
					 std::cin >> j;
					 switch(j){
						 case 1: ExampleComputer.saveFirstPerech(); break;
						 case 2: ExampleComputer.saveSecondPerech(); break;
						 case 3: ExampleComputer.saveThirdPerech(); break;
						 default: break;
					 }
					 break;
				 }
			case 19: searchComputer.SearchPrice(); break;
			case 20: searchComputer.SearchHddVolume(); break;
			case 21: searchComputer.SearchBrandTypeRamETC(); break;
			case 22:{
					std::cout << "1.Сорт по Типу процессора\n2. Сорт по объему ОЗУ\n";
					int c;
					std::cin >> c;
					switch(c){
						case 1: searchComputer.SortProcTypeAndClock(); break;
						case 2: searchComputer.SortRAM(); break;
					}
				}; break;
			case 23: fout << searchComputer; break; // перегруженный вывод результатов поиска в файл
			case 24: workComputer.workComputers::testCopyConstructor(); break;
			case 25: workComputer.workComputers::testCopyOperator(); break;
			case 26: ExampleComputer.SearchComp::testCopyConstructor(); break;
			case 27: ExampleComputer.SearchComp::testCopyOperator(); break;
			case 28: ExampleComputer.Perechen::testCopyConstructor(); break;
			case 29: ExampleComputer.Perechen::testCopyOperator(); break;
			case 30: std::cout << (SearchComp) ExampleComputer; break; // перегруженный вывод результатов поиска
			case 31: std::cout << ExampleComputer; break; // перегруженный вывод всех перечней
			default: exitFlag =false; break;
		}
		std::cout << "\nВведите 1 для повторного выбора пункта меню(без очистки экрана): ";
		std::cin >> SwitchChoose;  
	}
}

int main()
{
	Perechen go;
	bool flag = true;
	while(flag)
		GetMenu(go, flag);
	std::cout << "check\n";
	return 0;
}
