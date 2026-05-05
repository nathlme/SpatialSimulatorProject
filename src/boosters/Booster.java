package boosters;

import common.SpaceComponent;


public class Booster extends SpaceComponent {
     
    private int    additionalBoost;
    private int    mass; 

    // Constructeur
    public Booster(String name, int additionalBoost, int mass, int price) {
        super(name,price); 
        this.additionalBoost = additionalBoost;
        this.mass            = mass; 
    }
    
    // Get the booster additional boost
    public int getAdditionalBoost() {
        return additionalBoost;
    }


    // Get the booster mass
    public int getMass(){
        return mass;    
    }

    
    // Print the booster informations
    @Override
    public String printInfo() {
        String infos = "Nom : " + getName() + " - poussé additionel : " + additionalBoost + " kN - Poids : " + mass + " Tonnes - Price : " + getPrice() + " Millions"; 
        return infos;
    }


}