package davidsoliar.tetris.model;


public enum ShapeType {
    PLACEHOLDER, I, O, T, L, S;

    @Override
    public String toString() {
        return name();
    }
}
