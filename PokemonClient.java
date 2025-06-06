package pokemon;
import java.util.Arrays;
import java.util.List;

/**
 * Demo application showcasing the Pokemon battle system.
 * Creates sample Pokemon, trainers, and executes battles to demonstrate functionality.
 */
public class PokemonClient 
{
    public static void main(String[] args) 
    {
        try {
            // Create moves with different types and properties
            Move thunderbolt = new Move("Thunderbolt", "Electric", 90, 100, true);
            Move ironTail = new Move("Iron Tail", "Steel", 100, 75, false);
            Move flamethrower = new Move("Flamethrower", "Fire", 90, 100, true);
            Move dragonClaw = new Move("Dragon Claw", "Dragon", 80, 100, false);
            Move hydroPump = new Move("Hydro Pump", "Water", 110, 80, true);
            Move surf = new Move("Surf", "Water", 90, 100, true);

            // Create Pokemon with move sets
            Pokemon pikachu = new Pokemon("Pikachu", "Electric", 100, 
                Arrays.asList(thunderbolt, ironTail));
            
            Pokemon charizard = new Pokemon("Charizard", "Fire/Flying", 120, 
                Arrays.asList(flamethrower, dragonClaw));
            
            Pokemon blastoise = new Pokemon("Blastoise", "Water", 110, 
                Arrays.asList(hydroPump, surf));

            // Create trainers
            Trainer ash = new Trainer("Ash", Arrays.asList(pikachu, charizard));
            Trainer gary = new Trainer("Gary", Arrays.asList(blastoise));

            System.out.println("=== Pokemon Battle Simulator ===\n");

            // Display initial trainer information
            System.out.println("Trainer Information:");
            ash.displayStatus();
            System.out.println();
            gary.displayStatus();

            // Demonstrate single Pokemon battle
            System.out.println("\n=== Single Battle Demo ===");
            Battle singleBattle = new Battle();
            singleBattle.startBattle(pikachu, blastoise);

            // Demonstrate mega evolution
            System.out.println("\n=== Mega Evolution Demo ===");
            charizard.megaEvolve();
            
            // Demonstrate trainer battle
            System.out.println("\n=== Trainer Battle Demo ===");
            Battle trainerBattle = new Battle();
            singleBattle.startBattle(charizard, blastoise);

            // Display final trainer status
            System.out.println("\nFinal Trainer Status:");
            ash.displayStatus();
            System.out.println();
            gary.displayStatus();

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
