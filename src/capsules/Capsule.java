package capsules;

import boosters.*;
import launchers.*;
import missions.*;
import rockets.*;
import common.SpaceComponent;


public abstract class Capsule extends SpaceComponent {
    
     
    public  boolean   inhabited;
    public  int       maxPerson;
    public  double    mass; 

    public Capsule(String name, boolean inhabited, int maxPerson, double mass, int price) {
        super(name,price); 
        this.inhabited  = inhabited;
        this.maxPerson  = maxPerson;
        this.mass       = mass; 
    }

    public boolean IsInhabited(){
        return inhabited;
    }



    @Override 
    public String printInfo() {
        String infos = "Nom : " + getName() + " - Habitée : " + inhabited + " - Nombre de personne maximum : " + maxPerson + " - Poids : " + mass + " Tonnes - Prix : " + getPrice() + " Millions"; 
        return infos;
    }
}