import java.text.DecimalFormat;
import java.util.Scanner;

public class Lab203 {
    static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DecimalFormat dformat = new DecimalFormat("#,###.00");

        System.out.print("Enter product id: ");
        String product_id = scan.next();

        System.out.print("Enter product name: ");
        String product_name = scan.next();
        scan.nextLine();

        System.out.print("Enter product item: ");
        int quantity = Integer.parseInt(scan.next());

        System.out.print("Enter price per piece: ");
        double price_per_quantity = scan.nextDouble();

        System.out.print("--------------------------------");

        System.out.println("\nProduct name: " + product_name + " ( " + product_id + " )");
        System.out.println("Product item: " + quantity + " ( " + price_per_quantity +" baht/piece)");
        System.out.println("Total price: " + dformat.format(price_per_quantity * quantity) + " baht");
    }
}
