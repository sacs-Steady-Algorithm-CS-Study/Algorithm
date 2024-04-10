package Angitae;

import java.io.*;
import java.util.*;

public class boj_2637 {
    static class Node{
        int val, cnt;
        public Node(int val, int cnt){
            this.val = val;
            this.cnt = cnt;
        }
    }
    static ArrayList<Node>[] arr;
    static int[] tmp; // 위상정렬 체크용 (0인지 아닌지)
    static int[] input; // 중간물품 담아두기용
    static int[] answer; // 답변 제출용
    static int n,m,x,y,k;
    // 최종부품 n
    // m열의 개수만큼 조건 받기
    // 중간부품 x , x만들기 위한 y부품 k개
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new ArrayList[n+1];
        input = new int[n+1];
        tmp = new int[n+1];
        answer = new int[n+1];
        for(int i =1; i <=n; i++){
            arr[i] = new ArrayList<>();
        }
        m = Integer.parseInt(br.readLine());
        for(int i =0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            arr[x].add(new Node(y, k));
            input[x]++;
            tmp[y]++;
        }
        solve(n);
        for(int i = 1; i <= n; i++){
                if(input[i] == 0)
                    System.out.println(answer[i]);
        }
    }
    static void solve(int n){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(n, 1));
        answer[n] = 1;
        while(!q.isEmpty()) {
            Node temp = q.poll();
            for(int i = 0; i < arr[temp.val].size(); i++) {
                Node next = arr[temp.val].get(i);
                answer[next.val] += answer[temp.val] * next.cnt;
                tmp[next.val]--;
                if(tmp[next.val] == 0) {
                    q.offer(new Node(next.val, answer[next.val]));
                }
            }
        }
    }
}
