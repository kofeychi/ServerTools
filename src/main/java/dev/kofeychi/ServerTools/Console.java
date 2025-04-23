package dev.kofeychi.ServerTools;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

import java.io.OutputStream;
import java.io.PrintStream;

public class Console implements PreLaunchEntrypoint {
    public static StringBuilder consoleData = new StringBuilder();

    private static final OutputStream proxyStream = new OutputStream() {
        final PrintStream originalStream = System.out;

        @Override
        public void write(int b) {
            consoleData.append((char) b);
            originalStream.write(b);
        }
    };

    public void onPreLaunch() {
        System.setOut(new PrintStream(proxyStream, true));
        System.setErr(new PrintStream(proxyStream, true));
    }
}
