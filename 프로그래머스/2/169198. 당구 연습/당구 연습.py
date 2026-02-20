def solution(m, n, startX, startY, balls):
    answer = []
    for i in balls:
        arr = [[startX, startY, i[0], i[1]], [m - startX, startY, m - i[0], i[1]], [startY, startX, i[1], i[0]], [n - startY, startX, n - i[1], i[0]]]
        ans = 0
        for j in arr:
            if j[1] == j[3] and j[0] > j[2]:
                continue
            temp = (j[0]+j[2])**2 + (j[1]-j[3])**2
            if ans == 0:
                ans = temp
            else:
                ans = min(ans, temp)
        answer.append(ans)
    return answer