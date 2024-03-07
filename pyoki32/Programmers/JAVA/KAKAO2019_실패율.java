import java.util.*;

class KAKAO2019_실패율 {
    static class Stage {
        int num;
        double failureRate;

        public Stage(int num, double failureRate) {
            this.num = num;
            this.failureRate = failureRate;
        }
    }

    public int[] solution(int N, int[] stages) {

        int[] stagesClear = new int[N + 1];
        int[] stagesChallenge = new int[N + 1];
        for (int i = 0; i < stages.length; i++) {
            for (int j = 1; j < stages[i]; j++) {
                stagesClear[j]++;
            }
            if ((N + 1) > stages[i]) {
                stagesChallenge[stages[i]]++;
            }
        }

        int[] answer = new int[N];
        double[] arr = new double[N];
        PriorityQueue<Stage> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.failureRate == o2.failureRate) {
                return o1.num - o2.num;
            }
            return Double.compare(o2.failureRate, o1.failureRate);
        });
        for (int i = 0; i < N; i++) {
            double failureRate = (double) stagesChallenge[i + 1] / (stagesClear[i + 1] + stagesChallenge[i + 1]);

            if (stagesChallenge[i + 1] == 0) {
                pq.add(new Stage(i + 1, (double) 0));
            } else {
                pq.add(new Stage(i + 1, failureRate));
            }
        }
        int idx = 0;
        while (!pq.isEmpty()) {
            Stage s = pq.poll();
            answer[idx] = s.num;
            idx++;
        }
        return answer;
    }
}