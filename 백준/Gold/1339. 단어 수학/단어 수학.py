N = int(input())

alp = [0 for i in range(26)]

for i in range(N):
    temp = input()
    for j in range(len(temp)):
        alp[ord(temp[j])-65] += 10**(len(temp)-j-1)

alp = sorted(alp, reverse=True)
for i in range(10):
    alp[i] *= (9-i)

print(sum(alp))