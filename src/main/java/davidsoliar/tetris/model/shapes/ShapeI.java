package davidsoliar.tetris.model.shapes;

import davidsoliar.tetris.model.Shape;
import davidsoliar.tetris.model.ShapeType;


public class ShapeI extends Shape {
    public ShapeI() {
        shape = new int[][] {
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        color = ShapeType.I;
        swap();
    }
}