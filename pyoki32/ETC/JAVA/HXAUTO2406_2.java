package ETC.JAVA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HXAUTO2406_2 {
    static class JW {
        int w;
        int v;

        public JW(int w, int v) {
            this.w = w;
            this.v = v;

        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<JW> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.w == o2.w) return -(o1.v - o2.v);
            return o1.w - o2.w;
        });

        PriorityQueue<JW> pq2 = new PriorityQueue<>((o1, o2) -> {
            if (o1.w == o2.w) return -(o1.v - o2.v);
            return -(o1.v - o2.v);
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            pq.add(new JW(w,v));
        }

        while(!pq.isEmpty()){
            JW j = pq.poll();
            int jeW = j.w;
            int jeV = j.v;
                if(jeW <= M){

                }
        }
    }
}