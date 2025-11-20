T = int(input())

def backTracking(y):
    global answer
    if y == n:
        answer += 1
        return

    else:
        for i in range(n):
            if checkBoard(i, y):
                chess[y][i] = 1
                backTracking(y+1)
                chess[y][i] = 0

def checkBoard(x, y):
    for i in range(1, y+1):
        if chess[y-i][x] == 1:
            return False
        if x-i >= 0:
            if chess[y-i][x-i] == 1:
                return False
        if x+i < n:
            if chess[y-i][x+i] == 1:
                return False

    return True

for test_case in range(1, T + 1):
    answer = 0
    n = int(input())
    chess = [[0 for i in range(n)] for i in range(n)]

    backTracking(0)

    print("#%d %d"%(test_case, answer))