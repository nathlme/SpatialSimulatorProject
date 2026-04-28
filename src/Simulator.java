import java.util.List;
import java.util.Scanner;

import boosters.*;
import capsules.*;
import launchers.*;
import missions.*;

public class Simulator {
    List<Capsule>  listCapsules;
    List<Booster>  listBoosters;
    List<Launcher> listLaunchers;
    List<Launch>   listLaunch;

    public Simulator(List<Capsule> listCapsules, List<Booster> listBoosters, List<Launcher> listLaunchers, List<Launch> listLaunch) {
        this.listCapsules  = listCapsules;
        this.listBoosters  = listBoosters;
        this.listLaunchers = listLaunchers;
        this.listLaunch    = listLaunch;
    }

    
    public void startGame() {
        boolean inGame = true; 


        while (inGame) {
            System.out.println("appuie sur 1");
            Scanner sc = new Scanner(System.in);
            String action = sc.nextLine();
            
            switch (action) {
                case "1":
                    inGame = false; 
                    break;
            }
        }
        sc.close();
    }   
}