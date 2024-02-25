import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_12015 {

    static int[] minLis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int len = 0;
        int lenIdx = 0;
        minLis = new int[N + 1];

        for (int i = 0; i < N; i++) {
            if (minLis[len] < arr[i]) {
                len++;
                minLis[len] = arr[i];
            } else {
                lenIdx = binarySearch(0, len, arr[i]);
                minLis[lenIdx] = arr[i];
            }
        }
        System.out.println(len);
    }

    static int binarySearch(int left, int right, int key) {
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (minLis[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
