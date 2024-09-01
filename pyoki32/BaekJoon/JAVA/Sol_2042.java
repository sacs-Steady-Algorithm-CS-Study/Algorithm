import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_2042 {

    static long[] arr;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        arr = new long[N + 1];
        tree = new long[4 * N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] =  Long.parseLong(st.nextToken());
        }
        build(1, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                update(1, 1, N, b, c);
            } else {
                long RangeSum = query(1, 1, N, b, (int)c);
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

    static void update(int node, int start, int end, int index, long value) {
        if (start == end) {
            tree[node] = value;
            return;
        }
        int mid = (start + end) / 2;
        if (index >= start && index <= mid) {
            update(node * 2, start, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, end, index, value);
        }
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static long query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return 0;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        long left_child = query(node * 2, start, mid, left, right);
        long right_child = query(node * 2 + 1, mid + 1, end, left, right);
        return left_child + right_child;
    }
}