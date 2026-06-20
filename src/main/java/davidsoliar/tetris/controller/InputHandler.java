package davidsoliar.tetris.controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;


public class InputHandler {
    private EventHandler<KeyEvent> keyHandler;

    public void attach(Scene scene, Controller controller, Runnable afterAction) {
        keyHandler = e -> {
            if (e.getCode() == KeyCode.LEFT) {
                controller.moveLeft();
            } else if (e.getCode() == KeyCode.RIGHT) {
                controller.moveRight();
            } else if (e.getCode() == KeyCode.DOWN) {
                controller.moveDown();
            } else if (e.getCode() == KeyCode.UP) {
                controller.rotate();
            }

            if (afterAction != null) {
                afterAction.run();
            }
        };
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
    }

    public void detach(Scene scene) {
        if (keyHandler != null) {
            scene.removeEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
            keyHandler = null;
        }
    }
}
