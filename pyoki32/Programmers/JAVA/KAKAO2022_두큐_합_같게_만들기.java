import java.util.*;

class KAKAO2022_두큐_합_같게_만들기 {
    static int len;

    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        long total = 0;
        len = queue1.length;
        int[] arr = new int[len * 2];
        for (int i = 0; i < len; i++) {
            total += (long) queue1[i];
            total += (long) queue2[i];
            arr[i] = queue1[i];
            arr[i + queue1.length] = queue2[i];
        }

        total /= 2;
        int leftIdx = 0;
        int rightIdx = 0;

        int cnt = Integer.MAX_VALUE;
        long sum = arr[leftIdx];
        while (leftIdx <= rightIdx) {
            if (sum >= total) {
                if (sum == total) {
                    // System.out.println("  "+leftIdx+" "+rightIdx);
                    cnt = Math.min(cnt, calCnt(leftIdx, rightIdx));
                }
                sum -= arr[leftIdx];
                leftIdx++;
            } else {
                rightIdx++;
                if (rightIdx == len * 2) break;
                sum += arr[rightIdx];
            }
        }


        answer = cnt;
        if (cnt == Integer.MAX_VALUE) answer = -1;
        return answer;
    }

    static int calCnt(int leftIdx, int rightIdx) {
        int res = 0;
        res = (leftIdx + 1);
        if (rightIdx > len - 1) {
            res += rightIdx - len;
        } else if (rightIdx == len - 1) {
            res -= 1;
        } else {
            res = Integer.MAX_VALUE;
        }

        return res;
    }
}