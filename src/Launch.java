
import boosters.*;
import capsules.*;
import launchers.*;
import missions.*;
import rockets.*;
import utils.ConsoleUtils;


public class Launch {
   Rocket  rocket;
   Mission mission;
   boolean  success;
   String   reason;
   double   totalCost;
   String   date;

   public Launch(Rocket rocket, Mission mission, String date) {
        this.rocket    = rocket;
        this.mission   = mission;
        this.date      = date;
   }

   public boolean canGo() {
      if (rocket == null) {
         reason = "Une fusée est réquise pour le lancement...";
         ConsoleUtils.pause();
         return false;
      }
      if (rocket.getLauncher().getLauncherMaxFuel() < mission.getNecessaryFuel(rocket)) {
         reason = "Carburant insuffisant !";
         ConsoleUtils.pause();
         return false; 
      }
      if (rocket.getRocketTotalMass() > rocket.getLauncher().getCharge()) {
         reason = "Surcharge dépassée !";
         ConsoleUtils.pause();
         return false;
      }
      if (rocket.getBoosterCount() > rocket.getLauncher().getMaxBooster()) {
         reason = "Trop de boosters !";
         ConsoleUtils.pause();
         return false;
      }
      if (mission.doesRequiresCrew() && !rocket.getCapsule().IsInhabited()) {
         reason = "Capsule incompatible avec une mission habitée !";
         ConsoleUtils.pause();
         return false; 
      }

      double randomNum = (double)(Math.random() * 1); 
      if (randomNum < Constants.RANDOM_FAILURE_RATE) {
         reason = "Anomalie technique imprévue !";
         ConsoleUtils.pause();
         return false;
      }
      
      reason = "Succès";
      System.out.println("\nLancement réussi !");
      ConsoleUtils.pause();
      return true; 
   }


   public double getLaunchPrice() {
      double totalLaunchPrice = rocket.getRocketTotalPrice() + (mission.getNecessaryFuel(rocket) * Constants.FUEL_PRICE_PER_TON);
      System.out.println("Prix total du lancement " + totalLaunchPrice + " Millions d'euros");
      return totalLaunchPrice;
   } 

   public boolean printLaunch() {
       
      success = canGo();

      System.out.println("==== RÉSUMÉ DU LANCEMENT ====\n");

      if (success) { 
         System.out.println("Mission : " + mission.getName() + " - durée : " + mission.getDuration() + "h\n");
         System.out.println("Fusée utilisé : " + rocket.getName() + " - Composition : \n - Capsule : " + rocket.getCapsule().getName() + "\n - Lanceur : " + rocket.getLauncher().getName() + "\n - Booster : " + rocket.getBoosterCount());
         System.out.println("Le prix total de la fusée " + rocket.getName() + " est de " + rocket.getRocketTotalPrice() + " Millions d'euros."); 
         totalCost = getLaunchPrice();
         ConsoleUtils.pause();
         return true;
      }else {
         System.out.println("Échec du lancement - " + reason);
         ConsoleUtils.pause();
         return false;
      }

   }

   public String toFileLine() {
      return date + " - "
         + rocket.getName() + " - "
         + mission.getName() + " - "
         + success + " - "
         + reason + " - "
         + totalCost;
   }

}