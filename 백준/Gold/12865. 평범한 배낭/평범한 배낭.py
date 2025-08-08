n, k = map(int, input().split())
bag = [[] for i in range(n+1)]

for _ in range(1, n+1):
    bag[_] = list(map(int, input().split()))

dp = [[0 for i in range(n+1)] for i in range(k+1)]

for i in range(1, k+1):
    weight = 0
    for j in range(1, n+1):
        if dp[i][j-1] == 0:
            if weight + bag[j][0] <= i:
                dp[i][j] = bag[j][1]
                weight = bag[j][0]
        else:
            dp[i][j] = dp[i][j-1]
            if i-bag[j][0] >= 0:
                dp[i][j] = max(dp[i][j], dp[i-bag[j][0]][j-1] + bag[j][1])

print(dp[k][n])