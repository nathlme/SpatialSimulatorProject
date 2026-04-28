package capsules;

import boosters.*;
import launchers.*;
import missions.*;

public abstract class Capsule {
    
    public  String    name;
    public  boolean   inhabited;
    public  int       maxPerson;
    public  double    mass;
    public  int       price;

    public Capsule(String name, boolean inhabited, int maxPerson, double mass, int price) {
        this.name       = name;
        this.inhabited  = inhabited;
        this.maxPerson  = maxPerson;
        this.mass       = mass;
        this.price      = price;
    }

}