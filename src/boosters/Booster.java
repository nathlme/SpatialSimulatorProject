package boosters;

import common.SpaceComponent;


public class Booster extends SpaceComponent {
     
    private int    additionalBoost;
    private int    mass; 

    public Booster(String name, int additionalBoost, int mass, int price) {
        super(name,price); 
        this.additionalBoost = additionalBoost;
        this.mass            = mass; 
    }
    
    public int getAdditionalBoost() {
        return additionalBoost;
    }


    public int getMass(){
        return mass;    
    }

    
    @Override
    public String printInfo() {
        String infos = "Nom : " + getName() + " - poussé additionel : " + additionalBoost + " kN - Poids : " + mass + " Tonnes - Price : " + getPrice() + " Millions"; 
        return infos;
    }


}