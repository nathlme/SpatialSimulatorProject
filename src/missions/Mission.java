public abstract class Mission {

    protected String  name;
    protected boolean requiresCrew;
    protected int     distance;
    protected int     launchTime;
    protected int     returnTime;
    protected double  fuelCoeff; 

    public Mission(String name, boolean requiresCrew, int distance, int launchTime,int returnTime, double fuelCoeff) {
        this.name               = name;
        this.requiresCrew       = requiresCrew;
        this.distance           = distance;
        this.launchTime         = launchTime;
        this.returnTime         = returnTime;
        this.fuelCoeff          = fuelCoeff;

    }

}