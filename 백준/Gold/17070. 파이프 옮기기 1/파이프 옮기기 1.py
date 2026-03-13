import sys
read = sys.stdin.readline

t = int(read())
pipemap = [[0 for i in range(t)] for i in range(t)]
dpw = [[0 for i in range(t+1)] for i in range(t+1)]
dph = [[0 for i in range(t+1)] for i in range(t+1)]
dpd = [[0 for i in range(t+1)] for i in range(t+1)]

for i in range(t):
    pipemap[i] = list(map(int, read().split()))

dpw[1][2] = 1
for y in range(1, t+1):
    for x in range(3, t+1):
        if pipemap[y-1][x-1] == 0:
            dpw[y][x] = dpw[y][x-1] + dpd[y][x-1]
            dph[y][x] = dph[y-1][x] + dpd[y-1][x]
            if pipemap[y-2][x-1] == 0 and pipemap[y-1][x-2] == 0:
                dpd[y][x] = dpw[y-1][x-1] + dph[y-1][x-1] + dpd[y-1][x-1]

answer = dpw[t][t] + dph[t][t] + dpd[t][t]
print(answer)
# (n,n) -> (n-1,n)가로 + (n,n-1)세로 + (n-1,n-1)대각
# (n,n)가로 -> (n-1,n)가로 + (n-1,n)대각
# (n,n)세로 -> (n,n-1)세로 + (n,n-1)대각
# (n,n)대각 -> (n-1,n-1)가로 + (n-1,n-1)세로 + (n-1, n-1)대각