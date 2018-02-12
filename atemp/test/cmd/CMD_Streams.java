package atemp.test.cmd;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CMD_Streams {

    private static final int SPLIT = 99;
    private static final int JOIN = 88;
    private static final int QUIT = 77;

    public static void main(String[] args) {
        // args = new String[] { "streams.in" };
        if (args.length != 1) {
            System.out.println("need a file");
            System.exit(1);
        }
        StreamCommands lines = new StreamCommands(args[0]);
        Streams streams = runStreams(lines);
        print(streams);
    }

    private static Streams runStreams(StreamCommands lines) {
        Streams streams = fillStreams(lines);
        int command = lines.getNextValue();
        while (command != QUIT) {
            switch (command) {
                case SPLIT:
                    int sIndex = lines.getNextValue() - 1;
                    int percent = lines.getNextValue();
                    streams.splitRight(sIndex, percent);
                    break;
                case JOIN:
                    int jIndex = lines.getNextValue() - 1;
                    streams.mergeRight(jIndex);
                    break;
            }
            command = lines.getNextValue();
        }
        return streams;
    }

    private static Streams fillStreams(StreamCommands lines) {
        int[] streams = new int[100];
        int numStreams = lines.getNextValue();
        for (int i = 0; i < numStreams; i++) {
            streams[i] = lines.getNextValue();
        }
        return new Streams(streams, numStreams);
    }

    private static void print(Streams streams) {
        File out = getFile();
        for (int i = 0; i < streams.getNumStreams(); i++) {
            String water = streams.get(i) + " ";
            try {
                Files.write(Paths.get(out.getPath()), water.getBytes(),
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
            }
        }
    }

    private static File getFile() {
        File out = new File("c:/Temp/stream.out");
        if (out.exists())
            out.delete();
        try {
            out.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return out;
    }

}
