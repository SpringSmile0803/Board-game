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
        tablePanel.setLayout(new GridLayout(10, 10)); // 10x10 ���
        tablePanel.setBorder(new EmptyBorder(margin, margin, margin, margin)); // �P��ɶZ��
        tablePanel.setPreferredSize(new Dimension(750, 750));
        for (int i = 1; i <= 100; i++) {
            JLabel label = new JLabel(String.valueOf(i), SwingConstants.CENTER); // �~����r
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // ���C�� JLabel �[�W���
            tablePanel.add(label);
        }
        jFrame.add(tablePanel, BorderLayout.WEST);

        

        // 2. ������m�@�ӹϤ��B�@���r�B�A�@�ӹϤ�
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS)); // �����G��

        JLabel image1 = new JLabel();
        JLabel text = new JLabel("�֪�turn");
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



        // 3. �k���m 4 �i�Ϥ��M 4 ���r (2x4 ����)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(4, 2)); // 4 �� 2 �C������


        // �K�[ 4 �ӹϤ��M 4 ���r
        for (int i = 1; i <= 4; i++) {
            JLabel imageLabel = new JLabel("Image " + i);
            JLabel textLabel = new JLabel("Text " + i);

            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            textLabel.setHorizontalAlignment(JLabel.CENTER);

            // �i�H�]�m�Ϥ�, �ϥ� ImageIcon
            // imageLabel.setIcon(new ImageIcon("path/to/image" + i + ".png"));

            rightPanel.add(imageLabel); // �K�[�Ϥ�
            rightPanel.add(textLabel);  // �K�[��r
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
