import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RPSGameUI {

    private JFrame frame;
    private JLabel resultLabel;
    private JLabel scoreLabel;
    private JLabel botActionLabel;

    // 🌟 ประกาศตัวแปรปุ่มไว้ตรงนี้ เพื่อให้สั่งเปิด/ปิดจากที่อื่นได้
    private JButton btnRock;
    private JButton btnPaper;
    private JButton btnScissors;
    private JButton btnReset;

    private int userScore = 0;
    private int botScore = 0;
    private final String[] CHOICES = {"ค้อน", "กระดาษ", "กรรไกร"};
    private final int WINNING_SCORE = 5;

    private int[] userHistory = new int[3];
    private int totalRounds = 0;

    public RPSGameUI() {
        frame = new JFrame("เป่ายิ้งฉุบ (Rock Paper Scissors)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        scoreLabel = new JLabel("คุณ: 0  |  บอท: 0", SwingConstants.CENTER);
        botActionLabel = new JLabel("กดปุ่มด้านล่างเพื่อเริ่มเกม! (ใครได้ 5 แต้มก่อนชนะ)", SwingConstants.CENTER);
        resultLabel = new JLabel("-", SwingConstants.CENTER);

        scoreLabel.setFont(scoreLabel.getFont().deriveFont(Font.BOLD, 18f));
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        botActionLabel.setFont(botActionLabel.getFont().deriveFont(Font.PLAIN, 16f));
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 20f));

        JPanel centerPanel = new JPanel(new GridLayout(3, 1));
        centerPanel.add(scoreLabel);
        centerPanel.add(botActionLabel);
        centerPanel.add(resultLabel);

        JPanel buttonPanel = new JPanel();

        // 🌟 สร้างปุ่มอาวุธ
        btnRock = new JButton("✊ ค้อน");
        btnPaper = new JButton("✋ กระดาษ");
        btnScissors = new JButton("✌️ กรรไกร");

        Font buttonFont = btnRock.getFont().deriveFont(Font.BOLD, 20f);
        btnRock.setFont(buttonFont);
        btnPaper.setFont(buttonFont);
        btnScissors.setFont(buttonFont);

        btnRock.addActionListener(e -> playRound(0));
        btnPaper.addActionListener(e -> playRound(1));
        btnScissors.addActionListener(e -> playRound(2));

        buttonPanel.add(btnRock);
        buttonPanel.add(btnPaper);
        buttonPanel.add(btnScissors);

        // 🌟 สร้างปุ่ม เริ่มแมตช์ใหม่
        btnReset = new JButton("🔄 เริ่มแมตช์ใหม่");
        btnReset.setFont(btnReset.getFont().deriveFont(Font.BOLD, 14f));
        btnReset.setBackground(new Color(255, 200, 200));
        btnReset.addActionListener(e -> resetGame());

        // 🌟 ซ่อนปุ่มเริ่มเกมใหม่ไว้ก่อนในตอนแรก
        btnReset.setVisible(false);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(btnReset, BorderLayout.SOUTH);

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private int getSmartBotChoice() {
        Random rand = new Random();
        if (totalRounds < 2) return rand.nextInt(3);

        int mostFrequentUserChoice = 0;
        int maxCount = userHistory[0];

        if (userHistory[1] > maxCount) {
            mostFrequentUserChoice = 1;
            maxCount = userHistory[1];
        }
        if (userHistory[2] > maxCount) {
            mostFrequentUserChoice = 2;
        }

        int smartChoice = (mostFrequentUserChoice + 1) % 3;
        int chance = rand.nextInt(100);
        return (chance < 30) ? rand.nextInt(3) : smartChoice;
    }

    private void playRound(int userChoice) {
        int botChoice = getSmartBotChoice();

        userHistory[userChoice]++;
        totalRounds++;

        botActionLabel.setText("บอทออก: " + CHOICES[botChoice]);

        if (userChoice == botChoice) {
            resultLabel.setText("เสมอ! 🤝");
            resultLabel.setForeground(Color.GRAY);
        } else if ((userChoice == 0 && botChoice == 2) ||
                (userChoice == 1 && botChoice == 0) ||
                (userChoice == 2 && botChoice == 1)) {
            resultLabel.setText("คุณชนะรอบนี้! 🎉");
            resultLabel.setForeground(new Color(0, 150, 0));
            userScore++;
        } else {
            resultLabel.setText("บอทชนะรอบนี้! 🤖");
            resultLabel.setForeground(Color.RED);
            botScore++;
        }

        scoreLabel.setText("คุณ: " + userScore + "  |  บอท: " + botScore);

        checkCampaignWinner();
    }

    private void checkCampaignWinner() {
        if (userScore >= WINNING_SCORE || botScore >= WINNING_SCORE) {
            String message;
            String title;
            int messageType;

            if (userScore >= WINNING_SCORE) {
                message = "🏆 ยินดีด้วย! คุณเป็นผู้ชนะแคมเปญนี้! 🏆\nคุณพิชิตบอทได้ " + userScore + " ต่อ " + botScore;
                title = "จบเกม - คุณคือผู้ชนะ!";
                messageType = JOptionPane.INFORMATION_MESSAGE;
            } else {
                message = "🤖 เสียใจด้วย! บอทเป็นผู้ชนะแคมเปญนี้! 🤖\nบอทเอาชนะคุณไปได้ " + botScore + " ต่อ " + userScore;
                title = "จบเกม - บอทชนะ!";
                messageType = JOptionPane.WARNING_MESSAGE;
            }

            JOptionPane.showMessageDialog(frame, message, title, messageType);

            // 🌟 เมื่อเกมจบ ปิดการใช้งานปุ่มอาวุธ และโชว์ปุ่มเริ่มใหม่
            toggleWeapons(false);
            btnReset.setVisible(true);
        }
    }

    // 🌟 เมธอดสำหรับเปิด/ปิด ปุ่มอาวุธทั้ง 3 อันพร้อมกัน
    private void toggleWeapons(boolean isEnabled) {
        btnRock.setEnabled(isEnabled);
        btnPaper.setEnabled(isEnabled);
        btnScissors.setEnabled(isEnabled);
    }

    private void resetGame() {
        userScore = 0;
        botScore = 0;
        userHistory = new int[]{0, 0, 0};
        totalRounds = 0;

        scoreLabel.setText("คุณ: 0  |  บอท: 0");
        botActionLabel.setText("เริ่มแมตช์ใหม่แล้ว! กดปุ่มด้านล่างเลย (ใครได้ 5 แต้มก่อนชนะ)");
        resultLabel.setText("-");
        resultLabel.setForeground(Color.BLACK);

        // 🌟 พอกดเริ่มใหม่ ให้เปิดใช้งานปุ่มอาวุธอีกครั้ง แล้วซ่อนปุ่มเริ่มใหม่
        toggleWeapons(true);
        btnReset.setVisible(false);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Font systemFont = new JLabel().getFont().deriveFont(14f);
            UIManager.put("OptionPane.messageFont", systemFont);
            UIManager.put("OptionPane.buttonFont", systemFont);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new RPSGameUI());
    }
}