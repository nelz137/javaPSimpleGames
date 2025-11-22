import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Random;

public class RPSGPT1 {
  private static int wins = 0, losses = 0, ties = 0;
  private static int totalRounds = 0;

  private static DefaultTableModel scoreModel;
  private static DefaultTableModel historyModel;

  public static void startGame() {
    JFrame frame = new JFrame("Rock Paper Scissors - RPSGPT1");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(800, 700);
    frame.setLayout(new BorderLayout());

    // --- BUTTON PANEL --
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(180, 240, 180));
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));

    JButton rockButton = new JButton("Rock");
    JButton paperButton = new JButton("Paper");
    JButton scissorsButton = new JButton("Scissors");
    JButton exitButton = new JButton("Exit");

    Dimension btnSize = new Dimension(120, 40);
    rockButton.setPreferredSize(btnSize);
    paperButton.setPreferredSize(btnSize);
    scissorsButton.setPreferredSize(btnSize);
    exitButton.setPreferredSize(btnSize);

    buttonPanel.add(rockButton);
    buttonPanel.add(paperButton);
    buttonPanel.add(scissorsButton);
    buttonPanel.add(exitButton);

    frame.add(buttonPanel, BorderLayout.NORTH);

    // --- RESULT AREA --
    JTextArea resultArea = new JTextArea(6, 50);
    resultArea.setEditable(false);
    resultArea.setBackground(new Color(230, 255, 230));
    JScrollPane resultScroll = new JScrollPane(resultArea);
    frame.add(resultScroll, BorderLayout.CENTER);

    // --- LOWER PANEL: SCOREBOARD + HISTORY --
    JPanel lowerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
    lowerPanel.setBackground(new Color(200, 255, 200));

    // Scoreboard Table
    String[] scoreColumns = {"Wins", "Losses", "Ties", "Total", "Win %", "Lose %", "Tie %"};
    scoreModel = new DefaultTableModel(scoreColumns, 0);
    JTable scoreTable = new JTable(scoreModel);
    scoreTable.setRowHeight(28);
    scoreTable.setFont(new Font("SansSerif", Font.BOLD, 14));
    scoreTable.setBackground(new Color(150, 240, 150));
    scoreTable.setForeground(Color.BLACK);
    ((DefaultTableCellRenderer) scoreTable.getDefaultRenderer(Object.class)).setHorizontalAlignment(JLabel.CENTER);

    JScrollPane scoreScroll = new JScrollPane(scoreTable);
    scoreScroll.setPreferredSize(new Dimension(700, 60));

    // Round History Table
    String[] historyColumns = {"Round", "Your Choice", "Computer Choice", "Result"};
    historyModel = new DefaultTableModel(historyColumns, 0);
    JTable historyTable = new JTable(historyModel);
    historyTable.setRowHeight(22);
    historyTable.setBackground(new Color(180, 255, 180));
    historyTable.setForeground(Color.BLACK);
    ((DefaultTableCellRenderer) historyTable.getDefaultRenderer(Object.class)).setHorizontalAlignment(JLabel.CENTER);

    JScrollPane historyScroll = new JScrollPane(historyTable);

    lowerPanel.add(scoreScroll);
    lowerPanel.add(historyScroll);

    frame.add(lowerPanel, BorderLayout.SOUTH);

    // --- GAME LOGIC --
    Random rand = new Random();

  rockButton.addActionListener(e -> { playRound("Rock", rand, resultArea); e.getActionCommand(); });
  paperButton.addActionListener(e -> { playRound("Paper", rand, resultArea); e.getActionCommand(); });
  scissorsButton.addActionListener(e -> { playRound("Scissors", rand, resultArea); e.getActionCommand(); });

  exitButton.addActionListener(e -> { frame.dispose(); e.getActionCommand(); });

    frame.setVisible(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(RPSGPT1::startGame);
  }

  private static void playRound(String playerChoice, Random rand, JTextArea resultArea) {
    String[] options = {"Rock", "Paper", "Scissors"};
    String computerChoice = options[rand.nextInt(3)];
    String result;

    if (playerChoice.equals(computerChoice)) {
      ties++;
      result = "Tie";
    } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
        (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
        (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
      wins++;
      result = "You Win";
    } else {
      losses++;
      result = "Computer Wins";
    }

    totalRounds++;
    resultArea.append("You: " + playerChoice + " | Computer: " + computerChoice + " => " + result + "\n");

    // Update history table
    historyModel.addRow(new Object[]{totalRounds, playerChoice, computerChoice, result});

    // Update scoreboard (one row only)
    scoreModel.setRowCount(0);
    double winPct = (totalRounds > 0) ? (wins * 100.0 / totalRounds) : 0;
    double losePct = (totalRounds > 0) ? (losses * 100.0 / totalRounds) : 0;
    double tiePct = (totalRounds > 0) ? (ties * 100.0 / totalRounds) : 0;

    scoreModel.addRow(new Object[]{wins, losses, ties, totalRounds,
        String.format("%.2f%%", winPct),
        String.format("%.2f%%", losePct),
        String.format("%.2f%%", tiePct)});
  }
}
