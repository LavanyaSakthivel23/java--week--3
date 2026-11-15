import java.util.Scanner;

// Custom exception class (not public, so it can live in the same file)
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class CustomExceptionDemo {
    static void validateAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 120) {
            throw new InvalidAgeException("Age must be between 0 and 120");
        }
        if (age < 18) {
            throw new InvalidAgeException("Age is below 18. Not eligible");
        }
        System.out.println("Age " + age + " is valid. Eligible.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your age: ");
        try {
            int age = Integer.parseInt(sc.nextLine().trim());
            validateAge(age);
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a number");
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}