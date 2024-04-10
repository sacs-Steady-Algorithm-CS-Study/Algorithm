package Angitae;

import java.io.*;
import java.util.*;
public class boj_12100 {
    static int n;
    static int map[][];
//    static int tmp[][]; // 맵 원복시키기 위해 담아두는 임시 배열
    static int max = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        map = new int[n][n];
//        tmp = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve(0); // 최대 5번까지만 움직임
        System.out.println(max);
    }
    static void solve(int depth){
        if(depth == 5){
            chk();
            return;
        }
        int tmp[][] = new int[n][n];
        copy(tmp);
        for(int i = 0; i < 4; i++){
            move(i); // 여기서 상하좌우로 이동 쫙 시켜주기
            solve(depth+1);
            init(tmp); // 원복
        }
    }
    static void move(int dir){
        Queue<Integer> q = new LinkedList<>();
        if(dir == 0) { // 위로 이동하기
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(map[j][i] != 0) {
                        q.offer(map[j][i]);
                        map[j][i] = 0;
                    }
                }
                int idx = 0;
                int popdata;
                while(!q.isEmpty()){
                    popdata = q.poll();
                    if(map[idx][i] == 0){ // 비어있을 때
                        map[idx][i] = popdata;
                    }
                    else if(map[idx][i] == popdata){ // 값이 같을 때 2배로 합쳐주기
                        map[idx][i] = 2*popdata;
                        idx++;
                    }
                    else{ // 값이 있지만 다를 때
                        idx++;
                        map[idx][i] = popdata;
                    }
                }
            }
        }else if(dir == 1){ // 오른쪽으로 밀기
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(map[i][j]!=0){
                        q.offer(map[i][j]);
                        map[i][j] = 0;
                    }
                }
                int idx = 0;
                int popdata;
                while(!q.isEmpty()){
                    popdata = q.poll();

                    if(map[i][idx] == 0){
                        map[i][idx] = popdata;
                    }else if(map[i][idx] == popdata){
                        map[i][idx] = 2*popdata;
                        idx++;
                    }else{
                        idx++;
                        map[i][idx] = popdata;
                    }


                }

            }
        }else if(dir == 2){ // 아래로 밀기
            for(int i = 0 ; i < n; i++){
                for(int j = n-1; j >= 0; j--){
                    if(map[j][i]!=0){
                        q.offer(map[j][i]);
                        map[j][i] = 0;
                    }
                }
                int idx = n-1;
                int popdata;
                while(!q.isEmpty()){
                    popdata = q.poll();
                    if(map[idx][i] == 0){
                        map[idx][i] = popdata;
                    }else if(map[idx][i] == popdata){
                        map[idx][i] = 2*popdata;
                        idx--;
                    }else{
                        idx--;
                        map[idx][i] = popdata;
                    }

                }

            }
        }else { // 왼쪽으로 밀기
            for(int i = 0; i < n; i++){
                for(int j = n-1; j >= 0; j--){

                    if(map[i][j]!=0){
                        q.offer(map[i][j]);
                        map[i][j] = 0 ;
                    }
                }
                int idx = n-1;
                int popdata;
                while(!q.isEmpty()){
                    popdata = q.poll();
                    if(map[i][idx] == 0){
                        map[i][idx] = popdata;
                    }else if(map[i][idx] == popdata){
                        map[i][idx] = 2*popdata;
                        idx--;
                    }else{
                        idx--;
                        map[i][idx] = popdata;
                    }
                }

            }
        }

    }

    static void copy(int tmp[][]){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                tmp[i][j] = map[i][j];
            }
        }
    }

    static void init(int tmp[][]){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                map[i][j] = tmp[i][j];
            }
        }
    }

    static void chk(){
        for(int i = 0; i <n; i++){
            for(int j = 0; j <n; j++){
                max = Math.max(max, map[i][j]);
            }
        }
    }

}
