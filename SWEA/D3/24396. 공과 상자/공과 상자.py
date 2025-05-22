T = int(input())

for test_case in range(1, T+1):
    answer = 0
    b, w, x, y, z = map(int, input().split())

    if b > w:
        answer += (b-w) * x
        b = w
    elif b < w:
        answer += (w-b) * y
        w = b

    if x+y < z*2:
        answer += z*2*b
    else:
        answer += (x+y)*b

    print(answer)