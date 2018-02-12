package atemp.test.cmd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StreamCommands {

    private final List<String> lines;
    private int lineIndex = 0;

    public StreamCommands(String filename) {
        try {
            this.lines = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public int getNextValue() {
        int value = Integer.parseInt(lines.get(lineIndex));
        lineIndex++;
        return value;
    }
}
