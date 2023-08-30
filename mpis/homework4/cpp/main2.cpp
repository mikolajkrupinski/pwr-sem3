#include <iostream>
#include <cmath>
#include <vector>
#include <random>
#include <fstream>

int main()
{
    std::mt19937 mt{};

    std::ofstream file("data2.txt");

    for (unsigned int N = 1650; N <= 1750; N += 50)
    {
        std::cout << N << std::endl;

        std::vector<std::vector<unsigned int>> edges(N, std::vector<unsigned int>());
        
        for (unsigned int i = 0; i < floor(2 * N / 3); i++)
            for (unsigned int j = i + 1; j < floor(2 * N / 3); j++)
            {
                edges[i].push_back(j);
                edges[j].push_back(i);
            }
                   
        for (unsigned int i = floor(2 * N / 3) - 1; i < N - 1; i++)
        {
            edges[i].push_back(i + 1);
            edges[i + 1].push_back(i);
        }

        for (unsigned int k = 0; k < 50; k++)
        {
            std::vector<bool> visited(N, false);
            visited[0] = true;
            unsigned int num_of_visited = 1;
            unsigned int current = 0;
            unsigned int steps = 0;

            while (num_of_visited < N)
            {
                std::uniform_int_distribution<> rand_vertice{0, (int)(edges[current].size() - 1)};
                current = edges[current][rand_vertice(mt)];

                if (!visited[current])
                {
                    visited[current] = true;
                    num_of_visited++;
                }
                steps++;
            }

            file << steps << std::endl;
        }
    }

    file.close();

    return 0;
}
