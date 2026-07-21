class Solution {

    public long[] solution(int[] arr, long l, long r) {
        int n = arr.length;

        long[] countPrefix = new long[n + 1];
        long[] sumPrefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            countPrefix[i + 1] = countPrefix[i] + arr[i];
            sumPrefix[i + 1]
                    = sumPrefix[i] + (long) arr[i] * arr[i];
        }

        long totalLength = countPrefix[n];
        long windowLength = r - l + 1;

        // brr[l] ~ brr[r]의 합
        long targetSum
                = prefixSum(arr, countPrefix, sumPrefix, r)
                - prefixSum(arr, countPrefix, sumPrefix, l - 1);

        long[] answer = new long[2];
        answer[0] = targetSum;

        long currentSum
                = prefixSum(arr, countPrefix, sumPrefix, windowLength);

        // 가능한 윈도우 시작 위치의 개수
        long maxStart = totalLength - windowLength + 1;

        if (maxStart == 1) {
            answer[1] = currentSum == targetSum ? 1 : 0;
            return answer;
        }

        long start = 1;

        // 현재 빠져나갈 값이 속한 arr 인덱스
        int outIndex = 0;
        
        long inPosition = start + windowLength;

        // 현재 들어올 값이 속한 arr 인덱스
        int inIndex = findRun(countPrefix, inPosition);

        long count = 0;

        while (true) {
            if (start == maxStart) {
                if (currentSum == targetSum) {
                    count++;
                }
                break;
            }

            inPosition = start + windowLength;

            while (outIndex + 1 < n
                    && start > countPrefix[outIndex + 1]) {
                outIndex++;
            }

            while (inIndex + 1 < n
                    && inPosition > countPrefix[inIndex + 1]) {
                inIndex++;
            }

            long outRemain
                    = countPrefix[outIndex + 1] - start + 1;

            long inRemain
                    = countPrefix[inIndex + 1] - inPosition + 1;

            long step = Math.min(outRemain, inRemain);

            // 마지막 시작 위치를 넘지 않도록 제한
            step = Math.min(step, maxStart - start);

            long difference
                    = (long) arr[inIndex] - arr[outIndex];

            if (difference == 0) {
                // 구간 내 모든 윈도우의 합이 동일하다.
                if (currentSum == targetSum) {
                    count += step;
                }

            } else {
                long delta = targetSum - currentSum;

                if (delta % difference == 0) {
                    long k = delta / difference;

                    if (0 <= k && k < step) {
                        count++;
                    }
                }
            }

            // step칸 이동한 이후의 윈도우 합
            currentSum += difference * step;

            // 윈도우 시작 위치 이동
            start += step;
        }

        answer[1] = count;

        return answer;
    }

    private long prefixSum(
            int[] arr,
            long[] countPrefix,
            long[] sumPrefix,
            long position
    ) {
        if (position <= 0) {
            return 0;
        }

        int index = findRun(countPrefix, position);

        long result = sumPrefix[index];

        long countInCurrentRun
                = position - countPrefix[index];

        result += countInCurrentRun * arr[index];

        return result;
    }

    private int findRun(long[] countPrefix, long position) {
        int left = 0;
        int right = countPrefix.length - 2;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (countPrefix[mid] < position
                    && position <= countPrefix[mid + 1]) {
                return mid;
            }

            if (position <= countPrefix[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return -1;
    }
}