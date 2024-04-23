package Angitae;

import java.io.*;
import java.util.*;
public class boj_2644 {
    static int n; // 총 사람 수
    static int from, to;
    static int m; // 관계
    static ArrayList<Node> arr[];

    static class Node{
        int now, val;
        public Node(int now, int val){
            this.now = now;
            this.val = val;
        }
    }
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());

        arr = new ArrayList[n+1];
        visited = new boolean[n+1];
        for(int i = 0; i <= n; i++){
            arr[i] = new ArrayList<>();
        }
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(new Node(b, 0));
            arr[b].add(new Node(a, 0));
        }
        int answer = solve(from);
        System.out.println(answer);
    }
    static int solve(int from){
        Queue<Node> q = new LinkedList<>();
        int cnt = -1;
        q.add(new Node(from, 0));
        while(!q.isEmpty()){
            Node next = q.poll(); // 첫 번째는 from일테고 다음부터 하나씩 증가
            if(next.now == to){
                cnt = next.val;
                break;
            }
            visited[next.now] = true;
            for(int i = 0; i < arr[next.now].size(); i++){
                int tp = arr[next.now].get(i).now;
                if(visited[tp]) // 방문했으면 넘기기
                    continue;
                q.add(new Node(tp, next.val+1));
            }
        }
        return cnt;
    }
}
