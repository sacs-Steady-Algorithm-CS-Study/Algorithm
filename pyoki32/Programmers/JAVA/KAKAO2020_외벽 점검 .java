import java.util.*;

class KAKAO2022_외벽점검 {

    static boolean[] visited;
    static int[][] weakArr;
    static int ans;

    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;
        weakArr = new int[weak.length][weak.length * 2];
        int lastGap = n - weak[weak.length - 1] + weak[0];
        for (int i = 0; i < weakArr.length; i++) {
            for (int j = 0; j < weakArr.length; j++) {
                if (i == 0) weakArr[i][j] = weak[j];
                else {
                    if (j + 1 < weakArr.length) weakArr[i][j] = weakArr[i - 1][j + 1];
                    else {
                        weakArr[i][j] = weakArr[i - 1][(j + 1) % weak.length] + n;
                    }
                }
            }
        }

        int[] pick = new int[dist.length];
        visited = new boolean[dist.length];
        ans = Integer.MAX_VALUE;
        dfs(0, pick, dist);
        answer = ans;

        if (answer == Integer.MAX_VALUE) answer = -1;
        return answer;
    }

    static void dfs(int depth, int[] pick, int[] dist) {

        if (depth >= dist.length) {
            ans = Math.min(inputFriends(pick), ans);
            return;
        }
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                pick[depth] = dist[i];
                dfs(depth + 1, pick, dist);
                visited[i] = false;
            }
        }
    }

    static int inputFriends(int[] pick) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < weakArr.length; i++) {
            int friendCnt = 0;
            int checkCnt = 0;
            int weak_idx = 0;
            int pickIdx = 0;
            int end = -1;
            while (weak_idx < weakArr.length) {
                if (end < weakArr[i][weak_idx]) {
                    if (pickIdx >= pick.length) break;
                    end = weakArr[i][weak_idx] + pick[pickIdx];
                    pickIdx++;
                    friendCnt++;
                } else {
                    checkCnt++;
                    weak_idx++;
                }
            }
            if (checkCnt == weakArr.length) {
                res = Math.min(friendCnt, res);
            }
        }
        return res;
    }

}