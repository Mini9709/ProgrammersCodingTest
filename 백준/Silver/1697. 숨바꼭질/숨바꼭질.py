import sys
from collections import deque
read = sys.stdin.readline

N, K = map(int, read().split())
q = deque()
q.append(N)
dp = [-1 for i in range(100001)]
dp[N] = 0

while K not in q:
    location = q.popleft()
    move_plus, move_minus, move_multiple = location+1, location-1, location*2
    
    if move_plus <= 100000 and dp[move_plus] == -1:
        dp[move_plus] = dp[location] + 1
        q.append(move_plus)

    if move_multiple <= 100000 and dp[move_multiple] == -1:
        dp[move_multiple] = dp[location] + 1
        q.append(move_multiple)

    if move_minus >= 0 and dp[move_minus] == -1:
        dp[move_minus] = dp[location] + 1
        q.append(move_minus)

print(dp[K])