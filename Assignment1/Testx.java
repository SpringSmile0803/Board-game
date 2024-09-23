import java.util.Random;

public class Testx {
    public static void main(String[] args) {

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
                    player1.SettingPosition(num);
                    player1.check();
                    System.out.println(player1.getPosition());
                    i++;
                    break;
                case 2:
                    player2.SettingPosition(num);
                    player2.check();
                    System.out.println(player2.getPosition());
                    i++;
                    break;
                case 3:
                    player3.SettingPosition(num);
                    player3.check();
                    System.out.println(player3.getPosition());
                    i++;
                    break;
                case 4:
                    player4.SettingPosition(num);
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