package missions;

import boosters.*;
import capsules.*;
import launchers.*;
 
public abstract class Mission {

    public  String  name;
    public  boolean requiresCrew;
    public  int     distance;
    public  int     launchTime;
    public  int     returnTime;
    public  double  fuelCoeff;

    public Mission(String name, boolean requiresCrew, int distance, int launchTime,int returnTime, double fuelCoeff) {
        this.name         = name;
        this.requiresCrew = requiresCrew;
        this.distance     = distance;
        this.launchTime   = launchTime;
        this.returnTime   = returnTime;
        this.fuelCoeff    = fuelCoeff;
    }


    public double getNecessaryFuel(Rocket rocket) {
        int rocketMass = rocket.getRocketTotalMass();
        double necessaryFuel = (rocketMass * distance * fuelCoeff) / 1000;
        return necessaryFuel;
    }



}