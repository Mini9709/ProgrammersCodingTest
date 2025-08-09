from collections import deque
import sys

read = sys.stdin.readline

m, n = map(int, read().split())
graph = [[]for i in range(n)]
q = deque()
answer = -1
directions = [[1, 0], [-1, 0], [0, 1], [0, -1]]
for i in range(n):
    graph[i] = list(map(int, read().split()))
    for j in range(m):
        if graph[i][j] == 1:
            q.append([i, j])

while q:
    answer += 1
    for i in range(len(q)):
        x = q.popleft()
        tomatoX = x[1]
        tomatoY = x[0]
        for d in directions:
            nextX = tomatoX + d[1]
            nextY = tomatoY + d[0]
            if nextX >= 0 and nextX < m and nextY >= 0 and nextY < n:
                if graph[nextY][nextX] == 0:
                    graph[nextY][nextX] = 1
                    q.append([nextY, nextX])

for i in range(n):
    if 0 in graph[i]:
        answer = -1

print(answer)