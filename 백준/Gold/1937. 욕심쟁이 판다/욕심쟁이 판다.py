import sys
sys.setrecursionlimit(10**8)
read = sys.stdin.readline

def dfs(x, y):
    global answer
    temp = 0
    for d in directions:
        next_x = x + d[0]
        next_y = y + d[1]

        if 0 <= next_x < n and 0 <= next_y < n:
            if forest[next_y][next_x] > forest[y][x]:
                if dp[next_y][next_x] != -1:
                    temp = max(temp, dp[next_y][next_x])
                else:
                    temp = max(temp, dfs(next_x, next_y))

    dp[y][x] = temp + 1
    answer = max(answer, dp[y][x])

    return dp[y][x]

n = int(read())

forest = [list(map(int, read().split())) for i in range(n)]
dp = [[-1 for i in range(n)] for i in range(n)]
directions = [[1,0], [-1,0], [0,1], [0,-1]]
answer = 0

for y in range(n):
    for x in range(n):
        if dp[y][x] == -1:
            dfs(x, y)

print(answer)