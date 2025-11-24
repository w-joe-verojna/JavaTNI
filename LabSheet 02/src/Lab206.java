import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Lab206 {
    static void main() {
        Scanner scan = new Scanner(System.in);
        DecimalFormat dformat = new DecimalFormat("#,###.00");

        int customer = Integer.parseInt(JOptionPane.showInputDialog("How many customer?: "));

        int buffet = 299;
        double buffet_net = buffet * customer + ((buffet * customer) * 0.07);

        int discount_coupon = Integer.parseInt(JOptionPane.showInputDialog("Price with NET is "+ dformat.format(buffet_net) +
                " baht." + "\nHow much of discount (%) on your coupon?: "));

        double total_price = buffet_net * discount_coupon;
        int paid = Integer.parseInt(JOptionPane.showInputDialog("Total price is " + dformat.format(total_price) +
                " baht." + "\nEnter the amount the customer paid: "));

        double give_back_the_change = total_price - paid;
        JOptionPane.showMessageDialog(null, "Total price is " + dformat.format(total_price) +
                " baht." + "\nCustomer paid " + dformat.format(paid) +  " baht." + "\nGet change " +
                dformat.format(give_back_the_change) + " baht.");
    }
}
