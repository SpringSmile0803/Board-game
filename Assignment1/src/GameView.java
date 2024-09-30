import javax.swing.*;
import java.awt.*;

class TablePanel extends JPanel {
    public JLabel[][] TableLabels;

    public TablePanel() {
        int margin = 18;
        setLayout(new GridLayout(10, 10)); // 10x10 table
        setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
        setOpaque(false); // Transparent background

        TableLabels = new JLabel[10][10];
        int counter = 1;

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                TableLabels[row][col] = new JLabel(String.valueOf(counter), SwingConstants.CENTER);
                TableLabels[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                TableLabels[row][col].setFont(new Font("Arial", Font.BOLD, 24));
                TableLabels[row][col].setBackground(new Color(143, 143, 143));
                TableLabels[row][col].setOpaque(true);
                add(TableLabels[row][col]);
                counter++;
            }
        }

        // Set cell sizes to ensure they are square
        int cellSize = (Math.min(Toolkit.getDefaultToolkit().getScreenSize().width, 
                                  Toolkit.getDefaultToolkit().getScreenSize().height) - (margin * 2)) / 10;
        for (JLabel[] row : TableLabels) {
            for (JLabel label : row) {
                label.setPreferredSize(new Dimension(cellSize, cellSize));
            }
        }
        revalidate();
    }
}

class PlayerNamePanel extends JPanel {
    private GameController gameController;
    private JLabel[] scoreLabels;

    public PlayerNamePanel(GameController gameController) {
        this.gameController = gameController;
        PlayerSetting[] players = gameController.getplayers();

        setLayout(new GridLayout(4, 1)); // 4x1 table
        setOpaque(false); // Transparent background
        setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18)); // Margin

        scoreLabels = new JLabel[4];

        for (int i = 0; i < 4; i++) {
            JPanel nameList = new JPanel();
            nameList.setLayout(new GridLayout(3, 1));
            nameList.setOpaque(true);
            switch (i) {
                case 1:
                    nameList.setBackground(new Color(184, 157, 176));
                    break;
                case 2:
                    nameList.setBackground(new Color(156, 175, 207));
                    break;
                case 3:
                    nameList.setBackground(new Color(60, 99, 154));
                    break;
                case 4:
                    nameList.setBackground(new Color(112, 97, 139));
                    break;
                default:
                    break;
            }
            nameList.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel text = new JLabel("Player " + i);
            JLabel name = new JLabel(players[i].getName());
            JLabel score = new JLabel(String.valueOf(players[i].getPosition()));

            scoreLabels[i] = score; // save score for update

            text.setFont(new Font("Arial", Font.BOLD, 24));
            name.setFont(new Font("Arial", Font.BOLD, 24));
            score.setFont(new Font("Arial", Font.BOLD, 24));

            text.setHorizontalAlignment(JLabel.CENTER);
            name.setHorizontalAlignment(JLabel.CENTER);
            score.setHorizontalAlignment(JLabel.CENTER);

            nameList.add(text);
            nameList.add(name);
            nameList.add(score);

            add(nameList);
        }
    }

    // update score when rolldice
    public void updateScore() {
        PlayerSetting[] players = gameController.getplayers();
        for (int i = 0; i < 4; i++) {
            scoreLabels[i].setText(String.valueOf(players[i].getPosition()));
        }
        revalidate();
        repaint();
    }
}

class RightItemPanel extends JPanel {
    private final int cellSize;
    private GameController gameController;
    public JLabel imageLabel;

    public RightItemPanel(GameController gameController) {
        this.gameController = gameController;
        cellSize = 258;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setPreferredSize(new Dimension(300, 0));
        setOpaque(false); // Transparent background

        // EXIT button
        JButton exitButton = new JButton("EXIT");
        exitButton.setPreferredSize(new Dimension(180, 65));
        exitButton.addActionListener(e -> System.exit(0));
        ButtonStyler.styleButton(exitButton, "Arial", Font.BOLD, 28, Color.WHITE, Color.BLACK);

        // Dice image
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("image/one.png")); 
        Image scaledImage = imageIcon.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
        imageLabel = new JLabel(new ImageIcon(scaledImage));

        // trun text
        JLabel turntext = new JLabel("NOW IS:\n");
        turntext.setFont(new Font("Arial", Font.BOLD, 28));

        // Roll button
        JButton rollButton = new JButton("ROLL");
        rollButton.setPreferredSize(new Dimension(180, 65));
        rollButton.addActionListener(e -> rollDice());
        ButtonStyler.styleButton(rollButton, "Arial", Font.BOLD, 28, Color.WHITE, Color.BLACK);


        // First unit
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.SOUTH;
        add(exitButton, gbc);

        // Second unit
        gbc.gridy = 1;
        gbc.weighty = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(imageLabel, gbc);

        // Third unit
        gbc.gridy = 2;
        gbc.weighty = 1;
        add(turntext, gbc);

        // Forth unit
        gbc.gridy = 3;
        add(rollButton, gbc);
    }

    public void updateDiceImage(int Dicenum) {
        String  imagePath = "image/one.png"; 
        switch (Dicenum) {
            case 1:
                imagePath = "image/one.png"; 
                break;
            case 2:
                imagePath = "image/two.png"; 
                break;
            case 3:
                imagePath = "image/three.png";
                break;
            case 4:
                imagePath = "image/fore.png";
                break;
            case 5:
                imagePath = "image/five.png";
                break;
            case 6:
                imagePath = "image/six.png";
                break;
            default:
                break;
        }
        
        ImageIcon newImageIcon = new ImageIcon(getClass().getResource(imagePath));
        Image scaledImage = newImageIcon.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
    }

    private void rollDice() {

        int currentPlayerIndex = gameController.getcurrentPlayerIndex();
        PlayerSetting[] players = gameController.getplayers();
        
        // make sure score is not >= 100 
        if (players[currentPlayerIndex].getPosition() < 100) {
            int rollnumber = (int)(Math.random() * 6) + 1;       // random num
            updateDiceImage(rollnumber);
            players[currentPlayerIndex].setPosition(rollnumber);
            players[currentPlayerIndex].check();

            JOptionPane.showMessageDialog(null, "Player " + (currentPlayerIndex + 1) + " " + players[currentPlayerIndex].getName() + " roll " + rollnumber);
            JOptionPane.showMessageDialog(null, "Now is in " + players[currentPlayerIndex].getPosition());

            // update score
            gameController.playerNamePanel.updateScore();

            if (players[currentPlayerIndex].getPosition() >= 100) {
                JOptionPane.showMessageDialog(null, "Player " + (currentPlayerIndex + 1) + " " + players[currentPlayerIndex].getName() + " wins!");
                System.exit(0);
            } 

            gameController.setcurrentPlayerIndex((currentPlayerIndex + 1)%players.length);
            currentPlayerIndex = gameController.getcurrentPlayerIndex();
        }
    }
}

// button style
class ButtonStyler {

    public static void styleButton(JButton button, String fontName, int fontStyle, int fornSize, Color fontColor, Color bgColor) {
        
        // setfont
        button.setFont(new Font(fontName, fontStyle, fornSize));

        // set font color
        button.setForeground(fontColor);

        // set background color
        button.setBackground(bgColor);

        // Optional: if button shows frame
        button.setBorderPainted(false);

        // Optional: set opaque for show bgColor
        button.setOpaque(true);
    }

}

