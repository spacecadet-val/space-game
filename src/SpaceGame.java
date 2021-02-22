//Student Name: Valerie Wang
//Student ID: 260720570

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class SpaceGame {
  private Scanner reader;
  private Spaceship player;
  private Spaceship enemy;
  private static final int NUM_ARTIFACTS_WIN = 5;
  
  public SpaceGame(String fileName) {
    System.out.println("Welcome to the Space Game!");
    //initializing attribute of type Scanner
    this.reader = new Scanner(System.in);
    //calling loadSpaceship method to initialize the Spaceship attribtues
    this.player = FileIO.loadSpaceship("player.txt");
    this.enemy = FileIO.loadSpaceship("enemy.txt");
    //saving information in fileName parameter to an ArrayList of planets via the loadPlanets method
    ArrayList<Planet> planets = FileIO.loadPlanets(fileName);
    System.out.println("Loaded solar system from " + fileName + ": ");
    //initializing the solar system used within the Spaceship class to the ArrayList created above
    this.player.setPlanets(planets);
    //moving the player to the innermost planet of the solar system
    this.player.moveTo(planets.get(0).getName());
    //moving the enemy to the outermost planet of the solar system
    this.enemy.moveTo(planets.get(planets.size() -1).getName());
    System.out.println();
    System.out.println("You must find 5 alien artifacts to win!");
  }
  
  private int checkForDestroyed() {
    if(this.player.getCurrHP() <= 0.0) {
      //checking if the player is out of health points
      return 1;
    } else if(this.enemy.getCurrHP() <= 0.0) {
      //checking if the enemy is out of health points
      return 2;
    } else {
      return 0;
    }
  }
  
  private int checkForWin() {
    if(this.player.getNumArtifacts() >= NUM_ARTIFACTS_WIN) {
      //checking if the player has collected enough artifacts
      return 1;
    } else if(this.enemy.getNumArtifacts() >= NUM_ARTIFACTS_WIN) {
      //checking if the enemy has collected enough artifacts
      return 2;
    } else {
      return 0;
    }
  }
  
  public void playGame() {
    //looping through player and enemy commands while neither has been destroyed or collected enough artifacts
    while(checkForDestroyed() == 0 && checkForWin() == 0) {
      //the user's commands get executed
      System.out.println();
      System.out.println("Captain, what's your command?");
      String command = reader.nextLine();
      if(command.equalsIgnoreCase("moveIn")) {
        this.player.moveIn();
      } else if(command.equalsIgnoreCase("moveOut")) {
        this.player.moveOut();
      } else if(command.equalsIgnoreCase("moveTo")) {
        System.out.println("Captain, what's the destination?");
        String destination = reader.nextLine();
        this.player.moveTo(destination);
      } else if(command.equalsIgnoreCase("search")) {
        this.player.doSearch();
      } else {
        System.out.println("Sorry, your input was not recognized: " + command);
      }
      //creating the AI to make enemy commands
      Random generator = new Random();
      //using a Random object to generate a random int between 0 and 3 (not including 3)
      int randCommand = generator.nextInt(3);
      //using random int to dictate enemy commands
      if(randCommand == 0) {
        this.enemy.doSearch();
      } else if(randCommand == 1) {
        this.enemy.moveIn();
      } else {
        this.enemy.moveOut();
      }
    }
    //checking if the enemy has won or if the player's ship has been destroyed
    if(checkForWin() == 2 || checkForDestroyed() == 1) {
      System.out.println("Oh no! You lost... GAME OVER");
      //updating the enemy's win count
      this.enemy.increaseWins();
      //printing out how many times the enemy has won
      System.out.println(this.enemy.getName() + " has won: " + this.enemy.getNumWins() + " times");
    } else {
      //upon exiting the loop, if the enemy has not won nor the player's ship been destroyed, can assume the player's victory
      System.out.println("Congratulations! You beat the enemy... VICTORY");
      //updating the player's win count
      this.player.increaseWins();
      //printing out how many times the player has won
      System.out.println(this.player.getName() + " has won: " + this.player.getNumWins() + " times");
    }
    //trying and catching IOExceptions regarding saving both the player and enemy files
    try {
      //calling saveSpaceship method to write over player's file with updated information
      FileIO.saveSpaceship(this.player, this.player.getFileName());
      //informing user that the player's file has been saved
      System.out.println("Successfully wrote to file: " + this.player.getFileName()); 
    } catch (IOException e) {
      //error message
      System.out.println("Something went wrong with the file: " + this.player.getFileName());
    }
    try {
      //calling saveSpaceship method to write over enemy's file with updated information
      FileIO.saveSpaceship(this.enemy, this.enemy.getFileName());
      //informing the user that the enemy's file has been saved
      System.out.println("Successfully wrote to file: " + this.enemy.getFileName()); 
    } catch (IOException e) {
      //error message
      System.out.println("Something went wrong with the file: " + this.enemy.getFileName());
    }
  }
  
  public static void main(String[] args) {
    //setting up Space Game for testing
    Spaceship newShip = FileIO.loadSpaceship("player.txt");
    SpaceGame test = new SpaceGame("sol.txt");
    test.playGame();
  }
  
}