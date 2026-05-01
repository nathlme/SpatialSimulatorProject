import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import utils.ConsoleUtils;

import boosters.*;
import capsules.*;
import launchers.*;
import missions.*;
import rockets.*;
import common.SpaceComponent;


public class Simulator {
    List<Capsule>  listCapsules;
    List<Booster>  listBoosters;
    List<Launcher> listLaunchers;
    List<Launch>   listLaunch;
    List<Mission>  listMission;

    Capsule       currentCapsule;
    List<Booster> currentListBoosters;
    Launcher      currentLauncher;
    Mission       currentMission;
    Rocket actualRocket;

    private double money = 999999999;
    private long fuelQuantity = 0;

    private static Scanner sc = new Scanner(System.in);

    public Simulator(List<Capsule> listCapsules, List<Booster> listBoosters, List<Launcher> listLaunchers, List<Launch> listLaunch, List<Mission> listMission) {
        this.listCapsules  = listCapsules;
        this.listBoosters  = listBoosters;
        this.listLaunchers = listLaunchers;
        this.listLaunch    = listLaunch;
        this.listMission   = listMission;

        this.currentListBoosters = new ArrayList<>();
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
                System.out.println("Vous ne pouvez plus ajouter de booster avec ce lanceur !");
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
            System.out.println("Fusée assemblé ! - Choisissez un nom pour votre fusée : ");
            String name = sc.nextLine();
            this.actualRocket = new Rocket(name, currentLauncher, currentCapsule, currentListBoosters); 
            System.out.println("\nVoici votre fusée : " + actualRocket.getName());
            System.out.println("Composants : ");
            actualRocket.getComponents();
            ConsoleUtils.pause();
            return true;
        }
    }



    public boolean pay(SpaceComponent component) {
        if (money < component.getPrice()) {
            System.out.println("Achat impossible : pas assez d'argent.");
            System.out.println("Prix : " + component.getPrice() + "M");
            System.out.println("Solde : " + money + "M\n");
            ConsoleUtils.pause();
            return false;
        }

        money -= component.getPrice();

        System.out.println("Achat validé : " + component.getName());
        System.out.println("Solde restant : " + money + "M\n");
        ConsoleUtils.pause();

        return true;
    }


    public boolean buyFuel() {
        System.out.println("Quelle quantité de carburant voulez-vous acheter (Tonne) ? - (" + Constants.FUEL_PRICE_PER_TON + " euros/tonne)\n");
        String q = sc.nextLine();
        long quantite = Long.parseLong(q);
        double price = Constants.FUEL_PRICE_PER_TON * quantite;
        if (price > money) {
            System.out.println("Achat impossible : pas assez d'argent.");
            System.out.println("Prix : " + price + " euros");
            System.out.println("Solde : " + money + "M\n");
            return false;
        }
        
        money -= price;
        fuelQuantity += quantite;

        System.out.println("Achat validé : " + quantite + " tonnes de carburant pour " + price + " euros");
        System.out.println("Solde restant : " + money + "M\n");

        return true;
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
            
            System.out.println("\nChoisissez une action :\n");
            System.out.println("1 - Assembler une fusée");
            System.out.println("2 - Choisir une mission");
            System.out.println("3 - Historique des missions");
            System.out.println("4 - Acheter du carburant");
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
                case "4":
                    buyFuel();
                    break;
                default: 
                    System.out.println("\nChoix invalide\n");
                    break;
            }
        }
        sc.close();
    }   



    public void buildRocket(){
        boolean inMenu = true; 

        ConsoleUtils.clearConsole(); 
        while (inMenu) {

            System.out.println("\nChoisissez une action :\n");
            System.out.println("1 - Choisir le lanceur");
            System.out.println("2 - Choisir les boosters");
            System.out.println("3 - Choisir la capsule");
            System.out.print("4 - Assembler la fusée");
            printComponents();
            System.out.println("\nR - Retour\n");

            System.out.println("\nVotre choix :");

            String action = sc.nextLine();

            switch (action) {
                case "R":
                     
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
                    System.out.println("\nChoix invalide\n");
                    break;
            }
        }
    }


    
    public void chooseCapsule() {
        boolean inMenu = true; 

        ConsoleUtils.clearConsole();  
        while (inMenu) {

            System.out.println("\nChoisissez une capsule :\n");
            printComponentList(listCapsules);
            System.out.println("R - Retour");
            System.out.println("\nVotre choix :");

            String action = sc.nextLine();

            if (action.equalsIgnoreCase("R")) {
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

                    if (pay(selectedCapsule)) {
                        currentCapsule = selectedCapsule;
                        System.out.println("Capsule ajoutée à la fusée !");
                        inMenu = false;
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("\nVeuillez entrer un nombre valide ou R pour revenir.\n");
            }
        }
    }   




    public void chooseLauncher() {
        boolean inMenu = true; 

        ConsoleUtils.clearConsole();  
        while (inMenu) {

            System.out.println("\nChoisissez un lanceur :\n");
            printComponentList(listLaunchers);
            System.out.println("R - Retour");
            System.out.println("\nVotre choix :");

            String action = sc.nextLine();

            if (action.equalsIgnoreCase("R")) {
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

                    if (pay(selectedLauncher)) {
                        currentLauncher = selectedLauncher;
                        System.out.println("Lanceur ajoutée à la fusée !");
                        inMenu = false;
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("\nVeuillez entrer un nombre valide ou R pour revenir.\n");
            }
        }
    }


    public void chooseBooster() {
        boolean inMenu = true; 

        ConsoleUtils.clearConsole();  
        while (inMenu) {

            System.out.println("\nChoisissez un booster :\n");
            printComponentList(listBoosters);
            System.out.println("R - Retour");
            System.out.println("\nVotre choix :");

            String action = sc.nextLine();

            if (action.equalsIgnoreCase("R")) {
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

                    if (pay(selectedBooster)) {
                        currentListBoosters.add(selectedBooster);
                        System.out.println("Booster ajoutée à la fusée !");
                        inMenu = false;
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("\nVeuillez entrer un nombre valide ou R pour revenir.\n");
            }
        }
    }   



    public void chooseMission() {
        boolean inMenu = true; 

        ConsoleUtils.clearConsole();  

        System.out.println("\nChoisissez une mission :\n");
        printMissionList();
        System.out.println("R - Retour");
        System.out.println("\nVotre choix :");

        while (inMenu) {

            String action = sc.nextLine();

            if (action.equalsIgnoreCase("R")) {
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
                System.out.println("\nVeuillez entrer un nombre valide ou R pour revenir.\n");
            }
        }
    }



    public void showHistory() {
        boolean inMenu = true; 

        ConsoleUtils.clearConsole();  
        while (inMenu) {

            System.out.println("\nRécapitulatif des missions :\n");
            System.out.println("R - Retour");
            System.out.println("\nVotre choix :");

            String action = sc.nextLine();

            switch (action) {
                case "R":
                     
                    inMenu = false;  
                    break;
                case "1": 

                    break;
                default: 
                    System.out.println("\nChoix invalide\n");
                    break;
            }
        }
    } 


    public void startLaunch() {
        boolean inMenu = true; 

        ConsoleUtils.clearConsole();  
        while (inMenu) {

            System.out.println("\nPréparatif du lancement\n");
            System.out.println("1 - Partir pour la mission : " + currentMission.getName());
            System.out.println("2 - Conditions du lancement");
            System.out.println("R - Retour");
            System.out.println("\nVotre choix :");

            String action = sc.nextLine();

            switch (action) {
                case "R":
                    inMenu = false;  
                    break;
                case "1": 
                    Launch actualLaunch = new Launch(actualRocket, currentMission,"Aujourd'hui");
                    actualLaunch.saveLaunch();
                    break;
                default: 
                    System.out.println("\nChoix invalide\n");
                    break;
            }
        }
    }  



    public void template() {
        boolean inMenu = true; 

        ConsoleUtils.clearConsole();  
        while (inMenu) {

            String action = sc.nextLine();

            switch (action) {
                case "R":
                     
                    inMenu = false;  
                    break;
                case "1": 

                    break;
                default: 
                    System.out.println("\nChoix invalide\n");
                    break;
            }
        }
    }   








}