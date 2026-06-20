package davidsoliar.tetris.model.shapes;

import davidsoliar.tetris.model.Shape;
import davidsoliar.tetris.model.ShapeType;


public class ShapeS extends Shape {
    public ShapeS() {
        shape = new int[][] {
                {0, 0, 1, 1},
                {0, 1, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        color = ShapeType.S;
        swap();
    }
}