import sys
sys.setrecursionlimit(10**8)
read = sys.stdin.readline

def dfsRoad(x, y):
    if x == n-1 and y == m-1:
        return 1

    if visitRoute[y][x] != -1:
        return  visitRoute[y][x]

    temp = 0

    for d in directions:
        next_x = x + d[0]
        next_y = y + d[1]

        if next_x >= 0 and next_x < n and next_y >= 0 and next_y < m:
            if road[next_y][next_x] < road[y][x]:
                temp += dfsRoad(next_x, next_y)

    visitRoute[y][x] = temp

    return visitRoute[y][x]

m, n = map(int, read().split())

road = [[0 for i in range(n)] for i in range(m)]
visitRoute = [[-1 for i in range(n)] for i in range(m)]
directions = [[0,1], [1,0], [-1,0], [0,-1]]

for i in range(m):
    road[i] = list(map(int, read().split()))

print(dfsRoad(0, 0))