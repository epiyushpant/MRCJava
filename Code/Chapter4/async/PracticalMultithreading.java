/**
 * PracticalMultithreading.java
 * Demonstrates a practical scenario with 4 parallel tasks:
 * - 2 File Download tasks (Extending Thread)
 * - 2 Data Processing tasks (Implementing Runnable)
 */

// Practical Scenario 1: Downloading separate files (Extending Thread)
class FileDownloader extends Thread {
    private String fileName;

    public FileDownloader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        System.out.println("⬇️  Started downloading: " + fileName);
        try {
            // Simulate variable download time (2-4 seconds)
            for (int i = 0; i <= 100; i += 25) {
                System.out.println("   Downloading " + fileName + ": " + i + "%");
                Thread.sleep(500 + (long) (Math.random() * 500));
            }
            System.out.println("✅ Finished downloading: " + fileName);
        } catch (InterruptedException e) {
            System.out.println("❌ Download interrupted: " + fileName);
        }
    }
}

// Practical Scenario 2: Processing distinct datasets (Implementing Runnable)
class DataProcessor implements Runnable {
    private String datasetName;

    public DataProcessor(String datasetName) {
        this.datasetName = datasetName;
    }

    @Override
    public void run() {
        System.out.println("⚙️  Started processing: " + datasetName);
        try {
            // Simulate processing steps
            Thread.sleep(1000); // Initialization
            System.out.println("   Analyzed " + datasetName);

            Thread.sleep(1000); // Computing
            System.out.println("   Computed stats for " + datasetName);

            Thread.sleep(1000); // Saving
            System.out.println("💾 Saved results for: " + datasetName);
        } catch (InterruptedException e) {
            System.out.println("❌ Processing interrupted: " + datasetName);
        }
    }
}

public class PracticalMultithreading {
    public static void main(String[] args) {
        System.out.println("=== Starting Multithreading Demo ===\n");

        // --- Task Set 1: Using Thread Class (File Downloads) ---
        FileDownloader download1 = new FileDownloader("movie.mp4");
        FileDownloader download2 = new FileDownloader("document.pdf");

        // --- Task Set 2: Using Runnable Interface (Data Processing) ---
        // Create the Runnable objects
        DataProcessor process1 = new DataProcessor("UserLogs.txt");
        DataProcessor process2 = new DataProcessor("SalesData.csv");

        // Create Threads for the Runnables
        Thread t1 = new Thread(process1);
        Thread t2 = new Thread(process2);

        // --- Start All Threads ---
        // Notice: They will run concurrently (mixed order)
        download1.start();
        download2.start();
        t1.start();
        t2.start();

        System.out.println("=== Main Thread requested all tasks to start ===\n");
    }
}
