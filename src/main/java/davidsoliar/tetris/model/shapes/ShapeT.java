package davidsoliar.tetris.model.shapes;

import davidsoliar.tetris.model.Shape;
import davidsoliar.tetris.model.ShapeType;


public class ShapeT extends Shape {
    public ShapeT() {
        shape = new int[][] {
                {0, 1, 1, 1},
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        color = ShapeType.T;
        swap();
    }
}