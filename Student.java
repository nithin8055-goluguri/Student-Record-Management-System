// ============================================================
//  Student.java  –  Model / Data Class
//  Represents a single student record.
// ============================================================

public class Student {

    private int    rollNo;
    private String name;
    private char   grade;

    // ---------- Constructor ----------
    public Student(int rollNo, String name, char grade) {
        this.rollNo = rollNo;
        this.name   = name;
        this.grade  = grade;
    }

    // ---------- Getters ----------
    public int    getRollNo() { return rollNo; }
    public String getName()   { return name;   }
    public char   getGrade()  { return grade;  }

    // ---------- File serialisation ----------
    /**
     * Converts the object to a line that is written to students.txt.
     * Spaces in names are stored as underscores so each record fits
     * on one whitespace-delimited line (mirrors the C++ design).
     *
     * Format:  <rollNo> <name_with_underscores> <grade>
     * Example: 101 Alice_Bob A
     */
    public String toFileLine() {
        return rollNo + " " + name.replace(' ', '_') + " " + grade;
    }

    /**
     * Parses a line from students.txt back into a Student object.
     * Returns null if the line is malformed.
     */
    public static Student fromFileLine(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        String[] parts = line.trim().split("\\s+");
        if (parts.length < 3) return null;

        int    roll  = Integer.parseInt(parts[0]);
        String name  = parts[1].replace('_', ' ');
        char   grade = parts[2].charAt(0);
        return new Student(roll, name, grade);
    }

    // ---------- Display ----------
    @Override
    public String toString() {
        return String.format("Roll No : %d%nName    : %s%nGrade   : %c", rollNo, name, grade);
    }
}
