package launchers;



public class SaturneV extends Launcher {

    public SaturneV() {
        super("Saturne V", true, 0, 2700, 140, 1500);
    }
    
    @Override
    public String getSpecificity() {
        return "Lanceur historique très puissant utilisé pour les missions lunaires.";
    }
}