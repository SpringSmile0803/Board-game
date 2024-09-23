import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Popup {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();

        JOptionPane.showMessageDialog(jFrame, "Random dice");

        String P1 = JOptionPane.showInputDialog(jFrame, "Enter First Player Name:");

        JOptionPane.showMessageDialog(jFrame, "Frist Player is: "+ P1);

    }
}
