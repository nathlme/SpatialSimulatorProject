import boosters.*;
import capsules.*;
import launchers.*;
import missions.*;

public class Main {
    public static void main(String[] args) {
        
        List<Capsule> capsules = new ArrayList<>();
        capsules.add(new Apollo());
        capsules.add(new CargoDragon());
        capsules.add(new CrewDragon());
        capsules.add(new Orion());

        List<Launcher> launchers = new ArrayList<>();
        launchers.add(new Ariane5());
        launchers.add(new Falcon9());
        launchers.add(new SaturneV());
        launchers.add(new SLS());

        List<Booster> boosters = new ArrayList<>();
        boosters.add(new Booster("EAP Ariane", 6470, 270, 30));
        boosters.add(new Booster("SRB Shuttle", 12500, 590, 55));
        boosters.add(new Booster("BE-3", 490, 25, 12));

        List<Launch> launches = new ArrayList<>();
        
        Simulator simulator = new Simulator(capsules, boosters, launchers, launches);
        simulator.startGame();

    }
}