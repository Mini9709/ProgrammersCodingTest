import sys
sys.setrecursionlimit(10**6)

read = sys.stdin.readline
def dfs(x, y, color):
    visited[y][x] = True
    for d in directions:
        nextX = x + d[0]
        nextY = y + d[1]

        if nextX >= 0 and nextX < n and nextY >= 0 and nextY < n:
            if color[y][x] == color[nextY][nextX] and visited[nextY][nextX] == False:
                dfs(nextX, nextY, color)

n = int(read())
color = [[0 for i in range(n)] for i in range(n)]
rg = [[0 for i in range(n)] for i in range(n)]
visited = [[False for i in range(n)] for i in range(n)]
answer_color = 0
answer_rg = 0
directions = [[1, 0], [0, 1], [-1, 0], [0, -1]]

for i in range(n):
    str = read()
    for j in range(n):
        if str[j] == "R":
            color[i][j] = 0
            rg[i][j] = 0
        elif str[j] == "G":
            color[i][j] = 1
            rg[i][j] = 0
        else:
            color[i][j] = 2
            rg[i][j] = 2

for y in range(n):
    for x in range(n):
        if visited[y][x] == False:
            dfs(x, y, color)
            answer_color += 1

visited = [[False for i in range(n)] for i in range(n)]

for y in range(n):
    for x in range(n):
        if visited[y][x] == False:
            dfs(x, y, rg)
            answer_rg += 1

print(answer_color, answer_rg)