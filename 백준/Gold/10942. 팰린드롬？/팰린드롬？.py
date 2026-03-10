import sys

read = sys.stdin.readline

def palindrome(a, b):
    if board[a-1] == board[b-1] and dp[a+1][b-1] == 1:
        return 1
    else:
        return 0

n = int(read())
board = list(map(int, read().split()))
dp = [[0 for i in range(n+1)] for i in range(n+1)]

for i in range(1, n):
    dp[i][i] = 1
    if board[i-1] == board[i]:
        dp[i][i+1] = 1
dp[n][n] = 1

m = int(read())
distance = 2

while distance < n:
    for i in range(1, n+1):
        if i + distance <= n:
            dp[i][i+distance] = palindrome(i, i+distance)
    distance += 1

for i in range(m):
    questions = list(map(int, read().split()))
    print(dp[questions[0]][questions[1]])