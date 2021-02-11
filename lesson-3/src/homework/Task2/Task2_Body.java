package homework.Task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task2_Body {
    private InputStreamReader in = new InputStreamReader(System.in);
    private BufferedReader buf = new BufferedReader(in);

    void run() throws IOException {
        String inputString = getInputString();
        FlipString flipString = new FlipString(inputString);
        String flippedStr = flipString.flip();
        System.out.println("Flipped String --> " + flippedStr);
    }

    String getInputString() throws IOException {
        return buf.readLine();
    }
}
