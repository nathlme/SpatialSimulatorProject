package capsules;

import boosters.*;
import launchers.*;
import missions.*;
import rockets.*;
import common.SpaceComponent;


public abstract class Capsule extends SpaceComponent {
    
     
    private  boolean   inhabited;
    private  int       maxPerson;
    private  double    mass; 

    public Capsule(String name, boolean inhabited, int maxPerson, double mass, int price) {
        super(name,price); 
        this.inhabited  = inhabited;
        this.maxPerson  = maxPerson;
        this.mass       = mass; 
    }

    public boolean isInhabited() {
        return inhabited;
    }

    public int getMaxPerson() {
        return maxPerson;
    }

    public double getMass() {
        return mass;
    }


    @Override 
    public String printInfo() {
        String infos = "Nom : " + getName() + " - Habitée : " + inhabited + " - Nombre de personne maximum : " + maxPerson + " - Poids : " + mass + " Tonnes - Prix : " + getPrice() + " Millions"; 
        return infos;
    }
}