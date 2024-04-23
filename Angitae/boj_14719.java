package Angitae;

import java.io.*;
import java.util.*;

public class boj_14719 {
    static int W, H; // W : 가로 길이 , H : 높이 길이
    static int arr[]; // 담아둘 값
    static int ltor[]; // 왼쪽에서 오른쪽 탐색
    static int rtol[]; // 오른쪽에서 왼쪽 탐색
    static int min[]; // 최소값
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new int[W];
        ltor = new int[W];
        rtol = new int[W];
        min = new int[W];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < W; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        left(); // 왼쪽에서 오른쪽으로 탐방하면서 배열 값 넣어주기
        right(); // 오른쪽에서 왼쪽으로 탐방하면서 배열 값 넣어주기
//        for(int i = 0; i < W; i++) {
//            System.out.print("l -> r " + ltor[i]);
//            System.out.println();
//            System.out.print("r -> l " + rtol[i]);
//        }
        cal();
        int ans = solve();
        System.out.println(ans);
    }
    static void left(){
        int max = Integer.MIN_VALUE;
        for(int i = 0; i <W; i++){
            max = Math.max(max, arr[i]);
            ltor[i] = max;
        }
    }

    static void right(){
        int max = Integer.MIN_VALUE;
        for(int i = W-1; i >= 0; i--){
            max = Math.max(max, arr[i]);
            rtol[i] = max;
        }
    }

    static void cal(){
        int minn = 0;
        for(int i = 0; i < W; i++){
            minn = Math.min(ltor[i], rtol[i]);
            min[i] = minn;
        }
    }

    static int solve(){
        int sum = 0 ;
        int tmp = 0;
        for(int i = 0; i < W; i++){
            tmp = min[i] - arr[i];
            sum += tmp;
        }
        return sum;
    }
}
