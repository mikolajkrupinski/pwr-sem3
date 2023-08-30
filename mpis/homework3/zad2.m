S = zeros(10000, 1);
N = 2;

for i = 1:10000
    X = randi([0 1], 1, N);
    X(X == 0) = -1;
    S(i) = sum(X);
end

figure(1)
cdfplot(S);
hold on;
pd = makedist('Normal', 'mu', 0, 'sigma', sqrt(N));
x = -22:.1:22;
p = cdf(pd, x);
plot(x, p);
