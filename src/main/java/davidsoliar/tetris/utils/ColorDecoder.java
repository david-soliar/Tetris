package davidsoliar.tetris.utils;

import javafx.scene.paint.Color;


public class ColorDecoder {

    public static Color getColor(int value) {
        //     PLACEHOLDER, I, O, T, L, S;
        return switch (value) {
            case 0 -> Color.BLACK;
            case 1 -> Color.rgb(0, 255, 255);
            case 2 -> Color.rgb(255, 255, 0);
            case 3 -> Color.rgb(128, 0, 128);
            case 4 -> Color.rgb(0, 255, 0);
            case 5 -> Color.rgb(255, 0, 0);
            default -> Color.PURPLE;
        };
    }
}
