import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolFileProcessingDemo {
    public static void main(String[] args) {
        //number of thread
        ExecutorService executor = Executors.newFixedThreadPool(10);

        //file to be replaced
        String filename = "/Users/zrahbzhy/Documents/rit_master/fall_2023/swen_755/ThreadPooling/sample.txt"; // Replace with your file path

        try {
            //counts the total number of lines in the file
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            int lines = 0;
            while (reader.readLine() != null) lines++;
            reader.close();

            //opened for the same file, and the total number of lines is divided by 10 to calculate the size of each chunk of lines to be processed by each thread
            BufferedReader br = new BufferedReader(new FileReader(filename));
            int chunkSize = lines / 1000 ;
            for (int i = 0; i < 1000; i++) {
                int startLine = i * chunkSize;
                int endLine = (i + 1) * chunkSize;
                Runnable worker = new FileProcessingTask(filename, startLine, endLine);
                executor.execute(worker);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished processing the file.");
        System.out.println(Thread.activeCount());
    }
}
