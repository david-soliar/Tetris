package davidsoliar.tetris.model;

import java.util.Random;

import davidsoliar.tetris.utils.Constants;


public abstract class Shape {
    protected int[][] shape = new int[Constants.SHAPE_N][Constants.SHAPE_N];
    protected ShapeType color;

    public int[][] getShape() {
        return shape;
    }

    public int getColor() {
        return color.ordinal();
    }

    public void rotate() {
        int[][] rotated = new int[Constants.SHAPE_N][Constants.SHAPE_N];
        for (int r = 0; r < Constants.SHAPE_N; r++)
            for (int c = 0; c < Constants.SHAPE_N; c++)
                rotated[c][Constants.SHAPE_N - 1 - r] = shape[r][c];
        shape = rotated;
    }

    public void swap() {
        if (new Random().nextBoolean()) return;
        for (int row = 0; row < Constants.SHAPE_N; row++) {
            int left = 0, right = Constants.SHAPE_N - 1;
            while (left < right) {
                int temp = shape[row][left];
                shape[row][left] = shape[row][right];
                shape[row][right] = temp;
                left++; right--;
            }
        }
    }

    @Override
    public String toString() {
        return color.toString();
    }
}