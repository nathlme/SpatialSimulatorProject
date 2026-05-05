package capsules;


import common.SpaceComponent;


public abstract class Capsule extends SpaceComponent {
    
     
    private  boolean   inhabited;
    private  int       maxPerson;
    private  double    mass; 

    // Constructeur
    public Capsule(String name, boolean inhabited, int maxPerson, double mass, int price) {
        super(name,price); 
        this.inhabited  = inhabited;
        this.maxPerson  = maxPerson;
        this.mass       = mass; 
    }

    // Verify if the capsule is inhabited
    public boolean isInhabited() {
        return inhabited;
    }

    // Get the maximum number of people in the capsule
    public int getMaxPerson() {
        return maxPerson;
    }

    // Get the capsule mass
    public double getMass() {
        return mass;
    }


    // Print the capsule informations
    @Override 
    public String printInfo() {
        String infos = "Nom : " + getName() + " - Habitée : " + inhabited + " - Nombre de personne maximum : " + maxPerson + " - Poids : " + mass + " Tonnes - Prix : " + getPrice() + " Millions"; 
        return infos;
    }
}