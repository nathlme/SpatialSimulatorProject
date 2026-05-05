package launchers;



public class SLS extends Launcher {

    public SLS() {
        super("SLS", true, 2, 2600, 130, 2000);
    }

    @Override
    public String getSpecificity() {
        return "Lanceur lourd moderne inspiré du programme Artemis.";
    }
}