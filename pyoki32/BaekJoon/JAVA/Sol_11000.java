import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sol_18428 {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N;
    static int[] dRow = {-1, 1, 0, 0};
    static int[] dCol = {0, 0, -1, 1};
    static ArrayList<Point> teacherPoint;
    static char[][] map;
    static boolean ansCheck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        teacherPoint = new ArrayList<>();
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'T') {
                    teacherPoint.add(new Point(i, j));
                }
            }
        }
        ansCheck = false;
        setO(0, 3);
        if (ansCheck) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static void setO(int idx, int cnt) {

        if (cnt == 0) {
            if (ansCheck()) {
                ansCheck = true;
            }
            return;
        }
        if (idx > (N * N) - 1) {
            return;
        }
        int row = idx / N;
        int col = idx % N;
        if (map[row][col] == 'X') {
            map[row][col] = 'O';
            setO(idx + 1, cnt - 1);
            map[row][col] = 'X';
            setO(idx + 1, cnt);
        } else {
            setO(idx + 1, cnt);
        }
    }

    static boolean ansCheck() {

        for (Point p : teacherPoint) {
            for (int k = 0; k < 4; k++) {
                int len = 1;

                while (true) {
                    int nRow = p.r + dRow[k] * len;
                    int nCol = p.c + dCol[k] * len;
                    if (isRange(nRow, nCol)) {
                        if (map[nRow][nCol] == 'S') {
                            return false;
                        } else if (map[nRow][nCol] == 'O') {
                            break;
                        }
                    } else {
                        break;
                    }
                    len++;
                }
            }
        }
        return true;
    }

    static boolean isRange(int nRow, int nCol) {
        if (nRow < 0 || nRow > N - 1 || nCol < 0 || nCol > N - 1) return false;
        return true;
    }
}
