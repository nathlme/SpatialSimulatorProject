package launchers;


import common.SpaceComponent;



public abstract class Launcher extends SpaceComponent{
    
    private boolean   inhabited;
    private int       maxBoosters;
    private int       maxFuel;
    private int       charge; 

    // Constructeur
    public Launcher(String name, boolean inhabited,int maxBoosters, int maxFuel,int charge, int price) {
        super(name,price); 
        this.inhabited   = inhabited;
        this.maxBoosters = maxBoosters;
        this.maxFuel     = maxFuel;
        this.charge      = charge; 
    }
    
    // Get the launcher specificity
    public abstract String getSpecificity();

    // Verify if the launcher is inhabited
    public boolean isInhabited() {
        return inhabited;
    }

    // Get the maximum number of boosters
    public int getMaxBooster() {
        return maxBoosters;
    }

    // Get the maximum fuel capacity of the launcher
    public int getLauncherMaxFuel() {
        return maxFuel;
    }

    // Get the maximum useful charge of the launcher
    public int getCharge(){
        return charge;
    }
    
    // Print the launcher informations
    @Override
    public String printInfo() {
        String infos = "Nom : " + getName() + " - Habitée : " + inhabited + " - Nombre de booster maximum : " + maxBoosters + " - Carburant max : " + maxFuel + " Tonnes - Charge utile : " + charge + " - Price : " + getPrice() + " Millions - Spécificité : " + getSpecificity();
; 
        return infos;
    }

}