import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.*;

public class AdderThreadReturnResult implements Callable <Integer> {
    private String inFile;

    public AdderThreadReturnResult(String inFile) {
        this.inFile = inFile;
    }

    @Override
    public Integer call() throws IOException{
        return doAdd();
    }

    public int doAdd() throws IOException {
        int total = 0;
        String line = null;
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(inFile))) {
            while ((line = reader.readLine()) != null) {
                total += Integer.parseInt(line);
            }
        }

        return total;

    }

    public static void main(String[] args) {
        String[] inFiles = {"a.txt", "b.txt"};

        ExecutorService es = Executors.newFixedThreadPool(3); // allows no more than 3 threads at a time
        Future<Integer>[] results = new Future[inFiles.length]; // store results of thread

        for(int i = 0; i< inFiles.length; ++i) {
            AdderThreadReturnResult adder = new AdderThreadReturnResult(inFiles[i]);
            results[i] = es.submit(adder); // capture result of thread
        }

        for(Future<Integer> result : results) {
            try {
                int value = result.get(); // blocks until return value available
                System.out.println("Total: " + value);
            } catch (ExecutionException e) { // Exception raised by Adder
                Throwable adderEx = e.getCause(); // Get Adder exception
                // do something with adderEx
            } catch(Exception e) {
                System.out.println("Error"); // non-adder exceptions
            }
        }

        try {
            es.shutdown(); // cant use es anymore
            es.awaitTermination(60, TimeUnit.SECONDS); // block for 60 seconds
        } catch( InterruptedException e) {
            System.out.println("Error");
        }

    }
}
