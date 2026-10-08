# Hangman Escape Room — Java

**Hangman Escape Room** is a console-based Java game where players must guess a hidden word to escape an imaginary escape room. Players can choose a topic and difficulty level, with different guess limits for each difficulty.

## Features

* Topic-based word selection
* Easy, Medium, and Hard difficulty levels
* Random word selection from `words.txt`
* Interactive letter guessing
* Hangman ASCII-art display
* Input validation and exception handling
* Win/lose conditions
* Modular object-oriented design
* Extensible word bank using external text data

## How It Works

```text
Start Game
    ↓
Select Topic
    ↓
Select Difficulty
    ↓
Random Word Selected
    ↓
Guess Letters
    ↓
Update Word & Hangman
    ↓
Win / Lose
    ↓
Escape Room Result
```

## Difficulty Levels

| Level  | Maximum Incorrect Guesses |
| ------ | ------------------------: |
| Easy   |                         9 |
| Medium |                         7 |
| Hard   |                         5 |

## Topics

The current word bank includes:

* Animals
* Indian Cities
* Months
* Days

More topics and words can be added directly to `words.txt`.

## Java Concepts Used

* **Object-Oriented Programming** — classes, inheritance, abstraction, encapsulation, polymorphism
* **Collections** — `HashMap`, `HashSet`, `List`
* **Arrays & Strings** — word tracking and character processing
* **Recursion** — win-condition checking
* **Enums** — difficulty management
* **Exception Handling** — invalid input and file errors
* **File I/O** — loading words from `words.txt`
* **Generics** — type-safe collections

## Project Structure

```text
HANGMAN-ESCAPE-ROOM/
├── GameController.java
├── HangmanGame.java
├── WordBank.java
├── words.txt
└── README.md
```

## Getting Started

### Prerequisites

* Java JDK 8 or later
* Any Java-compatible IDE or terminal

### Run

```bash
javac *.java
java GameController
```

Make sure `words.txt` is in the same directory when running the program.

## Future Development

This is an **ongoing project**, and I plan to continue improving it in the future by adding:

* More topics and larger word banks
* Score and leaderboard system
* Multiple rounds
* Hints and bonus features
* Improved game interface
* Additional game modes
* GUI version of the game

## Project Status

**Prototype — Ongoing Development**

More features and improvements will be added as the project evolves.

## Author

**Saranya Aluru**
B.Tech — Artificial Intelligence and Data Science
