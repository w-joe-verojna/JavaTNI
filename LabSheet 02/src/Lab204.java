import java.util.Scanner;

public class Lab204 {
    static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Input hour: ");
        int hour = scan.nextInt();
        System.out.print("Input minute: ");
        int minute = scan.nextInt();

        int total_minute = hour * 60 + minute;
        System.out.print("Total Time is " + total_minute + " minutes");
    }
}
