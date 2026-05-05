package missions;


import rockets.*;

 
public abstract class Mission {

    private  String  name;
    private  boolean requiresCrew;
    private  int     distance;
    private  int     duration;
    private  double  fuelCoeff;

    // Constructeur
    public Mission(String name, boolean requiresCrew, int distance, int duration, double fuelCoeff) {
        this.name         = name;
        this.requiresCrew = requiresCrew;
        this.distance     = distance;
        this.duration     = duration;
        this.fuelCoeff    = fuelCoeff;
    }


    // Calculate the necessary fuel for the mission depending on the rocket mass
    public double getNecessaryFuel(Rocket rocket) {
        double rocketMass = rocket.getRocketTotalMass();
        double necessaryFuel = (rocketMass * distance * fuelCoeff) / 1000;
        return necessaryFuel;
    }

    // Verify if the mission requires a crew
    public boolean doesRequiresCrew() {
        return requiresCrew;
    }
    
    // Get the mission name
    public String getName() {
        return name;
    }

    // Get the mission duration
    public int getDuration() {
        return duration;
    }

    // Get the mission distance
    public int getDistance() {
        return distance;
    }

    // Get the fuel coefficient of the mission
    public double getFuelCoeff() {
        return fuelCoeff;
    }

    // Print the mission informations
    public String printInfo() {
        String infos = "Nom : " + name + " - Besoin d'humain : " + requiresCrew + " - Distance : " + distance + " km - Durée : " + duration + " h - Coefficient carburant : " + fuelCoeff; 
        return infos;
    }
    

}