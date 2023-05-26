# Pacmam game in Java
## Project from [IJA](https://www.fit.vut.cz/study/course/IJA/.en) university course 2022/23
----------------------------
----------------------------

### Authors
- **Matej Vadovič** - xvadov01 - vedúci tímu
- **Alina Vinogradova** - xvinog00
- [Matej Vadovič](https://github.com/Matej-V) - xvadov01 - team leader,
- [Alina Vinogradova](https://github.com/jsemaljaa) - xvinog00,

## Technologies used

The project was implemented using `JavaFX` and uses the `Maven` build system.

## Project compilation

Compiles the source files, generates the javadoc program documentation in `/doc/javadoc` and creates `jar` in the `target` folder.
```
mvn compile
```

## Project launch

To run the project you need to have JavaFX installed. The project is started from the project folder using the command:
```
mvn javafx:run
```

## Start by using the .jar archive

To start, you need to specify the path to the `JavaFX` modules:
```
java --module-path openjfx-20.0.1_windows-x64_bin-sdk\javafx-sdk-20.0.1\lib --add-modules javafx.fxml,javafx.controls -jar target\PacManApp.jar
```
where `openjfx-20.0.1_windows-x64_bin-sdk\javafx-sdk-20.0.1\lib` should be replaced with the path to the `JavaFX` modules.

## Generating doxygen documentation

To generate it, just run the `doc` command in the `doc` folder:
```
doxygen Doxyfile
```

## Introduction

The PacMan project is a single player game implemented in Java using `JavaFX`. 
The player controls the Pacman character using arrow keys, W,S,A,D keys and mouse (by clicking on the box) and his goal is to collect all the keys in the maze and get as many points as possible.
The ghosts are moving around the maze and Pacman is trying to avoid them. The ghosts from time to time become "eatable", which means Pacman can eat them and gets 100 extra points. 
If a player is caught three times, he loses the game. 
When the game is finished, the final score is displayed and the player has the option to start a new game. 
We have also implemented the ability to replay the last game using the R key and rewind using the B key. 
The game allows for interruptions using the P key.

Another interactive element of the game is the player's ability to influence the map. 
This can be done in-game by pressing the E key, which will place a bomb in the game.
This bomb, when the timer expires, will change the wall boxes and path boxes. The player can use 3 bombs in one game.

We've also added the ability to record the best scores of the players who played the game.

All actions can also be performed from the Menu menu at the top of the screen.

## Techniques used
In the implementation of the project, we used the Model-View-Controller (MVC) architectural pattern and the Observer design pattern. 
We used MVC to separate the GUI from the game logic and model. We used the Observer design pattern to provide communication between the model and the GUI.

## Packages and classes

The PacMan project consists of several packages and classes:

### Package `common`

The `common` package contains the interfaces that are used throughout the project. 
It contains the `MazeObject` interface, which defines methods for objects in the maze, the `Maze` interface, which defines methods for the maze, the `Field` interface, which defines methods for individual fields in the maze, and the `Observable` interface, which declares methods for the observers and the observed.

### Package `game`

The `game` package contains classes that implement the functionality of the game model. It contains the `GhostObject`, `KeyObject`, `MazeClass`, `MazeConfigure`, `PacmanObject`, `BombObject`, `PathField`, `TargetField` and `WallField` classes. 
The MazeClass class represents the maze itself and implements the Maze interface. The PacmanObject, GhostObject, KeyObject, and BombObject classes represent maze objects, and implement the MazeObject interface. 
The PathField, TargetField, and WallField classes represent different types of fields in the maze and implement the Field interface. 
This package also includes a `GameException` class, which serves as a flag that the player has won or lost the game.

### Package `pacman_project`

The `pacman_project` package contains the `PacManApp` main class, which is an extension of the `javafx.application.Application` class and initializes the graphical interface for the game. 
The `PacManController` class manages the movements of changing maze objects, loading the game, and controlling the zoning of the application environment. 
The `PacManView` class controls what is displayed in the application, and the `LogWriter` class is used to write logs to a file.

### Package `view`
The `view` package contains the classes that represent the graphical elements of the game, namely `FieldView`, `GhostObjectView`, `KeyObjectView`, `PacmanObjectView` and `BombObjectView`. 
These classes extend the `javafx.scene.layout.Pane` class and implement the `Observer` interface. 
When the state of the model changes, these classes react and update the view on the screen. It also contains the `UIBarView` class, which represents the part of the UI that displays pacman scores and lives.

## Future plans

In the future, we plan to improve the graphics and complete the proper playback functionality associated with the bomb checkout, since it doesn't work that well.
