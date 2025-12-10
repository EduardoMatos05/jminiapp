---
sidebar_position: 1
---

# TicTacToe Example Application

A fully interactive TicTacToe game built using the **JMiniApp** framework.  
This example demonstrates lifecycle management, persistent state handling, JSON import/export, and menu-driven user interaction.

This tutorial walks you step-by-step so any reader can build, run, understand, and extend the TicTacToe application.

---

## Overview

The TicTacToe example shows how to build a complete interactive game with JMiniApp core.  
It includes support for saving and resuming matches, switching game modes, printing game boards, and tracking winner/draw conditions.

---

## Features

- **Player vs Player Mode**  
  Two players alternate turns on the same device.

- **Player vs CPU Mode**  
  The CPU performs simple automatic moves.

- **Game Persistence**  
  The board layout, current player, and game status can be saved and loaded.

- **Save & Exit**  
  Stop mid-game and save your progress.

- **Load Previous Game**  
  Resume a saved match from the main menu.

- **Reset Game**  
  Clear progress and start a new match at any time.

- **Interactive Menu**  
  Clean, prompt-based navigation for all features.

---

# Step-by-Step Tutorial

This section explains the structure and behavior of the TicTacToe application so readers can easily understand or recreate the example.

### 1. Project Structure

The example is organized into a dedicated folder containing:
- A main application class controlling lifecycle and user flow
- A game state model storing board, players, and match status
- A JSON adapter enabling import/export functionality through the framework
- A bootstrap runner that registers the state and adapter and launches the app

### 2. Game State Responsibilities

The TicTacToe game tracks:
- A board of nine cells  
- Which player is currently active  
- Ongoing match status (in progress, won, or draw)  
- Winner detection logic  
- Validation of each move  

This state is automatically saved and restored by the JMiniApp framework through JSON.

### 3. JSON Persistence

The JSON adapter integrated with the application allows:
- Importing an existing game  
- Exporting the current state  
- Automatic storage in a JSON file whose name is based on the application configuration  

No filenames need to be typed by the user; the framework manages all file operations behind the scenes.

### 4. Application Flow

The application:
- Loads existing data (if any) when starting  
- Displays a main menu  
- Handles user selections  
- Executes gameplay loops  
- Saves the state before shutting down  

### 5. Bootstrap Configuration

The bootstrap class:
- Registers the TicTacToe state model  
- Registers the JSON adapter  
- Starts the application  

---

# Building and Running

### Build the project (from the root directory):

mvn clean install

graphql
Copiar código

This compiles both the JMiniApp core and the TicTacToe example.

### Run the TicTacToe app:

cd examples/ticTacToe
mvn exec:java

arduino
Copiar código

Alternatively, run the packaged JAR:

java -jar target/tictactoe-app.jar

yaml
Copiar código

---

# How to Play

When the application starts, you are presented with the main menu.  
From here, you can begin a new game, load a previous save, reset the board, or exit.

### Board Layout

The TicTacToe board uses cells numbered **1 through 9**, visually arranged as:

1 | 2 | 3
4 | 5 | 6
7 | 8 | 9

yaml
Copiar código

You choose a number to place your symbol (X or O) in that position.

---

## Player vs Player Mode

Two human players alternate turns.  
After each move, the game shows:
- The updated board  
- Whose turn is next  
- Whether the game has ended  

Players may choose to continue or pause, and they can save and exit mid-game.

---

## Player vs CPU Mode

In this mode:
- You play as **X**  
- The CPU plays as **O**  
- The CPU automatically selects an available position  

After each round (your move + CPU move), you may continue or pause the game.

---

## Game Over

When the board reaches a final state, the game prints:

- **Winner: X**  
- **Winner: O**  
- **Winner: Draw** (if no moves remain)  

You may then return to the menu or start a new game.

---

## Loading a Saved Game

From the main menu, selecting the load option restores:
- The board  
- The current player  
- The game-over flag  
- Any previously determined winner  

This allows you to continue exactly where you left off.

---

# How Import/Export Works

The TicTacToe application supports exporting and importing game state to/from JSON files with custom filenames:

## Export (Save Game)

From the main menu, select option **4. Save Game (Export JSON)**:
- You will be prompted: `Enter filename to export (e.g., tictactoe.json):`
- Type your desired filename, for example: `my-tictactoe.json`
- The application automatically adds the `.json` extension if not provided
- The file is saved in the current working directory
- You'll see a success message with the full path: `TicTacToe state exported successfully to: /path/to/my-tictactoe.json`

## Import (Load Game)

From the main menu, select option **5. Load Game (Import JSON)**:
- You will be prompted: `Enter filename to import (e.g., tictactoe.json):`
- Type the filename of the saved game, for example: `my-tictactoe.json`
- The application automatically adds the `.json` extension if not provided
- The game state is restored and the board is displayed
- You'll see a confirmation message: `TicTacToe state imported successfully!`

## Mid-Game Save

You can also save the game during play:
- After any move in Player vs Player or Player vs CPU mode
- When prompted `Continue playing? (y/n):`, type `n`
- You'll be asked `Save game before pausing? (y/n):`, type `y`
- Enter your custom filename when prompted
- The game returns to the main menu  

---

# Conclusion

The TicTacToe example showcases the core capabilities of the **JMiniApp** framework:

- Controlled application lifecycle  
- State-driven behavior  
- JSON-based persistence  
- Interactive menu applications  
- Compact and modular project organization  

With this foundation, you can extend the game, enhance the CPU logic, or build new applications following the same structure.

---

### Author  
**Eduardo Matos**