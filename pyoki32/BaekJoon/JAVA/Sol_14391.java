import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_14391 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] paper = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                paper[i][j] = str.charAt(j) - '0';
            }
        }
        int max = Integer.MIN_VALUE;
        //전체 경우의수
        for (int t = 0; t < (1 << N * M); t++) {
            //0인경우 가로
            int sum = 0;
            for (int i = 0; i < N; i++) {
                int num = 0;
                for (int j = 0; j < M; j++) {
                    int k = i * M + j;
                    if ((t & (1 << k)) == 0) {
                        num *= 10;
                        num += paper[i][j];
                    } else {
                        sum += num;
                        num = 0;
                    }
                }
                sum += num;
            }
            //1인경우 세로
            for (int i = 0; i < M; i++) {
                int num = 0;
                for (int j = 0; j < N; j++) {
                    int k = i * M + j;
                    if ((t & (1 << k)) != 0) {
                        num *= 10;
                        num += paper[j][i];
                    } else {
                        sum += num;
                        num = 0;
                    }
                }
                sum += num;
            }
            max = Math.max(sum, max);
        }
        System.out.println(max);
    }
}
