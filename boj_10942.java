import java.io.*;
import java.util.*;
public class boj_10942 {
    static String chk[];
    static int input[];
    static int n, m; // n : 칠판에 적은 숫자 갯수, m : 펠린드롬체크할 갯수(테케)
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        input = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i =1; i <= n; i++){
//            String tp = st.nextToken();
            input[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        for(int i = 0; i < m; i++){
            String tmp = "";
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(solve(a, b))
                sb.append("1");
            else
                sb.append("0");
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static boolean solve(int left, int right){
        while(left <= right) {
            if(input[left] != (input[right]))
                return false;
            left++;
            right--;
        }
        return true;
    }
}
