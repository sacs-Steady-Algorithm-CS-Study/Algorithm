import java.io.*;
import java.util.*;

public class boj_1520 {
    static int m,n;
    static int map[][];
    static int dy[] = {-1, 0, 1, 0};
    static int dx[] = {0, 1, 0, -1};
    static class Node{
        int y, x;
        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    static int cnt;
    static boolean visited[][];
    static int[][] dp; // 이 방법 아니고는 안 풀림 -- 추가버전
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        dp = new int[m][n];
        visited = new boolean[m][n];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1; // 초기화 필요 -- 추가버전
            }
        }
        Node tmp = new Node(0, 0);
        System.out.println(sol(tmp));
    }
    static int sol(Node tmp) {
        int y = tmp.y;
        int x = tmp.x;
        if(y == m-1 && x == n-1) return 1;
        if (dp[y][x] != -1) return dp[y][x];

        dp[y][x] = 0;
        for (int i=0; i<4; i++) {
            int dix = x + dx[i];
            int diy = y + dy[i];

            if (diy <0 || diy >= m || dix < 0 || dix >= n)
                continue;
            if (map[y][x] > map[diy][dix]) {
                dp[y][x] += sol(new Node(diy, dix));
            }
        }
        return dp[y][x];
    }
}
