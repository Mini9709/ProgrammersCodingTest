T = int(input())

for test_case in range(1, T + 1):
    num = int(input())

    if num == 1:
        print("B")

    elif num % 2 == 1:
        print("B")

    else:
        k, l = 0, num

        while l % 2 == 0:
            l //= 2
            k += 1

        if l > 1:
            print("A")
        else:
            if k % 2 == 1:
                print("B")
            else:
                print("A")