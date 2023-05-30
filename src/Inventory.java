public class Inventory {

    private Weapon weapon;
    private Armor armor;

    private int food = 0;
    private int firewood = 0;
    private int water = 0;

    public Inventory() {
        this.weapon = new Weapon(-1,"Yumruk",0,0);
        this.armor = new Armor(-1,"Paçavra", 0, 0);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getFirewood() {
        return firewood;
    }

    public void setFirewood(int firewood) {
        this.firewood = firewood;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }
}
