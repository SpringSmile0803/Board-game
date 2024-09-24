import java.util.Random;
import javax.swing.*;
import java.awt.*;

public class GameController {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameController().createAndShowGUI());
        
    }

    private void createAndShowGUI() {
        JFrame jframe = new JFrame();
        
        jframe.getContentPane().setBackground(new Color(233, 218, 182)); // Background
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Close on exit
        jframe.setUndecorated(true);                // no frame decorations
        
        jframe.add(new TablePanel(), BorderLayout.WEST);
        jframe.add(new PlayerNamePanel(), BorderLayout.CENTER);
        jframe.add(new RightItemPanel(), BorderLayout.EAST);
        
        jframe.pack();
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);         // Fullscreen
        jframe.setVisible(true);

        new Thread(this::startGame).start();
    }

    private void startGame() {
        //ª±®a³]©w
        PlayerSetting player1 = new PlayerSetting("A");
        PlayerSetting player2 = new PlayerSetting("B");
        PlayerSetting player3 = new PlayerSetting("C");
        PlayerSetting player4 = new PlayerSetting("D");

        Random rand = new Random();
        
        int i = 1;

        while (player1.getPosition() < 100 && player2.getPosition() < 100 && player3.getPosition() < 100 && player4.getPosition() < 100) {

            int num = rand.nextInt(6) + 1;
            System.out.println(i);
            System.out.println(num);
            switch (i) {
                case 1:
                    player1.setPosition(num);
                    player1.check();
                    System.out.println(player1.getPosition());
                    i++;
                    break;
                case 2:
                    player2.setPosition(num);
                    player2.check();
                    System.out.println(player2.getPosition());
                    i++;
                    break;
                case 3:
                    player3.setPosition(num);
                    player3.check();
                    System.out.println(player3.getPosition());
                    i++;
                    break;
                case 4:
                    player4.setPosition(num);
                    player4.check();
                    System.out.println(player4.getPosition());
                    i = 1;
                    break;
                default:
                    break;
            }
            System.out.println();
            
        }   
    }
}