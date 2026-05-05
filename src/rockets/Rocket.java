package rockets; 

import java.util.List;
import java.util.ArrayList;

import boosters.*;
import capsules.*;
import launchers.*;

public class Rocket {
    private List<Booster>  boosterList;
    private Launcher       launcher;
    private Capsule        capsule;

    private String  name;

    // Constructeur without boosters
    public Rocket(String name, Launcher launcher, Capsule capsule) {
        this.name = name;
        this.launcher = launcher;
        this.capsule = capsule;
        this.boosterList = new ArrayList<>();
    }
    
    // Constructor overload with boosters
    public Rocket(String name, Launcher launcher, Capsule capsule, List<Booster> boosterList) {
        this(name, launcher, capsule);
        this.boosterList.addAll(boosterList);
    }

    // Get the rocket launcher
    public Launcher getLauncher() {
        return launcher;
    }

    // Get the rocket capsule
    public Capsule getCapsule() {
        return capsule;
    }

    // Get the rocket name
    public String getName() {
        return name;
    }

    // Get the number of boosters used by the rocket
    public int getBoosterCount() {
        return boosterList.size();
    }

    // Calculate and return the total mass of the rocket
    public double getRocketTotalMass() {
        double totalMass = capsule.getMass();
        
        for (Booster booster : boosterList) {
            totalMass += booster.getMass();
        }

        // System.out.println("\nLe poid total de la fusée " + name + " est de " + totalMass + " tonnes.");
        return totalMass;
    }

    // Calculate and return the total price of the rocket
    public int getRocketTotalPrice() {
        int totalPrice = capsule.getPrice() + launcher.getPrice();
        
        for (Booster booster : boosterList) {
            totalPrice += booster.getPrice();
        }
        return totalPrice;
    }

    // Print every component used by the rocket
    public void printComponents() {

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