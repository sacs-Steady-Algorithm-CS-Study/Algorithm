package Programmers.JAVA;

import java.util.*;

class KAKAO2024_산_모양_타일링 {
    public int solution(int n, int[] tops) {
        int answer = 0;
        int [][] dp = new int[tops.length][4];

        //0:삼각형,1:왼쪽,2:오른쪽,3:위쪽
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[0][2] = 1;
        if(tops[0] == 1) dp[0][3] = 1;
        for(int i = 1;i<tops.length;i++){
            dp[i][0] = dp[i-1][0]+dp[i-1][1]+dp[i-1][2]+dp[i-1][3];
            dp[i][1] = dp[i-1][0]+dp[i-1][1]+dp[i-1][3];
            dp[i][2] = dp[i-1][0]+dp[i-1][1]+dp[i-1][2]+dp[i-1][3];
            if(tops[i] == 1)
                dp[i][3] = dp[i-1][0]+dp[i-1][1]+dp[i-1][2]+dp[i-1][3];
            for(int j = 0 ;j<4;j++){
                dp[i][j] %= 10007;
            }
        }
        for(int j = 0 ;j<4;j++){
            answer  += (dp[tops.length-1][j] % 10007);
        }
        answer %= 10007;
        return answer;
    }
}