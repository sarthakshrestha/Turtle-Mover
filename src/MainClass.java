import java.awt.*; // Importing the required libraries
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.awt.Graphics;

import uk.ac.leedsbeckett.oop.LBUGraphics;
import javax.swing.JFrame;
import java.util.ArrayList;

public class MainClass extends LBUGraphics {
    Graphics g = getGraphicsContext();
    ArrayList<String> todoCommand = new ArrayList<String>();
    public static void main(String[] args) {
        new MainClass();
    }

    public MainClass() {
        JFrame MainFrame = new JFrame(); // Frame to display the turtle panel
        MainFrame.setLayout(new FlowLayout()); // Not Necessary
        MainFrame.add(this); // this is the object which extends turtle graphics so that we are adding the turtle graphics
        // to the frame
        MainFrame.pack(); // Sets the frame to a size so that we can see
        MainFrame.setVisible(true);
        clear();
        reset();
    }

    public void saveCommand() {
        //String textToAppend = "Commands: " + command + "\n"; // Saves the command put in by the user to text file Commands.txt
        JPanel a = new JPanel();
        String name = JOptionPane.showInputDialog(a,"Enter the name for the file:");
        try {
            FileWriter file = new FileWriter(name + ".txt", true);
            //BufferedWriter writer = new BufferedWriter(file);
            for(int i = 0; i< todoCommand.size(); i++)
            {
                //writer.write(todoCommand.get(i)+"\n");
                file.write(todoCommand.get(i)+"\n");
            }
            file.close();
            //writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(a, "File could not be created.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void fill(int x) {
            g.setColor(Color.blue);
            g.drawOval(getxPos(), getyPos(), x, x);
            g.fillOval(getxPos(), getyPos(), x, x);
    }

        //int height = 20;
        //int width = 20;
        //g.drawOval(250, 20, x, x);
        //g.setColor(Color.blue);
        //g.fillOval(250, 20, height, width);

    @Override
    public void about() // Overriding about() function so that my name is displayed as the message
    {
        super.about();
        displayMessage("Sarthak Shrestha"); // Displays name in the output
    }

    public void saveimage(){
        JPanel panel2 = new JPanel();
        String sav = JOptionPane.showInputDialog(panel2, "Enter file name for saving: ");
        BufferedImage img = getBufferedImage();
        try {
            BufferedImage bi = getBufferedImage();
            File outputfile = new File(sav + ".jpeg");
            ImageIO.write(getBufferedImage(), "jpeg", outputfile);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(panel2, "Error saving the file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void circle(int radius){ // Overriden circle command
        g.drawOval(getxPos(), getyPos(), radius, radius);

    }

    public void newpencolor(int x, int y, int z){ // Adds new color to the pen according to RGB values
        penDown();
        Color c = new Color(x, y, z);
        setPenColour(c);
    }
    /*public void triangle(int x){
        penDown();
        setPenColour(Color.red);
        forward(x);
        turnRight(120);
        forward(x);
        turnRight(120);
        turnRight(x);
    }*/


    public void loadimage(){
        JPanel panel2 = new JPanel();
        String load = JOptionPane.showInputDialog(panel2, "Enter the file name of image to be loaded: ");
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File(load + ".jpeg"));
            setBufferedImage(img);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(panel2, "Error loading the file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadcommand() {
        JPanel p = new JPanel();
        String name = JOptionPane.showInputDialog(p, "Enter the File Name:");

        try {
            String data;
            File myObj = new File(name + ".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                processCommand(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(p, "File could not be found.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
/// Another try for loading the comment
        /*public void loadcommand(){ // Loads the command from the text file
        try{
            FileReader a = new FileReader("SavedCommands.txt");
            Scanner sc = new Scanner(a);
            while(sc.hasNextLine()){
                processCommand(sc.nextLine());
            }
        }
        catch (Exception e){
            JPanel panel2 = new JPanel();
            JOptionPane.showMessageDialog(panel2, "Error opening the file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    */

    public void processCommand(String command) //calls the JTextField method from LBUGraphics`
    {
        String textToAppend = "Commands: " + command + "\n"; // Saves ALL of the previous commands put in by the user to text file Commands.txt
        try {
            FileWriter file = new FileWriter("Commands.txt", true);
            BufferedWriter writer = new BufferedWriter(file);
            writer.write(textToAppend);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        command = command.toLowerCase();
        System.out.println("You have typed " + command + " command");
        if (command.equals("about")) { //if the user types in about the turtle is shown with its movement
            about();
        } else if (command.equals("penup")) { //when pinup is input in the field the pen is lifted
            penUp();
        } else if (command.equals("reset")) { // the frame is reset and cleared when reset is passed as input
            reset();
        } else if (command.equals("pendown")) { // when pendown is input in the field the pen is put down
            penDown();
        } else if (command.equals("turnright")) { // the turtle turns right when turnright is taken input in the text field
            turnRight();
        } else if (command.equals("turnleft")) { // the turtle turns left
            turnLeft();
        } else if (command.equals("black")) { // changes the output pen color to black
            setPenColour(Color.BLACK);
        } else if (command.equals("green")) { // changes the output pen color to green
            setPenColour(Color.GREEN);
        } else if (command.equals("red")) {  // changes the output pen color to red
            setPenColour(Color.RED);
        } else if (command.equals("white")) {  // changes the output pen color to white
            setPenColour(Color.white);
        } else if (command.equals("clear")) { // clears the display
            clear();
        } else if (command.equals("newturtle")) { // changes the turtle image to a cuter one
            setTurtleImage("Turtle.png");
    } else if (command.equals("saveimg")) { // Saves the buffered image into an image.jpeg file
            saveimage();
        } else if (command.equals("loadimg")) { // Loads the buffered image into the screen
            loadimage();
        } else if (command.equals("loadcommand")) { // Loads the commands from the text file SavedCommands.txt
            loadcommand();
        } else if (command.equals("savecommand")) {
            saveCommand();
        } else {
            String splitCommand[] = command.split(" "); // Splits the command with a space
            String commandEntered = splitCommand[0];
            try {
                int valueEntered = Integer.parseInt(splitCommand[1]); // the integer helps to put in the value after the command
                if (valueEntered <= 0) {
                    JPanel p = new JPanel();
                    JOptionPane.showMessageDialog(p, "The numeric value cant be less than or equal to zero.", "Value Error", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Please enter a value greater than 0 and please try again.");
                } else if (commandEntered.equals("forward")) { // Makes the turtle move forward
                    forward(valueEntered);
                } else if (commandEntered.equals("turnright")) { // Makes the turtle turn right to a certain degree
                    turnRight(valueEntered);
                } else if (commandEntered.equals("turnleft")) { // Makes the turtle turn left to a certain degree
                    turnLeft(valueEntered);
                } else if (commandEntered.equals("backward")) { // Makes the turtle move backwards
                    forward(-valueEntered);
                } else if (commandEntered.equals("fill")) { // fills the size of circle with parameter and a color
                    fill(valueEntered);
                } else if (command.startsWith("triangle")) { // Creates a triangle
                    for (int i = 0; i < 3; i++) {
                        penDown();
                        setPenColour(Color.red);
                        forward(valueEntered);
                        turnRight(120);
                        forward(valueEntered);
                        turnRight(120);
                        forward(valueEntered);
                        turnRight(120);
                    }
                } else if (command.startsWith("circle")) {
                    circle(valueEntered);
                } else if (command.startsWith("newcolor")) {
                    try
                    {
                        int r = Integer.parseInt(splitCommand[1]);
                        int g = Integer.parseInt(splitCommand[2]);
                        int b = Integer.parseInt(splitCommand[3]);
                        newpencolor(r, g, b);
                    }
                    catch (Exception e)
                    {
                        JPanel p = new JPanel();

                        JOptionPane.showMessageDialog(p, "Error!", "Value Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel, "Invalid command", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IndexOutOfBoundsException e) { // to detect valid command with missing parameter.
                JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Invalid command along with parameters", "Missing Parameters", JOptionPane.ERROR_MESSAGE);
                System.out.println("Missing parameters or command. Please provide the required command/parameters and try again.");
            }
        }
        todoCommand.add(command); // Passes the commands in the Array
    }
}