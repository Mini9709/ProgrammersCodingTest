T = int(input())

def dfs(x):
    global max_num

    if x == change:
        max_num = max(max_num, int("".join(map(str, num_list))))
        return

    else:
        for i in range(len(num)-1):
            for j in range(i+1, len(num)):
                num_list[i], num_list[j] = num_list[j], num_list[i]
                if [int("".join(map(str, num_list))), x] not in visit:
                    dfs(x+1)
                    visit.append([int("".join(map(str, num_list))), x])
                num_list[i], num_list[j] = num_list[j], num_list[i]


for test_case in range(1, T + 1):
    num, change = input().split()
    change = int(change)
    num_list = []
    visit = []
    max_num = 0

    for i in range(len(num)):
        num_list.append(num[i])
    if len(num) == 1:
        print("#%d %d" %(test_case, int(num)))
    else:
        dfs(0)
        print("#%d %d" %(test_case, max_num))