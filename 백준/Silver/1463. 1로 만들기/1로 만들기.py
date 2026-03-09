n = int(input())
dp = [0 for i in range(n+1)]

for i in range(2, n+1):
    if i == 2 or i == 3:
        dp[i] = 1
    else:
        dp[i] = dp[i-1]
        if i % 2 == 0:
            dp[i] = min(dp[i], dp[i//2])
        if i % 3 == 0:
            dp[i] = min(dp[i], dp[i//3])
        dp[i] += 1

print(dp[n])