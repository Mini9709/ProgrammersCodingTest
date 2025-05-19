def solution(info, n, m):
    answer = 0
    thief = [[[0]*m for i in range(n)] for j in range(len(info)+1)]
    thief[0][0][0] = 1
    
    for i in range(len(info)):
        a = info[i][0]
        b = info[i][1]
        
        for x in range(n):
            for y in range(m):
                if thief[i][x][y] == 0:
                    continue
                if x+a < n:
                    thief[i+1][x+a][y] = 1
                if y+b < m:
                    thief[i+1][x][y+b] = 1
                                  
    for x in range(n):
        for y in range(m):
            if thief[len(info)][x][y] == 1:
                return x
    return -1