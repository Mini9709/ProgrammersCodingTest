import sys
from itertools import combinations
read = sys.stdin.readline

n, m = map(int, read().split())
chickenmap = [[] for i in range(n)]
chicken = []
house = []
answer = 999999

for i in range(n):
    chickenmap[i] = list(map(int, read().split()))
    for j in range(n):
        if chickenmap[i][j] == 1:
            house.append([j,i])
        elif chickenmap[i][j] == 2:
            chicken.append([j, i])

for x in combinations(chicken, m):
    temp = 0
    for y in house:
        distance = min(abs(y[0]-x[z][0]) + abs(y[1]-x[z][1]) for z in range(m))
        temp += distance

    answer = min(answer, temp)

print(answer)