import java.io.*;
import java.util.*;

public class EmployeeManagement {
    private static final String FILE_NAME = "employees.txt";

    // Map: id -> Employee (fast search by id)
    private static final Map<Integer, Employee> employees = new HashMap<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadFromFile();

        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": addEmployee(); break;
                case "2": viewAll(); break;
                case "3": searchById(); break;
                case "4": updateSalary(); break;
                case "5": deleteEmployee(); break;
                case "6": sortEmployees(); break;
                case "7": showDepartments(); break;
                case "8": saveToFile(); break;
                case "9":
                    saveToFile();
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Enter 1-9.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== Employee Management System =====");
        System.out.println("1. Add employee");
        System.out.println("2. View all employees");
        System.out.println("3. Search by ID");
        System.out.println("4. Update salary");
        System.out.println("5. Delete employee");
        System.out.println("6. Sort employees");
        System.out.println("7. Show departments");
        System.out.println("8. Save to file");
        System.out.println("9. Save and exit");
        System.out.print("Choose: ");
    }

    // ---------- helpers for safe input ----------
    private static int readInt(String prompt) throws InvalidEmployeeException {
        System.out.print(prompt);
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidEmployeeException("Please enter a valid whole number");
        }
    }

    private static double readDouble(String prompt) throws InvalidEmployeeException {
        System.out.print(prompt);
        try {
            return Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidEmployeeException("Please enter a valid number");
        }
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    // ---------- features ----------
    private static void addEmployee() {
        try {
            int id = readInt("Enter ID: ");
            if (employees.containsKey(id)) {
                throw new InvalidEmployeeException("ID " + id + " already exists");
            }
            String name = readText("Enter name: ");
            String dept = readText("Enter department: ");
            double salary = readDouble("Enter salary: ");

            Employee e = new Employee(id, name, dept, salary);
            employees.put(id, e);
            System.out.println("Employee added.");
        } catch (InvalidEmployeeException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void viewAll() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        List<Employee> list = new ArrayList<>(employees.values());
        list.sort(Comparator.comparingInt(Employee::getId));
        for (Employee e : list) System.out.println(e);
        System.out.println("Total employees: " + list.size());
    }

    private static void searchById() {
        try {
            int id = readInt("Enter ID to search: ");
            Employee e = employees.get(id);
            System.out.println(e != null ? e : "No employee with ID " + id);
        } catch (InvalidEmployeeException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void updateSalary() {
        try {
            int id = readInt("Enter ID to update: ");
            Employee e = employees.get(id);
            if (e == null) {
                System.out.println("No employee with ID " + id);
                return;
            }
            double salary = readDouble("Enter new salary: ");
            e.setSalary(salary);
            System.out.println("Salary updated.");
        } catch (InvalidEmployeeException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void deleteEmployee() {
        try {
            int id = readInt("Enter ID to delete: ");
            if (employees.remove(id) != null) {
                System.out.println("Employee deleted.");
            } else {
                System.out.println("No employee with ID " + id);
            }
        } catch (InvalidEmployeeException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void sortEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        System.out.print("Sort by (1) Name  (2) Salary high to low: ");
        String c = sc.nextLine().trim();
        List<Employee> list = new ArrayList<>(employees.values());
        if (c.equals("1")) {
            list.sort(Comparator.comparing(e -> e.getName().toLowerCase()));
        } else if (c.equals("2")) {
            list.sort((a, b) -> Double.compare(b.getSalary(), a.getSalary()));
        } else {
            System.out.println("Invalid choice.");
            return;
        }
        for (Employee e : list) System.out.println(e);
    }

    private static void showDepartments() {
        // A Set keeps only unique values (TreeSet also keeps them sorted)
        Set<String> departments = new TreeSet<>();
        for (Employee e : employees.values()) departments.add(e.getDepartment());
        System.out.println(departments.isEmpty() ? "No departments yet." : "Departments: " + departments);
    }

    // ---------- file handling ----------
    private static void saveToFile() {
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            for (Employee e : employees.values()) {
                fw.write(e.toFileLine() + "\n");
            }
            System.out.println("Data saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Could not save file: " + e.getMessage());
        }
    }

    private static void loadFromFile() {
        File f = new File(FILE_NAME);
        if (!f.exists()) {
            System.out.println("No saved data found. Starting fresh.");
            return;
        }
        int loaded = 0, skipped = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                try {
                    Employee e = Employee.fromFileLine(line);
                    employees.put(e.getId(), e);
                    loaded++;
                } catch (InvalidEmployeeException ex) {
                    skipped++;
                }
            }
            System.out.println("Loaded " + loaded + " employee(s) from file."
                    + (skipped > 0 ? " Skipped " + skipped + " bad line(s)." : ""));
        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }
    }
}