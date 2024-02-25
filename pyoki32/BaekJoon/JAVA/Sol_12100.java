import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_12100 {

    static int N, answer;
    static int[][] board;
    //-> <-  DOWN UP
    static int[] dRow = {0, 0, -1, 1};
    static int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] pickIdx = new int[5];
        answer = Integer.MIN_VALUE;
        setDir(pickIdx, 0);
        System.out.println(answer);
    }

    static void setDir(int[] pickIdx, int round) {
        if (round >= 5) {
            int[][] initBoard = copyBoard(board);
            for (int i = 0; i < 5; i++) {
                initBoard = playGame(initBoard, pickIdx[i]);
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    answer = Math.max(initBoard[i][j], answer);
                }

            }
            return;
        }
        for (int k = 0; k < 4; k++) {
            pickIdx[round] = k;
            setDir(pickIdx, round + 1);
        }
    }

    static int[][] playGame(int[][] initBoard, int dir) {
        int[][] useBoard = copyBoard(initBoard);

        for (int line = 0; line < N; line++) {
            switch (dir) {
                //right
                case 0:
                    for (int i = N - 1; i >= 0; i--) {
                        if (i > 0) {
                            int k = i - 1;
                            boolean numCheck = false;
                            while (k >= 0) {
                                if (useBoard[line][k] != 0) {
                                    numCheck = true;
                                    break;
                                }
                                k--;
                            }
                            if (numCheck) {
                                if (useBoard[line][i] == useBoard[line][k]) {
                                    useBoard[line][i] += useBoard[line][i];
                                    useBoard[line][k] = 0;
                                }
                            }
                        }
                        int j = i + 1;
                        boolean zeroCheck = false;
                        while (j < N) {
                            if (useBoard[line][j] == 0) {
                                zeroCheck = true;
                            } else {
                                break;
                            }
                            j++;
                        }
                        if (zeroCheck) {
                            j--;
                            useBoard[line][j] = useBoard[line][i];
                            useBoard[line][i] = 0;
                        }
                    }
                    break;
                //left
                case 1:
                    for (int i = 0; i < N; i++) {
                        if (i < N - 1) {
                            int k = i + 1;
                            boolean numCheck = false;
                            while (k < N) {
                                if (useBoard[line][k] != 0) {
                                    numCheck = true;
                                    break;
                                }
                                k++;
                            }
                            if (numCheck) {
                                if (useBoard[line][i] == useBoard[line][k]) {
                                    useBoard[line][i] += useBoard[line][i];
                                    useBoard[line][k] = 0;
                                }
                            }
                        }
                        int j = i - 1;
                        boolean zeroCheck = false;
                        while (j >= 0) {
                            if (useBoard[line][j] == 0) {
                                zeroCheck = true;
                            } else {
                                break;
                            }
                            j--;
                        }
                        if (zeroCheck) {
                            j++;
                            useBoard[line][j] = useBoard[line][i];
                            useBoard[line][i] = 0;
                        }
                    }
                    break;
                //down
                case 2:
                    for (int i = N - 1; i >= 0; i--) {
                        if (i > 0) {
                            int k = i - 1;
                            boolean numCheck = false;
                            while (k >= 0) {
                                if (useBoard[k][line] != 0) {
                                    numCheck = true;
                                    break;
                                }
                                k--;
                            }

                            if (numCheck) {
                                if (useBoard[i][line] == useBoard[k][line]) {
                                    useBoard[i][line] += useBoard[i][line];
                                    useBoard[k][line] = 0;
                                }
                            }
                        }
                        int j = i + 1;
                        boolean zeroCheck = false;
                        while (j < N) {
                            if (useBoard[j][line] == 0) {
                                zeroCheck = true;
                            } else {
                                break;
                            }
                            j++;
                        }
                        if (zeroCheck) {
                            j--;
                            useBoard[j][line] = useBoard[i][line];
                            useBoard[i][line] = 0;
                        }
                    }
                    break;
                //up
                case 3:
                    for (int i = 0; i < N; i++) {
                        if (i < N - 1) {
                            int k = i + 1;
                            boolean numCheck = false;
                            while (k < N) {
                                if (useBoard[k][line] != 0) {
                                    numCheck = true;
                                    break;
                                }
                                k++;
                            }
                            if (numCheck) {
                                if (useBoard[i][line] == useBoard[k][line]) {
                                    useBoard[i][line] += useBoard[i][line];
                                    useBoard[k][line] = 0;
                                }
                            }
                        }
                        int j = i - 1;
                        boolean zeroCheck = false;
                        while (j >= 0) {
                            if (useBoard[j][line] == 0) {
                                zeroCheck = true;
                            } else {
                                break;
                            }
                            j--;
                        }
                        if (zeroCheck) {
                            j++;
                            useBoard[j][line] = useBoard[i][line];
                            useBoard[i][line] = 0;
                        }
                    }
                    break;
            }
        }
        return useBoard;
    }

    static boolean isRange(int nRow, int nCol) {
        if (nRow < 0 || nRow > N - 1 || nCol < 0 || nCol > N - 1) return false;
        return true;
    }

    static int[][] copyBoard(int[][] initBoard) {
        int[][] cBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cBoard[i][j] = initBoard[i][j];
            }
        }
        return cBoard;
    }
}
