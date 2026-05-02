# Simulateur de lancement spatial

## Présentation

Ce projet est une application Java en console permettant de simuler des lancements spatiaux.

L’utilisateur peut configurer une fusée, choisir une mission, lancer une simulation et consulter l’historique des lancements. Le projet a été réalisé dans le cadre du TP final Java des B1 de Toulouse Ynov Campus.

## Fonctionnalités

L’application permet de :

- choisir un lanceur parmi une liste disponible ;
- choisir une capsule parmi une liste disponible;
- ajouter un ou plusieurs boosters dans la limite autorisée par le lanceur ;
- assembler une fusée ;
- choisir une mission ;
- simuler un lancement ;
- déterminer si le lancement est réussi ou échoué ;
- afficher la raison de l’échec si le lancement échoue ;
- calculer le coût total d’un lancement ;
- sauvegarder l’historique dans un fichier ;
- recharger l’historique au démarrage du programme ;
- afficher l’historique des lancements.

## Missions disponibles

Le simulateur contient les missions suivantes :

- Orbite terrestre ;
- ISS ;
- Lune ;
- Mars ;
- Jupiter.

La mission Jupiter est la mission personnelle ajoutée au projet. Elle hérite de la classe abstraite Mission.

## Structure du projet

Le projet est organisé en plusieurs packages :

    src/
    ├── boosters/
    │   └── Booster.java
    ├── capsules/ 
    │   ├── Capsule.java
    │   ├── Apollo.java
    │   ├── CargoDragon.java
    │   ├── CrewDragon.java
    │   └── Orion.java
    ├── common/
    │   └── SpaceComponent.java
    ├── exceptions/
    │   └── NotEnoughtFuelExeption.java
    ├── launchers/
    │   ├── Launcher.java
    │   ├── Ariane5.java
    │   ├── Falcon9.java
    │   ├── SaturneV.java
    │   └── SLS.java
    ├── missions/
    │   ├── Mission.java
    │   ├── EarthOrbit.java
    │   ├── ISS.java
    │   ├── Moon.java
    │   ├── Mars.java
    │   └── Jupiter.java
    ├── rockets/
    │   └── Rocket.java
    ├── utils/
    │   └── ConsoleUtils.java
    ├── Constants.java
    ├── Launch.java
    ├── Main.java
    └── Simulator.java

## Compilation et exécution

Depuis la racine du projet, compiler avec PowerShell :

    javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName

Puis lancer le programme avec :

    java -cp out Main

Sur Linux ou macOS :

    javac -d out $(find src -name "*.java")
    java -cp out Main

## Utilisation

Au lancement du programme, un menu principal permet de choisir entre :

    1 - Configurer une fusée
    2 - Choisir une mission
    3 - Historique des missions
    Q - Quitter

Pour effectuer un lancement, il faut :

1. configurer une fusée (un lanceur, une capsule, éventuellement un ou plusieurs boosters) ;
2. assembler la fusée ;
3. choisir une mission ;
4. lancer la simulation.

## Logique métier

### Coût total d’un lancement

Le coût total d’un lancement est calculé avec la formule suivante :

    coût total = prix de la fusée + (carburant nécessaire × prix du carburant par tonne)

Le prix du carburant est défini dans Constants.java :

    public static final int FUEL_PRICE_PER_TON = 1200;

Les prix des composants étant exprimés en millions d’euros, le coût du carburant est converti en millions d’euros avant d’être ajouté au prix de la fusée.

### Carburant nécessaire

Le carburant nécessaire est calculé dans la classe Mission avec la formule :

    carburant = (masse totale de la fusée × distance × coefficient mission) / 1000

Chaque mission possède une distance et un coefficient de carburant propre à elle même.

## Conditions d’échec

Un lancement échoue si l’une des conditions suivantes est remplie :

- le carburant nécessaire dépasse la capacité maximale du lanceur ;
- la masse totale de la fusée dépasse la charge utile du lanceur ;
- le nombre de boosters dépasse la limite autorisée par le lanceur ;
- la mission nécessite une capsule habitée mais la capsule choisie n’est pas habitée ;
- une anomalie technique aléatoire survient.

La probabilité d’échec aléatoire est définie dans Constants.java :

    public static final double RANDOM_FAILURE_RATE = 0.05;

## Historique

Chaque lancement est sauvegardé dans le fichier :

    history.txt

Le fichier est créé automatiquement à la racine du projet lorsqu’un lancement est effectué.

L’historique est chargé au démarrage du simulateur, puis affiché depuis le menu principal.

Chaque ligne contient :

    date - fusée - mission - résultat - raison - coût total

## Notions de programmation orientée objet utilisées

### Classes et objets

Le projet utilise plusieurs classes métier :

- Rocket ;
- Launch ;
- Simulator ;
- Booster ;
- Capsule ;
- Launcher ;
- Mission.

Chaque classe représente un élément du domaine spatial ou du fonctionnement du simulateur.

### Encapsulation

Les attributs principaux sont privés et accessibles grâce à des getters utiles.

Exemple :

    private double mass;

    public double getMass() {
        return mass;
    }

Cela évite que les autres classes modifient directement les données internes des objets.

### Héritage

Le projet contient plusieurs hiérarchies :

