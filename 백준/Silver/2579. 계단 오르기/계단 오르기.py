import sys

count = int(sys.stdin.readline())
stair = [0 for i in range(count+1)]
for _ in range(1, count+1):
    stair[_] = int(sys.stdin.readline())

dp = [0 for i in range(count+1)]

for i in range(1, len(dp)):
    if i == 1:
        dp[i] = stair[i]
    elif i == 2:
        dp[i] = stair[i] + stair[i-1]
    elif i >= 3:
        dp[i] = max(dp[i-3]+stair[i-1], dp[i-2]) + stair[i]

print(dp[count])