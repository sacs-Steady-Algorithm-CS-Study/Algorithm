import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Sol_4949 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String str = br.readLine();
            if (str.charAt(0) == '.') {
                break;
            }
            boolean balanceCheck = true;
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(' || str.charAt(i) == '[') {
                    stack.add(str.charAt(i));
                } else if (str.charAt(i) == ')' || str.charAt(i) == ']') {
                    if (!stack.isEmpty()) {
                        if ((stack.peek() == '(' && str.charAt(i) == ')') || (stack.peek() == '[' && str.charAt(i) == ']')) {
                            stack.pop();
                        } else {
                            balanceCheck = false;
                            break;
                        }
                    } else {
                        balanceCheck = false;
                        break;
                    }
                }
            }
            if (!stack.isEmpty()) {
                balanceCheck = false;
            }
            if (balanceCheck) {
                sb.append("yes").append('\n');
            } else {
                sb.append("no").append('\n');
            }
        }
        System.out.println(sb);
    }
}
