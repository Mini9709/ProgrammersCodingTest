import sys
read = sys.stdin.readline

num = read().strip()
length = len(num)
dp = [0 for i in range(length+1)]
dp[0] = 1
if int(num[0]) != 0:
    dp[1] += 1

for i in range(2, length+1):
    if dp[i-1] == 0:
        break

    if int(num[i-1]) == 0:
        if int(num[i-2]) == 1 or int(num[i-2]) == 2:
            dp[i] += dp[i-2]
    else:
        dp[i] += dp[i-1]
        if int(num[i-2])*10 + int(num[i-1]) <= 26 and int(num[i-2]) != 0:
            dp[i] += dp[i-2]

    dp[i] %= 1000000

print(dp[length])