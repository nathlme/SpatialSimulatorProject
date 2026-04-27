public abstract class Lauchers {
    
    protected String    name;
    protected boolean   inhabited;
    protected int       maxBoosters
    protected int       maxFuel;
    protected int       charge;
    protected int       price;

    public Lauchers(String name, boolean inhabited,int maxBoosters, int maxFuel,int charge, int price) {
        this.name        = name;
        this.inhabited   = inhabited;
        this.maxBoosters = maxBoosters
        this.maxFuel     = maxFuel;
        this.charge      = charge;
        this.price       = price;
    }

}