package se.monty.adventure.game.model;

import java.util.Random;

public class Burglar extends Entity {
    private final Random random = new Random();
    public Burglar(int health, int damage, String role) {
        super(health, damage, role);
    }

    public boolean blockchance(){
        return random.nextInt(100) < 5;

    }


}
