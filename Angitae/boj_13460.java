package Angitae;

import java.io.*;
import java.util.*;

public class boj_13460 {

    static class Node{
        int y, x;
        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
        public Node(){

        }
    }

    static int dy[] = {-1, 0, 1, 0};
    static int dx[] = {0, 1, 0, -1};
    static int N, M; // N : ㅅㅔ로 , M : 가로
    static char [][] map;
    static boolean redEnd, blueEnd; // 종료 체
    static boolean redvisit[][];
    static boolean bluevisit[][];
    static Node blue, red;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        redvisit = new boolean[N][M];
        bluevisit = new boolean[N][M];
//        Node blue = new Node();
//        Node red =  new Node();
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++){
            st  = new StringTokenizer(br.readLine());
            String tp = st.nextToken();
            for(int j = 0; j <tp.length(); j++){
                map[i][j] = tp.charAt(j);
                if(map[i][j] == 'B')
                    blue = new Node(i, j);
                if(map[i][j] == 'R')
                    red = new Node(i , j);
            }
        }
        for(int i = 0; i < 4; i++){ // 4 방향으로 굴려보자
            moveRed(i, red);
            moveBlue(i, blue);
            ans = chk();
        }
//        ans = solve(red, blue,0);
        System.out.println(ans);
    }
    static void moveRed(int dir, Node red){

    }
    static void moveBlue(int dir, Node blue){

    }
    static int chk(){ // 가있는지는 여기서 체크해보기
        int ans = 0;
        return ans;
    }


//    static int solve(Node red, Node blue, int cnt){
//        int answer = Integer.MAX_VALUE;
//        if(cnt > 10){ // 10번 이상시 안 됨(실패)
//            System.out.println(-1);
//            System.exit(0);
//        }
//        if(redEnd && !blueEnd) // 빨간 구슬만 빠지면 성공
//        {
//            return cnt;
//        }
//        for(int i = 0; i < 4; i++){ // 한 방향으로 동시에 움직이니까 서남북만 보면 됨(구슬 동시에 움직임)
//            Node nRed;
//            Node nBlue;
//            if(redEnd){ // 도달했는지 체크
//                nRed = new Node(red.y, red.x);
//            }
//            else{
//                nRed = new Node(red.y+dy[i], red.x +dx[i]);
//            }
//            if(blueEnd){ // 도달했는지 체크
//                nBlue = new Node(blue.y, blue.x);
//            }else{
//                nBlue = new Node(blue.y+dy[i], blue.x+dx[i]);
//            }
//            if(redEnd && blueEnd) // 같이 빠지면 실패
//                continue;
//            if(isRange(nRed.y, nRed.x) || isRange(nBlue.y, nBlue.x)) // 범위 체크
//                continue;
//            // 기본 조건
////            if(map[nRed.y][nRed.x] == '#')
////                continue; // 빨간 구슬이 가려는 곳이 벽이거나 파란구슬이 있으면 안 됨
////            if(map[nBlue.y][nBlue.x] == '#')
////                continue; // 파란 구슬이 가려는 곳이 벽이거나 빨간구슬이 있으면 안 됨
//            if(nRed.y == nBlue.y && nRed.x == nBlue.x)
//                continue; // 둘이 같은 곳에 있을 수가 없음
//            if((nRed.y == blue.y && nRed.x == blue.x) || (nBlue.y == red.y && nBlue.x == red.x))
//                continue; // 서로 교차될 수가 없음
//            if(map[nRed.y][nRed.x] == 'O') {
//                redEnd = true;
//            }
//            if(map[nBlue.y][nBlue.x] == 'O')
//                blueEnd = true;
//            answer = Math.min(solve(nRed, nBlue, cnt + 1), answer);
//            redEnd = false;
//            blueEnd = false;
//        }
//
//        return answer;
//    }


    static boolean isRange(int y, int x){
        return(y < 0 || y >= N || x < 0 || x >= M);
    }
}
