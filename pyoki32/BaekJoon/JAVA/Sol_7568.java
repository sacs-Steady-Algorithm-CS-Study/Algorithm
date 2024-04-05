import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_7568 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] x = new int[N];
        int[] y = new int[N];
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }
       for(int i = 0;i<N;i++){
           int num = 1;
           for(int j =0 ;j<N;j++){
               if(i!=j){
                   if(x[i]<x[j] && y[i]<y[j]){
                       num++;
                   }
               }
           }
           answer[i] = num;
       }
       StringBuilder sb = new StringBuilder();
       for(int i = 0 ;i<N;i++){
           sb.append(answer[i]).append(' ');
       }
       System.out.println(sb);
    }
}
