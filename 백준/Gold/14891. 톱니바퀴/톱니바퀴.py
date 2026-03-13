from collections import deque
import sys

read = sys.stdin.readline

def rotateroutine(rw_num, rw_rotate):
    visited[rw_num] = True
    if rw_num == 0:
        if wheel[rw_num][2] != wheel[1][6] and visited[1] == False:
            rotateroutine(1, rw_rotate*(-1))
    elif rw_num == 3:
        if wheel[2][2] != wheel[rw_num][6]  and visited[2] == False:
            rotateroutine(2, rw_rotate*(-1))
    else:
        if wheel[rw_num][2] != wheel[rw_num+1][6]  and visited[rw_num+1] == False:
            rotateroutine(rw_num+1, rw_rotate*(-1))

        if wheel[rw_num-1][2] != wheel[rw_num][6]  and visited[rw_num-1] == False:
            rotateroutine(rw_num-1, rw_rotate*(-1))

    wheel[rw_num].rotate(rw_rotate)


wheel = [deque() for i in range(4)]

for i in range(4):
    num = read()
    for j in range(8):
        wheel[i].append(num[j])

k = int(read())
rotate_wheel = [list(map(int, read().split())) for i in range(k)]
visited = [False for i in range(4)]
answer = 0

for rw in rotate_wheel:
    visited = [False for i in range(4)]
    rw_num = rw[0]-1
    rw_rotate = rw[1]
    rotateroutine(rw_num, rw_rotate)

for i in range(4):
    answer += int(wheel[i][0])*(2**i)
print(answer)
#1 N극:0 S극:1 시계방향회전:1 반시계방향:-1
#2 wheel[0][2](1번째 바퀴의 3시방향) - wheel[1][8]
#3 wheel[1][2] - wheel[2][8], wheel[2][2] - wheel[3][8]
#4 시계방향 회전 시 rotate(1) 반시계일시 rotate(-1)
#5 톱니 비교 시 같으면 continue 다르면 반대방향 회전
#6 sum(wheel[i][0]*2**i)