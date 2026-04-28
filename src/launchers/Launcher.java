package launchers;

import boosters.*;
import capsules.*;
import missions.*;


public abstract class Launcher {
    public String    name;
    public boolean   inhabited;
    public int       maxBoosters;
    public int       maxFuel;
    public int       charge;
    public int       price;

    public Launcher(String name, boolean inhabited,int maxBoosters, int maxFuel,int charge, int price) {
        this.name        = name;
        this.inhabited   = inhabited;
        this.maxBoosters = maxBoosters;
        this.maxFuel     = maxFuel;
        this.charge      = charge;
        this.price       = price;
    }
    

}