import java.util.Random;

public class BattleLoc extends Location{
    private Obstacle obstacle;
    private String award;

    private int maxObstacle;

    Random r = new Random();
    private int kickStat = 0;
    public BattleLoc(Player player, String name, Obstacle obstacle, String award,int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        if(this.getLocationStat()){
            return true;
        }
        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şuan buradasınız :"+this.getName());
        System.out.println("Dikkali Ol ! Burada "+obsNumber+" tane "+this.getObstacle().getName()+" yaşıyor");
        System.out.println("<S>avaş veya <K> aç");
        String selectCase = input.nextLine().toUpperCase();
        if(selectCase.equals("S") && combat(obsNumber) ){
            System.out.println(this.getName()+" tüm düşmanları yendiniz !");
            return true;

        }
        if(this.getPlayer().getHealth()<=0){
            System.out.println("Öldünüz !");
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber){
        for(int i=1;i<=obsNumber;i++){
            this.getObstacle().setHealth(this.getObstacle().getOrjinalHealth());
            playerStats();
            obstacleStats(i);
            this.setKickStat(this.getFirstKick());
            while (this.getPlayer().getHealth()>0 && this.getObstacle().getHealth()>0){
                if(this.getKickStat() == 0) {
                    setKickStat(1);
                    System.out.println("<V>ur veya <K>aç");
                    String selectCombat = input.nextLine().toUpperCase();
                    if (selectCombat.equals("V")) {
                        System.out.println("Siz vurdunuz !");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();

                    } else {
                        return false;
                    }
                }else if(this.getKickStat() != 0){
                    setKickStat(0);
                    if(this.getObstacle().getHealth()>0){
                        System.out.println();
                        System.out.println("Canavar size vurdu !");
                        int obstacleDamage = this.getObstacle().getDamage()-this.getPlayer().getInventory().getArmor().getBlock();
                        if(obstacleDamage<0){
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth()-obstacleDamage);
                        afterHit();
                    }
                }else{


                }
            }
            if(this.getObstacle().getHealth()<this.getPlayer().getHealth()){
                System.out.println("Düşmanı yendiniz !");
                switch (this.getName()){
                    case "Mağara":
                        this.getPlayer().getInventory().setFood(1);
                        break;
                    case "Nehir":
                        this.getPlayer().getInventory().setWater(1);

                    case "Orman":
                        this.getPlayer().getInventory().setFirewood(1);
                        break;
                    default:
                        break;
                }

                System.out.println(this.getObstacle().getAward() + " para kazandnız !");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+this.getObstacle().getAward());
                System.out.println("Güncel Paranız : "+ this.getPlayer().getMoney());
            }else{
                return false;
            }
        }
        return true;

    }

    public void afterHit(){
        System.out.println("Canınız : "+this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı "+this.getObstacle().getHealth());
        System.out.println();
    }
    public void playerStats(){
        System.out.println("Oyuncu Degerleri");
        System.out.println("-----------------");
        System.out.println("Sağlık : "+this.getPlayer().getHealth());
        System.out.println("Silah : "+this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar : "+this.getPlayer().getTotalDamage());
        System.out.println("Zırh : "+this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : "+this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para : "+this.getPlayer().getMoney());
        if(this.getPlayer().getInventory().getWeapon().getDamage()>0){

        }
    }

    public void obstacleStats(int i){
        System.out.println(i+". "+this.getObstacle().getName()+" değerleri");
        System.out.println("----------------------------------------");
        System.out.println("Sağlık : "+this.getObstacle().getHealth());
        System.out.println("Hasar : "+this.getObstacle().getDamage());
        System.out.println("Ödül : "+this.getObstacle().getAward());
    }

    public int randomObstacleNumber(){
        return r.nextInt(this.getMaxObstacle())+1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    private int getFirstKick(){
        return r.nextInt(2);
    }

    public int getKickStat() {
        return kickStat;
    }

    public void setKickStat(int kickStat) {
        this.kickStat = kickStat;
    }

    private boolean getLocationStat(){
        boolean stat = false;
        switch (this.getName()){
            case "Mağara":
                if (this.getPlayer().getInventory().getFood()!=0){
                    System.out.println(this.getName()+" burada bulunan ödülü aldınız !");
                    stat = true;
                }
                break;
            case "Nehir":
                if (this.getPlayer().getInventory().getWater()!=0){
                    System.out.println(this.getName()+" burada bulunan ödülü aldınız !");
                    stat = true;
                }
               break;
            case "Orman":
                if (this.getPlayer().getInventory().getFirewood()!=0) {
                    System.out.println(this.getName()+" burada bulunan ödülü aldınız !");
                    stat = true;
                }
                break;
            default:
                break;
        }
        return stat;
    }
}
