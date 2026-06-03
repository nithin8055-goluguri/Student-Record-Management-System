# 🎓 Student Record System — Java
### File-Based CRUD Application | Resume Project

---

## 📁 Project Structure

```
StudentRecordSystem/
│
├── src/
│   ├── Student.java          ← Model / Data class
│   ├── DatabaseManager.java  ← File Engine (CRUD logic)
│   └── Main.java             ← Entry point + Menu
│
├── students.txt              ← Auto-generated flat-file database
└── README.md
```

---

## 🧠 Concepts Demonstrated (Resume Talking Points)

| Concept | Where Used |
|---|---|
| OOP – Encapsulation | `Student.java` (private fields + getters) |
| OOP – Single Responsibility | Each class has one job |
| File I/O | `BufferedReader` / `BufferedWriter` |
| CRUD Operations | `DatabaseManager.java` |
| Exception Handling | try-catch in every I/O method |
| Serialisation (manual) | `toFileLine()` / `fromFileLine()` |
| Input Validation | Non-integer guard in `Main.java` |

---

## 🚀 Step-by-Step Compilation & Run Guide

### Prerequisites
- Java JDK 11 or higher installed
- Verify: `java -version`  and  `javac -version`

---

### Step 1 — Create the folder & files
Create a folder called `StudentRecordSystem` and inside it a subfolder `src`.
Place the three `.java` files inside `src/`.

---

### Step 2 — Open Terminal in the project root
```
cd path/to/StudentRecordSystem
```

---

### Step 3 — Compile all three files at once
```bash
javac -d out Student.java DatabaseManager.java Main.java
```
- `-d out` tells the compiler to put `.class` files into an `out/` folder.
- All three files must be compiled together so they can see each other.

---

### Step 4 — Run the application
```bash
java -cp out Main
```
- `-cp out` sets the classpath to the `out/` folder where your `.class` files live.

---

### Step 5 — Test the complete flow

| Step | Action | Expected Result |
|---|---|---|
| 1 | Choose **1** (Add) | Enter roll, name, grade → record saved |
| 2 | Open `students.txt` | See the line: `101 Alice_Bob A` |
| 3 | Choose **2** (View All) | All records printed to console |
| 4 | Choose **3** (Search) | Enter roll number → matching record shown |
| 5 | Choose **4** (Delete) | Enter roll number → record removed |
| 6 | Choose **5** (Exit) | Program exits cleanly |

---

## 💡 How File Storage Works

```
students.txt (flat-file database)
─────────────────────────────────
101 Alice_Bob A
102 Ravi_Kumar B
103 Priya_Singh A
```

- Spaces in names → stored as `_` (underscore) for easy parsing
- Each record = one line
- `BufferedReader` reads line by line on every operation
- Delete = copy all lines except target to `temp.txt` → rename back

---

## 🏗️ Architecture Diagram

```
Main.java
  │  (user input via Scanner)
  ▼
DatabaseManager.java          students.txt
  │  addRecord()      ──────►  (append line)
  │  viewAllRecords() ◄──────  (read all lines)
  │  searchRecord()   ◄──────  (scan until match)
  │  deleteRecord()   ──────►  (rewrite without target)
  │
  ▼
Student.java
  (toFileLine / fromFileLine serialisation)
```

---


