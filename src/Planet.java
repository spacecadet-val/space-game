//Student Name: Valerie Wang
//Student ID: 260720570

import java.util.ArrayList;
import java.io.*;
import java.util.Random;

public class Planet {
  private String name;
  private double chance;
  private double maxDamage;
  
  public Planet(String name, double chance, double maxDamage) {
    this.name = name;
    this.chance = chance;
    this.maxDamage = maxDamage;
    //initializing attributes for object of type Planet
    if(chance < 0 || chance > 1) {
      throw new IllegalArgumentException("This is not a possible chance of success!");
      //throws exception if an impossible chance value is assigned
    }
    if(maxDamage < 0) {
      throw new IllegalArgumentException("There cannot be a negative number of damage points!");
      //throws exception if a negative maximum damage value is assigned
    }
  }
  
  public String getName() {
    return this.name;
  }
  
  public String toString() {
    return "Name: " + this.name + ", Artifact Chance: " + this.chance*100.0 + '%' + ", Possible Damage: " + this.maxDamage;
  }
  
  public static int findPlanet(String findName, ArrayList<Planet> planets) {
    for(int i = 0; i < planets.size(); i++) {
      //iterating through planets in ArrayList
      if(planets.get(i).getName().equalsIgnoreCase(findName)) {
        //identifying planet of interest in ArrayList
        return i;
        //returning that planet's index
      }
    }
    return -1;
  }
  
  public boolean searchForArtifact() {
    Random generator = new Random();
    if(generator.nextDouble() < this.chance) {
      //creating Random object and using it to generate a random double between 0 and 1
      //checking if this double is less than the chance of finding an artifact
      return true;
      //indicating that the artifact was found
    } else {
      return false;
      //indicating that the artifact was not found
    }
  }
  
  public double getDamageTaken() {
    Random generator = new Random();
    double damage = this.maxDamage*generator.nextDouble();
    //generating a random damage amount between 0 and the max damage possible using a Random object
    return damage;
  }
  
}