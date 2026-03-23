import sys
input = sys.stdin.readline

N = int(input())
arr = list(map(int, input().split()))

left, right = 0, 0
hap = float('inf')
for i in range(N):
    start = i + 1
    end = N - 1
    
    while start <= end:
        mid = (start + end) // 2
        
        if arr[i] + arr[mid] == 0:
            left = i
            right = mid
            break
        elif arr[i] + arr[mid] > 0:
            if abs(arr[i] + arr[mid]) < hap:
                hap = abs(arr[i] + arr[mid])
                left = i
                right = mid
            end = mid - 1
        else:
            if abs(arr[i] + arr[mid]) < hap:
                hap = abs(arr[i] + arr[mid])
                left = i
                right = mid
            start = mid + 1


print(arr[left], arr[right])