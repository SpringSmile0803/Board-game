import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class windows {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.getContentPane().setBackground(Color.blue);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setUndecorated(true);
        jFrame.setLayout(new BorderLayout());


        int margin = 18;
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(10, 10)); // 10x10 表格
        tablePanel.setBorder(new EmptyBorder(margin, margin, margin, margin)); // 與邊界距離
        tablePanel.setPreferredSize(new Dimension(750, 750));
        for (int i = 1; i <= 100; i++) {
            JLabel label = new JLabel(String.valueOf(i), SwingConstants.CENTER); // 居中文字
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 給每個 JLabel 加上邊框
            tablePanel.add(label);
        }
        jFrame.add(tablePanel, BorderLayout.WEST);

        

        // 2. 中間放置一個圖片、一行文字、再一個圖片
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS)); // 垂直佈局

        JLabel image1 = new JLabel();
        JLabel text = new JLabel("誰的turn");
        JLabel image2 = new JLabel();

        image1.setIcon(new ImageIcon("molecule.png"));
        image1.setAlignmentX(Component.CENTER_ALIGNMENT);
        text.setAlignmentX(Component.CENTER_ALIGNMENT);        
        image2.setIcon(new ImageIcon("sword.png"));
        image2.setAlignmentX(Component.CENTER_ALIGNMENT);

        middlePanel.add(image1);
        middlePanel.add(text);
        middlePanel.add(image2);
        
        JPanel wrapperJPanel = new JPanel(new GridBagLayout());
        wrapperJPanel.add(middlePanel);
        jFrame.add(wrapperJPanel, BorderLayout.CENTER);



        // 3. 右邊放置 4 張圖片和 4 行文字 (2x4 網格)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(4, 2)); // 4 行 2 列的網格


        // 添加 4 個圖片和 4 行文字
        for (int i = 1; i <= 4; i++) {
            JLabel imageLabel = new JLabel("Image " + i);
            JLabel textLabel = new JLabel("Text " + i);

            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            textLabel.setHorizontalAlignment(JLabel.CENTER);

            // 可以設置圖片, 使用 ImageIcon
            // imageLabel.setIcon(new ImageIcon("path/to/image" + i + ".png"));

            rightPanel.add(imageLabel); // 添加圖片
            rightPanel.add(textLabel);  // 添加文字
        }

        jFrame.add(rightPanel, BorderLayout.EAST);
        




        JPanel buttonPanel = new JPanel();
        JButton exitButton = new JButton("Exit");

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(exitButton);
        jFrame.add(buttonPanel, BorderLayout.SOUTH);




        jFrame.setVisible(true);
    }
}
