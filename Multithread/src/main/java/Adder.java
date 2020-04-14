import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Adder implements Runnable {
    private String inFile, outFile;

    public Adder(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    @Override
    public void run() {
        try {
            doAdd();
        } catch(IOException e) {
            System.out.println("Error");
        }
    }

    public void doAdd() throws IOException {
        int total = 0;
        String line = null;
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(inFile))) {
            while ((line = reader.readLine()) != null) {
                total += Integer.parseInt(line);
            }
        }
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(outFile))){
            writer.write("Total: " + total);
        }

    }

    public static void main(String[] args) {
        String[] inFiles = {"a.txt", "b.txt"};
        String[] outFiles = {"a.txt", "b.txt"};
        Thread[] threads = new Thread[inFiles.length];
        for(int i = 0; i< inFiles.length; ++i) {
            Adder adder = new Adder(inFiles[i], outFiles[i]);
            threads[i] = new Thread(adder);
            threads[i].start(); // calls run
        }

        try {
            for (Thread thread : threads) {
                thread.join(); // Blocks waiting for thread completion
            }
        } catch (InterruptedException e) {
            System.out.println("Error");
        }
    }
}
