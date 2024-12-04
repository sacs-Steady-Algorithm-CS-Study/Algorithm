import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_snail {
    static StringBuilder ans  = new StringBuilder();
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(),"");

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(),"");

        map = new int[n][n];
        int m = Integer.parseInt(st.nextToken());
        sol(n, m);
        System.out.println(ans);
    }
    static void sol(int n, int m){
        int midx = n /2; // 가운데 값은 2로 나눈 몫(배열은 0부터 시작되니까)
        int midy = n /2;
        int value = 1;
        int limit = 1;
        int ansx = 0;
        int ansy = 0;
        while(true) {
            for(int i = 0 ; i < limit; i++){
                map[midx--][midy] = value++;
            }
            if(value > n*n) break;
            for(int j = 0; j < limit; j++){
                map[midx][midy++] = value++;
            }
            limit++;
            for(int q = 0; q < limit; q++){
                map[midx++][midy] = value++;
            }
            for(int w = 0; w < limit; w++){
                map[midx][midy--] = value++;
            }
            limit++;
        }

        for(int a = 0; a <n; a++){
            for(int b = 0; b <n; b++){
                ans.append(map[a][b]).append(" ");
                 if(map[a][b] == m){
                     ansx = a+1;
                     ansy = b+1;
                 }
            }
            ans.append("\n");
        }
        ans.append(ansx).append(" ").append(ansy);
    }
}