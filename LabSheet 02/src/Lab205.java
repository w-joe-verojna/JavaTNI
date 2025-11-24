import javax.swing.*;
import java.util.Scanner;

public class Lab205 {
    static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int total_minute = Integer.parseInt(JOptionPane.showInputDialog("Input minute: "));

        int hour = total_minute / 60;
        int minute = total_minute % 60;

        JOptionPane.showMessageDialog(null, total_minute + " is " + hour + " hour "+ minute +
                " minutes");
    }
}
