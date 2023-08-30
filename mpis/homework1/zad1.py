import matplotlib.pyplot as plt
import numpy as np
from random import uniform
import math

def f_1(x):
    return x ** (1.0 / 3.0)

def f_2(x):
    return math.sin(x)

def f_3(x):
    return 4 * x * ((1 - x) ** 3)

MIN_N = 50
MAX_N = 5000

def calc_and_draw(a, b, M, func, VAL):
    means = np.array([])                #contains mean for each n
    all_points = np.array([])           #contains calculated integral for each k for each n
    
    for n in range(MIN_N, MAX_N + 1, 50):
        points = np.array([])           #contains calculated integral for each k 
        
        for k in range(50):
            count = 0
            for i in range(n):
                if uniform(0, M) <= func(uniform(a, b)):    #if point is under the function curve
                    count += 1
            points = np.append(points, [(count / n) * (b - a) * M])
            
        all_points = np.append(all_points, points)
        means = np.append(means, np.mean(points))

    #blue points for calculated integral each k for each n
    x_axis = [[n for i in range(50)] for n in range(MIN_N, MAX_N + 1, 50)]
    plt.scatter(x_axis, all_points, s = 0.2, color = '#5E81B5')
    #red points for mean for each n
    plt.scatter(range(MIN_N, MAX_N + 1, 50), means, s = 4, color = '#FF0000')
    #green line for the exact value of the integral
    plt.plot(range(MIN_N, MAX_N + 1, 50), [VAL for x in range(MIN_N, MAX_N + 1, 50)], linewidth = 2, color = '#00FF00')
    
    plt.show()

            

calc_and_draw(0, 8, 2, f_1, 12)

calc_and_draw(0, math.pi, 1, f_2, 2)

calc_and_draw(0, 1, 0.5, f_3, 1.0 / 5.0)
