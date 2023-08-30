import matplotlib.pyplot as plt
import numpy as np
from random import uniform
import math

MIN_N = 50
MAX_N = 5000

means = np.array([])                #contains mean for each n
all_points = np.array([])           #contains calculated PI for each k for each n
    
for n in range(MIN_N, MAX_N + 1, 50):
    points = np.array([])           #contains calculated PI for each k 
        
    for k in range(50):
        count = 0
        for i in range(n):
            if math.sqrt((uniform(0, 2) - 1) ** 2 + (uniform(0, 2) - 1) ** 2) <= 1:    #if point is in the circle
                count += 1
        points = np.append(points, [(count / n) * 4])
            
    all_points = np.append(all_points, points)
    means = np.append(means, np.mean(points))

#blue points for calculated integral each k for each n
x_axis = [[n for i in range(50)] for n in range(MIN_N, MAX_N + 1, 50)]
plt.scatter(x_axis, all_points, s = 0.2, color = '#5E81B5')
#red points for mean for each n
plt.scatter(range(MIN_N, MAX_N + 1, 50), means, s = 4, color = '#FF0000')
#green line for the exact value of PI
plt.plot(range(MIN_N, MAX_N + 1, 50), [math.pi for x in range(MIN_N, MAX_N + 1, 50)], linewidth = 2, color = '#00FF00')
    
plt.show()
