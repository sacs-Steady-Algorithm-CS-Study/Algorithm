package Angitae;

import java.io.*;
import java.util.*;
public class boj_6497 {
    static class Edge implements  Comparable<Edge>{
        int from, to ,weight;
        public Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight =weight;
        }
        @Override
        public int compareTo(Edge o){
            return this.weight - o.weight;
        }
    }

    static int total;
    static Edge[] edgelist;
    static int V, E; // V(정점), E(간선)
    static int[] parents;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            if(V ==0 && E == 0)
                break;

            edgelist = new Edge[E];
            parents = new int[V+1];
            for(int i = 0; i < E; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                total += weight;
                edgelist[i] = new Edge(from, to, weight);
            }
            System.out.println(kruskal());
        }
    }

    static int kruskal(){
        int res = 0;
        Arrays.sort(edgelist); // 간선 중심 가중치 오름차순
        make(); // 부모노드 매핑
        for(Edge edge : edgelist){
            if(!union(edge.from, edge.to)){
                //  합칠 수 있으면 가중치 더해주기
                res += edge.weight;
            }
        }
        return res;
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }
    static int find(int a){
        if(a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    static void make(){
        for(int a = 1; a <= V; a++) {
            parents[a] = a;
        }
    }
}