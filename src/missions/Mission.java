package missions;

import boosters.*;
import capsules.*;
import launchers.*;
import rockets.*;

 
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
        double rocketMass = rocket.getRocketTotalMass();
        double necessaryFuel = (rocketMass * distance * fuelCoeff) / 1000;
        return necessaryFuel;
    }

    public boolean doesRequiresCrew() {
        return requiresCrew;
    }
    
    public String getName() {
        return name;
    }

    public int getDuration() {
        return returnTime - launchTime;
    }

    public int distance() {
        return distance;
    }

    public String printInfo() {
        String infos = "Nom : " + name + " - Besoin d'humain : " + requiresCrew + " - Distance : " + distance + " km - Durée : " + getDuration() + " h - Coefficient carburant : " + fuelCoeff; 
        return infos;
    }
    

}