# Java Week 3 - Skill Nexis Internship

Name: YOUR NAME

## About the Project
Week 3 covers Data Structures and File Handling in Java: the Collections
Framework (ArrayList, HashMap, HashSet), sorting and searching, reading and
writing files with FileReader / FileWriter, and exception handling
(try, catch, finally, throw, throws).

## Folder Structure
```
java-week-3/
├── README.md
├── StudentMarks.java
├── WordFrequency.java
├── DivideByZero.java
├── FileReadWrite.java
├── MultipleCatch.java
├── CustomExceptionDemo.java
├── WordCount.java
└── EmployeeManagementSystem/
    ├── Employee.java
    ├── EmployeeManagement.java
    └── InvalidEmployeeException.java
```

## Assignments
| Program | Description |
|---------|-------------|
| StudentMarks.java | Stores marks in an ArrayList, finds highest, lowest, average and sorts |
| WordFrequency.java | Reads input.txt, counts word frequency (Map) and writes output.txt |

## Practice Set
| Program | Description |
|---------|-------------|
| DivideByZero.java | Handles divide-by-zero with try-catch-finally |
| FileReadWrite.java | Writes to and reads from a text file |
| MultipleCatch.java | Demonstrates multiple catch blocks |
| CustomExceptionDemo.java | Custom exception for invalid age |
| WordCount.java | Counts lines and words in a file |

## Mini Project: Employee Management System
A menu-driven console application using Collections and File I/O.

Features:
- Add, view, search, update salary and delete employees
- Sort by name or by salary
- Show unique departments (Set)
- Employees stored in a HashMap (ID to Employee)
- Data saved to and loaded from `employees.txt`
- Custom exception `InvalidEmployeeException` for invalid input
  (empty name, negative salary, duplicate ID, non-numeric values)
- Corrupted lines in the data file are skipped safely

## How to Run
Single files:
```
javac StudentMarks.java
java StudentMarks
```

Mini project (multiple files, compile all together):
```
cd EmployeeManagementSystem
javac *.java
java EmployeeManagement
```

## What I Learned
- Using ArrayList, HashMap, HashSet and the Collections utility class
- Sorting with Collections.sort and Comparator
- Reading and writing text files with try-with-resources
- Handling errors with try, catch, finally, throw and throws
- Creating custom exceptions
- Structuring a small project into multiple classes