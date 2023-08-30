import numpy as np
from random import randint

f = open("results.txt", "w")

for n in range(1000, 100000 + 1, 1000):
    print(n) # info not to drive crazy while waiting
        
    for k in range(50):
        
        ballsInBins = np.zeros(n) # each cell stores number of balls in each bin

        wasConflict = False # set to True after first conflict

        # variables to store results for each task
        conflict_place = 0
        empty_bins_after_n = 0
        max_balls_in_bin = 0
        throws_one_ball = 0
        throws_two_balls = 0

        bins_less_two_balls = n # number of bins with less than two balls
        empty_bins = n # number of currently empty bins
        t = 0 # number of already thrown balls

        while bins_less_two_balls > 0:
            # choosing random bin to throw ball into
            selectedBin = randint(0, n - 1)
            ballsInBins[selectedBin] += 1
            t += 1

            # empty bin is now not empty
            if ballsInBins[selectedBin] == 1:
                empty_bins -= 1

            # one-ball-bin has now two balls
            if ballsInBins[selectedBin] == 2:
                bins_less_two_balls -= 1
                # check if it's not the first conflict
                if not wasConflict:
                    wasConflict = True
                    conflict_place = t

            # after n-th ball thrown
            if t == n:
                empty_bins_after_n = len([bin for bin in ballsInBins if bin == 0])
                max_balls_in_bin = int(np.max(ballsInBins))
            
            # first momeant when all bins are full
            if throws_one_ball == 0 and empty_bins == 0:
                throws_one_ball = t

        # 'while' loop has finished so all the bins have at least two balls
        throws_two_balls = t

            
        f.write(str(conflict_place) + ';' + str(empty_bins_after_n) + ';' + str(max_balls_in_bin) + ';'
            + str(throws_one_ball) + ';' + str(throws_two_balls) + ';' + str(throws_two_balls - throws_one_ball) + '\n')

f.close()