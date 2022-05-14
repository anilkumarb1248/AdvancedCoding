package com.demos;

/**
 * Write a function: given two strings S and T consisting of N and M characters, respectively.
 * Determine whether string T can be obtained from string S by at most one insertion or deletion of a character,
 * or by swapping two adjacent characters once.
 * <p>
 * The function should return a string:
 * - "INSERT c" if string T can be obtained from string S by inserting a single character "c".
 * - "DELETE c" if string T can be obtained from string S by deleting a single character "c"
 * - "SWAP c d" if string T can be obtained from string S by swapping two adjacent characters "c" and "d"
 * (these characters should be distinct and they should be in this order in string S)
 * - "NOTHING" if no operation is needed (string T and S are equal)
 * - "IMPOSSIBLE" if none of the above works
 * <p>
 * For example:
 * - Given S="nice" and T="niece", the function should return "INSERT e"
 * - Given S="form" and T="from", the function should return "SWAP o r"
 * - Given S="o" and T="odd", the function should return "IMPOSSIBLE"
 * <p>
 * Assume That:
 * - N and M are integers within the range [1..100]
 * - string S and T consists only of lowercase letters (a-z)
 */
public class FindMispellingCharInString {

    public static void main(String[] args) {
        FindMispellingCharInString findMispellingCharInString = new FindMispellingCharInString();
        findMispellingCharInString.printStatements();
    }
    
    public void printStatements(){
        System.out.println("(null, null) actual: " + this.solution(null, null) + ", expected: IMPOSSIBLE"); // IMPOSSIBLE
        System.out.println("(anil, anil) --  actual: " + this.solution("anil", "anil") + ", expected: NOTHING"); // NOTHING
        System.out.println("(aniil, anil) --  actual: " + this.solution("aniil", "anil") + ", expected: DELETE i"); // Delete i
        System.out.println("(nice, niece) --  actual: " + this.solution("nice", "niece") + ", expected: INSERT e"); // INSERT e
        System.out.println("(form, from) --  actual: " + this.solution("form", "from") + ", expected: SWAP o r"); // SWAP o r
        System.out.println("(o, odd) --  actual: " + this.solution("o", "odd") + ", expected: IMPOSSIBLE"); // IMPOSSIBLE

        System.out.println("(xyz, xz) --  actual: " + this.solution("xyz", "xz") + ", expected: DELETE y");     // DELETE y
        System.out.println("(xyz, xyyz) --  actual: " + this.solution("xyz", "xyyz") + ", expected: INSERT y");   // INSERT y
        System.out.println("(xyz, xyx) --  actual: " + this.solution("xyz", "xyx") + ", expected: IMPOSSIBLE");    // IMPOSSIBLE
        System.out.println("(xyz, xxx) --  actual: " + this.solution("xyz", "xxx") + ", expected: IMPOSSIBLE");    // IMPOSSIBLE

        System.out.println("(kitten, mitten) --  actual: " + this.solution("kitten", "mitten") + ", expected: IMPOSSIBLE");
        System.out.println("(kitten, kitten) --  actual: " + this.solution("kitten", "kitten") + ", expected: NOTHING");
        System.out.println("(kitten, kitetn) --  actual: " + this.solution("kitten", "kitetn") + ", expected: SWAP t e");
        System.out.println("(kiten, kitten) --  actual: " + this.solution("kiten", "kitten") + ", expected: INSERT t");
        System.out.println("(kitten, kittn) --  actual: " + this.solution("kitten", "kittn") + ", expected: DELETE e");

        //**** Test Scenarios
        //First & Last char swap
        System.out.println("(abcd, bacd) --  actual: " + this.solution("abcd", "bacd") + ", expected: SWAP a b"); //SWAP a b
        System.out.println("(abdc, abcd) --  actual: " + this.solution("abdc", "abcd") + ", expected: SWAP d c"); //SWAP d c

        //First & Last position add
        System.out.println("(bcd, abcd) --  actual: " + this.solution("bcd", "abcd") + ", expected: INSERT a"); //INSERT a
        System.out.println("(abc, abcd) --  actual: " + this.solution("abc", "abcd") + ", expected: INSERT d"); //INSERT d

        //First & Last position delete
        System.out.println("(aabcd, abcd) --  actual: " + this.solution("aabcd", "abcd") + ", expected: DELETE a"); // DELETE a
        System.out.println("(abcde, abcd) --  actual: " + this.solution("abcde", "abcd") + ", expected: DELETE e"); // DELETE e

    }
    
    public void assertStatements(){
        
    }

    public String solution(String S, String T) {
        if (S == null || T == null) {
            return "IMPOSSIBLE";
        }
        if (S.equals(T)) {
            return "NOTHING";
        }
        int sLength = S.length();
        int tLength = T.length();

        int lengthDiff = sLength - tLength; // Finding the length difference between S string and T string
        StringBuilder sb = new StringBuilder();

        // Consider only if both string lengths are equal by adding/deleting one character or by default.
        if (lengthDiff == 0) { // We need to swap if two string lengths are same
            for (int index = 0; index < sLength; index++) {
                if (S.charAt(index) != T.charAt(index)) {
                    if ((index != sLength - 1)) {
                        sb.append(S, 0, index).append(S.charAt(index + 1)).append(S.charAt(index)).append(S, index + 2, sLength);
                        if (T.equals(sb.toString())) {
                            return "SWAP " + S.charAt(index) + " " + S.charAt(index + 1);
                        }
                    }
                    break;
                }
            }
        } else if (lengthDiff == -1) { // We need to insert one character if S length is less than one than T length
            for (int index = 0; index < sLength; index++) {
                if (S.charAt(index) != T.charAt(index) || (index+1 == sLength && index+2 == tLength)) {
                    char insertChar = T.charAt(index);
                    if ((index + 2) == tLength) {
                        insertChar = T.charAt(index + 1);
                        sb.append(S, 0, index + 1).append(insertChar);
                    } else {
                        sb.append(S, 0, index).append(T.charAt(index)).append(S, index, sLength);
                    }

                    if (T.equals(sb.toString())) {
                        return "INSERT " + insertChar;
                    }
                    break;
                }
            }
        } else if (lengthDiff == 1) { // We need to delete one character if S length is greater than one then T length
            for (int index = 0; index < sLength; index++) {
                if(index+1 == sLength && index == tLength){
                    if(T.equals(sb.append(S, 0, index).toString())){
                        return "DELETE " + S.charAt(index);
                    }
                }
                if (S.charAt(index) != T.charAt(index)) {
                    sb.append(S, 0, index).append(S, index + 1, sLength);
                    if (T.equals(sb.toString())) {
                        return "DELETE " + S.charAt(index);
                    }
                    break;
                }
            }
        }
        return "IMPOSSIBLE";
    }
}
