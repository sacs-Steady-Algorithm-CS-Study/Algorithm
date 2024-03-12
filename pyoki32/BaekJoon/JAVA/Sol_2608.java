import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_2608 {
    static class RomeNum {
        String mark;
        int num;

        public RomeNum(String mark, int num) {
            this.mark = mark;
            this.num = num;
        }
    }

    static ArrayList<RomeNum> romeList;
    static String[] marks;
    static String str1, str2;
    static int str1Value, str2Value;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();
        str1Value = -1;
        str2Value = -1;
        romeList = new ArrayList<>();

        romeList.add(new RomeNum("I", 1));
        romeList.add(new RomeNum("V", 5));
        romeList.add(new RomeNum("X", 10));
        romeList.add(new RomeNum("L", 50));
        romeList.add(new RomeNum("C", 100));
        romeList.add(new RomeNum("D", 500));
        romeList.add(new RomeNum("M", 1000));
        romeList.add(new RomeNum("IV", 4));
        romeList.add(new RomeNum("IX", 9));
        romeList.add(new RomeNum("XL", 40));
        romeList.add(new RomeNum("XC", 90));
        romeList.add(new RomeNum("CD", 400));
        romeList.add(new RomeNum("CM", 900));

        Collections.sort(romeList, (romeNum1, romeNum2) -> Integer.compare(romeNum2.num, romeNum1.num));
        marks = new String[4001];
        for (RomeNum r : romeList) {
            String key = r.mark;
            int value = r.num;
            marks[value] = key;
            Deque<String> dq = new LinkedList();
            if (key.equals("V") || key.equals("L") || key.equals("D")) {
                dq.addLast(key);
                dfs(value, dq, key, value, false, false, false);
                dq.pollLast();
            } else if (key.equals("I") || key.equals("X") || key.equals("C") || key.equals("M")) {
                dq.addLast(key);
                dfs(value, dq, key, value, false, false, false);
                dq.pollLast();
            } else if (key.equals("IX") || key.equals("IV")) {
                dq.addLast(key);
                dfs(value, dq, key, value, true, false, false);
                dq.pollLast();
            } else if (key.equals("XL") || key.equals("XC")) {
                dq.addLast(key);
                dfs(value, dq, key, value, false, true, false);
                dq.pollLast();
            } else if (key.equals("CD") || key.equals("CM")) {
                dq.addLast(key);
                dfs(value, dq, key, value, false, false, true);
                dq.pollLast();
            } else {
                dq.addLast(key);
                dfs(value, dq, key, value, false, false, false);
                dq.pollLast();
            }
        }
        int sum = str1Value + str2Value;
        System.out.println(sum);
        System.out.println(marks[sum]);
    }

    static void dfs(int total, Deque<String> dq, String key, int value, boolean ixiv, boolean xlxc, boolean cdcm) {

        if (total > 4000) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        if (!dq.isEmpty()) {
            int tempSize = dq.size();
            for (int i = 0; i < tempSize; i++) {
                String tmpStr = dq.pollFirst();
                sb.append(tmpStr);
                dq.addLast(tmpStr);
            }
        }

        if (sb.toString().equals(str1)) {
            str1Value = total;
        } else if (sb.toString().equals(str2)) {
            str2Value = total;
        }

        if (marks[total] == null || marks[total].isEmpty()) {
            marks[total] = sb.toString();
        } else {
            if (marks[total].length() > sb.toString().length()) {
                marks[total] = sb.toString();
            }
        }

        for (RomeNum r : romeList) {
            String nKey = r.mark;
            int nValue = r.num;
            if (value >= nValue) {
                if (nKey.equals("V") || nKey.equals("L") || nKey.equals("D")) {
                    int vldCnt = 0;
                    String pop1 = "";
                    String pop2 = "";
                    if (!dq.isEmpty()) {
                        pop1 = dq.pollLast();
                        if (nKey.equals(pop1)) vldCnt++;
                    }
                    if (!dq.isEmpty()) {
                        pop2 = dq.pollLast();
                        if (nKey.equals(pop2)) vldCnt++;
                    }
                    if (!pop2.equals("")) dq.addLast(pop2);
                    if (!pop1.equals("")) dq.addLast(pop1);

                    if (vldCnt < 2) {
                        dq.addLast(nKey);
                        dfs(total + nValue, dq, nKey, nValue, ixiv, xlxc, cdcm);
                        dq.pollLast();
                    }

                } else if (nKey.equals("I") || nKey.equals("X") || nKey.equals("C") || nKey.equals("M")) {
                    int ixcmCnt = 0;
                    String pop1 = "";
                    String pop2 = "";
                    String pop3 = "";
                    if (!dq.isEmpty()) {
                        pop1 = dq.pollLast();
                        if (nKey.equals(pop1)) ixcmCnt++;
                    }
                    if (!dq.isEmpty()) {
                        pop2 = dq.pollLast();
                        if (nKey.equals(pop2)) ixcmCnt++;
                    }
                    if (!dq.isEmpty()) {
                        pop3 = dq.pollLast();
                        if (nKey.equals(pop3)) ixcmCnt++;
                    }
                    if (!pop3.equals("")) dq.addLast(pop3);
                    if (!pop2.equals("")) dq.addLast(pop2);
                    if (!pop1.equals("")) dq.addLast(pop1);
                    if (ixcmCnt < 3) {
                        dq.addLast(nKey);
                        dfs(total + nValue, dq, nKey, nValue, ixiv, xlxc, cdcm);
                        dq.pollLast();
                    }
                } else if (nKey.equals("IX") || nKey.equals("IV")) {
                    if (!ixiv) {
                        dq.addLast(nKey);
                        dfs(total + nValue, dq, nKey, nValue, true, xlxc, cdcm);
                        dq.pollLast();
                    }
                } else if (nKey.equals("XL") || nKey.equals("XC")) {
                    if (!xlxc) {
                        dq.addLast(nKey);
                        dfs(total + nValue, dq, nKey, nValue, ixiv, true, cdcm);
                        dq.pollLast();
                    }
                } else if (nKey.equals("CD") || nKey.equals("CM")) {
                    if (!cdcm) {
                        dq.addLast(nKey);
                        dfs(total + nValue, dq, nKey, nValue, ixiv, xlxc, true);
                        dq.pollLast();
                    }
                } else {
                    dq.addLast(nKey);
                    dfs(total + nValue, dq, nKey, nValue, ixiv, xlxc, cdcm);
                    dq.pollLast();
                }
            }
        }
    }
}
