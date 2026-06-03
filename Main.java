// ============================================================
//  Main.java  –  Entry Point / Interface Controller
//  Displays the menu and delegates to DatabaseManager.
// ============================================================

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DatabaseManager db = new DatabaseManager();
        Scanner sc         = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n======= STUDENT RECORD SYSTEM =======");
            System.out.println("1. Add    Student Record  (Create)");
            System.out.println("2. View   All Records     (Read)");
            System.out.println("3. Search Student Record  (Read)");
            System.out.println("4. Delete Student Record  (Delete)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            // Guard against non-integer input
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number 1-5.");
                sc.next();           // discard bad token
                choice = 0;
                continue;
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1 -> db.addRecord(sc);
                case 2 -> db.viewAllRecords();
                case 3 -> db.searchRecord(sc);
                case 4 -> db.deleteRecord(sc);
                case 5 -> System.out.println("\nExiting application. Goodbye!\n");
                default -> System.out.println("Invalid choice! Please enter 1-5.");
            }

        } while (choice != 5);

        sc.close();
    }
}
