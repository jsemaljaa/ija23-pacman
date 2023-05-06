package ija.project.view;

import ija.project.common.MazeObject;
import ija.project.common.Observable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class PacmanObjectView extends Pane implements Observable.Observer {
    /**
     * Model of the {@link MazeObject}
     */
    private final MazeObject model;
    /**
     * Arc representing pacman
     */
    private final Arc mouth;

    public PacmanObjectView(FieldView parent, MazeObject model) {
        this.model = model;
        this.mouth = new Arc(parent.x + parent.size / 2, parent.y + parent.size / 2, parent.size * 0.35,
                parent.size * 0.35, 30, 300);
        mouth.setType(ArcType.ROUND);
        mouth.setFill(Color.web("f0d000"));

        getChildren().add(mouth);
        paint();
        model.addObserver(this);
    }

    /**
     * Paints a pacman as an <code>Arc</code> and adds it to self.
     * The direction of the pacman is determined by the direction of the model. Arc
     * is 60 degrees wide.
     */
    private void paint() {
        switch (model.getDirection()) {
            case U -> mouth.setStartAngle(120);
            case D -> mouth.setStartAngle(300);
            case L -> mouth.setStartAngle(210);
            case R -> mouth.setStartAngle(30);
        }
    }

    /**
     * Updates the view when notified by the model.
     */
    @Override
    public void update(Observable var1) {
        paint();
    }
}