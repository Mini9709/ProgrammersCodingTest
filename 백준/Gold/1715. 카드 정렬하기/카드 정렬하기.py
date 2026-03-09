import sys
import heapq
read = sys.stdin.readline

n = int(read())
card = [int(read()) for i in range(n)]
answer = 0

heapq.heapify(card)

if n == 1:
    print(0)

else:
    while len(card) > 2:
        x = heapq.heappop(card)
        y = heapq.heappop(card)
        answer += x + y
        heapq.heappush(card, x+y)

    x = heapq.heappop(card)
    y = heapq.heappop(card)
    answer += x + y

    print(answer)
#1 카드 정렬(정렬 알고리즘 작성)
#2 작은 두 수를 합하고 answer에 합하기
#3 합한 수를 배열에 넣고 #1 반복
#4 카드 배열길이가 1일 경우 반복문 종료