public abstract class Capsules {
    
    protected String    name;
    protected boolean   inhabited;
    protected int       maxPerson;
    protected float     mass;
    protected int       price;

    public Capsules(String name, boolean inhabited, int maxPerson,float mass, int price) {
        this.name       = name;
        this.inhabited  = inhabited;
        this.maxPerson  = maxPerson;
        this.mass       = mass;
        this.price      = price;
    }

}