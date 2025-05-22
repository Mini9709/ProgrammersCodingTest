import sys
from collections import deque

read = sys.stdin.readline
n, m, v = map(int, read().split())

graph = [[0 for i in range(n+1)] for i in range(n+1)]
visited = [0 for i in range(n+1)]
q = deque()
q.append(v)

for i in range(m):
    start, end = map(int, read().split())
    graph[start][end] = 1
    graph[end][start] = 1

def dfs(num):
    if visited[num] == 0:
        visited[num] = 1
        print(num, end=' ')

    for i in range(1, n+1):
        if graph[num][i] == 1 and visited[i] == 0:
            dfs(i)

def bfs():
    while q:
        start = q.popleft()
        if visited[start] == 0:
            visited[start] = 1
            print(start, end=' ')

        for i in range(1, n+1):
            if graph[start][i] == 1 and visited[i] == 0:
                q.append(i)

dfs(v)
print()
visited = [0 for i in range(n+1)]
bfs()