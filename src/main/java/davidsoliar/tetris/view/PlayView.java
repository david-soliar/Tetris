package davidsoliar.tetris.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import davidsoliar.tetris.controller.Controller;
import davidsoliar.tetris.model.Board;
import davidsoliar.tetris.utils.ColorDecoder;
import davidsoliar.tetris.utils.Constants;


public class PlayView {
    private final Controller controller;
    private final Canvas canvas;
    private final BorderPane canvasPanel;

    public PlayView(Controller controller) {
        this.controller = controller;
        this.canvas = new Canvas(Constants.WIDTH * Constants.TILE_SIZE, Constants.HEIGHT * Constants.TILE_SIZE);
        this.canvasPanel = new BorderPane();
        canvasPanel.setCenter(canvas);
        canvasPanel.setStyle("-fx-border-color: rgb(255, 255, 220); -fx-border-width: 5px;");
        render();
    }

    public BorderPane getView() {
        return canvasPanel;
    }

    public void render() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Board board = controller.getBoard();

        for (int r = 0; r < Constants.HEIGHT; r++) {
            for (int c = 0; c < Constants.WIDTH; c++) {
                int color = board.getColorOnPosition(r, c);
                gc.setFill(ColorDecoder.getColor(color));
                gc.fillRect(c * Constants.TILE_SIZE, r * Constants.TILE_SIZE,
                        Constants.TILE_SIZE, Constants.TILE_SIZE);
                gc.setStroke(Color.rgb(127, 127, 127));
                gc.setLineWidth(1);
                gc.strokeRect(c * Constants.TILE_SIZE, r * Constants.TILE_SIZE,
                        Constants.TILE_SIZE, Constants.TILE_SIZE);
            }
        }

        var currentShape = controller.getcurrentShape();
        int[][] shape = currentShape.getShape();
        int row = controller.getCurrentRow();
        int col = controller.getCurrentCol();

        gc.setFill(ColorDecoder.getColor(currentShape.getColor()));
        for (int r = 0; r < Constants.SHAPE_N; r++) {
            for (int c = 0; c < Constants.SHAPE_N; c++) {
                if (shape[r][c] == 1) {
                    gc.fillRect((col + c) * Constants.TILE_SIZE, (row + r) * Constants.TILE_SIZE,
                            Constants.TILE_SIZE, Constants.TILE_SIZE);
                    gc.setStroke(Color.rgb(127, 127, 127));
                    gc.setLineWidth(1);
                    gc.strokeRect((col + c) * Constants.TILE_SIZE, (row + r) * Constants.TILE_SIZE,
                            Constants.TILE_SIZE, Constants.TILE_SIZE);
                }
            }
        }
    }
}

