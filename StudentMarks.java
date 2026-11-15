import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentMarks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> marks = new ArrayList<>();

        System.out.print("How many students? ");
        int n;
        try {
            n = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number.");
            return;
        }

        for (int i = 1; i <= n; i++) {
            while (true) {
                System.out.print("Enter marks of student " + i + " (0-100): ");
                try {
                    int m = sc.nextInt();
                    if (m < 0 || m > 100) {
                        System.out.println("Marks must be between 0 and 100.");
                        continue;
                    }
                    marks.add(m);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Enter a number.");
                    sc.next(); // clear the wrong input
                }
            }
        }

        if (marks.isEmpty()) {
            System.out.println("No marks entered.");
            return;
        }

        int sum = 0;
        for (int m : marks) sum += m;

        System.out.println("\nMarks: " + marks);
        System.out.println("Highest: " + Collections.max(marks));
        System.out.println("Lowest: " + Collections.min(marks));
        System.out.println("Average: " + (double) sum / marks.size());

        Collections.sort(marks);
        System.out.println("Sorted: " + marks);
        sc.close();
    }
}