import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_15684 {

    static int N, H, M;
    static int[][] ladder;
    static int Answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new int[H + 2][N + 1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = b + 1;
            ladder[a][b + 1] = b;
        }
        Answer = Integer.MAX_VALUE;
        for (int i = 0; i <= 3; i++) {
            setLadderLine(i, 0, N + 1);
            if (Answer != Integer.MAX_VALUE)
                break;
        }
        if (Answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
    }

    static void setLadderLine(int lineCnt, int setCnt, int idx) {
        if (lineCnt == setCnt) {
            boolean correct = true;
            for (int i = 1; i <= N; i++) {
                if (!playGame(i)) {
                    correct = false;
                    break;
                }
            }
            if (correct) {
                Answer = Math.min(Answer, lineCnt);
                System.out.println(Answer);
                System.exit(0);
            }
            return;
        }
        if (idx == N * (H + 1))
            return;
        int row = idx / N;
        int col = idx % N;
        if (col <= N - 1) {
            if (ladder[row][col] == 0 && ladder[row][col + 1] == 0) {
                ladder[row][col] = col + 1;
                ladder[row][col + 1] = col;
                setLadderLine(lineCnt, setCnt + 1, idx + 1);
                ladder[row][col] = 0;
                ladder[row][col + 1] = 0;
            }
        }
        setLadderLine(lineCnt, setCnt, idx + 1);
    }

    static boolean playGame(int start) {
        int line = start;
        for (int i = 1; i <= H + 1; i++) {
            if (ladder[i][line] != 0) {
                line = ladder[i][line];
            }
        }
        if (start == line) {
            return true;
        }
        return false;
    }

}
