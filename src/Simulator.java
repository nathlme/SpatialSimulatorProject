import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import utils.ConsoleUtils;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import boosters.*;
import capsules.*;
import launchers.*;
import missions.*;
import rockets.*;
import common.SpaceComponent;



public class Simulator {
    private List<Capsule>  listCapsules;
    private List<Booster>  listBoosters;
    private List<Launcher> listLaunchers;
    private List<Launch>   listLaunch;
    private List<Mission>  listMission;

    private List<String> historyLines = new ArrayList<>();

    private Capsule       currentCapsule;
    private List<Booster> currentListBoosters;
    private Launcher      currentLauncher;
    private Mission       currentMission;
    private Rocket actualRocket;

    private static final String HISTORY_FILE = "history.txt";
     
    private static Scanner sc = new Scanner(System.in);

    private static Simulator instance;

    private Simulator(List<Capsule> listCapsules, List<Booster> listBoosters, List<Launcher> listLaunchers, List<Launch> listLaunch, List<Mission> listMission) {
        this.listCapsules  = listCapsules;
        this.listBoosters  = listBoosters;
        this.listLaunchers = listLaunchers;
        this.listLaunch    = listLaunch;
        this.listMission   = listMission;

        this.currentListBoosters = new ArrayList<>();

        loadHistory();
    }

    public static Simulator getInstance(List<Capsule> listCapsules, List<Booster> listBoosters, List<Launcher> listLaunchers, List<Launch> listLaunch, List<Mission> listMission) {
        if (instance == null) {
            instance = new Simulator( listCapsules, listBoosters, listLaunchers, listLaunch, listMission);
        }

        return instance;
    }

