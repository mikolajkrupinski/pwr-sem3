#include <iostream>
#include <random>
#include <ctime>
#include <fstream>

int main()
{
    std::mt19937 urbg {(unsigned int)time(NULL)};  
    std::uniform_int_distribution<int> distr1 {0, 1};

    std::ofstream f2("data2.txt");
    std::ofstream f3("data3.txt");
    std::ofstream f4("data4.txt");
    
    for (int n = 100; n <= 10000; n *= 10)
    {
        for (int k = 0; k < 5000; k++)
        {
            int position = 0; //aktualna pozycja
            int L = 0; // czas spędzony nad osią OX

            for (int i = 0; i < n; i++)
            {
                int step = distr1(urbg) == 0 ? -1 : 1;
                position += step;
                
                if (position > 0 || (position == 0 && step == -1))
                    L++;
            }

            double P = L * 1.0 / n;

            if (n == 100)
                f2 << P << (k != 4999 ? "," : "\n");
            else if (n == 1000)
                f3 << P << (k != 4999 ? "," : "\n");
            else
                f4 << P << (k != 4999 ? "," : "\n");;

        }
    }
    
    f2.close();
    f3.close();
    f4.close();

    return 0;
}