import sys

read = sys.stdin.readline

t = int(read())

for _ in range(t):
    n = int(read())
    answer = 0
    temp = 100001
    interviewee = sorted([list(map(int, read().split())) for i in range(n)])

    for i in interviewee:
        if i[1] < temp:
            answer += 1
            temp = i[1]
        if temp == 2 or temp == 1:
            answer += temp-1
            break

    print(answer)