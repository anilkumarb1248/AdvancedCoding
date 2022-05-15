package com.demos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Find the question at resources/Questions/PlaneSeatingReservation.jpg
public class PlaneSeatingReservation {

    public static void main(String arg[]){
        PlaneSeatingReservation planeSeatingReservation = new PlaneSeatingReservation();
        planeSeatingReservation.printStatements();
    }

    public void printStatements(){
        System.out.println("N=2, S='1A 2F 1C', actual= " + this.solution(2,"1A 2F 1C") + ", expected = 4");
        System.out.println("N=1, S='', actual= " + this.solution(1,"") + ", expected = 3");
    }

    public void assertStatements(){

    }

    public int solution(int N, String S){
        if(S == null || S.isEmpty()){
            return N * 3;
        }
        String[] array = S.split(" ");
        Map<Integer, List<Character>> allottedSeatsRows = new HashMap<>();
        // Finding the allotted seats in given rows

        for(int index=0; index<array.length; index++){
            String seat = array[index];
            Integer row = Integer.valueOf(seat.substring(0, seat.length()-1));

            if(allottedSeatsRows.containsKey(row)){
                allottedSeatsRows.get(row).add(seat.charAt(seat.length()-1));
            }else{
                List<Character> allottedSeats = new ArrayList();
                allottedSeats.add(seat.charAt(seat.length()-1));
                allottedSeatsRows.put(row, allottedSeats);
            }
        }

        // Can allocate 3 families in non-filled rows
        int possibleSeatsCount = (N - allottedSeatsRows.size()) * 3;
        System.out.println(possibleSeatsCount);

        //Find possible seats in already allotted rows
        for(Map.Entry<Integer,List<Character>> entry: allottedSeatsRows.entrySet()){
            List<Character> allottedSeats = entry.getValue();
            if(!allottedSeats.contains('A') && !allottedSeats.contains('B') && !allottedSeats.contains('C')){
                possibleSeatsCount++;
            }
            if((!allottedSeats.contains('D') && !allottedSeats.contains('E') && !allottedSeats.contains('F')) ||
                    (!allottedSeats.contains('E') && !allottedSeats.contains('F') && !allottedSeats.contains('G'))){
                possibleSeatsCount++;
            }
            if(!allottedSeats.contains('H') && !allottedSeats.contains('J') && !allottedSeats.contains('K')){
                possibleSeatsCount++;
            }
        }

        return possibleSeatsCount;
    }
}
