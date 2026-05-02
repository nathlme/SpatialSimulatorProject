package launchers;

import boosters.*;
import capsules.*;
import missions.*;
import rockets.*;
import common.SpaceComponent;



public abstract class Launcher extends SpaceComponent{
    
    private boolean   inhabited;
    private int       maxBoosters;
    private int       maxFuel;
    private int       charge; 

    public Launcher(String name, boolean inhabited,int maxBoosters, int maxFuel,int charge, int price) {
        super(name,price); 
        this.inhabited   = inhabited;
        this.maxBoosters = maxBoosters;
        this.maxFuel     = maxFuel;
        this.charge      = charge; 
    }
    
    public abstract String getSpecificity();

    public boolean isInhabited() {
        return inhabited;
    }

    public int getMaxBooster() {
        return maxBoosters;
    }

    public int getLauncherMaxFuel() {
        return maxFuel;
    }

    public int getCharge(){
        return charge;
    }
    
    @Override
    public String printInfo() {
        String infos = "Nom : " + getName() + " - Habitée : " + inhabited + " - Nombre de booster maximum : " + maxBoosters + " - Carburant max : " + maxFuel + " Tonnes - Charge utile : " + charge + " - Price : " + getPrice() + " Millions - Spécificité : " + getSpecificity();
; 
        return infos;
    }

}