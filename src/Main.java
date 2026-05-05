import java.util.List;
import java.util.ArrayList;

import boosters.*;
import capsules.*;
import launchers.*;
import missions.*;
import rockets.*;

public class Main {

    // Initialize all game components and start the simulator
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

        List<Mission> missions = new ArrayList<>();
        missions.add(new EarthOrbit());
        missions.add(new ISS());
        missions.add(new Mars());
        missions.add(new Moon());
        missions.add(new Jupiter());

        List<Booster> boosters = new ArrayList<>();
        boosters.add(new Booster("EAP Ariane", 6470, 27, 30));
        boosters.add(new Booster("SRB Shuttle", 12500, 59, 55));
        boosters.add(new Booster("BE-3", 490, 13, 12));

        List<Launch> launches = new ArrayList<>();
    
        Simulator simulator = Simulator.getInstance(capsules, boosters, launchers, launches, missions);   
        simulator.startGame();

    }
}