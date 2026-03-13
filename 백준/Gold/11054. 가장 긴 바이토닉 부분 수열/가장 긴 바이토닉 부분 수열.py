n = int(input())
dpl = [1 for i in range(n+1)]
dpr = [1 for i in range(n+1)]
answer = [0 for i in range(n+1)]
num = list(map(int, input().split()))

for i in range(2, n+1):
    tmp = num[i-1]
    for j in range(1, i):
        if num[j-1] < tmp:
            dpl[i] = max(dpl[i], dpl[j] + 1)

for i in range(n-1, 0, -1):
    tmp = num[i-1]
    for j in range(n, i, -1):
        if num[j-1] < tmp:
            dpr[i] = max(dpr[i], dpr[j] + 1)

for i in range(1, n+1):
    answer[i] = dpl[i] + dpr[i] - 1

print(max(answer))