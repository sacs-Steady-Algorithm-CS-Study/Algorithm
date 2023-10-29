    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.ArrayList;
    import java.util.PriorityQueue;
    import java.util.StringTokenizer;

    public class Sol_1219 {

        static class City {
            int num, cost;
            Long pay;

            public City(int num, int cost, Long pay) {
                this.num = num;
                this.cost = cost;
                this.pay = pay;
            }
        }

        static int N, M;
        static int sCity, eCity;
        static boolean [] cycleCheck;
        static long[] maxPayArr;
        static long[] payArr;
        static ArrayList<ArrayList<City>> adjList = new ArrayList<>();

        public static void main(String[] args) throws IOException {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            sCity = Integer.parseInt(st.nextToken());
            eCity = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                adjList.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                long initLong = 0;
                adjList.get(start).add(new City(end, cost, initLong ));
            }

            maxPayArr = new long[N];
            payArr = new long[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                maxPayArr[i] = Long.parseLong(st.nextToken());
                if (i != sCity) {
                    payArr[i] = Long.MIN_VALUE;
                }
            }
            payArr[sCity] = maxPayArr[sCity];
            cycleCheck  = new boolean[N];
            solve();
            if (payArr[eCity] == Long.MIN_VALUE) {
                System.out.println("gg");
            } else {
                if (cycleCheck[eCity]) {
                    System.out.println("Gee");
                } else {
                    System.out.println(payArr[eCity]);
                }
            }
        }

        static void solve() {

            PriorityQueue<City> pq = new PriorityQueue<>((o1, o2) -> {
                return -(o1.pay.compareTo(o2.pay));
                /* return  -(o1.pay - o2.pay);*/
            });

            //init
            ArrayList<City> startList = adjList.get(sCity);

            for (City city : startList) {
                payArr[city.num] = maxPayArr[sCity]+maxPayArr[city.num] - city.cost;
                pq.add(new City(city.num, city.cost, payArr[city.num]));
            }

            int visitedCnt = 1;
            while (!pq.isEmpty()) {

                City pCity = pq.poll();

                if (visitedCnt > N) {
                    cycleCheck[pCity.num] = true;
                }
                visitedCnt++;

                if(!cycleCheck[pCity.num]){
                    ArrayList<City> nStartList = adjList.get(pCity.num);
                    for (City nCity : nStartList) {
                        if (payArr[nCity.num] < maxPayArr[nCity.num] - nCity.cost + payArr[pCity.num]) {
                            payArr[nCity.num] = maxPayArr[nCity.num] - nCity.cost + payArr[pCity.num];
                            pq.add(new City(nCity.num, nCity.cost, payArr[nCity.num]));
                        }
                    }
                }

            }
        }
    }
