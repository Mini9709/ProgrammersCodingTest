import sys
N = int(sys.stdin.readline())
P_list = list(map(int, sys.stdin.readline().split()))

dp = [P_list[i] for i in range(N)]
for i in range(1,N):
    for j in range(0,i):
        dp[i] = max(dp[i], dp[i-j-1]+P_list[j])
print(dp[N-1])
#n = n | n-1 + 1 | n-2 + 2 | n-3 + 3 | ... 1 |