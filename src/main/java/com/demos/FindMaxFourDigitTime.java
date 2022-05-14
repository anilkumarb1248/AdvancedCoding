package com.demos;

//https://www.youtube.com/watch?v=sn6r0ZV_2y4
public class FindMaxFourDigitTime {

    public static void main(String args[]) {
        FindMaxFourDigitTime obj = new FindMaxFourDigitTime();
        System.out.println("obj.solution(1,7,2,7) : " + obj.solution(1,7,2,7));
        System.out.println("obj.solution(6,5,2,0) : " + obj.solution(6,5,2,0));
        System.out.println("obj.solution(3,9,5,0) : " + obj.solution(3,9,5,0));
        System.out.println("obj.solution(7,6,3,8) : " + obj.solution(7,6,3,8));
        System.out.println("obj.solution(0,1,2,3) : " + obj.solution(0,1,2,3));
        System.out.println("obj.solution(1,1,1,2) : " + obj.solution(1,1,1,2));
        System.out.println("obj.solution(1,1,1,1) : " + obj.solution(1,1,1,1));
        System.out.println("obj.solution(5,6,7,8) : " + obj.solution(5,6,7,8));
        System.out.println("obj.solution(2,9,3,1) : " + obj.solution(2,9,3,1));

        System.out.println(" --- ");
        System.out.println("obj.solution(1,8,3,2) : " + obj.solution(1,8,3,2)); // 23:18
        System.out.println("obj.solution(2,4,0,0) : " + obj.solution(2,4,0,0)); // 20:40
        System.out.println("obj.solution(3,0,7,0) : " + obj.solution(3,0,7,0)); // 07:30
        System.out.println("obj.solution(9,1,9,7) : " + obj.solution(9,1,9,7)); // Not possible
        System.out.println("obj.solution(1,2,3,4) : " + obj.solution(1,2,3,4)); // 23:41
        System.out.println("obj.solution(5,5,6,6) : " + obj.solution(5,5,6,6)); // Not possible
        System.out.println("obj.solution(0,0,0,9) : " + obj.solution(0,0,0,9)); // 09:00

    }

    //First digit of hours must be from the range [0, 2]
    //Second digit of hours must be from the range [0, 3] if first digit was chosen as 2 else [0, 9]
    //First digit of minutes must be from the range [0, 5]
    // and second digit of minutes must be from the range [0, 9]
    public String solution(int a, int b, int c, int d) {
        String maxTime = "";
        int[] array = {a, b, c, d};
        // hh:mm => h1 h2 : m1 m2
        int h1, h2, m1, m2;
        for (int i = 0; i < 4; i++) {
            h1 = array[i];
            if (h1 < 0 || h1 > 2) { //First digit of hours must be from the range [0, 2]
                continue;
            }
            for (int j = 0; j < 4; j++) {
                h2 = array[j];
                if (h1 == 2 && h2 > 3) { //Second digit of hours must be from the range [0, 3] if first digit was chosen as 2 else [0, 9]
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    m1 = array[k];
                    if (m1 < 0 || m1 > 5 || i == j || i == k || j == k) { //First digit of minutes must be from the range [0, 5] and // Indexes should not be the same
                        continue;
                    }
                    m2 = array[6-i-j-k]; //to find the fourth digit, here 6 is the indexes sum of array 0,1,2,3=6

                    String hh = h1 + "" + h2;
                    String mm = m1 + "" + m2;
                    String time = hh + ":" + mm;
                    if(hh.compareTo("24") < 0 && mm.compareTo("60") < 0 && time.compareTo(maxTime) > 0){
                        maxTime = time;
                    }
                    //System.out.println(maxTime);
                }
            }
        }
        if(maxTime.equals("")){
            maxTime = "NOT POSSIBLE";
        }
        return maxTime;
    }


}
