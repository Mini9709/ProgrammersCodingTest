from collections import deque
import sys

t = int(sys.stdin.readline())
for _ in range(t):
    n, k = map(int, sys.stdin.readline().split())
    time = list(map(int, sys.stdin.readline().split()))
    rule = [[0 for i in range(n+1)]for i in range(n+1)]
    indegree = [0 for i in range(n+1)]
    dp = [0 for i in range(n+1)]
    for i in range(k):
        a, b = map(int, sys.stdin.readline().split())
        rule[a][b] = 1
        indegree[b] += 1
    w = int(sys.stdin.readline())
    q = deque()

    for i in range(1, n+1):
        if indegree[i]  == 0:
            q.append(i)

    while q:
        point = q.popleft()
        dp[point] += time[point-1]

        for i in range(n+1):
            if rule[point][i] == 1:
                indegree[i] -= 1
                dp[i] = max(dp[i], dp[point])
                if indegree[i] == 0:
                    q.append(i)

    print(dp[w])