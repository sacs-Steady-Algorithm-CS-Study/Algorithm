package Angitae;

import java.io.*;
import java.util.*;

public class boj_2473 {

    static int N; // 용액의 수
    static long[] ans = new long[3]; // 정답용( 3개 용액 )
    static long[] liq; // 전체 용액
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        liq = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i =0; i < N; i++){
            liq[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(liq);
        long min = Long.MAX_VALUE;
        for(int i =0; i <N;i++){
            int left = i+1;
            int right = N-1;
            while(left < right){
                long sum = liq[i] + liq[left] + liq[right];
                if(min > Math.abs(sum)) {
                    min = Math.abs(sum);
                    ans[0] = liq[i];
                    ans[1] = liq[left];
                    ans[2] = liq[right];
                }
                    if(sum > 0)
                        right --;
                    else
                        left++;

                    if(sum == 0 )
                        break;

            }
        }
        System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
    }
}
