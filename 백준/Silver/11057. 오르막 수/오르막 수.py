import sys
read = sys.stdin.readline

n = int(read())

dp = [[0 for i in range(10)] for i in range(n+1)]
answer = [0 for i in range(n+1)]
for i in range(10):
    dp[1][i] = 1
answer[1] = 10
    
for i in range(2, n+1):
    for j in range(10):
        dp[i][j] = sum(dp[i-1][x] for x in range(j, 10))
    answer[i] = sum(dp[n])% 10007

print(answer[n])