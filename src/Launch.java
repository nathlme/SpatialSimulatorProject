
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
         System.out.println(reason);
         ConsoleUtils.pause();
         return false;
      }
      if (rocket.getLauncher().getLauncherMaxFuel() < mission.getNecessaryFuel(rocket)) {
         reason = "Carburant insuffisant !";
         System.out.println("Échec du lancement - " + reason);
         ConsoleUtils.pause();
         return false; 
      }
      if (rocket.getBoosterCount() > rocket.getLauncher().getMaxBooster()) {
         reason = "Trop de boosters !";
         System.out.println("Échec du lancement - " + reason);
         ConsoleUtils.pause();
         return false;
      }
      if (mission.doesRequiresCrew() && !rocket.getCapsule().IsInhabited()) {
         reason = "Capsule incompatible avec une mission habitée !";
         System.out.println("Échec du lancement - " + reason);
         ConsoleUtils.pause();
         return false; 
      }

      double randomNum = (double)(Math.random() * 1); 
      if (randomNum < Constants.RANDOM_FAILURE_RATE) {
         reason = "Anomalie technique imprévue !";
         System.out.println("Échec du lancement - " + reason);
         ConsoleUtils.pause();
         return false;
      }
      
      System.out.println("Lancement réussi !");
      ConsoleUtils.pause();
      return true; 
   }


   public double getLaunchPrice() {
      double totalLaunchPrice = rocket.getRocketTotalPrice() + (mission.getNecessaryFuel(rocket) * Constants.FUEL_PRICE_PER_TON);
      System.out.println("Prix total du lancement " + totalLaunchPrice + " €");
      return totalLaunchPrice;
   } 

   public void saveLaunch() {
       
      success = canGo();

      System.out.println("Résumé du lancement");

      if (success) { 
         System.out.println("Mission : " + mission.getName() + " - durée : " + mission.getDuration() + "h\n");
         System.out.println("Fusée utilisé : " + rocket.getName() + " - Composition : \n - Capsule : " + rocket.getCapsule().getName() + "\n - Lanceur : " + rocket.getLauncher().getName() + "\n - Booster : " + rocket.getBoosterCount());
         rocket.getRocketTotalPrice();
         getLaunchPrice();
         ConsoleUtils.pause();
      }else {
         System.out.println("Échec du lancement - " + reason);
         ConsoleUtils.pause();
      }

   }


}