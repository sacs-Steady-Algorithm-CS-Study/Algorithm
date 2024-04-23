package Angitae;

import java.io.*;
import java.util.*;

public class boj_15684 {
    static int M, N, H; // M : 가로줄, N : 세로줄, H : 가로선 놓을 수 있는 횟수
    static int map[][]; // M * N 배열 만들기
    static int dy[] = {0, 0};
    static int dx[] = {-1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        for(int i = 0; i <N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
            map[a][b+1] = 1;
        }
        int answer = 0;
        answer = solve();
        System.out.println(answer);
    }
    static int solve(){
        boolean visited[][] = new boolean[M][N];
        int ans = 0;
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){

            }
        }
        return ans;
    }
}
