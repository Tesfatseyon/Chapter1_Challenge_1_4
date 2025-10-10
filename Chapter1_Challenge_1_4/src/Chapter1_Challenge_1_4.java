import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Chapter1_Challenge_1_4 {

    public static void main(String[] args) {
        BufferedReader reader = null; // Initialize reader outside the try block
        try {
            // 1. Tries to read from a file named config.txt.
            File configFile = new File("config.txt");
            reader = new BufferedReader(new FileReader(configFile));

            // Read the first line and parse it as an integer
            String versionLine = reader.readLine();
            if (versionLine == null) {
                throw new IOException("Config file is empty or incomplete.");
            }

            int configVersion = Integer.parseInt(versionLine);

            // 3. If the version is less than 2, throw a custom exception
            if (configVersion < 2) {
                throw new Exception("Config version too old!");
            }

            // Read the second line (file path)
            String filePath = reader.readLine();
            if (filePath == null) {
                throw new IOException("File path missing in config file.");
            }
            // 4. Check if the file at that path exists
            File fileAtPath = new File(filePath);
            if (!fileAtPath.exists()) {
                throw new IOException("File not found at path: " + filePath);
            }

            System.out.println("Config file read successfully.");
            System.out.println("Config version: " + configVersion);
            System.out.println("File path: " + filePath);

        } catch (FileNotFoundException e) {
            // 2. Implements multiple specific catch blocks.
            System.err.println("Error: Config file not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid config version format (not a number): " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {  //Catch our custom exception
            System.err.println("Error: " + e.getMessage());
        } finally {
            // 5. Use a finally block to print a message saying "Config read attempt finished."
            System.out.println("Config read attempt finished.");
            if (reader != null) {
                try {
                    reader.close(); // Close the reader in the finally block to ensure it's always closed.
                } catch (IOException e) {
                    System.err.println("Error closing the reader: " + e.getMessage()); //Handle closing error
                }
            }
        }
    }
}
