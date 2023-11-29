// 207789140 Sondos Zoabi
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * includes the collection of the sprite in the game.
 */
public class SpriteCollection {
    private List<Sprite> sprites = new ArrayList<Sprite>();

    /**
     * adding the sprite which passed as parameter to the sprites collection.
     * @param s the sprite object to add to the game.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * removing sprite from the list.
     * @param s sprite variable.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d a surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).drawOn(d);
        }
    }
}