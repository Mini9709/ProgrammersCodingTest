from collections import deque
import sys
read = sys.stdin.readline

n, m = map(int, read().split())
graph = [[0 for i in range(m)]for i in range(n)]
visit = [[False for i in range(m)]for i in range(n)]
arr = [[0 for i in range(m)]for i in range(n)]
q = deque()
directions = [[0, 1], [1, 0], [0, -1], [-1, 0]]
for i in range(n):
     num = read()
     for j in range(m):
         graph[i][j] = int(num[j])

visit[0][0] = True
q.append([0, 0])
arr[0][0] = 1

while q:
    x, y = q.popleft()
    for d in directions:
        next_x = x + d[0]
        next_y = y + d[1]
        if next_y >= 0 and next_y < n and next_x >= 0 and next_x < m:
            if not visit[next_y][next_x] and graph[next_y][next_x] == 1:
                visit[next_y][next_x] = True
                q.append([next_x, next_y])
                arr[next_y][next_x] += arr[y][x]+1

print(arr[n-1][m-1])