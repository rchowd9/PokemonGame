package pokemon;
import java.util.List;
import java.util.Random;

/**
 * Manages Pokemon battles between trainers or individual Pokemon.
 * Implements battle mechanics including type effectiveness and turn-based combat.
 */
public class Battle 
{
	private static final Random random = new Random();
	private static final double TYPE_ADVANTAGE_MULTIPLIER = 1.5;
	private static final double TYPE_DISADVANTAGE_MULTIPLIER = 0.5;

	/**
	 * Initiates a battle between two Pokemon.
	 *
	 * @param p1 First Pokemon
	 * @param p2 Second Pokemon
	 * @throws IllegalArgumentException if either Pokemon is null or fainted
	 */
	public void startBattle(Pokemon p1, Pokemon p2) 
	{
		validatePokemon(p1, p2);
		System.out.println("\nBattle starts between " + p1.getName() + " and " + p2.getName() + "!");
		
		// Display initial status
		displayBattleStatus(p1, p2);

		while (p1.isAlive() && p2.isAlive()) {
			executeTurn(p1, p2);
			if (p2.isAlive()) {
				executeTurn(p2, p1);
			}
			// Display status after each round
			if (p1.isAlive() && p2.isAlive()) {
				displayBattleStatus(p1, p2);
			}
		}

		announceWinner(p1, p2);
	}

	/**
	 * Initiates a battle between two trainers.
	 *
	 * @param trainer1 First trainer
	 * @param trainer2 Second trainer
	 * @throws IllegalArgumentException if either trainer is null or has no available Pokemon
	 */
	public void startTrainerBattle(Trainer trainer1, Trainer trainer2) 
	{
		if (trainer1 == null || trainer2 == null) {
			throw new IllegalArgumentException("Trainers cannot be null");
		}
		if (!trainer1.hasAvailablePokemon() || !trainer2.hasAvailablePokemon()) {
			throw new IllegalArgumentException("Both trainers must have at least one available Pokemon");
		}

		System.out.println("\nTrainer Battle: " + trainer1.getName() + " VS " + trainer2.getName());
		
		while (trainer1.hasAvailablePokemon() && trainer2.hasAvailablePokemon()) {
			Pokemon p1 = trainer1.getFirstAvailablePokemon().get();
			Pokemon p2 = trainer2.getFirstAvailablePokemon().get();
			startBattle(p1, p2);
		}

		// Determine winner and update trainer records
		if (trainer1.hasAvailablePokemon()) {
			System.out.println("\n" + trainer1.getName() + " wins the trainer battle!");
			trainer1.recordWin();
			trainer2.recordLoss();
		} else {
			System.out.println("\n" + trainer2.getName() + " wins the trainer battle!");
			trainer2.recordWin();
			trainer1.recordLoss();
		}
	}

	private void validatePokemon(Pokemon p1, Pokemon p2) {
		if (p1 == null || p2 == null) {
			throw new IllegalArgumentException("Pokemon cannot be null");
		}
		if (!p1.isAlive() || !p2.isAlive()) {
			throw new IllegalArgumentException("Both Pokemon must be alive to battle");
		}
	}

	private void displayBattleStatus(Pokemon p1, Pokemon p2) {
		System.out.println("\nCurrent Battle Status:");
		p1.displayStatus();
		p2.displayStatus();
		System.out.println();
	}

	private void executeTurn(Pokemon attacker, Pokemon defender) 
	{
		// Select move (currently using first available, could be randomized or AI-driven)
		Move selectedMove = selectMove(attacker.getMoveList());
		System.out.println("\n" + attacker.getName() + " uses " + selectedMove.getName() + "!");
		
		// Check if move hits
		if (random.nextDouble() * 100 < 90) { // 90% base accuracy
			// Calculate and apply damage
			int damage = calculateDamage(selectedMove, attacker, defender);
			defender.takeDamage(damage);
			
			// Show effectiveness message
			double effectiveness = getTypeEffectiveness(selectedMove.getType(), defender.getType());
			if (effectiveness > 1.0) {
				System.out.println("It's super effective!");
			} else if (effectiveness < 1.0) {
				System.out.println("It's not very effective...");
			}
		} else {
			System.out.println("But it missed!");
		}
	}

	private Move selectMove(List<Move> moves) {
		// For now, randomly select a move
		return moves.get(random.nextInt(moves.size()));
	}

	private int calculateDamage(Move move, Pokemon attacker, Pokemon defender) 
	{
		// Base damage calculation
		double damage = move.getPower();
		
		// Apply type effectiveness
		damage *= getTypeEffectiveness(move.getType(), defender.getType());
		
		// Add random variation (85-100% of calculated damage)
		damage *= (0.85 + random.nextDouble() * 0.15);
		
		return (int) Math.round(damage);
	}

	private double getTypeEffectiveness(String moveType, String defenderType) 
	{
		// Simplified type effectiveness system
		if (isTypeAdvantage(moveType, defenderType)) {
			return TYPE_ADVANTAGE_MULTIPLIER;
		} else if (isTypeDisadvantage(moveType, defenderType)) {
			return TYPE_DISADVANTAGE_MULTIPLIER;
		}
		return 1.0;
	}

	private boolean isTypeAdvantage(String moveType, String defenderType) 
	{
		// Basic type advantages (could be expanded)
		return (moveType.equals("Water") && defenderType.equals("Fire")) ||
			   (moveType.equals("Fire") && defenderType.equals("Grass")) ||
			   (moveType.equals("Grass") && defenderType.equals("Water")) ||
			   (moveType.equals("Electric") && defenderType.contains("Flying"));
	}

	private boolean isTypeDisadvantage(String moveType, String defenderType) 
	{
		// Basic type disadvantages (could be expanded)
		return (moveType.equals("Fire") && defenderType.equals("Water")) ||
			   (moveType.equals("Water") && defenderType.equals("Grass")) ||
			   (moveType.equals("Grass") && defenderType.equals("Fire"));
	}

	private void announceWinner(Pokemon p1, Pokemon p2) 
	{
		System.out.println("\nBattle Result:");
		if (p1.isAlive()) {
			System.out.println(p1.getName() + " wins the battle!");
		} else {
			System.out.println(p2.getName() + " wins the battle!");
		}
	}
}
