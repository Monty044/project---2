package se.monty.adventure.game;

import se.monty.adventure.game.model.Burglar;
import se.monty.adventure.game.model.Resident;

import java.util.Scanner;

public class Game {
    private Resident resident;
    private Burglar burglar;
    private boolean fryingPanFound;
    private boolean running;

    private Room currentRoom;

    enum Room {
        KITCHEN, HALL, LIVINGROOM, BEDROOM, OFFICE
    }

    public Game(){
        this.resident = new Resident(150, 20, "Resident");
        this.burglar = new Burglar(200, 40, "Burglar");
        this.fryingPanFound = false;
        this.running = true;
        this.currentRoom = Room.LIVINGROOM;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        String playAgain;

        do {
            initializeGame();
            System.out.println("Welcome to the game! You are in the living room.");

            while (running && resident.isConscious()) {
                switch (currentRoom) {
                    case LIVINGROOM:
                        System.out.println("\nWhich room do you want to go to? (Kitchen, Hall, Office, Bedroom):");
                        System.out.println("Available options: KITCHEN, HALL, OFFICE, BEDROOM");
                        String input = scanner.nextLine().trim().toUpperCase();

                        switch (input){
                            case "KITCHEN":
                                currentRoom = Room.KITCHEN;
                                enterKitchen(scanner);
                                break;

                            case "HALL":
                                currentRoom = Room.HALL;
                                startFight(scanner);
                                break;

                            case "BEDROOM":
                                currentRoom = Room.BEDROOM;
                                enterBedroom(scanner);
                                break;

                            case "OFFICE":
                                currentRoom = Room.OFFICE;
                                enterOffice(scanner);
                                break;

                            default:
                                System.out.println("Invalid Room. Try again.");
                        }
                        break;

                    case KITCHEN:
                    case OFFICE:
                    case BEDROOM:
                    case HALL:
                        System.out.println("\nDo you want to go back to the living room? (y/n)");
                        String choice = scanner.nextLine().trim().toLowerCase();
                        if(choice.equals("y")) {
                            currentRoom = Room.LIVINGROOM;
                            enterLivingRoom();
                        } else {
                            System.out.println("You stay in the current room.");

                        }
                        break;
                }
            }

            if(!resident.isConscious()) {
                System.out.println("You have been defeated. Game over.");
            } else if (!burglar.isConscious() && currentRoom == Room.OFFICE) {
                System.out.println("Congratulations! You have defeated the burglar and called the police.");
            }

            System.out.println("\nWould you like to play again? (y/n)");
            playAgain = scanner.nextLine().trim().toLowerCase();

        } while(playAgain.equals("y"));

        System.out.println("Thank you for playing!");
        scanner.close();
    }

    private void initializeGame(){
        this.resident = new Resident(150, 20, "Resident");
        this.burglar = new Burglar(200, 40, "Burglar");
        this.fryingPanFound = false;
        this.running = true;
        this.currentRoom = Room.LIVINGROOM;
    }

    private void enterLivingRoom(){
        System.out.println("\nYou are back in the living room.");
    }

    private void enterKitchen(Scanner scanner) {
        System.out.println("\nYou are in the kitchen. There is a frying pan here.");
        if (!fryingPanFound) {
            System.out.println("Do you want to pick up the frying pan? (y/n)");
            String choice = scanner.nextLine().trim().toLowerCase();
            if(choice.equals("y")) {
                System.out.println("You picked up the frying pan! Your damage has increased to 40.");
                resident.setDamage(40);
                fryingPanFound = true;
            } else {
                System.out.println("You chose not to pick up the frying pan.");
            }
        } else{
            System.out.println("There is nothing else to find in the kitchen.");
        }
    }

    private void enterOffice(Scanner scanner) {
        System.out.println("\nYou are in the office.");
        if (!burglar.isConscious()) {
            System.out.println("The burglar is unconscious. Do you want to call the police? (y/n)");
            String choice = scanner.nextLine().trim().toLowerCase();
            if(choice.equals("y")) {
                System.out.println("You have called the police and won the game! Good job!");
                running = false;
            } else {
                System.out.println("You chose not to call the police yet.");
            }
        } else {
            System.out.println("The burglar is still out there! You can't call the police yet.");
        }
    }

    private void enterBedroom(Scanner scanner) {
        System.out.println("\nYou entered the bedroom. The burglar is not here. Look somewhere else.");
    }

    private void startFight(Scanner scanner) {
        System.out.println("\nYou encounter the burglar!");

        while(resident.isConscious() && burglar.isConscious()) {
            System.out.println("\nDo you want to punch the burglar? (y/n)");
            String choice = scanner.nextLine().trim().toLowerCase();
            if(choice.equals("y")) {
                if(!burglar.blockchance()) {
                    resident.punch(burglar);
                    System.out.println("You punched the burglar!");
                    System.out.println("Burglar's health: " + burglar.getHealth());
                } else {
                    System.out.println("The burglar blocked your attack!");
                }
            } else {
                System.out.println("You chose not to punch the burglar.");
            }

            if(burglar.isConscious()) {
                System.out.println("The burglar punches you back!");
                burglar.punch(resident);
                System.out.println("Your health: " + resident.getHealth());
            }

            if(!burglar.isConscious()) {
                System.out.println("You knocked out the burglar!");
            }

            if(!resident.isConscious()) {
                System.out.println("You have been defeated by the burglar.");
                running = false;
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
