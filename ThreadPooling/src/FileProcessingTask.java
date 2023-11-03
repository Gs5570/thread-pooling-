import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessingTask implements Runnable {
    private String filename;
    private int startLine;
    private int endLine;

    public FileProcessingTask(String filename, int startLine, int endLine) {
        this.filename = filename;
        this.startLine = startLine;
        this.endLine = endLine;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int currentLine = 0;
            while ((line = br.readLine()) != null) {
                if (currentLine >= startLine && currentLine < endLine) {
                    System.out.println(Thread.currentThread().getName() + " : " + line);
                }
                currentLine++;
                if (currentLine >= endLine) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
