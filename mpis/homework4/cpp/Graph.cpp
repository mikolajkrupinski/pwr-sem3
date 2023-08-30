#include "Graph.hpp"

void Graph::add_edge(unsigned int target, unsigned int edge)
{
    this->edges[target].push_back(edge);
}

std::vector<unsigned int> Graph::get_edges(unsigned int index)
{
    return this->edges[index];
}
