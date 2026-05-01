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

        // System.out.println("\nLe poid total de la fusée " + name + " est de " + totalMass + " tonnes.");
        return totalMass;
    }

    public int getRocketTotalPrice() {
        int totalPrice = capsule.getPrice() + launcher.getPrice();
        
        for (Booster booster : boosterList) {
            totalPrice += booster.getPrice();
        }
        return totalPrice;
    }

    public void getComponents() {

    System.out.print("Capsule : " + capsule.getName());
    System.out.print(" | Lanceur : " + launcher.getName());
    System.out.print(" | Boosters : ");

    if (boosterList == null || boosterList.isEmpty()) {
        System.out.println("Aucun");
    } else {
        for (Booster b : boosterList) {
            System.out.print(b.getName() + " ");
        }
        System.out.println();
    }
}

    

}