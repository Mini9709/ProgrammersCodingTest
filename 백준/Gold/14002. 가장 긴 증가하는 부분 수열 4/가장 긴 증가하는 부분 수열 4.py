import sys
N = int(sys.stdin.readline())
A = list(map(int, sys.stdin.readline().split()))

dp = [0 for i in range(N)]
dp[0] = 1
for x in range(1, N):
    for y in range(0, x):
        if A[y] < A[x] and dp[x] < dp[y]:
            dp[x] = dp[y]
    dp[x] += 1

num = max(dp)
lst = []
for a in range(N-1,-1,-1):
    if dp[a] == num:
        lst.append(A[a])
        num -= 1

print(max(dp))
print(*reversed(lst))