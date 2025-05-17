def solution(players, m, k):
    answer = 0
    servers = []
    for i in range(len(players)):
        if players[i] > 0:
            cur_server = 0
            for s in servers:
                if i < s[0]+k:
                    cur_server += s[1]
            if players[i] >= cur_server * m + m:
                answer += (players[i]-(cur_server * m + m))//m+1
                servers.append([i, (players[i]-(cur_server * m + m))//m+1])
    print(servers)        
    return answer