package Angitae;

import java.io.*;
import java.util.*;

public class boj_2573 {


    //  빙산 녹을 때 큐에서 뺄 때 어느 지점(x, y) 값의 val 감소시킬지 저장하려궁
    static class Node{
        int y, x, val;
        public Node(int y, int x, int val){
            this.y = y;
            this.x = x;
            this.val = val;
        }
    }
    static class Pow{
        int y, x;
        public Pow(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    static int N, M; // 행, 열
    static int dy[] = {-1, 0, 1, 0};
    static int dx[] = {0, 1, 0, -1};
    static int map[][];
//    static int arr[][];
//    static boolean visited[][];
    static int ans;
    public static void main(String[] args) throws IOException{
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
//        arr = new int[N][M];
//        visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if(isSeparate()){ // 처음 빙산이 주어졌을때 2개면 0
            System.out.println("0");
            System.exit(0);
        }

        // 녹는 서비스 구현 --> 얼마만큼 감소시킬지 계산 후, 큐에 넣기
        // 10년 안에 분리되던지 아님 다 녹음
        for(int i = 1; i <= 10; i++){
            melt(); // 녹는 거 구현
            for(int a = 0; a < N; a++){
                for(int b = 0; b < M; b++){
//                    System.out.print(map[a][b] + " ");
                }
//                System.out.println();
            }
            if(isSeparate()) { // 분리 되었는지 boolean으로 체크
                ans = 1;
                System.out.println(i); // 녹았으면 시간 얼마인지 return
                System.exit(0);
            }
//            init();
        }
        if(ans != 1)
            System.out.println("0");
    }
    static void melt(){
        Queue<Node> q = new LinkedList<>(); // q에 값을 넣을 예정
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                int cnt = 0;
                if(map[i][j] != 0){
                    for(int w = 0; w < 4; w++){
                        int dix = i + dy[w];
                        int diy = j + dx[w];
                        if(dix < 0 || dix >= N || diy < 0 || diy >= M)
                            continue;
                        if(map[dix][diy] == 0)
                            cnt++;
                    }
                    q.add(new Node(i, j, cnt));
                }
            }
        }
        while(!q.isEmpty()){
            Node tp = q.poll();
            map[tp.y][tp.x] -= tp.val;
            if(map[tp.y][tp.x] <0 )
                map[tp.y][tp.x] = 0;
        }

    }
    static boolean isSeparate(){
        int cnt = 0;
        cnt = check(cnt);
        if(cnt > 1) {
            return true;
        }
        return false;
    }
    static int check(int cnt){
        Queue<Pow> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M] ;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] != 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    q.add(new Pow(i, j));
                    while(!q.isEmpty()) {
                        Pow pw = q.poll();
                        for(int a=0; a< 4; a++){
                            int dix = pw.y +dy[a];
                            int diy = pw.x +dx[a];
                            if(dix <0 || dix >= N || diy <0 || diy >= M)
                                continue;
                            if(map[dix][diy] != 0 && !visited[dix][diy]){
//                                System.out.println("i : " + i + " j : "+ j + "map[i][j] :" + map[i][j]);
//                                System.out.println("dix : "+ dix + " diy : " + diy+ "map[dix][diy] : " + map[dix][diy]);
                                visited[dix][diy] = true;
                                q.add(new Pow(dix, diy));
                            }
                        }
                    }
                    cnt++;
//                    System.out.println(i + " 값, " + j+" -> "+map[i][j] + ": map[i][j]" + cnt);
//                    cnt++;
                }
            }
        }
        return cnt;
    }
//    static void init(){
//        visited = new boolean[N][M]; //초기화
//    }

}