- SpaceComponent est la classe mère de Capsule, Launcher et Booster ;
- Capsule est la classe mère de Apollo, CargoDragon, CrewDragon et Orion ;
- Launcher est la classe mère de Ariane5, Falcon9, SaturneV et SLS ;
- Mission est la classe mère de EarthOrbit, ISS, Moon, Mars et Jupiter.

Chaque sous-classe possède ses propres valeurs, mais réutilise la structure de la classe parent.

### Polymorphisme

Le polymorphisme est utilisé avec des listes de types parents.

Exemple :

    List<Mission> missions = new ArrayList<>();
    missions.add(new EarthOrbit());
    missions.add(new ISS());
    missions.add(new Mars());
    missions.add(new Moon());
    missions.add(new Jupiter());

Le simulateur manipule des objets de type Mission, mais chaque objet réel correspond à une mission concrète.

Le projet utilise aussi SpaceComponent pour manipuler plusieurs composants différents avec une seule méthode d’affichage ou de paiement.

### Composition

La classe Rocket est composée de :

- un Launcher ;
- une Capsule ;
- une liste de Booster.

La fusée n’hérite pas de ces classes. Elle les contient sous forme d’attributs. C’est donc une relation de composition.

### Redéfinition

La classe abstraite Launcher définit une méthode abstraite :

    public abstract String getSpecificity();

Chaque lanceur concret redéfinit cette méthode avec @Override.

Exemple :

    @Override
    public String getSpecificity() {
        return "Lanceur réutilisable, économique et adapté aux missions orbitales.";
    }

Cela permet à chaque lanceur d’avoir une description spécifique.

### Surcharge

La classe Rocket utilise une surcharge de constructeur.

Premier constructeur, pour créer une fusée sans booster :

    public Rocket(String name, Launcher launcher, Capsule capsule)

Deuxième constructeur, pour créer une fusée avec une liste de boosters :

    public Rocket(String name, Launcher launcher, Capsule capsule, List<Booster> boosterList)

Java choisit automatiquement le constructeur à utiliser selon les paramètres fournis.

### Exception personnalisée

Le projet contient une exception métier personnalisée :

    NotEnoughtFuelExeption

Elle est levée lorsque le carburant nécessaire dépasse la capacité maximale du lanceur.

Cette exception permet de représenter clairement une erreur métier liée au lancement.

### Lecture et écriture de fichier

L’historique est sauvegardé avec BufferedWriter et FileWriter.

Il est rechargé au démarrage avec BufferedReader et FileReader.

Cela permet de conserver les anciens lancements même après fermeture du programme.

## Singleton du simulateur

La classe Simulator est conçue pour n’avoir qu’une seule instance dans le programme.

Le constructeur est privé et l’instance est récupérée avec :

    Simulator.getInstance(...)

Cela permet de garantir qu’un seul simulateur orchestre le menu console, les composants disponibles, les missions et l’historique.

## Démonstration conseillée

### Exemple de lancement réussi

Configuration possible :

- Lanceur : Falcon 9 ;
- Capsule : Cargo Dragon ;
- Booster : aucun ;
- Mission : Orbite terrestre.

Cette configuration devrait réussir sauf en cas d’anomalie technique aléatoire.

### Exemple de lancement échoué

Configuration possible :

- Lanceur : Falcon 9 ;
- Capsule : Cargo Dragon ;
- Mission : Mars.

La mission Mars nécessite une capsule habitée. Cargo Dragon n’est pas habitée, donc le lancement échoue avec la raison :

    Capsule incompatible avec une mission habitée

### Affichage de l’historique

Après un lancement réussi et un lancement échoué, l’utilisateur peut retourner au menu principal et choisir :

    3 - Historique des missions

L’historique affiche les lancements sauvegardés avec la date, la fusée, la mission, le résultat, la raison et le coût total.

## Choix de conception

### SpaceComponent

La classe abstraite SpaceComponent regroupe les attributs communs aux composants achetables ou affichables :

- name ;
- price.

Elle permet de factoriser du code entre Booster, Capsule et Launcher.

### Mission

La classe Mission contient les informations communes à toutes les missions :

- nom ;
- besoin d’équipage ;
- distance ;
- durée ;
- coefficient de carburant.

Les missions concrètes définissent leurs propres valeurs dans leur constructeur.

### Launch

La classe Launch représente une tentative de lancement.

Elle contient :

- la fusée utilisée ;
- la mission choisie ;
- la date ;
- le résultat ;
- la raison du succès ou de l’échec ;
- le coût total.

Elle vérifie aussi les conditions d’échec et produit une ligne sauvegardable dans l’historique.

### Simulator

La classe Simulator orchestre l’application.

Elle gère :

- le menu console ;
- les catalogues de composants ;
- la configuration de la fusée ;
- le choix de mission ;
- le lancement ;
- l’historique ;
- le chargement et la sauvegarde fichier.

## Déclaration d’utilisation de l’IA

Une aide par intelligence artificielle a été utilisée pendant le développement pour obtenir des explications sur certaines notions Java, les exceptions personnalisées, la surcharge, le singleton et la persistance fichier.

Le code final a été compris, adapté et intégré manuellement dans le projet.