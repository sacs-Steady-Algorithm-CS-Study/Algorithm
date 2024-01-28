import java.io.*;
import java.util.*;

public class boj_11729 {
    static int N;
    static int cnt;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        solve(N, 'A', 'C', 'B');
        System.out.println(cnt);
        System.out.println(sb.toString());
    }
    static void solve(int n, char start, char end, char mid){
       if(n == 1){
//           sb.append(start).append(" ").append(end).append("\n");
           move(start, end, "tmp");
           cnt++;
           return;
       }
       solve(n-1, start, mid, end);
//       sb.append(start).append(" ").append(end).append("\n");
        move(start, end, "here");
       cnt++;
       solve(n-1, mid, end, start);
    }
    static void move(char start, char end, String tp){
        sb.append(start).append(" ").append(end).append(tp).append(" ").append("\n");
    }
}
