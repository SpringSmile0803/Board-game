import javax.swing.*;
import java.awt.*;

class TablePanel extends JPanel {
    public TablePanel() {
        int margin = 18;
        setLayout(new GridLayout(10, 10)); // 10x10 table
        setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
        setOpaque(false); // Transparent background

        JLabel[][] labels = new JLabel[10][10];
        int counter = 1;

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                labels[row][col] = new JLabel(String.valueOf(counter), SwingConstants.CENTER);
                labels[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                labels[row][col].setFont(new Font("Arial", Font.BOLD, 24));
                labels[row][col].setBackground(new Color(143, 143, 143));
                labels[row][col].setOpaque(true);
                add(labels[row][col]);
                counter++;
            }
        }

        // Set cell sizes to ensure they are square
        int cellSize = (Math.min(Toolkit.getDefaultToolkit().getScreenSize().width, 
                                  Toolkit.getDefaultToolkit().getScreenSize().height) - (margin * 2)) / 10;
        for (JLabel[] row : labels) {
            for (JLabel label : row) {
                label.setPreferredSize(new Dimension(cellSize, cellSize));
            }
        }
        revalidate();
    }
}

class PlayerNamePanel extends JPanel {
    public PlayerNamePanel() {
        setLayout(new GridLayout(4, 1)); // 4x1 table
        setOpaque(false); // Transparent background
        setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18)); // Margin

        for (int i = 0; i < 4; i++) {
            JPanel nameList = new JPanel();
            nameList.setLayout(new GridLayout(2, 1));
            nameList.setOpaque(false);
            nameList.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel text = new JLabel("Player " + i);
            JLabel name = new JLabel("t" + i);
            text.setFont(new Font("Arial", Font.BOLD, 18));
            name.setFont(new Font("Arial", Font.BOLD, 18));

            text.setHorizontalAlignment(JLabel.CENTER);
            name.setHorizontalAlignment(JLabel.CENTER);

            nameList.add(text);
            nameList.add(name);
            add(nameList);
        }
    }
}

class RightItemPanel extends JPanel {
    private final int cellSize;

    public RightItemPanel() {
        cellSize = 258;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setPreferredSize(new Dimension(300, 0));
        setOpaque(false); // Transparent background

        // EXIT button
        JButton exitButton = new JButton("EXIT");
        exitButton.setPreferredSize(new Dimension(150, 50));
        exitButton.setFont(new Font("Arial", Font.BOLD, 18));
        exitButton.addActionListener(e -> System.exit(0));

        // Dice image
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("image/one.png")); 
        Image scaledImage = imageIcon.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));

        // Roll button
        JButton rollButton = new JButton("Roll");
        rollButton.setFont(new Font("Arial", Font.BOLD, 18));
        rollButton.addActionListener(e -> {
            // 直接寫一個function改掉
        });

        // First unit
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(createPanel(exitButton), gbc);

        // Second unit
        gbc.gridy = 1;
        gbc.weighty = 2;
        // gbc.fill = GridBagConstraints.NONE;
        add(imageLabel, gbc);

        // Third unit
        gbc.gridy = 2;
        gbc.weighty = 1;
        add(new JLabel("Some Text"), gbc);

        // Forth unit
        gbc.gridy = 3;
        add(rollButton, gbc);
    }

    private JPanel createPanel(JComponent component) {
        JPanel panel = new JPanel();
        panel.add(component);
        return panel;
    }
}
