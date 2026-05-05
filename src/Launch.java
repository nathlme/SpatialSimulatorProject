import missions.*;
import rockets.*;
import utils.ConsoleUtils;
import exceptions.NotEnoughtFuelExeption;


public class Launch {
   Rocket  rocket;
   Mission mission;
   boolean  success;
   String   reason;
   double   totalCost;
   String   date;

   // Constructeur
   public Launch(Rocket rocket, Mission mission, String date) {
        this.rocket    = rocket;
        this.mission   = mission;
        this.date      = date;
   }

   // Verify if the rocket has enough fuel for the selected mission
   private void checkFuel() throws NotEnoughtFuelExeption {
      double necessaryFuel = mission.getNecessaryFuel(rocket);
      int maxFuel = rocket.getLauncher().getLauncherMaxFuel();

      if (maxFuel < necessaryFuel) {
         throw new NotEnoughtFuelExeption("Carburant insuffisant !");
      }
   }

   // Verify if the rocket can go to the selected mission
   public boolean canGo() {
      if (rocket == null) {
         reason = "Une fusée est réquise pour le lancement...";
         ConsoleUtils.pause();
         return false;
      }

      try {
         checkFuel();
      } catch (NotEnoughtFuelExeption e) {
         reason = e.getMessage();
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
      if (mission.doesRequiresCrew() && !rocket.getCapsule().isInhabited()) {
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


   // Calculate and print the total launch price
   public double getLaunchPrice() {
      double fuelCostInEuros = mission.getNecessaryFuel(rocket) * Constants.FUEL_PRICE_PER_TON;
      double fuelCostInMillions = fuelCostInEuros / 1_000_000;

      double totalLaunchPrice = rocket.getRocketTotalPrice() + fuelCostInMillions;
       

      System.out.println("Prix total du lancement " + totalLaunchPrice + " Millions d'euros");
      totalCost = totalLaunchPrice;
      return totalLaunchPrice;
   } 

   // Run the launch and print the launch summary
   public boolean runLaunch() {
       
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


   // Format the launch data to save it in the history file
   public String toFileLine() {
      return date + " - "
         + rocket.getName() + " - "
         + mission.getName() + " - "
         + success + " - "
         + reason + " - "
         + totalCost + " Millions d'euros ";
   }

}