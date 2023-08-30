#include <iostream>
#include <cmath>
#include <vector>
#include <random>
#include <fstream>

class Graph
{
private:
    std::vector<std::vector<unsigned int>> edges;

public:
    void create_vertices(unsigned int number)
    {
        for (unsigned int i = 0; i < number; i++)
            this->edges.push_back(std::vector<unsigned int>());
    }

    void add_edge(unsigned int target, unsigned int edge)
    {
        this->edges[target].push_back(edge);
    }

    std::vector<unsigned int> get_edges(unsigned int index)
    {
        return this->edges[index];
    }
};

int main()
{
    std::mt19937 mt{};

    std::ofstream file("data3.txt");

    for (unsigned int N = 1250; N <= 1750; N += 50)
    {
        std::cout << N << std::endl;

        Graph g;
        g.create_vertices(N);
        for (unsigned int i = 0; i < floor(2 * N / 3); i++)
            for (unsigned int j = i + 1; j < floor(2 * N / 3); j++)
            {
                g.add_edge(i, j);
                g.add_edge(j, i);
            }
                   
        for (unsigned int i = floor(2 * N / 3) - 1; i < N - 1; i++)
        {
            g.add_edge(i, i + 1);
            g.add_edge(i + 1, i);
        }

        for (unsigned int k = 0; k < 50; k++)
        {
            bool *visited = new bool[N];
            visited[0] = true;
            for (unsigned int i = 1; i < N; i++)
                visited[i] = false;
            unsigned int num_of_visited = 1;
            unsigned int current = 0;
            unsigned int steps = 0;

            while (num_of_visited < N)
            {
                std::uniform_int_distribution<> rand_vertice{0, (int)(g.get_edges(current).size() - 1)};
                current = g.get_edges(current)[rand_vertice(mt)];

                if (!visited[current])
                {
                    visited[current] = true;
                    num_of_visited++;
                }
                steps++;
            }

            file << steps << std::endl;
            delete[] visited;
        }
    }

    file.close();

    return 0;
}
