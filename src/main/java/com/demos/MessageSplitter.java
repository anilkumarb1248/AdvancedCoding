package com.demos;

import java.util.LinkedList;
import java.util.List;

/**
 * Message Split
 * You have long piece of text, and you want to send it to your friend as a series of SMS messages.
 * Your text consists of English letters (uppercase and lowercase) and spaces.
 * The text doesn't contain any leading spaces at the beginning or trailing spaces at the end.
 * There are no two consecutive spaces in the text.
 *
 * One proper SMS can contain at most K characters. If the text is longer,
 * it needs to be split into parts. Each part should be sent in a separate message that
 * fulfills the maximum length requirement. Text may not be split within words.
 * The order of the words and the messages can't change, so that we can later concatenate
 * the resulting messages to retrieve the original text. Spaces at the start and end of all
 * messages are removed, and thus are not accounted for in the overall message length.
 *
 *
 * The goal is to count the number of SMS messages needed to accommodate the whole text,
 * keeping the length of each message less than or equal to K.
 * The total number of SMS messages must be kept to a minimum,
 * however; it would not be acceptable, for example, to take the wasteful approach of
 * programmatically sending one SMS for each word in the original text!
 *
 * Write a function:
 * 	class Solution{  public int solution(String S, int K);}
 *
 * In that, given a string S representing the text that needs to be split and
 * an integer K that is equal to the maximum possible message length, returns
 * the number of SMS messages needed.
 *
 * For instance, given string S="SMS messages are really short" and K = 12, your
 * function should return 3.
 * You could split this text into proper messages as follows:
 * 	"SMS messages"
 * 	"are really"
 * 	"short"
 *
 * If it's impossible to split text into proper SMS messages, your function should return −1.
 *
 * Assume that:
 * 	- K is an integer within the range [1..500];
 * 	- S is a non-empty string containing at most 500 characters: only letters and spaces,
 * 	  there are no spaces at the beginning and at the end of S; also there can't be two
 * 	  or more consecutive spaces in S.
 */
public class MessageSplitter {

    public static void main(String[] args){
        MessageSplitter messageSplitter = new MessageSplitter();
        messageSplitter.printStatements();
    }

    public void printStatements(){
        System.out.println(this.solution(null, 5) + ", expected: -1");
        System.out.println(this.solution("", 5) + ", expected: -1");
        System.out.println(this.solution("Some text", 0) + ", expected: -1");
        System.out.println(this.solution("Some text", -5) + ", expected: -1");
        System.out.println(this.solution("Honeybee bite", 5) + ", expected: -1");
        System.out.println(this.solution("she is beautiful", 6) + ", expected: -1");

        System.out.println(this.solution("SMS messages are really short", 12) + ", expected: 3");
        System.out.println(this.solution("SMS messages are really short", 8) + ", expected: 5");
        System.out.println(this.solution("SMS messages are really short", 7) + ", expected: -1");

        System.out.println(this.solution("aaaa bbb ccc dddd", 4) + ", expected: 4");
        System.out.println(this.solution("aaaa bb ccc dddd", 6) + ", expected: 3");
        System.out.println(this.solution("aaaa", 4) + ", expected: 1");
        System.out.println(this.solution("aaaa", 3) + ", expected: -1");
        System.out.println(this.solution("aaaa", 5) + ", expected: 1");
        System.out.println(this.solution("aaaa bb", 6) + ", expected: 2");
        System.out.println(this.solution("something went wrong", 6) + ", expected: -1");
    }

    public void assertStatements(){

    }

    public int solution(String S, int K){
        if(S == null || S.length() == 0 || K <= 0){
            return -1;
        }

        String[] words = S.split(" ");
        List<String> messageList = new LinkedList<>();
        StringBuilder builder = new StringBuilder();

        for(int index = 0; index < words.length; index++){
            if(words[index].length() > K){
                return -1;
            }

            String tempString = (builder + " " + words[index]).trim();
            if(tempString.length() == K){
                messageList.add(tempString);
                builder.setLength(0);
            }else if(tempString.length() < K){
                builder.setLength(0);
                if(index+1 == words.length){
                    messageList.add(tempString);
                }else{
                    builder.append(tempString);
                }
            }else if(tempString.length() > K){
                messageList.add(builder.toString());
                if(index+1 == words.length){
                    messageList.add(words[index]);
                }else{
                    builder.setLength(0);
                    builder.append(words[index]);
                }
            }
        }
        for(String message: messageList){
            //System.out.println("-----"+ message+"-----");
        }

        return messageList.size();
    }

}
