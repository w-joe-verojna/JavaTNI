import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ยินดีต้อนรับสู่เกม เป่ายิ้งฉุบ!");
        System.out.println("📌 กติกา: ใครชนะครบ 3 ครั้งก่อน ถือเป็นผู้ชนะแมตช์นี้! (Best of 5)");

        boolean isPlaying = true; // ตัวแปรควบคุมลูปชั้นนอก (เล่นแมตช์ใหม่)

        // --- ลูปชั้นนอก: สำหรับเริ่มแมตช์ใหม่ ---
        while (isPlaying) {

            // ⭐️ หัวใจสำคัญ: สร้างอ็อบเจกต์เกมใหม่ทุกครั้งที่เริ่มแมตช์ใหม่ (คะแนนจะถูกรีเซ็ตเป็น 0)
            RPSGame game = new RPSGame();
            System.out.println("\n==============================");
            System.out.println("🔥 เริ่มแมตช์ใหม่! ขอให้โชคดี 🔥");
            System.out.println("==============================");

            // --- ลูปชั้นใน: สำหรับเล่นแต่ละตาใน 1 แมตช์ ---
            while (true) {
                System.out.println("\nเลือกอาวุธของคุณ: (0) ค้อน, (1) กระดาษ, (2) กรรไกร หรือพิมพ์ (9) ยอมแพ้");
                System.out.print("ใส่ตัวเลข: ");
                int choice = scanner.nextInt();

                if (choice == 9) {
                    System.out.println("คุณยอมแพ้แมตช์นี้...");
                    break; // หยุดลูปชั้นใน (จบแมตช์นี้)
                }

                if (choice < 0 || choice > 2) {
                    System.out.println("กรุณาใส่ตัวเลข 0, 1 หรือ 2 เท่านั้นครับ!");
                    continue;
                }

                game.playRound(choice);
                game.printScore();

                if (game.isGameOver()) {
                    game.printFinalResult();
                    break; // หยุดลูปชั้นใน (จบแมตช์เพราะมีคนชนะ)
                }
            }
            // --- จบลูปชั้นใน ---

            // ถามผู้เล่นว่าต้องการเริ่มแมตช์ใหม่หรือไม่
            System.out.println("\nคุณต้องการเล่นแมตช์ต่อไปไหม?");
            System.out.print("พิมพ์ 1 เพื่อเล่นต่อ หรือเลขอื่นเพื่อออกจากเกม: ");
            int playAgain = scanner.nextInt();

            // ถ้าผู้เล่นไม่ได้พิมพ์ 1 ให้เปลี่ยนสถานะลูปชั้นนอกเป็น false เพื่อจบเกม
            if (playAgain != 1) {
                isPlaying = false;
                System.out.println("\nขอบคุณที่เข้ามาเล่นด้วยกันครับ! บ๊ายบาย 👋");
            }
        }
        // --- จบลูปชั้นนอก ---

        scanner.close(); // ปิด Scanner เมื่อไม่ใช้งานแล้วเสมอ
    }
}