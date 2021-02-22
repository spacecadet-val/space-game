//Student Name: Valerie Wang
//Student ID: 260720570

import java.util.ArrayList;

public class Spaceship {
  private String name;
  private double currHP;
  private double maxHP;
  private int numArtifacts;
  private int numWins;
  private Planet currPlanet;
  private static ArrayList<Planet> planets;
  private String fileName;
  
  public Spaceship(String name, double maxHP, int numWins) {
    //initializing attributes of the Spaceship object being constructed
    this.name = name;
    this.currHP = maxHP;
    this.maxHP = maxHP;
    this.numWins = numWins;
  }
  
  //get and set methods
  
  public String getFileName() {
    return this.fileName;
  }
  
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  
  public int getNumWins() {
    return this.numWins;
  }
  
  public String getName() {
    return this.name;
  }
  
  public int getNumArtifacts() {
    return this.numArtifacts;
  }
  
  public double getCurrHP() {
    return this.currHP;
  }
  
  public double getMaxHP() {
    return this.maxHP;
  }
  
  public String toString() {
    String healthStr = String.format("%1$.2f", this.currHP);
    //formatting the current health points to have 2 decimal points as a String 
    return "Name: " + this.name + ", Current Health: " + healthStr + ", Number of Artifacts Found: " + this.numArtifacts;
  }
  
  public void setPlanets(ArrayList<Planet> planets) {
    ArrayList<Planet> copy = new ArrayList<Planet>(planets.size());
    //declaring an ArrayList of planets to act as copy
    for(int i = 0; i < planets.size(); i++) {
      copy.add(planets.get(i));
      //iterating through planets of input ArrayList and copying them to the copy
    }
    this.planets = copy;
    //initializing the ArrayList<Planet> attribute to the copy
    for(int i = 0; i < copy.size(); i++) {
      //iterating through planets of copy
      System.out.println(copy.get(i).toString());
      //printing out the information for each planet using toString method written above
    }
  }
  
  public void moveTo(String newPlanet) {
    int newIndex = Planet.findPlanet(newPlanet, this.planets);
    //finding index of destination planet in ArrayList of solar system using findPlanet method
    if(newIndex == -1) {
      //if method returns -1 then planet is not in solar system
      System.out.println(this.name + " tried to move to " + newPlanet + ", but that planet isn't in this solar system!");
      //appropriate error message prints
    } else {
      this.currPlanet = this.planets.get(newIndex);
      //currPlanet attribute of Spaceship object is initialized to the element in the ArrayList corresponding to the new index
      System.out.println(this.name + " has moved to " + this.currPlanet.getName() + '.');
    }
  }
  
  public void moveIn() {
    int currIndex = Planet.findPlanet(this.currPlanet.getName(), this.planets);
    //using findPlanet method to identify the index of the Spaceship's current planet in the ArrayList
    if(currIndex == 0) {
      System.out.println(this.name + " couldn't move in. No planet is closer in.");
      //informing that if Spaceship is at innermost planet, it is not possible to move further in
    } else {
      currIndex--;
      moveTo(this.planets.get(currIndex).getName());
      //decreasing the index by one and moving Spaceship to planet at new index
    }
  }
  
  public void moveOut() {
    int currIndex = Planet.findPlanet(this.currPlanet.getName(), this.planets);
    //using findPlanet method to identify the index of the Spaceship's current planet in the ArrayList
    if(currIndex == this.planets.size() -1) {
      System.out.println(this.name + " couldn't move out. No planet is further out.");
      //informing that if Spaceship is at outermost planet in ArrayList, it is not possible to move further out
    } else {
      currIndex++;
      moveTo(this.planets.get(currIndex).getName());
      //increasing the index by one and moving Spaceship to planet at new index
    }
  }
  
  public void increaseWins() {
    this.numWins++;
    //updating the player or enemy's number of wins after a victory
  }
  
  public void doSearch() {
    boolean foundArtifact = this.currPlanet.searchForArtifact();
    //saving whether an artifact is found on the Spaceship's current planet as a boolean
    if(foundArtifact == true) {
      System.out.println(this.name + " found an artifact!");
      //informing player that an artifact was found if the boolean is true
      this.numArtifacts++;
      //increasing player's artifact number
    } else {
      System.out.println(this.name + " failed to find an artifact!");
      //if false, informing player that no artifact was found
    }
    double damageTaken = this.currPlanet.getDamageTaken();
    //saving damage taken on that planet to a double
    String damageStr = String.format("%1$.2f", damageTaken);
    //formatting damage taken as a String
    this.currHP = this.currHP - Double.parseDouble(damageStr);
    //updating the health points to reflect the damage
    System.out.println(this.name + " took " + damageStr + " damage while searching for an artifact on " + currPlanet.getName() + '!');
    System.out.println(toString());
    //printing updated Spaceship information using toString method above
    if(this.currHP < 0) {
      System.out.println(this.name + " exploded!");
      //printing that ship has exploded if Spaceship runs out of health
    }
  }
  
}