import java.util.*;

class PCCP_1번_붕대_감기 {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;

        int t = bandage[0];
        int x = bandage[1];
        int y = bandage[2];
        int[] attacksArr = new int[1001];
        int maxTime = Integer.MIN_VALUE;
        for (int i = 0; i < attacks.length; i++) {
            attacksArr[attacks[i][0]] = attacks[i][1];
            maxTime = Math.max(attacks[i][0], maxTime);
        }
        int seconds = 1;
        int nHealth = health;
        int nT = 0;
        boolean die = false;
        while (seconds <= maxTime) {
            if (attacksArr[seconds] > 0) {
                nHealth -= attacksArr[seconds];
                nT = 0;
                if (nHealth <= 0) {
                    die = true;
                    break;
                }
            } else {
                nT++;
                if (nT >= t) {
                    nHealth += y;
                    nT = 0;
                }
                nHealth += x;
                if (nHealth > health) nHealth = health;
            }
            seconds++;
        }

        if (die) {
            answer = -1;
        } else {
            answer = nHealth;
        }
        return answer;
    }
}