#include <vector>

class Graph
{
private:
    std::vector<std::vector<unsigned int>> edges;

public:
    void add_edge(unsigned int target, unsigned int edge);
    std::vector<unsigned int> get_edges(unsigned int index);
};