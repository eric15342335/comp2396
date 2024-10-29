package assignment2;

/**
 * The SecurityGuard is well-funded by the department and must use a SuperGun.
 * Only the guard knows how to boost the SuperGun.
 * It is a subclass of the Character class and has the ability to boost a SuperGun.
 */
public class SecurityGuard extends Character {

    /**
     * Constructs a new SecurityGuard with the specified name, energy level, and skill level.
     *
     * @param name        the name of the security guard
     * @param energyLevel the initial energy level of the security guard
     * @param skillLevel  the skill level of the security guard
     */
    public SecurityGuard(String name, int energyLevel, int skillLevel) {
        super(name, energyLevel, skillLevel);
    }

    /**
     * Boosts the specified SuperGun for the next attack.
     *
     * @param w1 the SuperGun to be boosted
     */
    public void boostWeapon(SuperGun w1) {
        w1.boost();
    }
}