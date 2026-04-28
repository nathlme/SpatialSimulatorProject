
import boosters.*;
import capsules.*;
import launchers.*;
import missions.*;

public class Launch {
   Rocket  rocket;
   Mission mission;
   boolean  success;
   String   reason;
   double   totalCost;
   String   date;

   public Launch(Rocket rocket, Mission mission, boolean success, String reason, double totalCost, String date) {
        this.rocket    = rocket;
        this.mission   = mission;
        this.success   = success;
        this.reason    = reason;
        this.totalCost = totalCost;
        this.date      = date;
   }

   public boolean canGo() {
      if (rocket.launcher.maxFuel < mission.getNecessaryFuel(rocket)) {
         System.out.println("Échec du lancement - Carburant insuffisant !");
         return false; 
      }
      if (rocket.launcher.boosterList.length() > rocket.launcher.maxBoosters) {
         System.out.println("Échec du lancement - Trop de boosters !");
         return false;
      }
      if (mission.requiresCrew && !rocket.capsule.inhabited) {
         System.out.println("Échec du lancement - Capsule incompatible avec une mission habitée !");
         return false; 
      }

      double randomNum = (double)(Math.random() * 1); 
      if (randomNum < Constants.RANDOM_FAILURE_RATE) {
         System.out.println("Échec du lancement - Anomalie technique imprévue !");
         return false;
      }

      System.out.println("Tout est bon, lancement prêt !");
      return true; 
   }


   public double getLaunchPrice() {
      double totalLaunchPrice = rocket.getRocketTotalPrice() + (mission.getNecessaryFuel(rocket) * Constants.FUEL_PRICE_PER_TON);
      System.out.println("Prix total du lancement " +totalLaunchPrice + " €");
      return totalLaunchPrice;
   }
}