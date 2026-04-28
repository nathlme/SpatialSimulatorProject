package boosters;

import java.util.List;
import capsules.*;
import launchers.*;
import missions.*;

public class Booster {
    
    public String name;
    public int    additionalBoost;
    public int    mass;
    public int    price;

    public Booster(String name, int additionalBoost, int mass, int price) {
        this.name            = name;
        this.additionalBoost = additionalBoost;
        this.mass            = mass;
        this.price           = price;
    }

}