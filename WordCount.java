import java.io.*;

public class WordCount {
    public static void main(String[] args) {
        String fileName = "sample.txt";

        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write("Java is a popular programming language\n");
            fw.write("File handling is easy in Java\n");
        } catch (IOException e) {
            System.out.println("Could not create file: " + e.getMessage());
            return;
        }

        int words = 0, lines = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines++;
                line = line.trim();
                if (!line.isEmpty()) {
                    words += line.split("\\s+").length;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        System.out.println("Lines: " + lines);
        System.out.println("Words: " + words);
    }
}