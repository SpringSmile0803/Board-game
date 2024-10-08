import javax.swing.*;
import java.awt.*;

class Four_Color {
    private Color[] colors;
    
    public Four_Color() {
        colors = new Color[5];
        colors[0] = new Color(184, 157, 176);
        colors[1] = new Color(156, 175, 207);
        colors[2] = new Color(60, 99, 154);
        colors[3] = new Color(112, 97, 139);
        colors[4] = new Color(143, 143, 143);
    }

    public Color getColor(int nums) {
        if (nums >= 0 && nums < 4) {
            return colors[nums];
        }else {
            return colors[4];
        }
    }
}

class CellPanel extends JPanel {
    private Color[] playerColors;
    private JLabel numberLabel;

    public CellPanel(Color[] playerColors, String number){
        this.playerColors = playerColors;
        setLayout(new BorderLayout());
        setBackground(playerColors[4]);

        numberLabel = new JLabel(number, SwingConstants.CENTER);
        numberLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(numberLabel, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // setAreaColor
        int width = getWidth();
        int height = getHeight();

        // Every Color are a quarter of the grid
        g.setColor(playerColors[0]); 
        g.fillRect(0, 0, width / 2, height / 2);

        g.setColor(playerColors[1]); 
        g.fillRect(width / 2, 0, width / 2, height / 2);

        g.setColor(playerColors[2]); 
        g.fillRect(0, height / 2, width / 2, height / 2);

        g.setColor(playerColors[3]); 
        g.fillRect(width / 2, height / 2, width / 2, height / 2);
    }

    // update Colors
    public void updateColor(int colorIndex, Color newColor) {
        playerColors[colorIndex] = newColor;
        repaint();
    }
}

class TablePanel extends JPanel {
    public CellPanel[][] tableCells;
    private Four_Color four_Color;
    
    public TablePanel() {
        this.four_Color = new Four_Color();
        int margin = 18;
        setLayout(new GridLayout(10, 10)); // 10x10 table
        setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
        setOpaque(false); // Transparent background
        tableCells = new CellPanel[10][10];
        int counter = 1;

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Color[] initialColors = new Color[] {
                    new Color(0, 0, 0, 0), 
                    new Color(0, 0, 0, 0), 
                    new Color(0, 0, 0, 0), 
                    new Color(0, 0, 0, 0),
                    four_Color.getColor(4)
                };
                tableCells[row][col] = new CellPanel(initialColors, String.valueOf(counter));
                tableCells[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(tableCells[row][col]);
                counter++;
            }
        }

        // Set cell sizes to ensure they are square
        int cellSize = (Math.min(Toolkit.getDefaultToolkit().getScreenSize().width, 
                                  Toolkit.getDefaultToolkit().getScreenSize().height) - (margin * 2)) / 10;
        for (CellPanel[] row : tableCells) {
            for (CellPanel cell : row) {
                cell.setPreferredSize(new Dimension(cellSize, cellSize));
            }
        }
        revalidate();
    }

    public void updateCellColors(int row, int col, int colorIndex, Color newColor) {
        if (row >= 0 && row < tableCells.length && col >= 0 && col < tableCells.length) {
            CellPanel cell = tableCells[row][col];
            cell.updateColor(colorIndex, newColor);
        } else {
            System.out.println(row);
            System.out.println(col);
        }
    }
}

class PlayerNamePanel extends JPanel {
    private GameController gameController;
    private JLabel[] scoreLabels;
    private Four_Color four_Color;

    public PlayerNamePanel(GameController gameController) {
        this.gameController = gameController;
        this.four_Color = new Four_Color();
        PlayerSetting[] players = gameController.getplayers();

        setLayout(new GridLayout(4, 1)); // 4x1 table
        setOpaque(false); // Transparent background
        setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18)); // Margin

        scoreLabels = new JLabel[4];

        for (int i = 0; i < 4; i++) {
            JPanel nameList = new JPanel();
            nameList.setLayout(new GridLayout(3, 1));
            nameList.setOpaque(true);
            nameList.setBackground(four_Color.getColor(i)); 
            nameList.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel text = new JLabel("Player " + (i + 1));
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
    private TablePanel tablePanel;
    private Four_Color four_Color;

    public RightItemPanel(GameController gameController, TablePanel tablePanel) {
        this.gameController = gameController;
        this.tablePanel = tablePanel;
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
        String imagePath = "image/one.png"; 
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
                imagePath = "image/four.png";
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
        this.four_Color = new Four_Color();

        // make sure score is not >= 100 
        if (players[currentPlayerIndex].getPosition() < 100 - 1) {
            int rollnumber = (int)(Math.random() * 6) + 1;       // random num
            updateDiceImage(rollnumber);

            
            JOptionPane.showMessageDialog(null, "Player " + (currentPlayerIndex + 1) + " " + players[currentPlayerIndex].getName() + " roll " + rollnumber);
            int CurrenPosition = players[currentPlayerIndex].getPosition();
            players[currentPlayerIndex].setPosition(rollnumber);
            players[currentPlayerIndex].check();


            int oldrow = (CurrenPosition - 1) / 10;
            int oldcol = (CurrenPosition - 1) % 10;
            tablePanel.updateCellColors(oldrow, oldcol, currentPlayerIndex, new Color(143, 143, 143));
            
            CurrenPosition = players[currentPlayerIndex].getPosition();
            int newrow = (CurrenPosition - 1) / 10;
            int newcol = (CurrenPosition - 1) % 10;
            tablePanel.updateCellColors(newrow, newcol, currentPlayerIndex, four_Color.getColor(currentPlayerIndex));


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

