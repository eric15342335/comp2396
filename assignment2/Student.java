package assignment2;

/**
 * The Student is poor and can only afford using a BadGun. However, he/she can
 * hide to dodge an attack and receive 0 hurt.
 * It is a subclass of the Character class.
 */
public class Student extends Character {
    private boolean isHidden = false;

    /**
     * Constructs a new Student with the specified name, energy level, and skill level.
     *
     * @param name        the name of the student
     * @param energyLevel the initial energy level of the student
     * @param skillLevel  the skill level of the student
     */
    public Student(String name, int energyLevel, int skillLevel) {
        super(name, energyLevel, skillLevel);
    }

    /**
     * Hides the student from the next attack. When hidden, the student takes no damage.
     */
    public void hide() {
        isHidden = true;
    }

    /**
     * Calculates the amount of hurt taken from an attack.
     * If the student is hidden, they take no damage.
     * Otherwise, reduces the energy level by the attack amount.
     *
     * @param attackAmount the amount of attack received
     * @return the actual amount of energy reduced from the attack
     */
    @Override
    public int hurt(int attackAmount) {
        if (isHidden) {
            isHidden = false;
            return 0;
        } else {
            return super.hurt(attackAmount);
        }
    }
}