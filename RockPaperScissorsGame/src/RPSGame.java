import java.util.Random;

public class RPSGame {
    private final String[] CHOICES = {"ค้อน", "กระดาษ", "กรรไกร"};
    private int userScore;
    private int botScore;
    private int draws;
    private final int WINNING_SCORE = 3;

    // 🧠 เพิ่มหน่วยความจำให้บอท: เก็บสถิติว่าผู้เล่นออกอะไรไปกี่ครั้ง
    // index 0 = ค้อน, index 1 = กระดาษ, index 2 = กรรไกร
    private int[] userHistory;

    public RPSGame() {
        userScore = 0;
        botScore = 0;
        draws = 0;
        userHistory = new int[3]; // เริ่มต้นสถิติเป็น 0 ทั้งหมด
    }

    public void playRound(int userChoice) {
        int botChoice;
        int totalPlays = userHistory[0] + userHistory[1] + userHistory[2];

        // 🤖 ลอจิกความฉลาดของบอท
        if (totalPlays == 0) {
            // ถ้ายกแรกสุด ยังไม่มีข้อมูล ให้สุ่มมั่วไปก่อน
            Random rand = new Random();
            botChoice = rand.nextInt(3);
        } else {
            // หาว่าผู้เล่นชอบออกอะไรมากที่สุด
            int favoriteChoice = 0;
            if (userHistory[1] > userHistory[favoriteChoice]) {
                favoriteChoice = 1; // ชอบออกกระดาษ
            }
            if (userHistory[2] > userHistory[favoriteChoice]) {
                favoriteChoice = 2; // ชอบออกกรรไกร
            }

            // บอทเลือกอาวุธที่ "ชนะทาง" สิ่งที่ผู้เล่นชอบออก
            // สูตร: (สิ่งที่ผู้เล่นชอบออก + 1) % 3
            // เช่น ผู้เล่นชอบออก ค้อน(0) -> บอทออก กระดาษ(1)
            // เช่น ผู้เล่นชอบออก กรรไกร(2) -> บอทออก ค้อน(0)
            botChoice = (favoriteChoice + 1) % 3;
        }

        System.out.println("\nคุณออก: " + CHOICES[userChoice]);
        System.out.println("บอทออก: " + CHOICES[botChoice] + " 🤖 (บอทกำลังวิเคราะห์คุณ!)");

        // ตรวจสอบผลแพ้ชนะ
        if (userChoice == botChoice) {
            System.out.println("ผลรอบนี้: เสมอ! 🤝");
            draws++;
        } else if ((userChoice == 0 && botChoice == 2) ||
                (userChoice == 1 && botChoice == 0) ||
                (userChoice == 2 && botChoice == 1)) {
            System.out.println("ผลรอบนี้: คุณชนะ! 🎉");
            userScore++;
        } else {
            System.out.println("ผลรอบนี้: บอทชนะ! 🤖");
            botScore++;
        }

        // 📝 อัปเดตสถิติของผู้เล่น "หลังจาก" เล่นจบตา เพื่อให้บอทเอาไปคิดในตาถัดไป
        userHistory[userChoice]++;
    }

    public void printScore() {
        System.out.println("--- สรุปคะแนน ---");
        System.out.println("คุณ: " + userScore + " | บอท: " + botScore + " | เสมอ: " + draws);
        System.out.println("-----------------");
    }

    public boolean isGameOver() {
        return userScore >= WINNING_SCORE || botScore >= WINNING_SCORE;
    }

    public void printFinalResult() {
        System.out.println("\n===== จบเกม! (Best of 5) =====");
        if (userScore >= WINNING_SCORE) {
            System.out.println("🏆 สุดยอด! คุณหลอกบอทได้ และเป็นผู้ชนะ! 🏆");
        } else {
            System.out.println("🤖 บอทอ่านใจคุณออก! บอทเป็นฝ่ายชนะ! 🤖");
        }
        System.out.println("==============================");
    }
}