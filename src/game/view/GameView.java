package game.view;

public interface GameView {
    int OVERLAY_NONE = 0;
    int OVERLAY_START = 1;
    int OVERLAY_PAUSE = 2;
    int OVERLAY_GAME_OVER = 3;

    /**
     * Sets the overlay to be drawn.
     * @param overlay
     */
    void setOverlay(int overlay);

}
