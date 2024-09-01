import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_2239 {
    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int[][] board;
    static boolean[][] rowCheck;
    static boolean[][] colCheck;
    static boolean[][] areaCheck;
    static ArrayList<Point> zeroArr;
    static int answerCnt;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[9][9];
        rowCheck = new boolean[9][10];
        colCheck = new boolean[9][10];
        areaCheck = new boolean[9][10];
        zeroArr = new ArrayList<>();
        sb = new StringBuilder();
        int zeroCnt = 0;
        answerCnt = 0;
        for (int i = 0; i < 9; i++) {
            String str = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = str.charAt(j) - '0';
                if (board[i][j] == 0) {
                    zeroArr.add(new Point(i, j));
                } else {
                    int areaNum = getArea(i, j);
                    rowCheck[i][board[i][j]] = true;
                    colCheck[j][board[i][j]] = true;
                    areaCheck[areaNum][board[i][j]] = true;
                }
            }
        }
        zeroCnt = zeroArr.size();
        dfs(9, zeroCnt, 0);
        System.out.println(sb.toString());
    }

    static void dfs(int n, int zeroCnt, int depth) {
        if (answerCnt > 0) return;
        if (depth == zeroCnt) {
            if (answerCnt == 0) {
                answerCnt++;
                printBoard();
            }
            return;
        }
        Point p = zeroArr.get(depth);
        int row = p.row;
        int col = p.col;
        for (int k = 1; k <= n; k++) {
            int areaNum = getArea(row, col);
            if (!rowCheck[row][k] && !colCheck[col][k] && !areaCheck[areaNum][k]) {
                board[row][col] = k;
                rowCheck[row][k] = true;
                colCheck[col][k] = true;
                areaCheck[areaNum][k] = true;
                dfs(n, zeroCnt, depth + 1);
                board[row][col] = 0;
                rowCheck[row][k] = false;
                colCheck[col][k] = false;
                areaCheck[areaNum][k] = false;
            }
        }
    }

    static int getArea(int i, int j) {
        return i / 3 + j / 3 + (i / 3) * 2;
    }

    static void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
            }
            sb.append('\n');
        }
    }
}