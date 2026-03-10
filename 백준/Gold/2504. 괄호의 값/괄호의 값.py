from collections import deque
import sys
read = sys.stdin.readline

str = read().strip()
cal = ""
answer = -1
q = deque()

for i in range(len(str)):

    if str[i] == '(':
        if i > 0:
            if str[i-1] == '(' or str[i-1] == '[':
                cal += '*('
            if str[i-1] == ')' or str[i-1] == ']':
                cal += '+'
        cal += '2'
        q.append(str[i])

    elif str[i] == '[':
        if i > 0:
            if str[i-1] == '(' or str[i-1] == '[':
                cal += '*('
            if str[i-1] == ')' or str[i-1] == ']':
                cal += '+'
        cal += '3'
        q.append(str[i])

    elif i == 0:
        answer = 0
        break

    elif str[i] == ')':
        if not q:
            answer = 0
            break
        elif q.pop() == '(':
            if str[i-1] == ')' or str[i-1] == ']':
                cal += ')'
        else:
            answer = 0
            break

    elif str[i] == ']':
        if not q:
            answer = 0
            break
        elif q.pop() == '[':
            if str[i-1] == ')' or str[i-1] == ']':
                cal += ')'
        else:
            answer = 0
            break

if answer == 0:
    print(answer)
elif q:
    print(0)
else:
    answer = eval(cal)
    print(answer)