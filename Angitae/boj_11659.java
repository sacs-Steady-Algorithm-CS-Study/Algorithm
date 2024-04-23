package Angitae;

import java.io.*;
import java.util.*;

public class boj_11659 {
    static int[] arr;
    static int N,M;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            arr[0] = 0;
            arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
        }
        int ans;
        for(int i =0; i < M; i++){
            ans = 0;
            st = new StringTokenizer(br.readLine());
            int start =  Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            ans = arr[end]-arr[start-1];
            System.out.println(ans);
        }
    }
}
