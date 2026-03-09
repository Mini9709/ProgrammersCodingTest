N = int(input())
dp = [0 for i in range(91)]
dp[0] = 0
dp[1] = 1
dp[2] = 1
for x in range(3, N+1):
    dp[x] = sum(dp[1:x-1])+1
ans = dp[N]
print(ans)