# TicTacToe Example

A classic TicTacToe game demonstrating the JMiniApp framework.

## Overview

This example shows how to create an interactive game using JMiniApp core. Players can play TicTacToe in Player vs Player or Player vs CPU modes, with full game state persistence.

## Features

- **Player vs Player**: Two players take turns on the same device
- **Player vs CPU**: Play against a simple AI opponent
- **Continue/Pause Gameplay**: After each move (or after both players in vs CPU), choose to continue or pause
- **Save & Exit**: Option to save your game before exiting mid-match
- **Load Game**: Resume a saved game from the main menu
- **Reset Game**: Start a new game at any time
- **Persistent State**: Game board and current player are maintained

## Project Structure

```
ticTacToe/
├── pom.xml
├── README.md
└── src/main/java/com/jminiapp/examples/tictactoe/
    ├── TicTacToeApp.java          # Main application class
    ├── TicTacToeAppRunner.java    # Bootstrap configuration
    ├── TicTacToeState.java        # Game model
    └── TicTacToeJSONAdapter.java  # JSON format adapter
```

## Key Components

### TicTacToeState
The game model class that manages:
- 9-cell game board (char array)
- Current player (X or O)
- Game over status
- Winner detection (including draws)
- Move validation and execution

### TicTacToeJSONAdapter
A format adapter that enables JSON import/export for `TicTacToeState`:
- Implements `JSONAdapter<TicTacToeState>` from the framework
- Registers with the framework during app bootstrap
- Provides automatic serialization/deserialization

### TicTacToeApp
The main application class that extends `JMiniApp` and implements:
- `initialize()`: Set up the app and load existing game state
- `run()`: Main loop displaying board/menu and handling user input
- `shutdown()`: Save the game state before exiting
- Uses framework's `context.importData()` and `context.exportData()` for file operations

### TicTacToeAppRunner
Bootstrap configuration that:
- Registers the `TicTacToeJSONAdapter` with `.withAdapters()`
- Configures the app name and model class
- Launches the application

## Building and Running

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Build the project

From the **project root** (not the examples/ticTacToe directory):
```bash
mvn clean install
```

This will build both the jminiapp-core module and the ticTacToe example.

### Run the application

**Option 1: Using Maven exec plugin (from the examples/ticTacToe directory)**
```bash
cd examples/ticTacToe
mvn exec:java
```

**Option 2: Using the packaged JAR (from the examples/ticTacToe directory)**
```bash
cd examples/ticTacToe
java -jar target/tictactoe-app.jar
```

**Option 3: From the project root**
```bash
cd examples/ticTacToe && mvn exec:java
```

### How Save/Load Works

The game supports exporting and importing game state to/from JSON files with custom filenames:

- **Export**: Choose "Save Game (Export JSON)" from the main menu
  - You'll be prompted to enter a filename (e.g., `my-tictactoe.json`)
  - The `.json` extension is automatically added if not provided
  - The file will be saved in the `src/main/resources/` directory
  - Success message confirms the save: `TicTacToe state exported successfully to: my-tictactoe.json`

- **Import**: Choose "Load Game (Import JSON)" from the main menu
  - You'll be prompted to enter the filename to load (e.g., `my-tictactoe.json`)
  - The `.json` extension is automatically added if not provided
  - The game state will be restored from `src/main/resources/` directory
  - The board will be displayed after successful import

The saved JSON file format:
```json
[
  {
    "board": ["O", " ", " ", " ", "X", " ", " ", " ", " "],
    "currentPlayer": "O",
    "gameOver": false,
    "winner": null
  }
]
```

**Example Usage:**

```
Choose an option: 4
Enter filename to export (e.g., tictactoe.json): my-tictactoe.json
TicTacToe state exported successfully to: my-tictactoe.json
```

```
Choose an option: 5
Enter filename to import (e.g., tictactoe.json): my-tictactoe.json
TicTacToe state imported successfully!
```

**Note:** Files are stored in `src/main/resources/` relative to the project. You can find your exported files in `examples/ticTacToe/src/main/resources/`

## Author

**Eduardo Matos**
