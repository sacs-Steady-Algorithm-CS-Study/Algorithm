import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    static class Player {
        int row;
        int col;
        int depth;


        public Player(int row, int col, int depth) {
            this.row = row;
            this.col = col;
            this.depth = depth;

        }
    }

    static int[] dRow = {1, -1, 0, 0};
    static int[] dCol = {0, 0, 1, -1};
    static int rLen, lLen;
    static int minA, maxA;
    static int minB, maxB;

    static char win;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = -1;
        rLen = board.length;
        lLen = board[0].length;
        minA = Integer.MAX_VALUE;
        minB = Integer.MAX_VALUE;
        maxA = Integer.MIN_VALUE;
        maxB = Integer.MIN_VALUE;
        win =' ';
        dfs(board, new Player(aloc[0], aloc[1], 0), new Player(bloc[0], bloc[1], 0), true);
        if(win == 'A'){
            answer =  minA+maxB;
        }else{
            answer = minB+maxA;
        }
        return answer;
    }

    static void dfs(int[][] board, Player playerA, Player playerB, boolean turnA) {

        //A
        boolean moveCheck = false;
        if (turnA) {
            for (int k = 0; k < 4; k++) {
                int nARow = playerA.row + dRow[k];
                int nACol = playerA.col + dCol[k];
                if (isRange(nARow, nACol)) {
                    if (board[nARow][nACol] == 1) {
                        int[][] nBoard = copyBoard(board);
                        moveCheck = true;
                        nBoard[playerA.row][playerA.col] = 0;
                        if (playerA.row == playerB.row && playerA.col == playerB.col) {
                            //A가 이김
                            if(minA > playerA.depth + 1 ){
                                minA = playerA.depth + 1;
                                maxB = playerB.depth;
                                win = 'A';
                            }
                            return;
                        }
                        dfs(nBoard, new Player(nARow, nACol, playerA.depth + 1), playerB, false);
                    }
                }
            }
            // A가 이동하지 않아 패배
            if (!moveCheck) {
                if(maxA < playerA.depth){
                    maxA = playerA.depth;
                    minB = playerB.depth;
                    win = 'B';
                }
                return;
            }

        } else {//B
            for (int k = 0; k < 4; k++) {
                int nBRow = playerB.row + dRow[k];
                int nBCol = playerB.col + dCol[k];
                if (isRange(nBRow, nBCol)) {
                    if (board[nBRow][nBCol] == 1) {
                        int[][] nBoard = copyBoard(board);
                        moveCheck = true;
                        nBoard[playerB.row][playerB.col] = 0;
                        if (playerA.row == playerB.row && playerA.col == playerB.col) {
                            //B가 이김
                            if(minB > playerB.depth + 1 ){
                                minB = playerB.depth + 1;
                                maxA = playerA.depth;
                                win = 'B';
                            }
                            return;
                        }
                        dfs(nBoard, new Player(nBRow, nBCol, playerB.depth + 1), playerB, true);
                    }
                }
            }
            // B가 이동하지 않아 패배
            if (!moveCheck) {
                if(maxB < playerB.depth){
                    maxB = playerB.depth;
                    minA = playerA.depth;
                    win = 'A';
                }
                return;
            }
        }

    }

    static boolean isRange(int nRow, int nCol) {
        if (nRow < 0 || nRow >= rLen || nCol < 0 || nCol >= lLen) return false;
        return true;
    }

    static int[][] copyBoard(int[][] board) {
        int[][] res = new int[rLen][lLen];
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < lLen; j++) {
                res[i][j] = board[i][j];
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        int[][] board = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[] aloc = {1, 0};
        int[] bloc = {1, 2};
        Solution s = new Solution();
        System.out.println(s.solution(board, aloc, bloc));
    }
}