package se.monty.adventure.game;

import se.monty.adventure.game.Burglar;
import se.monty.adventure.game.Resident;
import se.monty.adventure.game.Entity;


import java.util.Scanner;


public class Game {
    private Resident resident;
    private Burglar burglar;
    private boolean fryingPanFound = false;
    private boolean running = true;


    static Room currentroom = Room.LIVINGROOM;

    enum Room{
        KITCHEN,HALL,LIVINGROOM,BEDROOM,OFFICE
    }

    public Game(){
        this.resident = new Resident(150, 20, "resident");
        this.burglar = new Burglar(200, 40, "burglar");
    }


    public void startGame() {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the game you are in the living room");


        while (running && resident.isConscious()) {

            if (currentroom.equals(Room.LIVINGROOM)) {
                System.out.println("Which room do you want to go to? (Kitchen, hall, office, bedroom):");
                String input = scanner.nextLine();


                switch (input.toUpperCase()){

                    case "KITCHEN" -> { currentroom = Room.KITCHEN;
                        enterKitchen();
                        break;

                    }


                    case "HALL" -> { currentroom = Room.HALL;
                        StartFight();
                        break;

                    }


                    case "BEDROOM" -> { currentroom = Room.BEDROOM;
                        enterBedroom();
                        break;
                    }


                    case "OFFICE" -> { currentroom = Room.OFFICE;
                        enterOffice();
                        break;
                    }


                    case "LIVINGROOM" -> { currentroom = Room.LIVINGROOM;
                        enterLivingroom();
                    }

                    default ->
                        System.out.println("Invalid Room. try again.");




                }




            }
            else {
                System.out.println("do you want to go back to the living room");
                currentroom = Room.LIVINGROOM;
            }

        }

    }

    private void enterLivingroom(){
        System.out.println("Your back in the living room:");
    }

    private void enterKitchen() {
        if (!fryingPanFound) {
            System.out.println("You found a frying pan!, your big man ting");
            resident.setDamage(40);
            fryingPanFound = true;
        }else{
            System.out.println("You are in the kitchen but there is nothing left to find");
        }
    }

    private void enterOffice() {
        if (!burglar.isConscious()) {
            System.out.println("You called the police and won the game good job!");
            running = false;
        } else {
            System.out.println("The burglar is still out there! You can't call the police yet.");
        }
    }

    private void enterBedroom() {
        System.out.println("You entered the room and the burglar is not here look somewhere else");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playAgain;

        do {
            Game game = new Game();
            game.startGame();

            System.out.println("Would you like to play again? (y/n)");
            playAgain = scanner.nextLine();

        }while(playAgain.equalsIgnoreCase("y"));
        System.out.println("Thank you for playing!");
    }


    private void StartFight() {
        System.out.println("You encounter the burglar!");
        while(resident.isConscious() && burglar.isConscious()) {
            System.out.println("you punched the burglar!");
            if(!burglar.blockchance()) {
                resident.punch(burglar);
                System.out.println("Burglar's health: " + burglar.getHealth());
            }else {
                System.out.println("The burglar blocked your attack!");
            }

            if (burglar.isConscious()) {
                System.out.println("the burglar punches you");
                burglar.punch(resident);
                System.out.println("Your health: " + resident.getHealth());
            }

        }
        if (!burglar.isConscious()) {
            System.out.println("You knocked out the burglar!");

        }else {
            System.out.println("You lost the fight. Game over");
            running = false;
        }

    }


}


