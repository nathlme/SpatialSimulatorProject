package rockets; 

import java.util.List;

import boosters.*;
import capsules.*;
import launchers.*;
import missions.*;

public class Rocket {
    List<Booster>  boosterList;
    Launcher       launcher;
    Capsule        capsule;

    String  name;

    public Rocket(String name, Launcher launcher, Capsule capsule, List<Booster>  boosterList){
        this.name        = name ;
        this.launcher    = launcher;
        this.capsule     = capsule;
        this.boosterList = boosterList;
    }
    
    public Launcher getLauncher() {
        return launcher;
    }

    public Capsule getCapsule() {
        return capsule;
    }

    public String getName() {
        return name;
    }

    public int getBoosterCount() {
        return boosterList.size();
    }

    public double getRocketTotalMass() {
        double totalMass = capsule.mass;
        
        for (Booster booster : boosterList) {
            totalMass += booster.mass;
        }

        System.out.println("Le poid total de la fussé " + name + " est de " + totalMass + " tonnes.");
        return totalMass;
    }

    public int getRocketTotalPrice() {
        int totalPrice = capsule.getPrice() + launcher.getPrice();
        
        for (Booster booster : boosterList) {
            totalPrice += booster.getPrice();
        }
        
        System.out.println("Le prix total de la fusée " + name + " est de " + totalPrice + "  €.");
        return totalPrice;
    }

    public void getComponents() {
        System.out.println("Capsules : " + capsule.getName() + "; Lanceurs : " + launcher.getName() + "; Boosters : " + boosterList);
    }

    

}