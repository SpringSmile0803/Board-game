import javax.swing.*;
import java.awt.*;

public class GameController {
    public int currentPlayerIndex = 0;
    public PlayerSetting[] players;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameController().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame jframe = new JFrame();
        
        jframe.getContentPane().setBackground(new Color(233, 218, 182)); // Background
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Close on exit
        jframe.setUndecorated(true);                // no frame decorations
        
        RightItemPanel rightItemPanel = new RightItemPanel(this);

        jframe.add(new TablePanel(), BorderLayout.WEST);
        jframe.add(new PlayerNamePanel(), BorderLayout.CENTER);
        jframe.add(rightItemPanel, BorderLayout.EAST);
        
        jframe.pack();
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);         // Fullscreen
        jframe.setVisible(true);

        new Thread(this::startGame).start();
    }

    private void startGame() {
        
        String[] PlayerNames = GameInteraction.getName();

        players = new PlayerSetting[4];

        for (int i = 0; i < 4; i++) {
            players[i] = new PlayerSetting(PlayerNames[i]);
        } 
    }


    public PlayerSetting[] getplayers() {
        return players;
    }

    public void setPlayers(PlayerSetting[] players) {
        this.players = players;
    }

    public int getcurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setcurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }
}