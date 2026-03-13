a, b = map(int, input().split())
answer = 1

while b != a:
    if b % 2 == 0:
        b /= 2
        answer += 1
    elif b % 10 == 1 and b >= 10:
        b //= 10
        answer += 1
    else:
        answer = -1
        break

print(answer)