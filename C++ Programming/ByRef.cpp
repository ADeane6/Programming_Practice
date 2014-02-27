#include <iostream>
using namespace std;

void Swap(int &a, int &b) 	//Using & before var name shows your using a function by reference
{							//this means it alters the parameters it the main as well
	int temp = a;
	a= b;
	b = temp;
}
int main()
{
	int one;
	int two;

	char con = 'y';

	while (con == 'y' || con == 'Y')
	{
		cout << "Please Input two integers: ";
		cin >> one >> two;

		Swap(one, two); //Calling function that is by reference

		cout << "First number is now: " << one <<"\nSecond numbe is now: " << two << endl;

		cout << "Do you want to continue (y/n): ";
		cin >> con;
	}
}