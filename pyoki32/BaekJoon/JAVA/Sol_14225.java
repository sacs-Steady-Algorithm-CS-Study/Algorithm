import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_14225 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean[] madeNumCheck = new boolean[2000001];

        int N = Integer.parseInt(st.nextToken());
        int[] S = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < (1 << N); i++) {
            int madeNum = 0;
            for (int j = 0; j < N; j++) {
                System.out.println(Integer.toBinaryString(i)+" "+Integer.toBinaryString((1<< j)));
                System.out.println(Integer.toBinaryString(i & (1 << j)));
                if ((i & (1 << j)) != 0 ) {
                    madeNum += S[j];
                }
            }
            madeNumCheck[madeNum] = true;
        }
        int answer = 1;
        for (int i = 1; i < madeNumCheck.length; i++) {
            if (!madeNumCheck[i]) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }
}
