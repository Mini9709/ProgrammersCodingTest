n, k = map(int, input().split())
coin = []
for _ in range(n):
    c = int(input())
    if c not in coin:
        coin.append(c)

coin = sorted(coin)

dp = [-1 for i in range(k+1)]
for i in range(1, len(dp)):
    for j in coin:
        if i == j:
            dp[i] = 0
        elif i > j and dp[i] != -1 and dp[i-j] != -1:
            dp[i] = min(dp[i], dp[i-j])
        elif i > j and dp[i] == -1:
            dp[i] = dp[i-j]
    if dp[i] != -1:
        dp[i] += 1

print(dp[k])