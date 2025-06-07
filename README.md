# Pokémon Battle Simulator

## Project Overview
This is a console-based, turn-by-turn Pokémon battle game developed in **Java**. It simulates the core combat mechanics of the Pokémon franchise, allowing players to engage in strategic battles between individual Pokémon or full trainer teams.

## Main Game Features
* **Turn-Based Combat:** Players engage in sequential battles, selecting moves and reacting to opponent actions.
* **Damage Calculation:** Implements a battle algorithm that calculates damage based on move power, type effectiveness (e.g., Water vs. Fire), and random variation.
* **Mega Evolution:** Features the ability for certain Pokémon to Mega Evolve during battle, providing dynamic health boosts.
* **Object-Oriented Design (OOP):** Built with a modular OOP structure, defining clear `Pokemon`, `Move`, `Trainer`, and `Battle` classes to manage game entities and logic.
* **Console Interface:** Provides real-time battle feedback and status updates directly in the terminal.

## Project Structure
This project is organized into a `pokemon` package containing several core Java classes:

* `Battle.java`: Manages the overall battle flow between two Pokémon or two trainers. It orchestrates turns, applies damage, and determines winners.
* `Pokemon.java`: Represents individual Pokémon, handling their attributes (name, type, health), managing their moves, and implementing Mega Evolution mechanics.
* `Move.java`: Defines the properties of a Pokémon's attack, including its name, type, power, and accuracy.
* `Trainer.java`: Represents a Pokémon trainer, allowing them to manage a team of Pokémon, track battle records, and participate in trainer battles.
* `PokemonClient.java`: Contains the `main` method and serves as the demo application. It sets up sample Pokémon and trainers, then initiates and showcases various battle scenarios.

## Technologies Used
* **Java** – Core programming language.
* **Object-Oriented Programming (OOP)** – Employed for modular design and encapsulation of game entities.
* **Standard Library Collections** – Utilizes `java.util.List` for managing Pokémon teams and move sets.
* **Basic Algorithms** – For damage calculation, type effectiveness, and random elements.
* **Command Line Interface (CLI)** – For interactive gameplay and displaying battle progress.

## How to Run
1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/rchowd9/PokemonBattleSimulator.git](https://github.com/rchowd9/PokemonBattleSimulator.git)
    cd PokemonBattleSimulator
    ```
    *(Note: Adjust the repository name if it's different on your GitHub.)*

2.  **Compile the Java files:**
    Ensure you are in the `PokemonBattleSimulator` directory (or the parent directory containing the `pokemon` folder).
    ```bash
    javac pokemon/*.java
    ```

3.  **Run the game:**
    ```bash
    java pokemon.PokemonClient
    ```
