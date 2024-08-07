package pyoki32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sol_3079 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        long [] arr = new long[N];
        long max = Long.MIN_VALUE;
        for(int i = 0 ; i <N ;i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Long.parseLong(st.nextToken());
            max = Math.max(arr[i],arr[i]);
        }
        long left = 0;
        long right = (long) max * M;
        long mid = 0;
        long answer = Long.MAX_VALUE;
        while(left <= right){
            mid = (left + right) / 2;
            long passCnt = calPassCnt(arr,mid);
            if(passCnt >= M){
                right = mid - 1;
                answer = Math.min(mid,answer);
            }else{
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }
    static long calPassCnt(long [] arr ,long time){
       long res = 0;
       for(int i = 0 ;i< arr.length;i++){
           res += time /arr[i] ;
       }
       return res;
    }
}