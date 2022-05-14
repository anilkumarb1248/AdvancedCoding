package com.demos;

//https://www.youtube.com/watch?v=sn6r0ZV_2y4
public class FindMaxFourDigitTime_Performance {
    public static void main(String args[]) {
        FindMaxFourDigitTime_Performance obj = new FindMaxFourDigitTime_Performance();
        System.out.println("obj.solution(1,7,2,7) : " + obj.solution(1, 7, 2, 7));
        System.out.println("obj.solution(6,5,2,0) : " + obj.solution(6, 5, 2, 0));
        System.out.println("obj.solution(3,9,5,0) : " + obj.solution(3, 9, 5, 0));
        System.out.println("obj.solution(7,6,3,8) : " + obj.solution(7, 6, 3, 8));
        System.out.println("obj.solution(0,1,2,3) : " + obj.solution(0, 1, 2, 3));
        System.out.println("obj.solution(1,1,1,2) : " + obj.solution(1, 1, 1, 2));
        System.out.println("obj.solution(1,1,1,1) : " + obj.solution(1, 1, 1, 1));
        System.out.println("obj.solution(5,6,7,8) : " + obj.solution(5, 6, 7, 8));
        System.out.println("obj.solution(2,9,3,1) : " + obj.solution(2, 9, 3, 1));

        System.out.println(" --- ");
        System.out.println("obj.solution(1,8,3,2) : " + obj.solution(1, 8, 3, 2)); // 23:18
        System.out.println("obj.solution(2,4,0,0) : " + obj.solution(2, 4, 0, 0)); // 20:40
        System.out.println("obj.solution(3,0,7,0) : " + obj.solution(3, 0, 7, 0)); // 07:30
        System.out.println("obj.solution(9,1,9,7) : " + obj.solution(9, 1, 9, 7)); // Not possible
        System.out.println("obj.solution(1,2,3,4) : " + obj.solution(1, 2, 3, 4)); // 23:41
        System.out.println("obj.solution(5,5,6,6) : " + obj.solution(5, 5, 6, 6)); // Not possible
        System.out.println("obj.solution(0,0,0,9) : " + obj.solution(0, 0, 0, 9)); // 09:00
    }

    //First digit of hours must be from the range [0, 2]
    //Second digit of hours must be from the range [0, 3] if first digit was chosen as 2 else [0, 9]
    //First digit of minutes must be from the range [0, 5]
    // and second digit of minutes must be from the range [0, 9]
    public String solution(int a, int b, int c, int d) {
        String maxTime = "";
        int[] array = {a, b, c, d};
        StringBuilder sb = new StringBuilder();
        for (int h1Index = 0; h1Index < 4; h1Index++) {
            if (array[h1Index] < 0 || array[h1Index] > 2) { //First digit of hours must be from the range [0, 2]
                continue;
            }
            for (int h2Index = 0; h2Index < 4; h2Index++) {
                if (array[h1Index] == 2 && array[h2Index] > 3) { //Second digit of hours must be from the range [0, 3] if first digit was chosen as 2 else [0, 9]
                    continue;
                }
                for (int m1Index = 0; m1Index < 4; m1Index++) {
                    if (array[m1Index] < 0 || array[m1Index] > 5 || h1Index == h2Index || h1Index == m1Index || h2Index == m1Index) { //First digit of minutes must be from the range [0, 5] and // Indexes should not be the same
                        continue;
                    }
                    int m2 = array[6 - h1Index - h2Index - m1Index]; //to find the fourth digit, here 6 is the indexes sum of array 0,1,2,3=6
                    sb.setLength(0);
                    String time = sb.append(array[h1Index]).append(array[h2Index]).append(":").append(array[m1Index]).append(m2).toString();
                    if (time.compareTo(maxTime) > 0) {
                        maxTime = time;
                    }
                }
            }
        }
        if (maxTime.equals("")) {
            maxTime = "NOT POSSIBLE";
        }
        return maxTime;
    }
}
