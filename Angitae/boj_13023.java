package Angitae;

import java.io.*;
import java.util.*;

public class boj_13023 {
    static class Node{
        int now, lv;
        public Node(int now, int lv){
            this.now = now;
            this.lv = lv;
        }
    }


    // N : 사람 수 <= 2,000 , M : 관계 수 <= 2,000
    static int N, M;
    static boolean[] visit;
    static ArrayList<Integer>[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new ArrayList[N];
            visit = new boolean[N];
            for(int i = 0; i < N; i++){
                arr[i] = new ArrayList<>();
            }

            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());

                arr[n1].add(n2);
                arr[n2].add(n1);
            }
            boolean chk;
            for(int i = 0; i < N; i++) {
                chk = solve(i, 0);
                if(chk) {
                    System.out.println("1");
                    System.exit(0);
                    break;
                }
            }
        System.out.println("0");
    }
    static boolean solve(int now, int cnt){
        if(cnt >= 4){
            System.out.println("1");
            System.exit(0);
            return true;
        }
        visit[now] = true;
        for(int i = 0; i < arr[now].size(); i++){
            int tp = arr[now].get(i);
            if(!visit[tp])
             solve(tp, cnt+1);
        }
        visit[now] = false;

//        boolean visit[] = new boolean[N];
//        Queue<Node> q = new LinkedList<>();
//        q.add(new Node(n, 0));
//        visit[n] = true;
//        int cnt = 0;
//        while(!q.isEmpty()){
//            Node tmp = q.poll();
//            System.out.println("현재 값 :  "+tmp.now + " " + tmp.lv);
//            visit[tmp.now] =true;
//            if(tmp.lv >= 4)
//                return true;
//            for(int i = 0; i < arr[tmp.now].size(); i++){
//                int e = arr[tmp.now].get(i);
//                if(!visit[e])
//                    q.add(new Node(e, tmp.lv+1));
//            }
//        }
        return false;
    }
}
