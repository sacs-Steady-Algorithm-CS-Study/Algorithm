package pyoki32.BaekJoon.JAVA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Sol_1450 {
    static int N;
    static long C;
    static long[] arr;
    static ArrayList<Long> list1, list2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Long.parseLong(st.nextToken());
        arr = new long[N];
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        int half = N / 2;
        makeSum(0, half, 0, half, 0, 1);
        makeSum(half, N, 0, half, 0, 2);
        Collections.sort(list1);
        Collections.sort(list2);
        int answer = 1;
        for(long list1Sum : list1){
            if(list1Sum <= C) answer++;
            int targetIdx = -1;
            for(int i = list2.size()-1 ; i>=0 ;i--){
                long tarGet = list1Sum + list2.get(i);
                if(tarGet <= C){
                    targetIdx = i;
                    break;
                }
            }
            if(targetIdx != -1){
                answer += targetIdx+1;
            }
        }
        for(long list2Sum : list2){
            if(list2Sum <= C ) answer++;
        }
        System.out.println(answer);
    }

    static void makeSum(int startIdx, int endIdx, int depth, int cnt, long sum, int listNum) {
        if (depth != 0 && sum != 0) {
            if (listNum == 1) {
                list1.add(sum);
            } else {
                list2.add(sum);
            }
        }
        if (depth > cnt) {
            return;
        }
        for (int i = startIdx; i < endIdx; i++) {
            makeSum(i + 1, endIdx, depth + 1, cnt + 1, sum + arr[i], listNum);
        }
    }
}
