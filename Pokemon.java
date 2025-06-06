package pokemon;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a Pokemon entity in the game with battle capabilities and evolution mechanics.
 * This class implements core Pokemon features including health management, move sets,
 * and mega evolution mechanics.
 */
public class Pokemon 
{
	// Constants for game balance
	private static final int MEGA_EVOLUTION_HEALTH_BOOST = 50;
	
	private final String name;
	private final String type;
	private int health;
	private final int maxHealth;
	private boolean isMegaEvolved;
	private final List<Move> moveList;
	
	/**
	 * Constructs a new Pokemon with specified attributes.
	 *
	 * @param name The Pokemon's name
	 * @param type The Pokemon's elemental type
	 * @param health The Pokemon's initial and maximum health points
	 * @param moves List of moves this Pokemon can use
	 * @throws IllegalArgumentException if health is negative or moves list is empty
	 */
	public Pokemon(String name, String type, int health, List<Move> moves)
	{
		if (health < 0) {
			throw new IllegalArgumentException("Health cannot be negative");
		}
		if (moves == null || moves.isEmpty()) {
			throw new IllegalArgumentException("Pokemon must have at least one move");
		}
		
		this.name = name;
		this.type = type;
		this.health = health;
		this.maxHealth = health;
		this.isMegaEvolved = false;
		this.moveList = new ArrayList<>(moves); // Create defensive copy
	}
	
	/**
	 * Triggers mega evolution for this Pokemon if not already evolved.
	 * Increases health by a fixed amount and marks as mega evolved.
	 *
	 * @return true if evolution was successful, false if already mega evolved
	 */
	public boolean megaEvolve()
	{
		if(!isMegaEvolved)
		{
			this.isMegaEvolved = true;
			this.health += MEGA_EVOLUTION_HEALTH_BOOST;
			System.out.println(name + " has mega evolved!");
			return true;
		}
		return false;
	}
	
	/**
	 * Applies damage to this Pokemon, ensuring health doesn't go below 0.
	 *
	 * @param damage Amount of damage to apply
	 */
	public void takeDamage(int damage) {
		if (damage < 0) {
			throw new IllegalArgumentException("Damage cannot be negative");
		}
		this.health = Math.max(0, this.health - damage);
		System.out.println(name + " takes " + damage + " damage!");
	}

	/**
	 * Checks if the Pokemon has remaining health points.
	 *
	 * @return true if health is greater than 0, false otherwise
	 */
	public boolean isAlive() {
		return this.health > 0;
	}

	/**
	 * Returns an unmodifiable view of this Pokemon's move list.
	 *
	 * @return Unmodifiable List of moves
	 */
	public List<Move> getMoveList() 
	{
		return Collections.unmodifiableList(moveList);
	}

	/**
	 * @return The Pokemon's name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * Displays the current status of the Pokemon including health and type.
	 */
	public void displayStatus() 
	{
		System.out.println(name + " - HP: " + health + "/" + maxHealth + 
						 " | Type: " + type + 
						 (isMegaEvolved ? " | MEGA" : ""));
	}
	
	/**
	 * @return The Pokemon's current health points
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * @return The Pokemon's type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @return true if Pokemon is mega evolved, false otherwise
	 */
	public boolean isMegaEvolved() {
		return isMegaEvolved;
	}
}
