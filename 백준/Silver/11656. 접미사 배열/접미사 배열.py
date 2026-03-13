s = input()
stack = []
for _ in range(len(s)):
    stack.append(s[_:len(s)])

print(*sorted(stack), sep='\n')