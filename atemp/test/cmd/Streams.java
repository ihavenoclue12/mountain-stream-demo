package atemp.test.cmd;

public class Streams {

    private final int[] streams;
    private int numStreams;

    public Streams(int[] streams, int numStreams) {
        this.streams = streams;
        this.numStreams = numStreams;
    }

    public int get(int index) {
        return streams[index];
    }

    public int getNumStreams() {
        return numStreams;
    }

    public void splitRight(int index, int percent) {
        int water = get(index);
        int leftStream = (int) (water * (percent * 1.0) / 100);
        int rightStream = water - leftStream;
        streams[index] = leftStream;
        insertAt(index + 1, rightStream);
    }

    public void mergeRight(int index) {
        streams[index] += streams[index + 1];
        for (int i = index + 1; i < numStreams; i++) {
            streams[i] = streams[i + 1];
        }
        numStreams--;
    }
    
    private void insertAt(int index, int value) {
        for (int i = numStreams; i > index; i--) {
            streams[i] = streams[i - 1];
        }
        numStreams++;
        streams[index] = value;
    }

}
