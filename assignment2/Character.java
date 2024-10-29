package assignment2;

/**
 * The Character class represents any game character with a name, skill level, and energy level.
 * A character can attack using a weapon and can be hurt by attacks.
 */
public class Character {
    private final String characterName;
    private final int skillLevel;
    private int energyLevel;

    /**
     * Constructs a new Character with the specified name, energy level, and skill level.
     *
     * @param name        the name of the character
     * @param energyLevel the initial energy level of the character
     * @param skillLevel  the skill level of the character
     */
    public Character(String name, int energyLevel, int skillLevel) {
        this.characterName = name;
        this.energyLevel = Math.max(0, energyLevel);
        this.skillLevel = Math.max(0, skillLevel);
    }

    /**
     * Returns the name of the character.
     *
     * @return the character's name
     */
    public String getName() {
        return characterName;
    }

    /**
     * Returns the skill level of the character.
     *
     * @return the character's skill level
     */
    public int getSkillLevel() {
        return skillLevel;
    }

    /**
     * Returns the current energy level of the character.
     *
     * @return the character's current energy level
     */
    public int getEnergyLevel() {
        return energyLevel;
    }

    /**
     * Reduces the energy level of the character by the specified attack amount.
     *
     * @param attackAmount the amount of energy to reduce
     * @return the actual amount of energy reduced
     */
    public int hurt(int attackAmount) {
        this.energyLevel -= attackAmount;
        return attackAmount;
    }

    /**
     * Calculates the total attack amount generated by the character.
     * It is the sum of the weapon's power and the character's skill level.
     *
     * @param w1 the weapon used for the attack
     * @return the total attack amount
     */
    public int attack(Weapon w1) {
        return w1.shoot() + this.skillLevel;
    }

    /**
     * Determines whether the character has lost the combat.
     * A character loses if their energy level is zero or below.
     *
     * @return true if the character has lost, false otherwise
     */
    public boolean isLose() {
        return this.energyLevel <= 0;
    }
}