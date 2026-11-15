import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class WordFrequency {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        // Create a sample input file if it does not exist
        File f = new File(inputFile);
        if (!f.exists()) {
            try (FileWriter fw = new FileWriter(inputFile)) {
                fw.write("java is fun and java is powerful\n");
                fw.write("learning java makes coding fun\n");
            } catch (IOException e) {
                System.out.println("Could not create input file: " + e.getMessage());
                return;
            }
        }

        Map<String, Integer> freq = new TreeMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String word : line.toLowerCase().split("\\W+")) {
                    if (word.isEmpty()) continue;
                    freq.put(word, freq.getOrDefault(word, 0) + 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + inputFile);
            return;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        try (FileWriter fw = new FileWriter(outputFile)) {
            for (Map.Entry<String, Integer> e : freq.entrySet()) {
                fw.write(e.getKey() + " : " + e.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            return;
        }

        System.out.println("Word frequency written to " + outputFile);
        for (Map.Entry<String, Integer> e : freq.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
    }
}