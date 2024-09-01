package pyoki32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_10999 {
    static long[] arr;
    static long[] tree;
    static long[] lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        arr = new long[N + 1];
        tree = new long[4 * N + 1];
        lazy = new long[4 * N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Long.parseLong(st.nextToken());
        }
        build(1, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if (a == 1) {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                long d = Long.parseLong(st.nextToken());
                update_range(1, 1, N, b, c, d);
            } else {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                long RangeSum = query(1, 1, N, b, c);
                sb.append(RangeSum).append('\n');
            }
        }
        System.out.println(sb);
    }

    static void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        build(node * 2, start, mid);
        build(node * 2 + 1, mid + 1, end);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static void update_lazy(int node, int start, int end) {

        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    static void update_range(int node, int start, int end, int left, int right, long diff) {
        update_lazy(node, start, end);
        if (left > end || right < start) return;
        if (left <= start && end <= right) {
            lazy[node] += diff;
            update_lazy(node, start, end);
            return;
        }
        int mid = (start + end) / 2;
        update_range(node * 2, start, mid, left, right, diff);
        update_range(node * 2 + 1, mid + 1, end, left, right, diff);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static long query(int node, int start, int end, int left, int right) {
        update_lazy(node, start, end);
        if (left > end || right < start) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        long left_child = query(node * 2, start, mid, left, right);
        long right_child = query(node * 2 + 1, mid + 1, end, left, right);
        return left_child + right_child;
    }
}