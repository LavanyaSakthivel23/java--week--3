public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary)
            throws InvalidEmployeeException {
        if (id <= 0) {
            throw new InvalidEmployeeException("ID must be a positive number");
        }
        this.id = id;
        setName(name);
        setDepartment(department);
        setSalary(salary);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    public void setName(String name) throws InvalidEmployeeException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidEmployeeException("Name cannot be empty");
        }
        if (name.contains(",")) {
            throw new InvalidEmployeeException("Name cannot contain a comma");
        }
        this.name = name.trim();
    }

    public void setDepartment(String department) throws InvalidEmployeeException {
        if (department == null || department.trim().isEmpty()) {
            throw new InvalidEmployeeException("Department cannot be empty");
        }
        if (department.contains(",")) {
            throw new InvalidEmployeeException("Department cannot contain a comma");
        }
        this.department = department.trim();
    }

    public void setSalary(double salary) throws InvalidEmployeeException {
        if (salary <= 0) {
            throw new InvalidEmployeeException("Salary must be greater than 0");
        }
        this.salary = salary;
    }

    // Format used to save in the .txt file: id,name,department,salary
    public String toFileLine() {
        return id + "," + name + "," + department + "," + salary;
    }

    // Create an Employee from one line of the file
    public static Employee fromFileLine(String line) throws InvalidEmployeeException {
        String[] p = line.split(",");
        if (p.length != 4) {
            throw new InvalidEmployeeException("Bad line format: " + line);
        }
        try {
            return new Employee(Integer.parseInt(p[0].trim()), p[1], p[2],
                    Double.parseDouble(p[3].trim()));
        } catch (NumberFormatException e) {
            throw new InvalidEmployeeException("Bad number in line: " + line);
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %-4d Name: %-15s Dept: %-12s Salary: %.2f",
                id, name, department, salary);
    }
}