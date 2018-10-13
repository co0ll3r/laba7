#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <iomanip>
#include <set>
#include <algorithm>
#pragma once

#include "Err.h" // class for try\catch

class Perechen;
class workComputers;
class aggregate;

struct COMP{
	/*class fields */
	std::string ProcName; // Brand name
	std::string ProcType; // Specification
	std::string Graphics; // Graphic Card
	double GraphicVolume;
	double ClockSpeed;
	double RAM;
	double Storage;
	
	/* overloads I/0*/
	friend std::istream& operator>>(std::istream&, COMP&);
	friend std::ostream& operator<<(std::ostream&, COMP);
	friend std::ifstream& operator>>(std::ifstream&, COMP&);
	friend std::ofstream& operator<<(std::ofstream&, COMP);

	/* overloads relational operators */
	friend bool operator<(COMP& a, COMP& b);
	bool operator>(COMP& a){ return !(a < *this && a != *this); }
	friend bool operator==(COMP&, COMP&);
	bool operator!=(COMP& a) {return !(a == *this);}
};

struct RECORD{
	/*class fields */
	COMP CompInfo;
	double CompCost;
	int CompInStock;
	
	/* overloads*/
	friend std::istream& operator>>(std::istream&, RECORD&);
	friend std::ostream& operator<<(std::ostream&, RECORD);
	friend std::ifstream& operator>>(std::ifstream&, RECORD&);
	friend std::ofstream& operator<<(std::ofstream&, RECORD);

	/* overloads relational operators */
	friend bool operator<(RECORD& a, RECORD& b);
	bool operator>(RECORD& a){ return !(a < *this && a != *this); }
	friend bool operator==(RECORD&, RECORD&);
	bool operator!=(RECORD& a) {return !(a == *this);}
};

struct BrandPerech{
	/*class fields */
	std::string ProcName;
	int Count;

	/* overloads*/
	friend std::ostream& operator<<(std::ostream&, BrandPerech);
	friend std::ofstream& operator<<(std::ofstream&, BrandPerech);

	/* overloads relational operators */
	friend bool operator<(BrandPerech& a, BrandPerech& b);
	friend bool operator>(BrandPerech& a, BrandPerech& b){ return !(a < b && a != b); }
	friend bool operator==(BrandPerech& a, BrandPerech& b);
	friend bool operator!=(BrandPerech& a, BrandPerech& b) {return !(a==b);}
};

struct TypeProcPerech{
	/*class fields */
	std::string ProcType;
	int Count;

	/* overloads*/
	friend std::ostream& operator<<(std::ostream&, TypeProcPerech);
	friend std::ofstream& operator<<(std::ofstream&, TypeProcPerech);
	/* overloads relational operators */
	friend bool operator<(TypeProcPerech& a, TypeProcPerech& b);
	friend bool operator>(TypeProcPerech& a, TypeProcPerech& b){ return !(a < b && a != b); }
	friend bool operator==(TypeProcPerech& a, TypeProcPerech& b);
	friend bool operator!=(TypeProcPerech& a, TypeProcPerech& b) {return !(a==b);}
};

struct VideocardsPerech{
	/*class fields */
 	double GraphicVolume;
	int Count;

	/* overloads*/
	friend std::ostream& operator<<(std::ostream&, VideocardsPerech);
	friend std::ofstream& operator<<(std::ofstream&, VideocardsPerech);
	/* overloads relational operators */
	friend bool operator<(VideocardsPerech& a, VideocardsPerech& b);
	friend bool operator>(VideocardsPerech& a, VideocardsPerech& b){ return !(a < b && a != b); }
	friend bool operator==(VideocardsPerech& a, VideocardsPerech& b);
	friend bool operator!=(VideocardsPerech& a, VideocardsPerech& b) {return !(a==b);}
}; 

class workComputers {
	public:
		/*Constructors*/
		workComputers(int size = 0) : size(size), CapabilitiesComp(size ? new RECORD[size] : nullptr) {}
		~workComputers() { delete [] CapabilitiesComp; }
		workComputers(const workComputers&);

		/* overloads*/
		workComputers& operator=(workComputers);
		friend std::istream& operator>>(std::istream& in, workComputers& a);
		friend std::ostream& operator<<(std::ostream&, workComputers);
		friend std::ifstream& operator>>(std::ifstream&, workComputers&);
		friend std::ofstream& operator<<(std::ofstream&, workComputers);
		
		/* METHODS*/
		friend void swap(workComputers&, workComputers&);
		virtual void testCopyOperator();
		virtual void testCopyConstructor();

//		void InputFromFile();
//		virtual void OutputInFile();
		void Add_comp();
		void Delete_comp();
		void swapElementsInMassive(unsigned);
		virtual void SortProcTypeAndClock();
		void SortProcName();
		void SortPrice();

		friend void makePerechen1(workComputers, Perechen&);
		friend void makePerechen2(workComputers,Perechen&);
		friend void makePerechen3(workComputers, Perechen&);
	/*class fields */
	protected:
		int size;
		RECORD* CapabilitiesComp;
};

class SearchComp : public workComputers{
	public:
		// constructors
		SearchComp(int size = 0) : size(size), SearchResult(size ? new RECORD[size] : nullptr) {}
		~SearchComp(){ delete [] SearchResult; }
		SearchComp(const SearchComp&);
		friend void swap(SearchComp&, SearchComp&);

		/* overloads*/
		SearchComp& operator=(SearchComp);
		friend std::ostream& operator<<(std::ostream&, SearchComp);
		friend std::ofstream& operator<<(std::ofstream&, SearchComp);

		/* METHODS */
		virtual void testCopyConstructor();
		virtual void testCopyOperator();

		void SearchPrice();
		void SearchHddVolume();
		void SearchBrandTypeRamETC();
		void SortRAM();
		virtual void OutputInFile();
		virtual void SortProcTypeAndClock();
		void swapElementsInSearch(unsigned);
	/*class fields */
	protected:
		int size;
		RECORD* SearchResult;
};

class Perechen : public SearchComp{
	public:
		/* Constructors */
		Perechen(int sB = 0, int sP = 0, int sV = 0) : pBrandlen(sB), pProclen(sP), pVideolen(sV), perechenBrands(pBrandlen ? new BrandPerech[pBrandlen] : nullptr), perechenProcTypes(pProclen? new TypeProcPerech[pProclen] : nullptr), perechenVideocardVolume(pVideolen ? new VideocardsPerech[pVideolen] : nullptr) {}
		~Perechen() { delete [] perechenBrands; delete [] perechenProcTypes; delete [] perechenVideocardVolume;}
		Perechen(const Perechen&); 

		/*overloads*/
		Perechen& operator=(Perechen); 
		friend std::ostream& operator<<(std::ostream&, Perechen); // перегрузка вывода всех перечней
		
		/* Methods */
		friend void swap(Perechen&, Perechen&);
		virtual void testCopyConstructor();
		virtual void testCopyOperator();

		void showFirstPerech();
		void showSecondPerech();
		void showThirdPerech();
		void sortProcTypeFirstPerech();
		void sortCountSecondPerech();
		void sortVideoVolumeThirdPerech();
		void saveFirstPerech();
		void saveSecondPerech(); 
		void saveThirdPerech();

		friend void makePerechen1(workComputers, Perechen&);
		friend void makePerechen2(workComputers, Perechen&);
		friend void makePerechen3(workComputers, Perechen&);
	/*class fields */
	protected:
		int pBrandlen, pProclen, pVideolen;
		BrandPerech* perechenBrands;
		TypeProcPerech* perechenProcTypes;
		VideocardsPerech* perechenVideocardVolume ;
};
