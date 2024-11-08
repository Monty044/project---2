package se.monty.adventure.game.model;

import java.util.Random;

public abstract class Entity {
    private float blockchance;
    private float critchance;
    private int health;
    private int damage;


    private String role;
    Random random = new Random();

    public Entity(int health, int damage, String role) {
        this.critchance = 0.2F;
        this.blockchance = 0.2F;
        this.health = health;
        this.damage = damage;
        this.role = role;

    }
    public void punch (Entity toPunch) {
        if(chanceCrit()) {
            toPunch.takeHit(damage * 2);
            System.out.println("Critcal Strike!");
        }
        else toPunch.takeHit(damage);

    }
    public void takeHit(int damage){


        if(!chanceBlock()) {
            this.health -= damage;

        }
        else System.out.println(role + " Blocked ");



    }


    public boolean chanceCrit(){
        return random.nextFloat() < critchance;

    }

    public boolean chanceBlock(){
        return random.nextFloat() < blockchance;

    }


    public float getBlockchance() {
        return blockchance;
    }

    public float getCritchance() {
        return critchance;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void setBlockchance(float blockchance) {
        this.blockchance = blockchance;
    }

    public void setCritchance(float critchance) {
        this.critchance = critchance;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage += damage;
    }

    public boolean isConscious(){
        return this.health > 0;

    }


}
