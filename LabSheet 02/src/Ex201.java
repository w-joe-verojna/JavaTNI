import java.text.DecimalFormat;

public class Ex201 {
    static void main(String[] args) {
        String product_id = "PB-001";
        String product_name = "Pencil Box";
        int quantity = 112;
        double total_price = 30.20;

        DecimalFormat dformat = new DecimalFormat("#,###.00");

        System.out.println("Product name: " + product_name + "("  + product_id + ")");
        System.out.println("Product item: " + quantity + "(" + total_price + " baht/piece)");

        double total_price2 = quantity * total_price;
        System.out.println("Total price: " + total_price2 + " baht");
    }
}
