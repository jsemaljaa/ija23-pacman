package ija.project.pacman_project;

import ija.project.common.MazeObject;
import ija.project.common.Observable;
import ija.project.view.FieldView;
import ija.project.view.UIBarView;
import javafx.geometry.Pos;
import javafx.scene.Group;

import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class PacManView implements Observable {
    private final PacManModel model;
    private PacManController controller;
    public Pane currentScene; // currentScreen
    private final Set<Observable.Observer> observers = new HashSet<>();
    private double widthOfScreen;
    private double heightOfScreen;
    public StackPane gameBox; // UI + maze

    public PacManView(PacManModel model, double width, double height) {
        this.model = model;
        this.widthOfScreen = width;
        this.heightOfScreen = height;
    }

    public void setController(PacManController controller) {
        this.controller = controller;
    }

    /**
     * Generates maze representation using Pane objects
     * @return Group od Pane objects that represents maze
     */
    public Group drawMaze() {
        Group mazeGroup = new Group();
        /*
         * Now all the fields are generated here as FieldView objects and added into
         * mazeTile
         */
        for (int row = 0; row < model.maze.numRows(); row++) {
            for (int column = 0; column < model.maze.numCols(); column++) {
                FieldView fieldView = new FieldView(model.maze.getField(row, column), (Math.min(widthOfScreen, heightOfScreen) - 100) / model.maze.numCols(), row, column);
                mazeGroup.getChildren().add(fieldView);
            }
        }
        return mazeGroup;
    }


    public Group drawUI() {
        UIBarView UIBar = new UIBarView(this.model.maze.getPacMan());
        return new Group(UIBar);
    }

    public void generateMainScreen() {
        StackPane pane = new StackPane(drawBackgroundImage("./src/main/resources/ija/project/pacman_project/img/title.jpg"), drawButton("START THE GAME"));
        pane.setAlignment(Pos.CENTER);
        this.currentScene = pane;
    }


    public void generateGame() {
        System.out.println(currentScene);
        // Create Menu
        Menu menu = new Menu("Menu");
        MenuItem restartGame = new MenuItem("Restart game");

        // Create MenuBar
        MenuBar menuBar = new MenuBar(menu);
        menu.getItems().addAll(restartGame);
        restartGame.setOnAction(e -> {
            System.out.println("Restarting game");
            controller.newGame();
        });

        VBox uiMazeBox = new VBox();
        HBox mazeHolder = new HBox(drawMaze());
        mazeHolder.alignmentProperty().set(Pos.CENTER);
        HBox statsHolder = new HBox(drawUI());
        statsHolder.alignmentProperty().set(Pos.CENTER);
        statsHolder.setStyle("-fx-background-color: #FFFFFF;");
        uiMazeBox.getChildren().addAll(statsHolder, mazeHolder);
        //Stack Pane to let us add pause screen over game screen
        StackPane temp = new StackPane(uiMazeBox);
        temp.setAlignment(Pos.CENTER);
        this.gameBox = temp;
        // Set background image
        StackPane pane = new StackPane(drawBackgroundImage("./src/main/resources/ija/project/pacman_project/img/title.jpg"), new VBox(menuBar, gameBox));
        pane.setAlignment(Pos.CENTER);
        this.currentScene = pane;
        System.out.println(currentScene);
        notifyObservers();
    }

    public void generateEndScreen() {
        System.out.println("Generating end screen");
        //Score
        Text score = new Text("Total score: " + model.maze.getPacMan().getScore());
        score.setStyle("-fx-font-size: 20px; -fx-fill: #FFFFFF");
        score.setTranslateY(-100);
        StackPane pane = new StackPane(drawBackgroundImage("./src/main/resources/ija/project/pacman_project/img/game-over.jpg"), drawButton("PLAY AGAIN"), score);
        pane.setAlignment(Pos.CENTER);
        this.currentScene = pane;
        notifyObservers();
    }

    public void generateSuccessScreen(){
        System.out.println("Generating success screen");
        //Score
        Text score = new Text("Total score: " + model.maze.getPacMan().getScore());
        score.setStyle("-fx-font-size: 20px; -fx-fill: #FFFFFF");
        score.setTranslateY(-50);
        StackPane pane = new StackPane(drawBackgroundImage("./src/main/resources/ija/project/pacman_project/img/title.jpg"), drawButton("PLAY AGAIN"), score);
        // add win text
        Text text = new Text("YOU WON");
        text.setStyle("-fx-font-size: 50px; -fx-font-weight: bold; -fx-fill: white;");
        text.setTranslateY(-150);
        pane.getChildren().add(text);
        // Load button
        pane.setAlignment(Pos.CENTER);
        this.currentScene = pane;
        notifyObservers();
    }



    private ImageView drawBackgroundImage(String url){
        ImageView backgroundImage = new ImageView();
        try {
            backgroundImage.setImage(new Image(new FileInputStream(url)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        backgroundImage.setX(0);
        backgroundImage.setY(0);
        backgroundImage.setFitWidth(widthOfScreen);
        backgroundImage.setFitHeight(heightOfScreen);
        Blend blend = new Blend();
        blend.setMode(BlendMode.MULTIPLY);
        backgroundImage.setEffect(blend);
        return backgroundImage;
    }

    private Button drawButton(String text){
        Button button = new Button();
        button.setText(text);
        button.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #00022A; -fx-border-radius: 20%; -fx-font-size: 16px");
        button.setOnAction(e -> {
            System.out.println(text + " button clicked");
            controller.newGame();
        });
        button.requestFocus();
        button.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                button.fire();      // Simulate button action
                event.consume();    // Consume the event to prevent further processing
            }
        });
        return button;
    }

    public void addObserver(Observable.Observer o) {
        this.observers.add(o);
    }

    public void removeObserver(Observable.Observer o) {
        this.observers.remove(o);
    }

    public void notifyObservers() {
        this.observers.forEach((o) -> o.update(this));
    }

    // Unused
    @Override
    public void addLogObserver(Observer var1) {}
    @Override
    public void removeLogObserver(Observer var1) {}
    @Override
    public void notifyLogObservers(MazeObject o) {}
}