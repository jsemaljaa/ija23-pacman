package ija.project.pacman_project;

import ija.project.common.Field;
import ija.project.game.PathField;
import ija.project.game.TargetField;
import ija.project.game.WallField;
import javafx.scene.Group;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;

public class PacManView {
    private PacManController controller;
    private PacManModel model;
    private double cellWidth;
    private double cellHeight;
    private Group mazeGroup;

    public PacManView(PacManModel model, PacManController controller){
        this.controller = controller;
        this.model = model;
        cellWidth = 500 / this.model.maze.numCols();
        cellHeight = 500 / this.model.maze.numRows();
        this. mazeGroup = new Group();
    }

    /**
     * Generates maze representation
     *
     * @return Group that represents Maze
     */
    public Group drawMaze() {
        for (int row = 0; row < this.model.maze.numRows(); row++) {
            for (int column = 0; column < this.model.maze.numCols(); column++) {
                Field currentField = this.model.maze.getField(row, column);
                Rectangle field = new Rectangle(column * cellWidth, row * cellHeight, cellWidth, cellHeight);
                if (currentField instanceof WallField) {
                    for (Field.Direction dir : ija.project.common.Field.Direction.values()) {
                        if (!(currentField.nextField(dir) instanceof WallField)) {
                            Line line = new Line();

                            // set the start and end points of the line
                            switch (dir) {
                                case U:
                                    line.setStartX(field.getX());
                                    line.setStartY(field.getY());
                                    line.setEndX(field.getX() + field.getWidth());
                                    line.setEndY(field.getY());
                                    break;
                                case D:
                                    line.setStartX(field.getX());
                                    line.setStartY(field.getY() + field.getHeight());
                                    line.setEndX(field.getX() + field.getWidth());
                                    line.setEndY(field.getY() + field.getHeight());
                                    break;
                                case L:
                                    line.setStartX(field.getX());
                                    line.setStartY(field.getY());
                                    line.setEndX(field.getX());
                                    line.setEndY(field.getY() + field.getHeight());
                                    break;
                                case R:
                                    line.setStartX(field.getX() + field.getWidth());
                                    line.setStartY(field.getY());
                                    line.setEndX(field.getX() + field.getWidth());
                                    line.setEndY(field.getY() + field.getHeight());
                                    break;
                            }
                            line.setStroke(Color.web("#051D9D"));
                            line.setStrokeWidth(5);
                            line.setStrokeLineCap(StrokeLineCap.BUTT);
                            this.mazeGroup.getChildren().add(line);
                        }

                    }
                    this.mazeGroup.getChildren().add(field);
                }else if(currentField instanceof TargetField) {
                    Circle target = new Circle(field.getWidth()/2 - 5, Color.web("#B02E0C"));
                    target.setCenterX(field.getX() + field.getWidth()/2);
                    target.setCenterY(field.getY() + field.getHeight()/2);
                    this.mazeGroup.getChildren().addAll(target);
                }else if(currentField instanceof PathField) {
                    Circle point = new Circle(3, Color.web("#CED6EE"));
                    point.setCenterX(field.getX() + field.getWidth()/2);
                    point.setCenterY(field.getY() + field.getHeight()/2);
                    this.mazeGroup.getChildren().addAll(point);
                }
            }
        }
        return this.mazeGroup;
    }

    public VBox generateGame(){
        // Create Menu
        Menu menu = new Menu("Menu");
        MenuItem mapChange = new MenuItem("Change Map");

        //Create MenuBar
        MenuBar menuBar = new MenuBar(menu);
        menu.getItems().addAll(mapChange);
        mapChange.setOnAction(e ->{
            System.out.println("Changing map");
        });

        VBox gameBox = new VBox(menuBar, drawMaze());
        gameBox.setStyle("-fx-background-color: #00022A");

        return gameBox;
    }
}
