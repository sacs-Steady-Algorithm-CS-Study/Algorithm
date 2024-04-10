package Angitae;

import java.io.*;
import java.util.*;

public class boj_1707 {
    static int tc; // test-case
    static int v, e; // 간선과 정점 갯수
    static ArrayList<ArrayList<Integer>> arr ; // 인접 리스트
    static int[] check; // 1과 2로 이분 그래프 만들기
    static String ans;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        tc = Integer.parseInt(st.nextToken()); // te-ca
        for(int i = 0; i < tc; i++){
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            check = new int[v+1];
            arr = new ArrayList<ArrayList<Integer>>();
            ans = "YES";
            for(int j = 0; j <= v; j++){ // 끝까지 뽑아줘야하기 때문에 v와 같거나 작을 때까지
                arr.add(new ArrayList<Integer>());
            }

            for(int w = 0; w < e; w++){
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                arr.get(v1).add(v2);
                arr.get(v2).add(v1);
            }

            for(int r = 1; r <= v; r++){
                if(check[r] == 0){
                    bfs(r);
                }
            }
            System.out.println(ans);
        }
    }
    static void bfs(int r){
        Queue<Integer> q = new LinkedList<>();
        q.add(r);
        check[r] = 1; // 처음에 들어오면 1로 치환
        while(!q.isEmpty()){
            int tmp = q.poll();
            for(int a : arr.get(tmp)){
                if(check[a] == check[tmp]){
                    ans = "NO";
                }
                if(check[a] == 0 ){
                    check[a] = check[tmp]+1;
                    q.add(a);
                }

            }

        }
    }

}