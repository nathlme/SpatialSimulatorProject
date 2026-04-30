package common;

public abstract class SpaceComponent {
    private String name;
    private int price;

    public SpaceComponent(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public abstract String printInfo();
}