// ============================================================
//  DatabaseManager.java  –  File Engine / CRUD Operations
//  Handles Create, Read (all + filtered), and Delete for the
//  flat-file "database" stored in students.txt.
// ============================================================

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DatabaseManager {

    // ----- Configuration -----
    private static final String FILE_NAME = "students.txt";
    private static final String TEMP_FILE = "temp.txt";

    // ==================================================
    //  CREATE – Append a new student record to the file
    // ==================================================
    public void addRecord(Scanner sc) {
        System.out.print("\nEnter Roll Number : ");
        int roll = sc.nextInt();
        sc.nextLine(); // consume leftover newline

        System.out.print("Enter Name        : ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Grade (A-F) : ");
        char grade = sc.next().toUpperCase().charAt(0);

        Student student = new Student(roll, name, grade);

        // Append mode  →  true keeps existing data
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_NAME, true))) {

            writer.write(student.toFileLine());
            writer.newLine();
            System.out.println("\n✔  Record added successfully!");

        } catch (IOException e) {
            System.out.println("✘  Error writing to file: " + e.getMessage());
        }
    }

    // ==================================================
    //  READ (ALL) – Print every record in the file
    // ==================================================
    public void viewAllRecords() {
        File file = new File(FILE_NAME);
        if (!file.exists() || file.length() == 0) {
            System.out.println("\nNo records found.");
            return;
        }

        System.out.println("\n========== ALL STUDENT RECORDS ==========");
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student s = Student.fromFileLine(line);
                if (s != null) {
                    System.out.println("-----------------------------------------");
                    System.out.println(s);
                    count++;
                }
            }
        } catch (IOException e) {
            System.out.println("✘  Error reading file: " + e.getMessage());
            return;
        }

        System.out.println("-----------------------------------------");
        System.out.println("Total records: " + count);
    }

    // ==================================================
    //  READ (FILTERED) – Search by Roll Number
    // ==================================================
    public void searchRecord(Scanner sc) {
        File file = new File(FILE_NAME);
        if (!file.exists() || file.length() == 0) {
            System.out.println("\nNo records found.");
            return;
        }

        System.out.print("\nEnter Roll Number to search: ");
        int targetRoll = sc.nextInt();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student s = Student.fromFileLine(line);
                if (s != null && s.getRollNo() == targetRoll) {
                    System.out.println("\n✔  Record Found:");
                    System.out.println("-----------------------------------------");
                    System.out.println(s);
                    System.out.println("-----------------------------------------");
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("✘  Error reading file: " + e.getMessage());
            return;
        }

        System.out.println("✘  Record not found.");
    }

    // ==================================================
    //  DELETE – Remove a record by Roll Number
    // ==================================================
    public void deleteRecord(Scanner sc) {
        File file = new File(FILE_NAME);
        if (!file.exists() || file.length() == 0) {
            System.out.println("\nNo records found.");
            return;
        }

        System.out.print("\nEnter Roll Number to delete: ");
        int targetRoll = sc.nextInt();

        File tempFile = new File(TEMP_FILE);
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                Student s = Student.fromFileLine(line);
                if (s != null && s.getRollNo() == targetRoll) {
                    found = true;           // skip this record (don't copy)
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("✘  Error processing file: " + e.getMessage());
            return;
        }

        // Swap files: delete original, rename temp → original
        if (!file.delete()) {
            System.out.println("✘  Could not remove original file.");
            return;
        }
        if (!tempFile.renameTo(file)) {
            System.out.println("✘  Could not rename temp file.");
            return;
        }

        if (found) {
            System.out.println("✔  Record deleted successfully!");
        } else {
            System.out.println("✘  Record not found.");
        }
    }
}
