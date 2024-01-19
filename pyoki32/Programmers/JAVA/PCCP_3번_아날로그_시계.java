
//[PCCP 기출문제] 3번 / 아날로그 시계

    public class PCCP_3번_아날로그_시계 {
        class Solution {
            public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
                int answer = -1;
                int hV = 1;
                int mV = 12;
                int sV = 720;
                int aX = sV*60;

                //초기 위치
                int initH1 = h1%12;
                int initM1 = m1;
                int initS1 = s1;

                int h_hand_s1 = hV*(initH1 * 3600 + initM1 * 60 + initS1);
                int m_hand_s1 = mV*(initM1 * 60 + s1);
                int s_hand_s1 = sV*initS1;

                int total_seconds1 = h1 * 3600 + m1 * 60 + s1;
                int total_seconds2 = h2 * 3600 + m2 * 60 + s2;

                int start_seconds = total_seconds1;
                int h_hand = h_hand_s1;
                int m_hand = m_hand_s1;
                int s_hand = s_hand_s1;
                int ex_s_hand = -1;
                int alertCnt = 0;
                boolean hStartCheck = false;
                boolean mStartCheck = false;
                if(s_hand == h_hand){
                    hStartCheck = true;
                }
                if(s_hand == m_hand){
                    mStartCheck = true;
                }
                // 시작 시 분 확인
                if(hStartCheck || mStartCheck ) alertCnt++;

                while(start_seconds <= total_seconds2){
                    //1초 이상 이동 후
                    if(total_seconds1 < start_seconds ){
                        if(s_hand == h_hand || s_hand == m_hand) alertCnt++;
                        if(!hStartCheck && (ex_s_hand < h_hand  && h_hand < s_hand)) {;
                            alertCnt++;
                        }
                        if(!mStartCheck && (ex_s_hand< m_hand  && m_hand < s_hand)){
                            alertCnt++;
                        }
                    }

                    if(h_hand == aX ){
                        h_hand = 0;
                    }
                    if(m_hand == aX){
                        m_hand = 0;
                    }
                    if(s_hand == aX){
                        s_hand = 0;
                    }

                    if(s_hand == h_hand){
                        hStartCheck = true;
                    }else{
                        hStartCheck = false;
                    }
                    if(s_hand == m_hand){
                        mStartCheck = true;
                    }else{
                        mStartCheck = false;
                    }

                    start_seconds++;
                    ex_s_hand = s_hand;
                    h_hand += hV;
                    m_hand += mV;
                    s_hand += sV;

                }
                answer = alertCnt;
                return answer;
            }
        }
    }
