package builder;

//TODO The idea now is to make the God the base for the menu. First you will select a God and then items. We will be able to input
//TODO information from item objects into god objects after the item is selected to alter the God stats.

public class God {

    private String damageType;
    private String name;
    public God(String n, String d){
        damageType = d;
        name = n;
    } //Constructor for a god that sets damage type (P or M) and name.

    //Other variables for gods. These are set by setters rather than constructor.
    private int baseHealth;
    private double healthScale;
    private int baseMana;
    private double manaScale;
    private int baseMS; //Base movement speed, does not scale
    private double basicAttackDamage; //Scaling is often a double, so base should be double to avoid converting types.
    private double basicAttackScale;
    private double attackSpeed;
    private double attackSpeedScale;
    private int autoRange = 55; //Initialized to 55 since that is standard for most gods. Does not scale.
    private double power = 0; //Initialized to 0 since that is the starting point for all gods not including passives
    //TODO Make sure this does not scale: private double powerScale;
    private double basePhysicalDef;
    private double physicalDefScale;
    private double baseMagicalDef;
    private double magicalDefScale;


    public void PrintInfo(){
        System.out.println(name + " stats: ");
        System.out.println("Health: " + baseHealth);
        System.out.println("Health Scaling: " + healthScale);
        System.out.println("Mana: " + baseMana);
        System.out.println("Mana Scaling: " + manaScale);
        System.out.println("Base Movement Speed: " + baseMS);
        System.out.println("Basic Attack Damage: " + basicAttackDamage);
        System.out.println("Bassic Attack Scaling: " + basicAttackScale);
        System.out.println("Attack Speed: " + attackSpeed);
        System.out.println("Attack Speed Scaling: " + attackSpeedScale);
        System.out.println("Auto Attack Range: " + autoRange);
        System.out.println("Power: " + power);
        System.out.println("Base Physical Defense: " + basePhysicalDef);
        System.out.println("Physical Defense Scaling: " + physicalDefScale);
        System.out.println("Base Magical Defense: " + baseMagicalDef);
        System.out.println("Magical Defense Scaling: " + magicalDefScale);
    }

    //--------------------------------------Getters and setters-------------------------------------------------------//

    //Begin setters
    public void setBaseHealth(int h){
        baseHealth = h;
    }

    public void setHealthScale(double s){
        healthScale = s;
    }

    public void setBaseMana(int m){
        baseMana = m;
    }

    public void setManaScale(double s){
        manaScale = s;
    }

    public void setBaseMS(int m){
        baseMS = m;
    }

    public void setBasicAttackDamage(double b){
        basicAttackDamage = b;
    }

    public void setBasicAttackScale(double s){
        basicAttackScale = s;
    }

    public void setAttackSpeed(double a){
        attackSpeed = a;
    }

    public void setAttackSpeedScale(double s){
        attackSpeedScale = s;
    }

    public void setAutoRange(int r){
        autoRange = r;
    }

    public void setPower(double p){
        power = p;
    }

    public void setBasePhysicalDef(double p){
        basePhysicalDef = p;
    }

    public void setPhysicalDefScale(double s){
        physicalDefScale = s;
    }

    public void setBaseMagicalDef(double m){
        baseMagicalDef = m;
    }

    public void setMagicalDefScale(double s){
        magicalDefScale = s;
    }

    //TODO Create getters for god class
    //Begin getters

    public String getName(){
        return name;
    }

    public String getDamageType(){
        return damageType;
    }

    public double getPower(){
        return power;
    }

}
