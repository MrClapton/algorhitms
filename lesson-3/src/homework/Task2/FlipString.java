package homework.Task2;

import stack.IStack;
import stack.StackImpl;
import java.io.IOException;

public class FlipString {
    private String inputString;
    //private final int strLength = inputString.length();
    private IStack<Character> stack;

    public FlipString(String inputString) throws IOException {
        this.inputString = inputString;
    }

    String flip() {
        stringToArrayOfChars();
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    private void stringToArrayOfChars() {
        int strLength = inputString.length();
        stack = new StackImpl<>(strLength);

        for (int i = 0; i < strLength; i++) {
            char currChar = inputString.charAt(i);
            stack.push(currChar);
        }
   }

}
