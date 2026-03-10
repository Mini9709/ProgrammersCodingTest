n, k = map(int, input().split())
coin = []
for _ in range(n):
    coin.append(int(input()))

dp = [[0 for i in range(n+1)] for i in range(k+1)]

for i in range(1, k+1):
    for j in range(1, n+1):
        dp[i][j] = dp[i][j-1]
        if coin[j-1] == i:
            dp[i][j] += 1
        if coin[j-1] < i:
            dp[i][j] += dp[i-coin[j-1]][j]

print(dp[k][n])