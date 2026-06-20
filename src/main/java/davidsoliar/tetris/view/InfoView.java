package davidsoliar.tetris.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import davidsoliar.tetris.controller.Controller;
import davidsoliar.tetris.model.State;
import davidsoliar.tetris.utils.ColorDecoder;
import davidsoliar.tetris.utils.Constants;


public class InfoView {
    private final VBox infoPanel;
    private Label scoreValueLabel;
    private Label linesCompletedValueLabel;
    private Label levelValueLabel;
    private Button gameOverButton;
    private Canvas nextCanvas;
    private final State state;
    private final Controller controller;

    public InfoView(Controller controller) {
        this.controller = controller;
        state = controller.getState();

        infoPanel = new VBox(10);
        infoPanel.setPadding(new Insets(10));
        infoPanel.setPrefWidth((Constants.SHAPE_N * 2) * Constants.TILE_SIZE);

        infoPanel.getChildren().addAll(
                createScoreBox(),
                createLinesCompletedBox(),
                createLevelBox(),
                createCanvasBox(),
                createBottomBox()
        );
        infoPanel.setStyle("-fx-font-family: 'Press Start 2P'");

        state.scoreProperty().addListener((_, _, newVal) -> scoreValueLabel.setText(newVal + ""));
        state.levelProperty().addListener((_, _, newVal) -> levelValueLabel.setText(newVal + ""));
        state.linesCompletedProperty().addListener((_, _, newVal) -> linesCompletedValueLabel.setText(newVal + ""));
        state.nextShapeProperty().addListener((_, _, _) -> updateNextCanvas());
        state.gameOverProperty().addListener((_, _, newVal) -> {
            if (newVal) gameOverButton.setVisible(true);
        });
    }

    private VBox createScoreBox() {
        Label scoreLabel = new Label("Score: ");
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setStyle("-fx-font-size: 20px;");

        scoreValueLabel = new Label(state.getScore() + "");
        scoreValueLabel.setTextFill(Constants.VALUE_COLOR);
        scoreValueLabel.setStyle("-fx-font-size: 40px;");

        VBox centerBox = new VBox();
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(scoreValueLabel);

        VBox scoreBox = new VBox(5, scoreLabel, centerBox);
        scoreBox.setPadding(new Insets(10));
        return scoreBox;
    }

    private VBox createLinesCompletedBox() {
        Label linesCompletedLabel = new Label("Lines: ");
        linesCompletedLabel.setTextFill(Color.WHITE);
        linesCompletedLabel.setStyle("-fx-font-size: 20px;");

        linesCompletedValueLabel = new Label(state.getLinesCompleted() + "");
        linesCompletedValueLabel.setTextFill(Constants.VALUE_COLOR);
        linesCompletedValueLabel.setStyle("-fx-font-size: 40px;");

        VBox centerBox = new VBox();
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(linesCompletedValueLabel);

        VBox linesCompletedBox = new VBox(5, linesCompletedLabel, centerBox);
        linesCompletedBox.setPadding(new Insets(10));
        return linesCompletedBox;
    }

    private VBox createLevelBox() {
        Label levelLabel = new Label("Level: ");
        levelLabel.setTextFill(Color.WHITE);
        levelLabel.setStyle("-fx-font-size: 20px;");

        levelValueLabel = new Label(state.getLevel() + "");
        levelValueLabel.setTextFill(Constants.VALUE_COLOR);
        levelValueLabel.setStyle("-fx-font-size: 40px;");

        VBox centerBox = new VBox();
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(levelValueLabel);

        VBox levelBox = new VBox(5, levelLabel, centerBox);
        levelBox.setPadding(new Insets(10));
        return levelBox;
    }

    private VBox createCanvasBox() {
        Label canvasLabel = new Label("Next: ");
        canvasLabel.setTextFill(Color.WHITE);
        canvasLabel.setStyle("-fx-font-size: 20px;");

        nextCanvas = new Canvas(Constants.SHAPE_N * Constants.TILE_SIZE, (Constants.SHAPE_N - 1) * Constants.TILE_SIZE);

        VBox centerBox = new VBox();
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(nextCanvas);

        VBox canvasBox = new VBox(5, canvasLabel, centerBox);
        canvasBox.setPadding(new Insets(10));
        updateNextCanvas();
        return canvasBox;
    }

    private VBox createBottomBox() {
        Label controlsLabel = new Label("use arrows to move");
        controlsLabel.setTextFill(Constants.VALUE_COLOR);
        controlsLabel.setStyle("-fx-font-size: 10px;");
        controlsLabel.setPadding(new Insets(15));

        VBox bottomBox = new VBox();
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.getChildren().addAll(createGameOverButton(), controlsLabel);
        return bottomBox;
    }

    private Button createGameOverButton() {
        Text gameOvetText = new Text("Game Over\n");
        gameOvetText.setStyle("-fx-font-size: 25px;");
        gameOvetText.setFill(Constants.GAMEOVER_COLOR);

        Text playAgainText = new Text("(play again?)");
        playAgainText.setStyle("-fx-font-size: 10px;");

        TextFlow textFlow = new TextFlow();
        textFlow.getChildren().addAll(gameOvetText, playAgainText);

        gameOverButton = new Button();
        gameOverButton.setGraphic(textFlow);

        gameOverButton.setBackground(Constants.NORMAL_BACKGROUND);
        gameOverButton.setMaxWidth(Constants.TILE_SIZE * (Constants.SHAPE_N + 2));
        gameOverButton.setMaxHeight(Constants.TILE_SIZE * (Constants.SHAPE_N - 1));
        gameOverButton.setPadding(new Insets(25));

        gameOverButton.setOnMouseEntered(_ -> gameOverButton.setBackground(Constants.HOVER_BACKGROUND));
        gameOverButton.setOnMouseExited(_ -> gameOverButton.setBackground(Constants.NORMAL_BACKGROUND));
        gameOverButton.setOnAction(_ -> controller.restartGame());

        gameOverButton.setVisible(false);
        return gameOverButton;
    }

    private void updateNextCanvas() {
        GraphicsContext gc = nextCanvas.getGraphicsContext2D();
        gc.setFill(Constants.BACKGROUND_COLOR);
        gc.fillRect(0, 0, nextCanvas.getWidth(), nextCanvas.getHeight());

        var nextShape = state.getNextShape();
        int[][] shape = nextShape.getShape();

        gc.setFill(ColorDecoder.getColor(nextShape.getColor()));
        for (int r = 0; r < Constants.SHAPE_N - 1; r++) {
            for (int c = 0; c < Constants.SHAPE_N; c++) {
                if (shape[r][c] == 1) {
                    gc.fillRect(c * Constants.TILE_SIZE, r * Constants.TILE_SIZE,
                            Constants.TILE_SIZE, Constants.TILE_SIZE);
                    gc.setStroke(Color.rgb(127, 127, 127));
                    gc.setLineWidth(1);
                    gc.strokeRect(c * Constants.TILE_SIZE, r * Constants.TILE_SIZE,
                            Constants.TILE_SIZE, Constants.TILE_SIZE);
                }
            }
        }
    }

    public VBox getView() {
        return infoPanel;
    }
}
