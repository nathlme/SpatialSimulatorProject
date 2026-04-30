import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

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

    Capsule  currentCapsule;
    List<Booster>  currentListBoosters;
    Launcher currentLauncher;

    private double money = 100000;

    private static Scanner sc = new Scanner(System.in);

    public Simulator(List<Capsule> listCapsules, List<Booster> listBoosters, List<Launcher> listLaunchers, List<Launch> listLaunch, List<Mission> listMission) {
        this.listCapsules  = listCapsules;
        this.listBoosters  = listBoosters;
        this.listLaunchers = listLaunchers;
        this.listLaunch    = listLaunch;
        this.listMission   = listMission;

        this.currentListBoosters = new ArrayList<>();
    }


    public static void pause(){
        System.out.print("\nAppuyez sur Entrée pour continuer...\n");
        String action = sc.nextLine();
    }

    public static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
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
                System.out.println("Vous ne pouvez plus ajouter de booster avec ce lanceur !");
                pause();
                return false;
            }
        }else{
            System.out.println("\nVeuillez acheter un lanceur d'abord !");
            pause();
            return false;
        }
    }


    public boolean assemble() {
        if (currentCapsule == null || currentLauncher == null) {
            System.out.println("\nIl vous manque des composant pour assembler votre fusée !\n");
            pause();
            return false;
        }else {
            System.out.println("Fusée assemblé ! - Choisissez un nom pour votre fusée : ");
            String name = sc.nextLine();
            Rocket actualRocket = new Rocket(name, currentLauncher, currentCapsule, currentListBoosters); 
            System.out.println("Voici votre fusée : " + actualRocket.getName());
            actualRocket.getComponents();
            pause();
            return true;
        }
    }



    public boolean pay(SpaceComponent component) {
        if (money < component.getPrice()) {
            System.out.println("Achat impossible : pas assez d'argent.");
            System.out.println("Prix : " + component.getPrice() + "M");
            System.out.println("Solde : " + money + "M\n");
            return false;
        }

        money -= component.getPrice();

        System.out.println("Achat validé : " + component.getName());
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

        clearConsole(); 
        while (inGame) {
            
            System.out.println("\nChoisissez une action :\n");
            System.out.println("1 - Assembler une fusée");
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
                    System.out.println("\nChoix invalide\n");
                    break;
            }
        }
        sc.close();
    }   



    public void buildRocket(){
        boolean inMenu = true; 

        clearConsole(); 
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

        clearConsole();  
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

        clearConsole();  
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

        clearConsole();  
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

        clearConsole();  

        System.out.println("\nChoisissez une mission :\n");
        printMissionList();
        System.out.println("R - Retour");
        System.out.println("\nVotre choix :");

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



    public void showHistory() {
        boolean inMenu = true; 

        clearConsole();  
        while (inMenu) {

            System.out.println("\nRécapitulatif des missions :\n");
            printMissionList();
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





    public void template() {
        boolean inMenu = true; 

        clearConsole();  
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