import java.util.Scanner;

public class Ex202 {
    static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter student-id: ");
        String student_id = scan.next();
        System.out.print("Enter student-name: ");
        String student_name = scan.next();

        System.out.println("\nStudent-id: " + student_id);
        System.out.println("Student-name: " + student_name);
    }
}
