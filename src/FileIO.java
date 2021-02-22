//Student Name: Valerie Wang
//Student ID: 260720570

import java.io.*;
import java.util.ArrayList;

public class FileIO {
  
  public static Spaceship loadSpaceship(String fileName) {
    try {
      FileReader fr = new FileReader(fileName);
      BufferedReader br = new BufferedReader(fr);
      //read the first line
      String currentLine = br.readLine();
      //initializing a String representing the Spaceship name with the first line of the file
      String newName = currentLine;
      ///update current line
      currentLine = br.readLine();
      //initializing a double representing the maxHP with the next line
      double newMaxHP = Double.parseDouble(currentLine);
      currentLine = br.readLine();
      //initializing an integer representing the number of wins with the last line
      int newNumWins = Integer.parseInt(currentLine);
      //constructing a Spaceship object with variables containing info from file
      Spaceship newShip = new Spaceship(newName, newMaxHP, newNumWins);
      br.close();
      fr.close();
      //setting the file name of the created ship to the fileName parameter
      newShip.setFileName(fileName);
      //returning the created ship
      return newShip;
      //catching and throwing exceptions, with appropriate error messages
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    } catch (IOException e) {
      throw new IllegalArgumentException("Something went wrong");
    }
  }
  
  public static ArrayList<Planet> loadPlanets(String fileName) {
    //method that loads a text file (i.e. sol.txt) onto an ArrayList called planets
    ArrayList<Planet> planets = new ArrayList<Planet>();
    try {
      FileReader fr = new FileReader(fileName);
      BufferedReader br = new BufferedReader(fr);
      //read the first line
      String currentLine = br.readLine();
      //loop to read each line
      while(currentLine != null) {
        //using split method to break the String currentLine into a String array by " "
        String[] splitLine = currentLine.split(" ");
        //adding elements of splitLine to ArrayList of planets
        planets.add(new Planet(splitLine[0], Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[2])));
        ///update current line
        currentLine = br.readLine();
      }
      br.close();
      fr.close();
      //catching and throwing exceptions, with appropriate error messages
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    } catch (IOException e) {
      throw new IllegalArgumentException("Something went wrong");
    }
    return planets;
  }
  
  public static void saveSpaceship(Spaceship writeShip, String writeName) throws IOException {
    try {
      FileWriter fw = new FileWriter(writeName);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(writeShip.getName());
      //writing name of the Spaceship to file
      bw.newLine();
      //ensuring following information is written on a new line
      bw.write(Double.toString(writeShip.getMaxHP()));
      //writing maxHP of the Spaceship to file
      bw.newLine();
      bw.write(Integer.toString(writeShip.getNumWins()));
      //writing number of wins of the Spaceship to file
      bw.newLine();
      bw.close();
      fw.close();
    } catch (FileNotFoundException e) {
      //catching a FileNotFoundException and informing the user that the file could not be found
      throw new IllegalArgumentException("File not found");
    }
  }
  
}