package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class GameSurface extends JPanel implements ActionListener {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static int GAME_START = 0;
    public static int GAME_PAUSE = 1;
    public static int GAME_OVER = 2;

    private final RenderingHints renderingHints;
    private Font font;

    private ArrayList<Renderable> renderables;

    /**
     * Creates a new GameSurface instance.
     */
    public GameSurface(ArrayList<Renderable> renderables) {
        super();
        // Configure JPanel.
        setSize(WIDTH, HEIGHT);
        setBackground(new Color(30, 30, 30));
        setFocusable(true);
        // Create rendering hints.
        Map<RenderingHints.Key, Object> hintsMap = Map.of(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON,
                RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY
        );
        renderingHints = new RenderingHints(hintsMap);
        this.renderables = renderables;
    }

    /**
     * Initializes this game surface.
     */
    public void init() {
        InputStream fontIs = this.getClass().getResourceAsStream("/resources/fonts/vt323-v17-latin-regular.ttf");
        try {
            Font uniFont = Font.createFont(Font.TRUETYPE_FONT, fontIs);
            font = uniFont.deriveFont(20f);
            getGraphics().setFont(font);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Calls draw() on every game object.
     *
     * @param g A Graphics object.
     */
    private void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(font);
        g2d.setRenderingHints(renderingHints);
        // Draw game objects.
        renderables.forEach(gameObject -> gameObject.draw(g2d));
    }

    public static int getGameStart() {
        return GAME_START;
    }

    public static int getGamePause() {
        return GAME_PAUSE;
    }

    public static int getGameOver() {
        return GAME_OVER;
    }

    public static int getGameStatus(int gameStatus) {
        switch (gameStatus) {
            case 0  ->  {return GAME_START;}
            case 1  ->  {return GAME_PAUSE;}
            default ->  {return GAME_OVER;}
        }
        /*if (gameStatus == 0){
            return GAME_START;
        } else if (gameStatus == 1) {
            return GAME_PAUSE;
        } else {
            return GAME_OVER;
        }*/
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }

}
