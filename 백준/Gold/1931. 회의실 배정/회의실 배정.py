import sys
read = sys.stdin.readline

N = int(read())

room = [[0, 0] for i in range(N)]
answer = 0
endtime = 999999

for _ in range(N):
    room[_] = list(map(int, read().split()))

start_room = sorted(room)

for i in range(len(start_room)):
    if endtime <= start_room[i][0]:
        answer += 1
        endtime = start_room[i][1]

    elif start_room[i][1] < endtime:
        endtime = start_room[i][1]



answer += 1
print(answer)