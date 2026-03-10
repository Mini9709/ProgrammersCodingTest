n = int(input())
dp = [9999 for i in range(n+1)]

for i in range(3, n+1):
    if i == 3 or i == 5:
        dp[i] = 1
    elif i > 5:
        dp[i] = min(dp[i-3], dp[i-5]) + 1

if dp[n] >= 9999:
    print(-1)
else:
    print(dp[n])