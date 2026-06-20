package davidsoliar.tetris.model;

import davidsoliar.tetris.utils.Constants;


public class Board {

    private final int[][] board = new int[Constants.HEIGHT][Constants.WIDTH];

    public int getColorOnPosition(int row, int col) {
        return board[row][col];
    }

    public void setColorOnPosition(int row, int col, int color) {
        board[row][col] = color;
    }

    public boolean isOccupied(int row, int col) {
        return board[row][col] != 0;
    }

    public int clearFullRows() {
        int rowsCleared = 0;
        for (int row = Constants.HEIGHT - 1; row >= 0; row--) {
            if (isRowFull(row)) {
                rowsCleared++;
                clearRow(row);
                shiftDown(row);
                row++;
            }
        }
        return rowsCleared;
    }

    private boolean isRowFull(int row) {
        for (int col = 0; col < Constants.WIDTH; col++) {
            if (board[row][col] == 0) {
                return false;
            }
        }
        return true;
    }

    private void clearRow(int row) {
        for (int col = 0; col < Constants.WIDTH; col++) {
            board[row][col] = 0;
        }
    }

    private void shiftDown(int startRow) {
        for (int row = startRow; row > 0; row--) {
            System.arraycopy(board[row - 1], 0, board[row], 0, Constants.WIDTH);
        }
        for (int col = 0; col < Constants.WIDTH; col++) {
            board[0][col] = 0;
        }
    }
}
