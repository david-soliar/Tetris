package davidsoliar.tetris.controller;

import java.util.Random;

import davidsoliar.tetris.model.*;
import davidsoliar.tetris.model.shapes.*;
import davidsoliar.tetris.utils.Constants;


public class Controller {
    private final Board board;
    private final State state;

    private Shape currentShape;

    private int currentRow;
    private int currentCol;

    private final Runnable restart;

    public Controller(Board board, State state, Runnable restart) {
        this.board = board;
        this.state = state;
        this.restart = restart;
        resetPosition();
        generateRandomNextShape();
        spawnShape();
    }

    public Board getBoard() {
        return board;
    }

    public Shape getcurrentShape() {
        return currentShape;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public State getState() {
        return state;
    }

    public void restartGame() {
        restart.run();
    }

    private int scoreCalculator(int rowsCleared) {
        return switch (rowsCleared) {
            case 1 -> 100;
            case 2 -> 300;
            case 3 -> 500;
            case 4 -> 800;
            default -> 0;
        };
    }

    public void tick() {
        if (!tryMove(currentRow + 1, currentCol)) {
            lockShape();
            int rowsCleared = board.clearFullRows();
            state.setLinesCompleted(state.getLinesCompleted() + rowsCleared);
            state.setScore(state.getScore() + scoreCalculator(rowsCleared));
            spawnShape();
        }
    }

    public void moveLeft() {
        tryMove(currentRow, currentCol - 1);
    }

    public void moveRight() {
        tryMove(currentRow, currentCol + 1);
    }

    public void moveDown() {
        state.setScore(state.getScore() + 1);
        tick();
    }

    public void rotate() {
        currentShape.rotate();
        if (!isValidPosition(currentRow, currentCol)) {
            currentShape.rotate();
            currentShape.rotate();
            currentShape.rotate();
        }
    }

    private boolean tryMove(int newRow, int newCol) {
        if (isValidPosition(newRow, newCol)) {
            currentRow = newRow;
            currentCol = newCol;
            return true;
        }
        return false;
    }

    private boolean isValidPosition(int row, int col) {
        int[][] shape = currentShape.getShape();
        for (int r = 0; r < Constants.SHAPE_N; r++) {
            for (int c = 0; c < Constants.SHAPE_N; c++) {
                if (shape[r][c] != 0) {
                    int boardRow = row + r;
                    int boardCol = col + c;
                    if (boardRow < 0 || boardRow >= Constants.HEIGHT ||
                            boardCol < 0 || boardCol >= Constants.WIDTH ||
                            board.isOccupied(boardRow, boardCol)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void lockShape() {
        int[][] shape = currentShape.getShape();
        int color = currentShape.getColor();
        for (int r = 0; r < Constants.SHAPE_N; r++) {
            for (int c = 0; c < Constants.SHAPE_N; c++) {
                if (shape[r][c] != 0) {
                    int boardRow = currentRow + r;
                    int boardCol = currentCol + c;
                    if (boardRow >= 0 && boardRow < Constants.HEIGHT &&
                            boardCol >= 0 && boardCol < Constants.WIDTH) {
                        board.setColorOnPosition(boardRow, boardCol, color);
                    }
                }
            }
        }
    }

    private void resetPosition() {
        currentRow = 0;
        currentCol = 3;
    }

    private void spawnShape() {
        currentShape = state.getNextShape();
        generateRandomNextShape();
        resetPosition();

        if (!isValidPosition(currentRow, currentCol)) {
            state.setGameOver(true);
        }
    }

    private Shape generateRandomNextShape() {
        int pick = new Random().nextInt(ShapeType.values().length);
        state.setNextShape(switch (ShapeType.values()[pick]) {
            case I -> new ShapeI();
            case L -> new ShapeL();
            case O -> new ShapeO();
            case S -> new ShapeS();
            case T -> new ShapeT();
            default -> generateRandomNextShape();
        });
        return state.getNextShape();
    }
}