#include <iostream>
#include <cstdlib>
using namespace std;

int main()
{
	int month, day;
	cout << "Welcome to your friendly weather program.\n"
	<< "Enter today's date ass two integers for the month and the day:\n";

	cin >> month;
	cin >> day;

	srand(month*day); //Seeding the RNG using Month*Day

	int prediction;
	char ans;

	cout << "Weather for Today\n";

	do
	{
		prediction = rand()%3;
		switch(prediction)
		{
			case 0:
				cout << "The day will be Sunny!!\n";
				break;
			case 1:
				cout << "The day will be Cloudy.\n";
				break;
			case 2:
				cout << "The day will be Stormy!\n";
				break;
			default:
				cout << "An unkownen Error has occuered.\n";
		}

		cout << "Want the weather for the next day? (y/n): ";
		cin >> ans;
	} while (ans == 'y' || ans == 'Y');

	cout << "That's it from your 24-hour weather program.\n";
	return 0;
}