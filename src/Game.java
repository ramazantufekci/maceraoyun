import javax.annotation.processing.SupportedSourceVersion;
import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start(){
        System.out.println("Macera Oyununa Hoşgeldiniz !");
        System.out.print("Lütfen bir isim giriniz : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println(player.getName() + " Hoşgeldiniz !");
        player.selectChar();
        Location location = null;
        while (true){
            if(player.getInventory().getFirewood()!=0 && player.getInventory().getFood()!=0&&player.getInventory().getWater()!=0){
                System.out.println("Oyunu bitirdiniz!");
                break;
            }
            player.printInfo();
            System.out.println();
            System.out.println("########## Bölgeler ##########");
            System.out.println();
            System.out.println("1 - Güvenli Ev");
            System.out.println("2 - Eşya dükkanı");
            System.out.println("3 - Mağara --> Ödül <Yemek>");
            System.out.println("4 - Orman --> Ödül <Odun> ");
            System.out.println("5 - Nehir --> Ödül <Su>");
            System.out.println("6 - Maden --> Ödül <Su>");
            System.out.println("0 - Çıkış Yap");
            System.out.print("Lütfen gitmak istediğiniz bölgeyi seçiniz : ");
            int selectLoc = input.nextInt();
            switch (selectLoc){
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if(player.getInventory().getFood() == 1){
                        System.out.println("Buradaki öülleri aldın !");
                    }else {
                        location = new Cave(player);
                    }
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    System.out.println("Lütfen geçerli bir bölge giriniz !");
                    break;
            }
            if(location==null){
                System.out.println("Oyun bitti görüşmek üzere ");
                break;
            }
            if(!location.onLocation()){
                System.out.println("GAME OVER !");
                break;
            }

        }
    }
}
