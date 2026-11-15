public class MultipleCatch {
    public static void main(String[] args) {
        try {
            int[] arr = new int[3];
            String text = null;
            int x = 10 / 0;            // ArithmeticException
            arr[5] = 1;                // ArrayIndexOutOfBoundsException
            System.out.println(text.length()); // NullPointerException
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException");
        } catch (Exception e) {
            System.out.println("Some other exception: " + e);
        }

        // Second example: a different exception each time
        String[] values = {"25", "abc", null};
        for (String v : values) {
            try {
                int n = Integer.parseInt(v);
                System.out.println("Number: " + n);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number: " + v);
            }
        }
    }
}