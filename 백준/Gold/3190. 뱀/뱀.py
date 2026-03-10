import sys
read = sys.stdin.readline
def rotatehead(time):
    global head_time, curr_direction
    if head_time >= r:
        return
    else:
        if time == rotate[head_time][0]:  # 방향전환
            if rotate[head_time][1] == "L":
                curr_direction -= 1
            else:
                curr_direction += 1
            head_time += 1
        if curr_direction == -1:
            curr_direction = 3
        if curr_direction == 4:
            curr_direction = 0

def rotatetail(time, count_tails):
    global tail_time, curr_tail
    if tail_time >= r:
        return
    else:
        if time == rotate[tail_time][0] + count_tails:  # 방향전환
            if rotate[tail_time][1] == "L":
                curr_tail -= 1
            else:
                curr_tail += 1
            tail_time += 1
        if curr_tail == -1:
            curr_tail = 3
        if curr_tail == 4:
            curr_tail = 0

n = int(read())
snakemap = [[0 for i in range(n)] for i in range(n)]
a = int(read())
apple = [list(map(int, read().split())) for i in range(a)]
r = int(read())
rotate = [list(read().split()) for i in range(r)]
for i in range(r):
    rotate[i][0] = int(rotate[i][0])

directions = [[1,0], [0,1], [-1,0], [0,-1]]
curr_direction = 0 # 현재 헤드 방향
curr_tail = 0 # 현재 꼬리 방향
count_tails = 0 # 꼬리개수
head_x, head_y = 0, 0
tail_x, tail_y = 0, 0
time, head_time, tail_time = 0, 0, 0

snakemap[0][0] = 2

for i in range(a):
    snakemap[apple[i][0]-1][apple[i][1]-1] = 3

while True:
    rotatehead(time)
    next_x = head_x + directions[curr_direction][0]
    next_y = head_y + directions[curr_direction][1]

    if 0<= next_x < n and 0<= next_y < n: # 벽에 부딪치지 않음
        snakemap[head_y][head_x] = 1
        if snakemap[next_y][next_x] == 0: # 이동한 칸에 사과가 없음
            snakemap[next_y][next_x] = 2
            snakemap[tail_y][tail_x] = 0
            rotatetail(time, count_tails)
            head_x, head_y = next_x, next_y
            tail_x, tail_y = tail_x + directions[curr_tail][0], tail_y + directions[curr_tail][1]

        elif snakemap[next_y][next_x] == 3: # 사과가 있음
            snakemap[next_y][next_x] = 2
            head_x, head_y = next_x, next_y
            count_tails += 1

        else: #몸통에 부딪침
            break

        time += 1

    else: # 벽에 부딪침
        break

time += 1
print(time)