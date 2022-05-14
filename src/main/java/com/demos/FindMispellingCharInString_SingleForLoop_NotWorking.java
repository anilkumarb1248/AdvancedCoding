package com.demos;

/**
 * Write a function: given two strings S and T consisting of N and M characters, respectively.
 * Determine whether string T can be obtained from string S by at most one insertion or deletion of a character,
 * or by swapping two adjacent characters once.
 *
 * The function should return a string:
 *   - "INSERT c" if string T can be obtained from string S by inserting a single character "c".
 *   - "DELETE c" if string T can be obtained from string S by deleting a single character "c"
 *   - "SWAP c d" if string T can be obtained from string S by swapping two adjacent characters "c" and "d"
 *   (these characters should be distinct and they should be in this order in string S)
 *   - "NOTHING" if no operation is needed (string T and S are equal)
 *   - "IMPOSSIBLE" if none of the above works
 *
 *   For example:
 *      - Given S="nice" and T="niece", the function should return "INSERT e"
 *      - Given S="form" and T="from", the function should return "SWAP o r"
 *      - Given S="o" and T="odd", the function should return "IMPOSSIBLE"
 *
 *   Assume That:
 *      - N and M are integers within the range [1..100]
 *      - string S and T consists only of lowercase letters (a-z)
 */
public class FindMispellingCharInString_SingleForLoop_NotWorking {
//TODO: Not Working delete last char scenario, need to work on it

    public static void main(String args[]){
        FindMispellingCharInString obj = new FindMispellingCharInString();

//        System.out.println(obj.solution(null, null)); // IMPOSSIBLE
//        System.out.println(obj.solution("anil", "anil")); // NOTHING
//        System.out.println(obj.solution("aniil", "anil")); // Delete i
//        System.out.println(obj.solution("nice", "niece")); // INSERT e
//        System.out.println(obj.solution("form", "from")); // SWAP o r
//        System.out.println(obj.solution("o", "odd")); // IMPOSSIBLE

//        System.out.println(obj.solution("xyz", "xz"));     // DELETE y
//        System.out.println(obj.solution("xyz", "xyyz"));   // INSERT y
//        System.out.println(obj.solution("xyz", "xyx"));    // IMPOSSIBLE
//        System.out.println(obj.solution("xyz", "xxx"));    // IMPOSSIBLE

        //**** Test Scenarios
        //First & Last char swap
//        System.out.println(obj.solution("abcd", "bacd")); //SWAP a b
//        System.out.println(obj.solution("abdc", "abcd")); //SWAP d c

        //First & Last position add
//        System.out.println(obj.solution("bcd", "abcd")); //INSERT a
//        System.out.println(obj.solution("abc", "abcd")); //INSERT d

        //First & Last position delete
//        System.out.println(obj.solution("aabcd", "abcd")); // DELETE a
        System.out.println(obj.solution("abcde", "abcd")); // DELETE e

    }

    public String solution(String S, String T){
        if(S == null || T == null){
            return "IMPOSSIBLE";
        }
        if(S.equals(T)){
            return "NOTHING";
        }
        int sLength = S.length();
        int tLength = T.length();

        int lengthDiff = sLength - tLength; // Finding the length difference between S string and T string

        // Consider only if both string lengths are equal by adding/deleting one character or be default.
        StringBuilder sb = new StringBuilder();
        if((sLength == tLength) || (sLength + 1 == tLength) || (sLength - 1 == tLength)){
            for(int i=0;i<sLength;i++){
                char sChar = S.charAt(i);
                char tChar = T.charAt(i);
                if(sChar == tChar && ((i+2) != tLength )){
                    continue;
                }

                if(sLength == tLength && (i != sLength-1)){
                    sb.append(S.substring(0,i)).append(S.charAt(i+1)).append(S.charAt(i)).append(S.substring(i+2,sLength));
                    if(T.equals(sb.toString())){
                        return "SWAP "+S.charAt(i) +" " + S.charAt(i+1);
                    }

                } else if (sLength + 1 == tLength) {
                    char insertChar = tChar;
                    if((i+2) == tLength){
                        insertChar = T.charAt(i+1);
                        sb.append(S.substring(0,i+1)).append(insertChar);
                    }else{
                        sb.append(S.substring(0,i)).append(tChar).append(S.substring(i,sLength));
                    }

                    if(T.equals(sb.toString())){
                        return "INSERT "+insertChar;
                    }
                } else if(sLength - 1 == tLength) {
                    sb.append(S.substring(0,i)).append(S.substring(i+1,sLength));
                    if(T.equals(sb.toString())){
                        return "DELETE "+sChar;
                    }
                }
                break;
            }
        }

        return "IMPOSSIBLE";
    }
}
