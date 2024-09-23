import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class shows {
    public static void main(String[] args) {
        
        JFrame jFrame = new JFrame();
        jFrame.getContentPane().setBackground(new Color(233, 218, 182));     // Background
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close and exit
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);        // FULLSCREEN
        //jFrame.setUndecorated(true);               // no frame decorations
        



        
        // table 
        int margin = 18;
        JPanel TablePanel = new JPanel();
        TablePanel.setLayout(new GridLayout(10, 10));   // 10*10 table
        TablePanel.setBorder(new EmptyBorder(margin, margin, margin, margin));
        TablePanel.setOpaque(false);                     // Transparent background
        

        // make an array to save table number 
        JLabel[][] labels = new JLabel[10][10];

        // default JLabel and save to array
        int counter = 1;

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++){
                labels[row][col] = new JLabel(String.valueOf(counter), SwingConstants.CENTER);
                labels[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                labels[row][col].setFont(new Font("Arial", Font.BOLD, 24));
                labels[row][col].setBackground(new Color(143, 143, 143));
                labels[row][col].setOpaque(true);
                TablePanel.add(labels[row][col]);
                counter++;
            }
        }

        jFrame.add(TablePanel, BorderLayout.WEST);






        // create middle item; 
        JPanel PlayerNmae = new JPanel();        
        PlayerNmae.setLayout(new GridLayout(4, 1));   // 4*1 table
        PlayerNmae.setOpaque(false);                   // Transparent background

        // // setBorder
        // Border emptyBorder = new EmptyBorder(margin, margin, margin, margin);
        // Border linBorder = BorderFactory.createLineBorder(Color.BLACK);
        // Border compoundBorder = new CompoundBorder(emptyBorder, linBorder);

        PlayerNmae.setBorder(new EmptyBorder(margin, margin, margin, margin));

        for (int i = 0; i < 4; i++) {
            JPanel NameList = new JPanel();
            NameList.setLayout(new GridLayout(2, 1));
            NameList.setOpaque(false);
            NameList.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel text = new JLabel("Player " + i);
            JLabel name = new JLabel("t" +i);

            text.setFont(new Font("Arial", Font.BOLD, 18));
            name.setFont(new Font("Arial", Font.BOLD, 18));

            text.setHorizontalAlignment(JLabel.CENTER);
            name.setHorizontalAlignment(JLabel.CENTER);

            NameList.add(text);
            NameList.add(name);

            PlayerNmae.add(NameList);
        }

        jFrame.add(PlayerNmae, BorderLayout.CENTER);





        // create right item
        JPanel rightitem = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        rightitem.setPreferredSize(new Dimension(300, 0));
        rightitem.setOpaque(false);  // Transparent background

        //EXIT button
        JPanel buttonPanel = new JPanel();
        JButton exiButton = new JButton("EXIT");
        exiButton.setPreferredSize(new Dimension(150, 50));
        exiButton.setFont(new Font("Arial", Font.BOLD, 18));
        
        exiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        buttonPanel.add(exiButton);

        // dice image
        ImageIcon imageIcon = new ImageIcon("molecule.png");
        JLabel imagLabel = new JLabel(imageIcon);

        // turn playername
        
        // Roll button 
        JPanel RollbuttomJPane = new JPanel();
        JButton rollButton = new JButton("Roll");
        rollButton.setFont(new Font("Arial", Font.BOLD, 18));

        RollbuttomJPane.add(rollButton);

        
        // 1st unit for exit button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        rightitem.add(buttonPanel, gbc);
        
        // 2nd unit for dice image
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        rightitem.add(imagLabel, gbc);

        // 3rd unit for turn playerName
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 1;
        rightitem.add(new JLabel("Some Text"), gbc);
        

        // 4th unit for roll button
        gbc.gridx = 0;
        gbc.gridy = 3;
        rightitem.add(rollButton, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        jFrame.add(rightitem, BorderLayout.EAST);

        




        jFrame.setVisible(true);


        // After the frame is visible, set cell sizes to ensure they are square
        int cellSize = (Math.min(jFrame.getWidth(), jFrame.getHeight()) - (margin * 2)) / 10; // Calculate cell size
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                labels[row][col].setPreferredSize(new java.awt.Dimension(cellSize, cellSize));
            }
        }
        // Revalidate the panel to apply the changes
        TablePanel.revalidate();
    }
}