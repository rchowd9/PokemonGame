package pokemon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents a Pokemon Trainer who can manage a team of Pokemon and participate in battles.
 * Implements team management functionality and basic trainer statistics.
 */
public class Trainer 
{
    public static final int MAX_TEAM_SIZE = 6;
    
    private final String name;
    private final List<Pokemon> pokemonTeam;
    private int badges;
    private int wins;
    private int losses;

    /**
     * Creates a new Trainer with the specified name and team.
     *
     * @param name The trainer's name
     * @param team Initial team of Pokemon
     * @throws IllegalArgumentException if team size exceeds MAX_TEAM_SIZE or name is invalid
     */
    public Trainer(String name, List<Pokemon> team) 
    {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Trainer name cannot be null or empty");
        }
        if (team == null) {
            throw new IllegalArgumentException("Pokemon team cannot be null");
        }
        if (team.size() > MAX_TEAM_SIZE) {
            throw new IllegalArgumentException("Team cannot exceed " + MAX_TEAM_SIZE + " Pokemon");
        }

        this.name = name;
        this.pokemonTeam = new ArrayList<>(team); // Create defensive copy
        this.badges = 0;
        this.wins = 0;
        this.losses = 0;
    }

    /**
     * Returns an unmodifiable view of the trainer's Pokemon team.
     */
    public List<Pokemon> getPokemonTeam() 
    {
        return Collections.unmodifiableList(pokemonTeam);
    }

    public String getName() 
    {
        return name;
    }

    /**
     * Attempts to add a Pokemon to the team.
     *
     * @param pokemon The Pokemon to add
     * @return true if successful, false if team is full
     */
    public boolean addPokemon(Pokemon pokemon) 
    {
        if (pokemon == null) {
            throw new IllegalArgumentException("Cannot add null Pokemon");
        }
        if (pokemonTeam.size() >= MAX_TEAM_SIZE) {
            return false;
        }
        return pokemonTeam.add(pokemon);
    }

    /**
     * Removes a Pokemon from the team by its position.
     *
     * @param index The position of the Pokemon to remove (0-based)
     * @return The removed Pokemon
     * @throws IllegalArgumentException if index is invalid
     */
    public Pokemon removePokemon(int index) 
    {
        if (index < 0 || index >= pokemonTeam.size()) {
            throw new IllegalArgumentException("Invalid team position: " + index);
        }
        return pokemonTeam.remove(index);
    }

    /**
     * Gets the first Pokemon in the team that is still alive.
     *
     * @return Optional containing the first available Pokemon, or empty if none are available
     */
    public Optional<Pokemon> getFirstAvailablePokemon() 
    {
        return pokemonTeam.stream()
                         .filter(Pokemon::isAlive)
                         .findFirst();
    }

    /**
     * Checks if the trainer has any Pokemon able to battle.
     */
    public boolean hasAvailablePokemon() 
    {
        return pokemonTeam.stream().anyMatch(Pokemon::isAlive);
    }

    /**
     * Records a win for this trainer and awards a badge.
     */
    public void recordWin() 
    {
        wins++;
        badges++;
    }

    /**
     * Records a loss for this trainer.
     */
    public void recordLoss() 
    {
        losses++;
    }

    /**
     * Displays detailed information about the trainer and their team.
     */
    public void displayStatus() 
    {
        System.out.println("Trainer: " + name);
        System.out.println("Badges: " + badges);
        System.out.println("Record: " + wins + "W/" + losses + "L");
        System.out.println("\nTeam:");
        for (int i = 0; i < pokemonTeam.size(); i++) 
        {
            System.out.print((i + 1) + ". ");
            pokemonTeam.get(i).displayStatus();
        }
    }

    @Override
    public String toString() 
    {
        return String.format("Trainer %s (Badges: %d, Record: %d-%d)", 
            name, badges, wins, losses);
    }
}
