import java.util.*;

public class StudentAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        System.out.println("=== Student Performance Analyzer ===");
        
        try {
            System.out.print("Enter number of students: ");
            int numStudents = sc.nextInt();
            
            System.out.print("Enter number of subjects: ");
            int numSubjects = sc.nextInt();

            for (int i = 0; i < numStudents; i++) {
                sc.nextLine(); 
                System.out.print("\nEnter name for Student " + (i + 1) + ": ");
                String name = sc.nextLine();
                
                int[] marks = new int[numSubjects];
                for (int j = 0; j < numSubjects; j++) {
                    System.out.print("  Mark for Subject " + (j + 1) + ": ");
                    marks[j] = sc.nextInt();
                }
                students.add(new Student(name, marks));
            }

            displayResults(students);

        } catch (Exception e) {
            System.out.println("Invalid input.");
        } finally {
            sc.close();
        }
    }

    public static void displayResults(List<Student> students) {
        
       
        if (students.isEmpty()) return;
        System.out.println("\n" + "=".repeat(35));
        System.out.printf("%-15s %-10s %-5s%n", "NAME", "AVERAGE", "GRADE");
        System.out.println("-".repeat(35));
        Student topStudent = students.get(0);
        double classSum = 0;
        for (Student s : students) {
            double avg = s.calculateAverage();
            classSum += avg;
            System.out.printf("%-15s %-10.2f %-5c%n", s.getName(), avg, s.getGrade());
            if (avg > topStudent.calculateAverage()) topStudent = s;
        }
        System.out.println("-".repeat(35));
        System.out.printf("Class Average: %.2f%n", (classSum / students.size()));
        System.out.println("Top Performer: " + topStudent.getName());
        System.out.println("=".repeat(35));
    }
}

class Student {
    private String name;
    private int[] marks;

    public Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() { return name; }

    public double calculateAverage() {

        int sum = 0;
        for (int mark : marks) sum += mark;
        return (double) sum / marks.length;
    }

    public char getGrade() {
        double avg = calculateAverage();
        if (avg >= 90) return 'A';
        if (avg >= 80) return 'B';
        if (avg >= 70) return 'C';
        if (avg >= 60) return 'D';
        if (avg >= 50) return 'E';
        return 'F';
    }
}