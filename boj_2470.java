import java.io.*;
import java.util.*;

public class boj_2470 {

    static int N;
    static int[] ans = new int[2];
    static int[] input;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        input = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input); // 비교를 위해 오름차순 정렬해두기
        int left = 0;
        int right = N-1;
        int min = Integer.MAX_VALUE;
        while(left < right){
            int sum = input[left] + input[right];
            if(min > Math.abs(sum))
            {
                min = Math.abs(sum);
                ans[0] = input[left];
                ans[1] = input[right];
                if(sum == 0 )
                    break;
            }
                if(sum < 0)
                    left++;
                else
                    right--;
        }
        System.out.println(ans[0] +" "+ ans[1]);
//        System.out.println(ans[1]);
    }
}
