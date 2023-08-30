from igraph import *
from random import randint
import numpy as np
from matplotlib import pyplot as plt
from math import sqrt, log, floor

MAX_K = 50

x_axis = [[n for k in range(MAX_K)] for n in range (100, 2001, 50)]
x_axis_for_n = range(100, 2001, 50)

allStepsA = np.array([])
meansA = np.array([])

for N in range(100, 2001, 50):

    graph = Graph.Full(N)
    stepsArray = np.zeros(MAX_K)

    for k in range(MAX_K):

        visited = [False for i in range(N)]
        numOfVisited = 1
        currPosition = 0
        visited[0] = True

        steps = 0

        while numOfVisited < N:
            neighbors = graph.neighbors(currPosition)
            deg = len(neighbors)
            
            currPosition = neighbors[randint(0, deg - 1)]

            if visited[currPosition] == False:
                visited[currPosition] = True
                numOfVisited += 1
            steps += 1

        stepsArray[k] = steps

    allStepsA = np.append(allStepsA, stepsArray)
    meansA = np.append(meansA, [np.mean(stepsArray)])