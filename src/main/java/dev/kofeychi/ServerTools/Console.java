package dev.kofeychi.ServerTools;

import java.io.OutputStream;
import java.io.PrintStream;

public class Console {
    public static StringBuilder consoleData = new StringBuilder();

    private static final OutputStream proxyStream = new OutputStream() {
        final PrintStream originalStream = System.out;

        @Override
        public void write(int b) {
            consoleData.append((char) b);
            originalStream.write(b);
        }
    };

    public static void main() {
        System.setOut(new PrintStream(proxyStream, true));
        System.setErr(new PrintStream(proxyStream, true));
    }
}
