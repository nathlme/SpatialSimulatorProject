import java.util.List;
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

    private double money = 1000;

    private Scanner sc = new Scanner(System.in);

    public Simulator(List<Capsule> listCapsules, List<Booster> listBoosters, List<Launcher> listLaunchers, List<Launch> listLaunch, List<Mission> listMission) {
        this.listCapsules  = listCapsules;
        this.listBoosters  = listBoosters;
        this.listLaunchers = listLaunchers;
        this.listLaunch    = listLaunch;
        this.listMission   = listMission;
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






    public void startGame() {
        boolean inGame = true; 

         
        while (inGame) {
            
            System.out.println("\nChoisissez une action :\n");
            System.out.println("1 - Construire une fusée");
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

     
        while (inMenu) {

             
            System.out.println("\nChoisissez une action :\n");
            System.out.println("1 - Choisir le lanceur");
            System.out.println("2 - Choisir les boosters");
            System.out.println("3 - Choisir la capsule");
            System.out.println("R - Retour\n");

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
                    chooseBooster();
                    break;
                case "3": 
                    chooseCapsule();
                    break;
                default: 
                    System.out.println("\nChoix invalide\n");
                    break;
            }
        }
    }


    
    public void chooseCapsule() {
        boolean inMenu = true; 

         
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

         
        while (inMenu) {

            System.out.println("\nChoisissez un lanceur :\n");
            printComponentList(listLaunchers);
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


    public void chooseBooster() {
        boolean inMenu = true; 

         
        while (inMenu) {

            System.out.println("\nChoisissez un booster :\n");
            printComponentList(listBoosters);
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



    public void chooseMission() {
        boolean inMenu = true; 

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