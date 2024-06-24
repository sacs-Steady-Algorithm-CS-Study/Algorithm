import java.util.*;

class KAKAO2022_양궁대회 {
    static int[] res;
    static int maxRes;

    public int[] solution(int n, int[] info) {
        int[] answer = new int[11];
        res = new int[11];
        maxRes = 0;
        int[] pickedArrowCnt = new int[11];
        shootArrow(info, pickedArrowCnt, 0, n);
        for (int i = 0; i <= 10; i++) {
            answer[i] = res[i];
        }
        if (maxRes == 0) {
            answer = new int[1];
            answer[0] = -1;
        }
        return answer;
    }

    public void shootArrow(int[] info, int[] pickedArrowCnt, int idx, int ArrowCnt) {

        if (idx > pickedArrowCnt.length - 1) {
            int getPoint = calPoint(info, pickedArrowCnt);
            if (maxRes < getPoint) {
                maxRes = getPoint;
                for (int i = 0; i < pickedArrowCnt.length; i++) {
                    res[i] = pickedArrowCnt[i];
                }
            } else if (maxRes == getPoint) {
                if (changeArrCheck(pickedArrowCnt)) {
                    for (int i = 0; i < pickedArrowCnt.length; i++) {
                        res[i] = pickedArrowCnt[i];
                    }
                }
            }
            return;
        }
        //이기거나
        if (ArrowCnt > info[idx]) {
            pickedArrowCnt[idx] = (info[idx] + 1);
            shootArrow(info, pickedArrowCnt, idx + 1, ArrowCnt - (info[idx] + 1));
            pickedArrowCnt[idx] = 0;
        }
        //비기거나 지거나
        for (int c = 0; c <= info[idx]; c++) {
            if (ArrowCnt >= (info[idx] - c)) {
                pickedArrowCnt[idx] = info[idx] - c;
                shootArrow(info, pickedArrowCnt, idx + 1, ArrowCnt - (info[idx] - c));
                pickedArrowCnt[idx] = 0;
            }
        }
    }

    public int calPoint(int[] info, int[] pickedArrowCnt) {

        int lion = 0;
        int apeach = 0;
        for (int i = 0; i < pickedArrowCnt.length; i++) {
            if (info[i] < pickedArrowCnt[i]) {
                lion += (10 - i);
            } else {
                if (info[i] > 0) apeach += (10 - i);
            }
        }
        return lion - apeach;
    }

    public boolean changeArrCheck(int[] pickedArrowCnt) {
        for (int i = pickedArrowCnt.length - 1; i >= 0; i--) {
            if (res[i] < pickedArrowCnt[i]) return true;
            else if (res[i] > pickedArrowCnt[i]) break;
        }
        return false;
    }
}