import sys
read = sys.stdin.readline

def dfsblock(x, y, idx, temp_number):
    global answer
    visited[y][x] += 1

    if idx == 3:
        answer = max(answer, temp_number)
        return

    if temp_number + (3-idx)*max_number < answer:
        return
    else:
        for d in directions:
            next_x = x + d[0]
            next_y = y + d[1]
            if 0 <= next_x < m and 0 <= next_y < n:
                if visited[next_y][next_x] == 0:
                    dfsblock(next_x, next_y, idx + 1, temp_number+tetro[next_y][next_x])
                    visited[next_y][next_x] -= 1

def tblock(x, y):
    global answer
    tblocklist = [0, 0, 0, 0]
    for d in range(4):
        next_x = x + directions[d][0]
        next_y = y + directions[d][1]
        if 0 <= next_x < m and 0 <= next_y < n:
            tblocklist[d] = tetro[next_y][next_x]
    temp = tetro[y][x] + sum(tblocklist) - min(tblocklist)
    answer = max(answer, temp)

n, m = map(int, read().split()) # y,x

tetro = [list(map(int, read().split())) for i in range(n)]
visited = [[0 for i in range(m)] for i in range(n)]
max_number = max(map(max, tetro))
directions = [[1,0], [-1,0], [0,1], [0,-1]]
temp_number, answer = 0, 0

for y in range(n):
    for x in range(m):
        dfsblock(x, y, 0, tetro[y][x])
        visited[y][x] -= 1
        tblock(x, y)

print(answer)