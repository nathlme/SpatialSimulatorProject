package launchers;



public class Ariane5 extends Launcher {

    public Ariane5() {
        super("Ariane 5", false, 2, 700, 20, 180);
    }

    @Override
    public String getSpecificity() {
        return "Lanceur européen adapté aux charges commerciales avec boosters EAP.";
    }
}