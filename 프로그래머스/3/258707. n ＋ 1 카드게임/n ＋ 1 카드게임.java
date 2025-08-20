import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    
    static class Card implements Comparable<Card>{
        int num;
        int seq;
        
        public Card(int num, int seq) {
            this.num = num;
            this.seq = seq;
        }
        
        @Override
        public int compareTo(Card c) {
            return this.seq - c.seq;
        }
    }
    
    public int solution(int coin, int[] cards) {
        int answer = 1;
        int n = cards.length;
        
        ArrayList<Card> arr_cards = new ArrayList<>();
        PriorityQueue<Card> pq = new PriorityQueue<>();
        
        for (int i = 0; i < n/3; i++) {
            arr_cards.add(new Card(cards[i], 0));
            int arr_cards_len = arr_cards.size();
            for (int j = 0; j < arr_cards_len; j++) {
                if (cards[i] + arr_cards.get(j).num == n+1) {
                    pq.add(arr_cards.get(j));
                    coin++;
                    break;
                }
            }
        }
        
        for (int i = n-1; i >= n/3; i--) {
            int arr_cards_len = arr_cards.size();
            int seq = i;
            
            for (int j = 0; j < arr_cards_len; j++) {
                if (cards[i] + arr_cards.get(j).num == n+1) {
                    seq = arr_cards.get(j).seq;
                }
            }
            
            arr_cards.add(new Card(cards[i], seq));
        }
        
        Collections.reverse(arr_cards);
        
        int stage = 0;
        while (coin > 0 && stage < n*2/3) {
            pq.add(arr_cards.get(stage));
            pq.add(arr_cards.get(stage+1));
            stage += 2;
            
            Card c1 = pq.poll();
            if (c1.seq == 0) {
                answer++;
                coin--;
            } else if (coin > 1) {
                Card c2 = pq.poll();
                if (c1.num + c2.num == n + 1) {
                    answer++;
                    coin -= 2;
                } else {
                    break;
                }
            } else {
                break;
            }
            
        }
        
        return answer;
    }
}