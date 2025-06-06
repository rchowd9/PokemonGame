package pokemon;

/**
 * Represents a Pokemon's move/attack with associated properties.
 * Each move has a name, type, and power value that determines its effectiveness in battle.
 */
public class Move 
{
	// Constants for move balance
	public static final int MAX_POWER = 100;
	public static final int MIN_POWER = 0;
	
	private final String name;
	private final String type;
	private final int power;
	private final int accuracy; // Percentage chance to hit (1-100)
	private final boolean isSpecial; // Special or Physical move

	/**
	 * Creates a new Move with the specified properties.
	 *
	 * @param name The name of the move
	 * @param type The elemental type of the move
	 * @param power The base power of the move
	 * @param accuracy The accuracy percentage (1-100)
	 * @param isSpecial Whether the move is special (true) or physical (false)
	 * @throws IllegalArgumentException if power or accuracy are out of valid ranges
	 */
	public Move(String name, String type, int power, int accuracy, boolean isSpecial) 
	{
		if (power < MIN_POWER || power > MAX_POWER) {
			throw new IllegalArgumentException("Power must be between " + MIN_POWER + " and " + MAX_POWER);
		}
		if (accuracy < 1 || accuracy > 100) {
			throw new IllegalArgumentException("Accuracy must be between 1 and 100");
		}
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Move name cannot be null or empty");
		}
		if (type == null || type.trim().isEmpty()) {
			throw new IllegalArgumentException("Move type cannot be null or empty");
		}

		this.name = name;
		this.type = type;
		this.power = power;
		this.accuracy = accuracy;
		this.isSpecial = isSpecial;
	}

	/**
	 * Convenience constructor for moves with 100% accuracy.
	 */
	public Move(String name, String type, int power, boolean isSpecial) 
	{
		this(name, type, power, 100, isSpecial);
	}

	/**
	 * Legacy constructor for backward compatibility.
	 */
	public Move(String name, String type, int power) 
	{
		this(name, type, power, 100, false);
	}

	public String getName() 
	{
		return name;
	}

	public String getType() 
	{
		return type;
	}

	public int getPower() 
	{
		return power;
	}

	public int getAccuracy() 
	{
		return accuracy;
	}

	public boolean isSpecial() 
	{
		return isSpecial;
	}

	/**
	 * Determines if the move hits based on its accuracy.
	 *
	 * @return true if the move hits, false if it misses
	 */
	public boolean doesHit() 
	{
		return Math.random() * 100 < accuracy;
	}

	@Override
	public String toString() 
	{
		return String.format("%s (%s) - Power: %d, Accuracy: %d%%, %s", 
			name, type, power, accuracy, isSpecial ? "Special" : "Physical");
	}
}
