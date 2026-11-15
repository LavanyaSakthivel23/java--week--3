import java.io.*;

public class FileReadWrite {
    public static void main(String[] args) {
        String fileName = "notes.txt";

        // Write
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write("Hello from Java file handling.\n");
            fw.write("This is the second line.\n");
            System.out.println("Data written to " + fileName);
        } catch (IOException e) {
            System.out.println("Write error: " + e.getMessage());
        }

        // Read
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            System.out.println("\nFile content:");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
    }
}