import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sol_15684 {

    static int N, M, H;
    static boolean [][][] connectionCheck;
    static boolean [][][] visitedCheck;
    static int minCnt [];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        connectionCheck = new boolean[M+2][N+1][N+1];

        for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                connectionCheck[a][b][b+1] = true;
                connectionCheck[a][b+1][b] = true;
        }
        minCnt = new int[N+1];

        for(int i = 1 ; i<=N ;i++){
                minCnt[i] = Integer.MAX_VALUE;
                int r = 1;
                int c = i;

        }
    }
    static void setLine(){

    }
    static boolean move(int r , int c , int target){
            while (r < M+2){
                if(r == M+1){
                    if(target == c){
                        return true;
                    }
                    break;
                }
                if(c-1 > 0){
                    if(connectionCheck[r][c][c-1]){
                        c--;
                    }
                }
                else if(c+1 < N+1){
                    if(connectionCheck[r][c][c+1]){
                        c++;
                    }
                }
                else{
                        r++;
                }
            }
        return  false;
    }
}
