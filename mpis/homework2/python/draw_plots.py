import numpy as np
from matplotlib import pyplot as plt

f = open("results.txt", "r")
lines = f.read().split('\n')

conflict_places = np.array([])
avg_conflict_places = np.array([])

empty_bins_after_n = np.array([])
avg_empty_bins_after_n = np.array([])

max_balls_in_bin = np.array([])
avg_max_balls_in_bin = np.array([])

throws_one_ball = np.array([])
avg_throws_one_ball = np.array([])

throws_two_balls = np.array([])
avg_throws_two_balls = np.array([])

diff_two_one_throws = np.array([])
avg_diff_two_one_throws = np.array([])

line_number = 0 # number of line that I read from

for n in range(1000, 100000 + 1, 1000):

    sum_conflict_place = 0
    sum_empty_bins_after_n = 0
    sum_max_balls_in_bin = 0
    sum_throws_one_ball = 0
    sum_throws_two_balls = 0
    sum_diff_two_one_throws = 0

    for k in range(0, 50):
        data = [int(val) for val in lines[line_number].split(';')]

        conflict_places = np.append(conflict_places, data[0])
        sum_conflict_place += data[0]

        empty_bins_after_n = np.append(empty_bins_after_n, data[1])
        sum_empty_bins_after_n += data[1]

        max_balls_in_bin = np.append(max_balls_in_bin, data[2])
        sum_max_balls_in_bin += data[2]

        throws_one_ball = np.append(throws_one_ball, data[3])
        sum_throws_one_ball += data[3]

        throws_two_balls = np.append(throws_two_balls, data[4])
        sum_throws_two_balls += data[4]

        diff_two_one_throws = np.append(diff_two_one_throws, data[5])
        sum_diff_two_one_throws += data[5]

        line_number += 1

    avg_conflict_places = np.append(avg_conflict_places, sum_conflict_place / 50.0)
    avg_empty_bins_after_n = np.append(avg_empty_bins_after_n, sum_empty_bins_after_n / 50.0)
    avg_max_balls_in_bin = np.append(avg_max_balls_in_bin, sum_max_balls_in_bin / 50.0)
    avg_throws_one_ball = np.append(avg_throws_one_ball, sum_throws_one_ball / 50.0)
    avg_throws_two_balls = np.append(avg_throws_two_balls, sum_throws_two_balls / 50.0)
    avg_diff_two_one_throws = np.append(avg_diff_two_one_throws, sum_diff_two_one_throws / 50.0)

f.close()

x_axis = [[n for k in range(50)] for n in range (1000, 100000 + 1, 1000)]
x_axis_for_n = range(1000, 100000 + 1, 1000)

plt.scatter(x_axis , conflict_places, s = 0.2, color = '#5E81B5')
plt.scatter(x_axis_for_n, avg_conflict_places, s = 4, color = '#FF0000')
plt.show()

plt.scatter(x_axis , empty_bins_after_n, s = 0.2, color = '#5E81B5')
plt.scatter(x_axis_for_n, avg_empty_bins_after_n, s = 4, color = '#FF0000')
plt.show()

plt.hist2d([n for k in range(50) for n in range(1000, 100000 + 1, 1000)], max_balls_in_bin, bins=50, cmap='Blues')
cb = plt.colorbar()
plt.scatter(x_axis_for_n, avg_max_balls_in_bin, s = 4, color = '#FF0000')
plt.show()

plt.scatter(x_axis , throws_one_ball, s = 0.2, color = '#5E81B5')
plt.scatter(x_axis_for_n, avg_throws_one_ball, s = 4, color = '#FF0000')
plt.show()

plt.scatter(x_axis , throws_two_balls, s = 0.2, color = '#5E81B5')
plt.scatter(x_axis_for_n, avg_throws_two_balls, s = 4, color = '#FF0000')
plt.show()

plt.scatter(x_axis , diff_two_one_throws, s = 0.2, color = '#5E81B5')
plt.scatter(x_axis_for_n, avg_diff_two_one_throws, s = 4, color = '#FF0000')
plt.show()
