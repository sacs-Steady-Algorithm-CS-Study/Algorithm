package Angitae;

import java.io.*;
import java.util.*;

public class boj_14225 {
    static int N;// 갯수
    static int[] num;
    static HashSet<Long> hs = new HashSet<>();
    static long ans;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        num = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        solve();
    }
    static void solve(){
//        long oneSum = 0;
//        long zeroSum = 0;
        long count = 0;
        for(int i = (int)Math.pow(2, N-1); i < (int)Math.pow(2, N); i++){
            long oneSum = 0;
            long zeroSum = 0;
            String[] str = Integer.toString(i, 2).split("");
            for(int j = 0; j < str.length; j++){
                if(str[j].equals("1"))
                    oneSum += num[j];
                else if(str[j].equals("0"))
                    zeroSum += num[j];
            }
            System.out.println("oneSum = " + oneSum);
            System.out.println("zeroSum = " + zeroSum);
            hs.add(oneSum);
            hs.add(zeroSum);
        }

        while(true){
            if(!hs.contains(count)){
                break;
            }
            count++;
        }
        System.out.println(count);
    }
}
