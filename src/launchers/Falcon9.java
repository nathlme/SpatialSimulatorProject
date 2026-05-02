package launchers;

import boosters.*;
import capsules.*;
import missions.*;


public class Falcon9 extends Launcher {

    public Falcon9() {
        super("Falcon 9", true, 0, 500, 22, 60);
    }

    @Override
    public String getSpecificity() {
        return "Lanceur réutilisable, économique et adapté aux missions orbitales.";
    }
}