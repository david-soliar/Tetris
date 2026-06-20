package davidsoliar.tetris.model;

import javafx.beans.property.*;


public class State {
    private final BooleanProperty gameOver = new SimpleBooleanProperty();
    private final IntegerProperty linesCompleted = new SimpleIntegerProperty();
    private final IntegerProperty score = new SimpleIntegerProperty();
    private final IntegerProperty level = new SimpleIntegerProperty();
    private final ObjectProperty<Shape> nextShape = new SimpleObjectProperty<>();

    private int speed = 1_100_000_000;

    public boolean getGameOver() {
        return gameOver.get();
    }

    public void setGameOver(boolean value) {
        gameOver.set(value);
    }

    public BooleanProperty gameOverProperty() {
        return gameOver;
    }

    public int getLinesCompleted() {
        return linesCompleted.get();
    }

    public void setLinesCompleted(int value) {
        linesCompleted.set(value);
    }

    public IntegerProperty linesCompletedProperty() {
        return linesCompleted;
    }

    public Shape getNextShape() {
        return nextShape.get();
    }

    public void setNextShape(Shape value) {
        nextShape.set(value);
    }

    public ObjectProperty<Shape> nextShapeProperty() {
        return nextShape;
    }

    public int getScore() {
        return score.get();
    }

    public void setScore(int value) {
        score.set(value);
        int newLevel = (value / 1000);
        if (newLevel > getLevel()) {
            setLevel(newLevel);
        }
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    public int getLevel() {
        return level.get();
    }

    public int getSpeed() {
        return speed;
    }

    public void setLevel(int value) {
        level.set(value);
        speed = switch (level.get()) {
            case 0 -> 1_100_000_000;
            case 1 -> 900_000_000;
            case 2 -> 700_000_000;
            case 3 -> 500_000_000;
            case 4 -> 400_000_000;
            case 5 -> 300_000_000;
            default -> 200_000_000;
        };
    }

    public IntegerProperty levelProperty() {
        return level;
    }
}