    public String getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return now.format(formatter);
    }

    public void saveLaunchInFile(Launch launch) {
        
        String line = launch.toFileLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE, true))) {
            writer.write(launch.toFileLine());
            writer.newLine();
            
            historyLines.add(line);

        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde de l'historique.");
        }
    }

    public void printComponentList(List<? extends SpaceComponent> componentList) {
        int i = 1;

        for (SpaceComponent component : componentList) {
            System.out.println(i + " - " + component.printInfo());
            i++;
        }
    }
     
    public void printMissionList() {
        int i = 1;         
        for (Mission mission : listMission) {
            System.out.println("" + i + " - " + mission.printInfo());
            i++;
        }
    }

    public boolean canAddBooster(){
        if (currentLauncher != null) {
            if (currentListBoosters.size() < currentLauncher.getMaxBooster()) {
                return true;
            }else {
                System.out.println("\nVous ne pouvez pas ajouter plus de booster avec le lanceur actuel !");
                ConsoleUtils.pause();
                return false;
            }
        }else{
            System.out.println("\nVeuillez acheter un lanceur d'abord !");
            ConsoleUtils.pause();
            return false;
        }
    }


    public boolean assemble() {
        if (currentCapsule == null || currentLauncher == null) {
            System.out.println("\nIl vous manque des composant pour assembler votre fusée !\n");
            ConsoleUtils.pause();
            return false;
        }else {
            System.out.println("\nFusée assemblé ! - Choisissez un nom pour votre fusée : ");
            String name = sc.nextLine();

            if (currentListBoosters.isEmpty()) {
                this.actualRocket = new Rocket(name, currentLauncher, currentCapsule);
            } else {
                this.actualRocket = new Rocket(name, currentLauncher, currentCapsule, currentListBoosters);
            }

            System.out.println("\nVoici votre fusée : " + actualRocket.getName());
            System.out.println("Composants : ");
            actualRocket.printComponents();
            System.out.println("Prix total de la fusée : " + actualRocket.getRocketTotalPrice() + " Millions d'euros");
            ConsoleUtils.pause();
            return true;
        }
    }







    public void printComponents() {
        System.out.println("\nPièces disponible : Capsule : " + (currentCapsule != null ? currentCapsule.getName() : "Aucune") + " | Lanceur : " + (currentLauncher != null ? currentLauncher.getName() : "Aucun") + " | Boosters : ");
        for (Booster b : currentListBoosters) {
            System.out.print(b.getName() + " ");
        }
    }


    public void startGame() {
        boolean inGame = true; 

        ConsoleUtils.clearConsole(); 
        while (inGame) {
            
            System.out.println("\n==== CHOISISSEZ UNE ACTION ====\n");
            System.out.println("1 - Configurer une fusée");
            System.out.println("2 - Choisir une mission");
            System.out.println("3 - Historique des missions");
            System.out.println("Q - Quitter\n");

            System.out.println("Votre choix :");

            String action = sc.nextLine();

            switch (action) {
                case "Q":
                    inGame = false; 
                    System.out.println("MERCI D'AVOIR JOUÉ !");
                    break;
                case "1": 
                    buildRocket();
                    break;
                case "2":
                    chooseMission();
                    break;
                case "3":
                    showHistory();
                    break;
                default: 
                    ConsoleUtils.clearConsole();
                    System.out.println("\n==== Choix invalide ====\n");
                    break;
            }
        }
        sc.close();
    }   



    public void buildRocket(){
        boolean inMenu = true; 

        ConsoleUtils.clearConsole(); 
        while (inMenu) {

            System.out.println("\n==== CHOISISSEZ UNE ACTION ====\n");
            System.out.println("1 - Choisir le lanceur");
            System.out.println("2 - Choisir les boosters");
            System.out.println("3 - Choisir la capsule");
            System.out.println("\n4 - Assembler la fusée");
            printComponents();

            System.out.println("\nR - Retour\n");

            System.out.println("\nVotre choix :");

            String action = sc.nextLine();

            switch (action) {
                case "R":
                    ConsoleUtils.clearConsole(); 
                    inMenu = false;
                    break;
                case "1":
                    chooseLauncher();
                    break;
                case "2":
                    if (canAddBooster()) {
                        chooseBooster();
                    }
                    break;
                case "3": 
                    chooseCapsule();
                    break;
                case "4":
                    assemble();
                    break;
                default: 
                    ConsoleUtils.clearConsole();
                    System.out.println("\n==== Choix invalide ====\n");
                    break;
            }
        }
    }


    
    public void chooseCapsule() {
        boolean inMenu = true; 

        ConsoleUtils.clearConsole();  
        while (inMenu) {

            System.out.println("\n==== CHOISISSEZ UNE CAPSULE ====\n");
            printComponentList(listCapsules);
            System.out.println("R - Retour");
            System.out.println("\nVotre choix :");

            String action = sc.nextLine();

            if (action.equalsIgnoreCase("R")) {
                ConsoleUtils.clearConsole(); 
                inMenu = false;
                break;
            }

            try {
                int choice = Integer.parseInt(action);

                if (choice < 1 || choice > listCapsules.size()) {
                    System.out.println("\nChoix invalide\n");
                } else {
                    Capsule selectedCapsule = listCapsules.get(choice - 1);

                    System.out.println("\nVous avez choisi la capsule: " + selectedCapsule.getName());
                    
                    currentCapsule = selectedCapsule;
                    System.out.println("Capsule ajoutée à la fusée !");
                    inMenu = false;
                    
                }

            } catch (NumberFormatException e) {
                ConsoleUtils.clearConsole();
                System.out.println("\n==== Choix invalide ====\n");
            }
        }
    }   




    public void chooseLauncher() {
        boolean inMenu = true; 

        ConsoleUtils.clearConsole();  
        while (inMenu) {

            System.out.println("\n==== CHOISISSEZ UN LANCEUR ====\n");
            printComponentList(listLaunchers);
            System.out.println("R - Retour");
            System.out.println("\nVotre choix :");

            String action = sc.nextLine();

            if (action.equalsIgnoreCase("R")) {
                ConsoleUtils.clearConsole(); 
                inMenu = false;
                break;
            }

            try {
                int choice = Integer.parseInt(action);

                if (choice < 1 || choice > listLaunchers.size()) {
                    System.out.println("\nChoix invalide\n");
                } else {
                    Launcher selectedLauncher = listLaunchers.get(choice - 1);

                    System.out.println("\nVous avez choisi le lanceur: " + selectedLauncher.getName());

                   
                    currentLauncher = selectedLauncher;
                    currentListBoosters.clear();
                    System.out.println("Lanceur ajoutée à la fusée !");
                    inMenu = false;
                    
                }

            } catch (NumberFormatException e) {
                ConsoleUtils.clearConsole();
                System.out.println("\n==== Choix invalide ====\n");
            }
        }
    }


    public void chooseBooster() {
        boolean inMenu = true; 

        ConsoleUtils.clearConsole();  
        while (inMenu) {

            System.out.println("\n==== CHOISISSEZ UN BOOSTER ====\n");
            printComponentList(listBoosters);
            System.out.println("R - Retour");
            System.out.println("\nVotre choix :");

            String action = sc.nextLine();

            if (action.equalsIgnoreCase("R")) {
                ConsoleUtils.clearConsole(); 
                inMenu = false;
                break;
            }

            try {
                int choice = Integer.parseInt(action);

                if (choice < 1 || choice > listBoosters.size()) {
                    System.out.println("\nChoix invalide\n");
                } else {
                    Booster selectedBooster = listBoosters.get(choice - 1);

                    System.out.println("\nVous avez choisi le booster : " + selectedBooster.getName());

                   
                    currentListBoosters.add(selectedBooster);
                    System.out.println("Booster ajoutée à la fusée !");
                    inMenu = false;
                    
                }

            } catch (NumberFormatException e) {
                ConsoleUtils.clearConsole();
                System.out.println("\n==== Choix invalide ====\n");
            }
        }
    }   



    public void chooseMission() {
        boolean inMenu = true; 

        ConsoleUtils.clearConsole();  

        System.out.println("\n==== CHOISISSEZ UNE MISSION ====\n");
        printMissionList();
        System.out.println("R - Retour");
        System.out.println("\nVotre choix :");

        while (inMenu) {

            String action = sc.nextLine();

            if (action.equalsIgnoreCase("R")) {
                ConsoleUtils.clearConsole(); 
                inMenu = false;
                break;
            }

            try {
                int choice = Integer.parseInt(action);

                if (choice < 1 || choice > listMission.size()) {
                    System.out.println("\nChoix invalide\n");
                } else {
                    Mission selectedMission = listMission.get(choice - 1);

                    System.out.println("\nVous avez choisi la mission : " + selectedMission.getName());
                    currentMission = selectedMission;
                    startLaunch();
                    ConsoleUtils.pause();
                    inMenu = false;
                }

            } catch (NumberFormatException e) {
                ConsoleUtils.clearConsole();
                System.out.println("\n==== Choix invalide ====\n");
            }
        }
    }


    public void loadHistory() {
        File file = new File(HISTORY_FILE);

        if (!file.exists()) {
            return;
        }

        historyLines.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(HISTORY_FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    historyLines.add(line);
                }
            }

        } catch (IOException e) {
            System.out.println("Erreur lors du chargement de l'historique.");
        }
    }

    public void showHistory() {
        ConsoleUtils.clearConsole();

        if (historyLines.isEmpty()) {
            System.out.println("Aucun historique trouvé.");
            ConsoleUtils.pause();
            return;
        }

        System.out.println("\n===== HISTORIQUE DES LANCEMENTS =====\n");

        for (String line : historyLines) {
            System.out.println(line);
        }

        ConsoleUtils.pause();
    }


    public void startLaunch() {
        boolean inMenu = true; 

        ConsoleUtils.clearConsole();  
        while (inMenu) {

            System.out.println("\n==== PRÉPARATIF DU LANCEMENT ====\n");
            System.out.println("1 - Partir pour la mission : " + currentMission.getName());
            System.out.println("R - Retour");
            System.out.println("\nVotre choix :");

            String action = sc.nextLine();

            switch (action) {
                case "R":
                    ConsoleUtils.clearConsole(); 
                    inMenu = false;  
                    break;
                case "1": 
                    if (actualRocket == null) {
                        System.out.println("\nVous devez d'abord assembler une fusée avant de lancer une mission.");
                        ConsoleUtils.pause();
                        return;
                    }

                    Launch actualLaunch = new Launch(actualRocket, currentMission, getCurrentDate());

                    actualLaunch.runLaunch();

                    listLaunch.add(actualLaunch);
                    saveLaunchInFile(actualLaunch);
                    break;
                default: 
                    ConsoleUtils.clearConsole();
                    System.out.println("\n==== Choix invalide ====\n");
                    break;
            }
        }
    }  
}