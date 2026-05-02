import java.io.*;
import java.util.*;

class Employee implements Serializable {
    int id;
    String name;
    String department;
    double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Dept: " + department + " | Salary: $" + salary;
    }
    
    public String toFileString() {
        return id + "," + name + "," + department + "," + salary;
    }
}

public class EmployeeSystem {
    private static List<Employee> employees = new ArrayList<>();
    private static final String FILE_NAME = "employees.txt";

    public static void main(String[] args) {
        loadFromFile(); // Load existing data at startup
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n--- Employee Management System ---");
                System.out.println("1. Add Employee\n2. View All\n3. Save & Exit");
                System.out.print("Choice: ");
                
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    System.out.print("Enter ID (Integer): ");
                    int id = Integer.parseInt(scanner.nextLine());
                    
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    
                    System.out.print("Enter Department: ");
                    String dept = scanner.nextLine();
                    
                    System.out.print("Enter Salary: ");
                    double salary = Double.parseDouble(scanner.nextLine());

                    employees.add(new Employee(id, name, dept, salary));
                    System.out.println("Employee added locally!");

                } else if (choice == 2) {
                    if (employees.isEmpty()) System.out.println("No records found.");
                    for (Employee e : employees) System.out.println(e);

                } else if (choice == 3) {
                    saveToFile();
                    System.out.println("Data saved. Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid option. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number for ID or Salary.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private static void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Employee e : employees) {
                writer.println(e.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file.");
        }
    }

    private static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                employees.add(new Employee(
                    Integer.parseInt(parts[0]), 
                    parts[1], 
                    parts[2], 
                    Double.parseDouble(parts[3])
                ));
            }
        } catch (IOException e) {
            System.out.println("Could not load data.");
        }
    }
}