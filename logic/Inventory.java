package edu.skidmore.cs326.game.sudoku.logic;

import java.util.HashMap;
import java.util.Map;

/**
 * The Inventory class manages the storage and use of power-ups within the game.
 * It differentiates between permanent and temporary power-ups, 
 * providing methods
 * to add, use, count, and transfer these items.
 */
public class Inventory {
    /**
     * Stores permanent power-ups with their counts.
     */
    private Map<PowerUp, Integer> permanentInventory;

    /**
     * Stores temporary power-ups with their counts.
     */
    private Map<PowerUp, Integer> temporaryInventory;

    /**
     * Constructs a new inventory with empty permanent and temporary storage.
     */
    public Inventory() {
        permanentInventory = new HashMap<>();
        temporaryInventory = new HashMap<>();
    }

    /**
     * Adds a specified count of a power-up to the inventory.
     * @param powerUp the power-up to be added
     * @param count the number of instances of the power-up to be added
     * @param isTemporary specifies if the power-up should 
     * be added to the temporary inventory
     */
    public void addPowerUp(PowerUp powerUp, int count, boolean isTemporary) {
        Map<PowerUp, Integer> inventory = isTemporary 
        		? temporaryInventory : permanentInventory;
        inventory.merge(powerUp, count, Integer::sum);
    }

    /**
     * Attempts to use a power-up from the inventory.
     * @param powerUp the power-up to be used
     * @param isTemporary specifies if the power-up should be
     *  used from the temporary inventory
     * @return true if the power-up was successfully 
     * used, false if it was not available
     */
    public boolean usePowerUp(PowerUp powerUp, boolean isTemporary) {
        Map<PowerUp, Integer> inventory = isTemporary 
        		? temporaryInventory : permanentInventory;
        if (inventory.containsKey(powerUp) && inventory.get(powerUp) > 0) {
            inventory.merge(powerUp, -1, Integer::sum);
            return true;
        }
        return false;
    }

    /**
     * Retrieves the count of a specific power-up in the inventory.
     * @param powerUp the power-up to check
     * @param isTemporary specifies if the count should be
     *  retrieved from the temporary inventory
     * @return the count of the specified power-up
     */
    public int getPowerUpCount(PowerUp powerUp, boolean isTemporary) {
        Map<PowerUp, Integer> inventory = isTemporary 
        		? temporaryInventory : permanentInventory;
        return inventory.getOrDefault(powerUp, 0);
    }

    /**
     * Transfers all power-ups from the temporary inventory to 
     * the permanent inventory and clears the temporary inventory.
     */
    public void transferTemporaryToPermanent() {
        for (Map.Entry<PowerUp, Integer> entry  
        	: temporaryInventory.entrySet()) {
            addPowerUp(entry.getKey(), entry.getValue(), false);
        }
        temporaryInventory.clear();
    }
}
