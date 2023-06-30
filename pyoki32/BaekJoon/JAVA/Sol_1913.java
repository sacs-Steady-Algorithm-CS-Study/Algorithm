import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_1913 {

    static int N, K;
    static int[][] board;
    static int answerR, answerC;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        answerR = -1;
        answerC = -1;
        board = new int[N][N];

        putNumber(N * N, N, 0, 0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]).append(' ');
            }
            sb.append("\n");
        }
        answerR++;
        answerC++;
        sb.append(answerR + " " + answerC);
        System.out.println(sb);
    }
    static void putNumber(int num, int len, int startR, int startC) {
        if (len < 1) return;

        for (int i = startR; i < startR + len; i++) {
            board[i][startC] = num;
            if (num == K) {
                answerR = i;
                answerC = startC;
            }
            num--;
        }
        for (int i = startC + 1; i < startC + len; i++) {
            board[startR + (len - 1)][i] = num;
            if (num == K) {
                answerR = startR + (len - 1);
                answerC = i;
            }
            num--;
        }
        for (int i = startR + (len - 1) - 1; i >= startR; i--) {
            board[i][startC + (len - 1)] = num;
            if (num == K) {
                answerR = i;
                answerC = startC + (len - 1);
            }
            num--;
        }
        for (int i = startC + (len - 1) - 1; i > startC; i--) {
            board[startR][i] = num;
            if (num == K) {
                answerR = startR;
                answerC = i;
            }
            num--;
        }
        putNumber(num, len - 2, startR + 1, startC + 1);
    }
}