import sys

read = sys.stdin.readline

n, k = map(int, read().split())
dp = [[0 for i in range(k+1)] for i in range(n+1)]

for y in range(1, k+1):
    for x in range(n+1):
        if y == 1:
            dp[x][1] = 1
        else:
            dp[x][y] = sum(dp[a][y-1] for a in range(x+1)) % 1000000000

print(dp[n][k])