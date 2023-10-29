import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_2531 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] dish = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            dish[i] = Integer.parseInt(st.nextToken());
        }

        int frontIdx = 0;
        int endIdx = k - 1;
        HashMap<Integer, Integer> hm = new HashMap<>();
        int sushiCnt = 0;
        for (int i = frontIdx; i <= endIdx; i++) {
            if (!hm.containsKey(dish[i])) {
                hm.put(dish[i], 1);
                sushiCnt++;
            } else {
                int cnt = hm.get(dish[i]);
                cnt++;
                hm.put(dish[i], cnt);
            }
        }

        int maxCnt = sushiCnt;

        while (frontIdx < N) {
            int cnt = hm.get(dish[frontIdx]);
            cnt--;
            hm.put(dish[frontIdx], cnt);
            if (hm.get(dish[frontIdx]) == 0) {
                sushiCnt--;
            }

            endIdx++;
            endIdx = endIdx % N;

            if (!hm.containsKey(dish[endIdx]) || (hm.containsKey(dish[endIdx]) && hm.get(dish[endIdx]) == 0)) {
                hm.put(dish[endIdx], 1);
                sushiCnt++;
            } else if((hm.containsKey(dish[endIdx]) && hm.get(dish[endIdx]) > 0)){
                cnt = hm.get(dish[endIdx]);
                cnt++;
                hm.put(dish[endIdx], cnt);
            }
            int pickCnt = sushiCnt;
            //print(hm);
            if (!hm.containsKey(c) || (hm.containsKey(c) && hm.get(c) == 0)) {
                pickCnt++;
            }
            maxCnt = Math.max(maxCnt, pickCnt);
            frontIdx++;
        }
        System.out.println(maxCnt);
    }

    static public void print(HashMap<Integer, Integer> hm) {

        for (Map.Entry<Integer, Integer> entrySet : hm.entrySet()) {
            System.out.println(entrySet.getKey() + "," + entrySet.getValue());
        }
        System.out.println();

        System.out.println();
    }
}
