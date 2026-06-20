package davidsoliar.tetris.utils;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;


public class Constants {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 20;
    public static final int TILE_SIZE = 30;
    public static final int SHAPE_N = 4;
    public static final Color VALUE_COLOR = Color.rgb(255, 255, 220);
    public static final Color BACKGROUND_COLOR = Color.rgb(60, 60, 60);
    public static final Color BACKGROUND_COLOR_BIT_LIGHTER = Color.rgb(69, 69, 69);
    public static final Color BACKGROUND_COLOR_BIT_BIT_LIGHTER = Color.rgb(79, 79, 79);
    public static final Color GAMEOVER_COLOR = Color.rgb(155, 32, 32);
    public static final Background NORMAL_BACKGROUND = new Background(new BackgroundFill(
            Constants.BACKGROUND_COLOR_BIT_LIGHTER,
            CornerRadii.EMPTY,
            Insets.EMPTY
    ));
    public static final Background HOVER_BACKGROUND = new Background(new BackgroundFill(
            Constants.BACKGROUND_COLOR_BIT_BIT_LIGHTER,
            CornerRadii.EMPTY,
            Insets.EMPTY
    ));
}
