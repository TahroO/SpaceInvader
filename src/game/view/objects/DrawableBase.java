package game.view.objects;

import java.awt.*;

abstract public class DrawableBase implements Drawable {
    /**
     * Scales internal units to game view size.
     * @param scale Scaling factor.
     * @param value Value to be scaled.
     * @return Scaled value.
     */
    protected int toPixel(int scale, double value) {
        return (int) Math.round(scale * value);
    }

    protected void  drawMatrix(Graphics2D g2d, int[][] matrix, int width, int height, int offsetX, int offsetY) {
        int cellHeight = height / matrix.length;
        for (int row = 0; row < matrix.length; row++) {
            int[] rowData = matrix[row];
            int cellWidth = width / rowData.length;
            int y = row * cellHeight + offsetY;
            for (int col = 0; col < rowData.length; col++) {
                int x = col * cellWidth + offsetX;
                if (rowData[col] == 1) {
                    g2d.fillRect(x, y, cellWidth, cellHeight);
                }
            }
        }
    }

}
