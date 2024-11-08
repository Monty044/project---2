
package se.monty.adventure.game;

import org.junit.jupiter.api.Test;
import se.monty.adventure.game.model.Burglar;
import se.monty.adventure.game.model.Resident;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    public void testTakeHit() {
        Resident resident = new Resident(150, 20, "Resident");
        resident.takeHit(30);
        assertEquals(120, resident.getHealth(), "Resident health should be reduced by 30.");
    }

    @Test
    public void testIsConscious() {
        Resident resident = new Resident(50, 20, "Resident");
        assertTrue(resident.isConscious(), "Resident should be conscious with positive health.");
        resident.takeHit(50);
        assertFalse(resident.isConscious(), "Resident should be unconscious with zero or negative health.");
    }

    @Test
    public void testFightAndConsciousness() {
        Resident resident = new Resident(150, 20, "Resident");
        Burglar burglar = new Burglar(200, 40, "Burglar");

        resident.takeHit(burglar.getDamage());
        assertEquals(110, resident.getHealth(), "Resident's health should decrease after taking a hit from Burglar.");
        assertTrue(resident.isConscious(), "Resident should still be conscious with remaining health.");

        burglar.takeHit(resident.getDamage() * 5);
        assertTrue(burglar.getHealth() < 200, "Burglar's health should be less than 200 after taking multiple hits.");
        assertTrue(burglar.isConscious(), "Burglar should still be conscious if health is above zero.");

        burglar.takeHit(200);
        assertFalse(burglar.isConscious(), "Burglar should be unconscious with zero or negative health.");
    }
}
