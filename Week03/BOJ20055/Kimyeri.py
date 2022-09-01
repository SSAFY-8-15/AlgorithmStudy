from collections import deque

N,K = map(int,input().split())
A= list(map(int,input().split()))
cnt_step=0
on_belt=deque([i for i in range(N)])
off_belt=deque([i for i in range(2*N-1,N-1,-1)])
robot=[0]*N*2
while(True):
    if A.count(0)>=K : break
    cnt_step+=1
    on_belt.appendleft(off_belt.popleft()) # 앞에서 빠지고 올라가기
    off_belt.append(on_belt.pop()) # 뒤에서 빠자고 내려가기

    for i in list(reversed(on_belt)):
        next = i+1 if i+1!=2*N else 0
        if robot[on_belt[-1]] == 1:  # 내리는 위치에 로봇이 있다면 내리기
            robot[on_belt[-1]] = 0
        if robot[i]==1 and robot[next]==0 and A[next]!=0 : # 다음 칸에 로봇이 없고 내구성이 0이상이면 로봇이 있는 경우 옮기기
            robot[i],robot[next]=robot[next],robot[i]
            A[next]-=1 # 내구성 감소하기
    if robot[on_belt[0]] == 0 and A[on_belt[0]]!=0: # 올리는 위치에 로봇이 있다면 올리기
        robot[on_belt[0]] = 1
        A[on_belt[0]]-=1
print(cnt_step)