package ija.project.game;

import ija.project.common.AbstractObservableObject;
import ija.project.common.Field;
import ija.project.common.MazeObject;

public class KeyObject extends AbstractObservableObject implements MazeObject {
    /**
     * Current field on which the object is placed.
     */
    private Field field;
    /**
     * Flag if key is collected
     */
    public boolean collected;

    /**
     * Constructor for KeyObject.
     * @param field field in which the object is located
     */
    public KeyObject(PathField field){
        this.field = field;
        this.collected = false;
    }

    /**
     * Sets collected flag to true and notifies observers.
     */
    public void collectKey(){
        this.collected = true;
        notifyObservers();
    }

    @Override
    public boolean canMove(Field.Direction dir) {
        return false;
    }

    @Override
    public boolean move(Field.Direction dir) {
        return false;
    }

    @Override
    public boolean move(Field field){
        return false;
    }

    @Override
    public boolean isPacman() {
        return super.isPacman();
    }

    /**
     * Return field in which the key is located.
     * @return
     */
    @Override
    public Field getField() {
        return this.field;
    }

    @Override
    public int getLives() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getScore() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Field.Direction getDirection() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setDirection(Field.Direction dir) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void moveToStart() {
        throw new UnsupportedOperationException();
    }

}