import java.io.*;
import java.util.*;

public class boj_14888 {
    static int N;
    static int input[];
    static int arr[] = new int[4]; // + - * / 갯수 받기
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        input = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
//            st = new StringTokenizer(br.readLine());
            input[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, input[0]);
        System.out.println(max);
        System.out.println(min);
    }
    static void solve(int depth, int sum){
        if(depth == N-1){
            min = Math.min(sum, min);
            max = Math.max(sum, max);
            return;
        }
        for(int i = 0; i < 4; i++){
            if(arr[i] > 0 ){
                arr[i]-=1;
                if(i == 0){
                    solve(depth+1, sum +input[depth+1]);
                }else if(i == 1){
                    solve(depth+1, sum - input[depth+1]);
                }else if(i == 2){
                    solve(depth+1, sum * input[depth+1]);
                }else if(i == 3){
                    solve(depth+1, sum / input[depth+1]);
                }
                arr[i]+=1;
            }
        }
    }
}
