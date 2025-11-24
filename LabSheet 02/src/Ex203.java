import javax.swing.*;
import java.text.DecimalFormat;

public class Ex203 {
    static void main(String[] args) {
        final int lecture_price = 1900;
        final int lab_price = 3500;

        DecimalFormat dformat = new DecimalFormat("#,###.00");

        int lecture_credit = Integer.parseInt(JOptionPane.showInputDialog("Enter lecture credit:"));
        int lab_credit = Integer.parseInt(JOptionPane.showInputDialog("Enter lab credit:"));

        int lecture_total_price = lecture_credit * lecture_price;
        int lab_total_price = lab_credit * lab_price;
        int total_price = lecture_total_price + lab_total_price;

        JOptionPane.showMessageDialog(null, "Lecture " + lecture_credit + "x" + lecture_price +
                " = "+ dformat.format(lecture_total_price) + "\nLaboratory: " + lab_credit + "x" + lab_price + " = " +
                dformat.format(lab_total_price) + "\nTotal price: " + dformat.format(total_price));
    }
}
