import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AdderPool implements Runnable {
    private String inFile, outFile;

    public AdderPool(String inFile, String outFile) {
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

        ExecutorService es = Executors.newFixedThreadPool(3); // allows no more than 3 threads at a time
        for(int i = 0; i< inFiles.length; ++i) {
            AdderPool adder = new AdderPool(inFiles[i], outFiles[i]);
            es.submit(adder); // use available thread
        }

        try {
            es.shutdown(); // cant use es anymore
            es.awaitTermination(60, TimeUnit.SECONDS); // block for 60 seconds
        } catch( InterruptedException e) {
            System.out.println("Error");
        }

    }
}