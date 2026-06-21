package davidsoliar.tetris;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.beans.value.ChangeListener;

import davidsoliar.tetris.controller.*;
import davidsoliar.tetris.view.*;
import davidsoliar.tetris.model.Board;
import davidsoliar.tetris.model.State;
import davidsoliar.tetris.utils.Constants;


public class App extends Application {
    private Controller controller;
    private State state;
    private Scene scene;
    private Stage stage;
    private InputHandler inputHandler;
    private PlayView playView;
    private AnimationTimer timer;
    private ChangeListener<Boolean> gameOverListener;

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        var fontUrl = getClass().getResource("/fonts/Press_Start_2P/PressStart2P-Regular.ttf");
        if (fontUrl != null) {
            Font.loadFont(fontUrl.toExternalForm(), 20);
        }

        var iconUrl = getClass().getResource("/images/icon.png");
        if (iconUrl != null) {
            stage.getIcons().add(new Image(iconUrl.toExternalForm()));
        }

        gameSetup();

        stage.setTitle("Tetris");
        
        stage.setResizable(false);
        stage.setMaxWidth((Constants.WIDTH + Constants.SHAPE_N * 2 + 2) * Constants.TILE_SIZE);
        stage.setMaxHeight((Constants.HEIGHT + Constants.SHAPE_N + 1) * Constants.TILE_SIZE);
        
        stage.show();
    }

    private void gameSetup() {
        state = new State();
        controller = new Controller(new Board(), state, this::restart);

        InfoView infoView = new InfoView(controller);
        playView = new PlayView(controller);
        inputHandler = new InputHandler();

        HBox root = new HBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(playView.getView(), infoView.getView());
        root.setBackground(new Background(
                new BackgroundFill(Constants.BACKGROUND_COLOR, null, null)
        ));

        scene = new Scene(root);
        stage.setScene(scene);
        
        inputHandler.attach(scene, controller, () -> playView.render());

        timer = new AnimationTimer() {
            private long last = 0;

            @Override
            public void handle(long now) {
                if (last == 0) last = now;
                if (now - last > state.getSpeed()) {
                    controller.tick();
                    playView.render();
                    last = now;
                }
            }
        };
        gameOverListener = (a, b, newVal) -> {
            if (newVal) {
                inputHandler.detach(scene);
                playView.render();
                timer.stop();
            }
        };
        state.gameOverProperty().addListener(gameOverListener);
        
        timer.start();
    }

    public void restart() {
        if (timer != null) {
            timer.stop();
        }
        if (state != null && gameOverListener != null) {
            state.gameOverProperty().removeListener(gameOverListener);
        }
        if (inputHandler != null && scene != null) {
            inputHandler.detach(scene);
        }
        gameSetup();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
