
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
    
    public int getRocketTotalMass() {
        double totalMass = capsule.mass;
        
        for (Booster booster : boosterList) {
            totalMass += booster.mass;
        }

        System.out.println("Le poid total de la fussé " + name + " est de " + totalMass + " tonnes.");
        return totalMass;
    }

    public int getRocketTotalPrice() {
        int totalPrice = capsule.price + launcher.price;
        
        for (Boosters booster : boosterList) {
            totalPrice += booster.price;
        }
        
        System.out.println("Le prix total de la fusée " + name + " est de " + totalPrice + "  €.");
        return totalPrice;
    }

    public void getComponents() {
        System.out.println("Capsules : " + capsule.name + "; Lanceurs : " + launcher.name + "; Boosters : " + boosterList);
    }

}