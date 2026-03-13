import sys
N = int(sys.stdin.readline())
A = list(map(int, sys.stdin.readline().split()))
lst = sorted(set(A))
dp = [0 for i in range(N)]
dp[0] = 1
for x in range(1, N):
    for y in range(0, x):
        if A[y] < A[x] and dp[x] < dp[y]:
            dp[x] = dp[y]
    dp[x] += 1
ans = max(dp)
print(ans)