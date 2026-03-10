from collections import deque
import sys

read = sys.stdin.readline

t = int(read())

for _ in range(t):
    command = read()
    l = int(read())
    lst = read().strip()
    arr = deque(lst[1:-1].split(','))
    error = False
    reversecount = 0
    if l == 0:
        arr = deque()

    for i in range(len(command)):
        if command[i] == "R":
            reversecount += 1
        if command[i] == "D":
            if not arr:
                error = True
                break
            else:
                if reversecount % 2 == 0:
                    arr.popleft()
                else:
                    arr.pop()

    if error:
        print("error")
    else:
        if reversecount % 2 == 1:
            arr.reverse()
        print('[' + ",".join(arr) + ']')
