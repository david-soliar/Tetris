package davidsoliar.tetris.model.shapes;

import davidsoliar.tetris.model.Shape;
import davidsoliar.tetris.model.ShapeType;


public class ShapeL extends Shape {
    public ShapeL() {
        shape = new int[][] {
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        color = ShapeType.L;
        swap();
    }
}