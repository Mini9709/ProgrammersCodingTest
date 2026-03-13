import sys
from itertools import combinations
read = sys.stdin.readline

def dfs(x, y, stat):
    global answer
    if floor[y][x] == 0:
        answer += 1
        floor[y][x] = 2

    for d in directions[stat]:
        next_x = x + d[0]
        next_y = y + d[1]
        if 0 <= next_y < n and 0 <= next_x < m:
            if floor[next_y][next_x] == 0:
                dfs(next_x, next_y, d[2])
                return

    next_x = x + directions[stat][1][0]
    next_y = y + directions[stat][1][1]
    if 0 <= next_y < n and 0 <= next_x < m:
        if floor[next_y][next_x] != 1:
            dfs(next_x, next_y, stat)
    return

n, m = map(int, read().split()) #y, x
start_y, start_x, stat = map(int, read().split())
visit = [[False for i in range(m)] for i in range(n)]
floor = [list(map(int, read().split())) for i in range(n)]
directions = [[[-1,0,3], [0,1,2], [1,0,1], [0,-1,0]], [[0,-1,0], [-1,0,3], [0,1,2], [1,0,1]],
              [[1,0,1], [0,-1,0], [-1,0,3], [0,1,2]], [[0,1,2], [1,0,1], [0,-1,0], [-1,0,3]]]
answer = 0
dfs(start_x, start_y, stat)
print(answer)