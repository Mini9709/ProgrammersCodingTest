import heapq

def solution(book_time):
    answer = 1
    room = []
    book_convert = [(int(s[:2])*60 + int(s[3:]), int(e[:2])*60 + int(e[3:])) for s, e in book_time]
        
    for s, e in sorted(book_convert):
        if not room:
            heapq.heappush(room, e)
        else :
            last = heapq.heappop(room)
            if s >= last+10:
                heapq.heappush(room, e)
            else:
                heapq.heappush(room, e)
                heapq.heappush(room, last)
                
    return len(room)