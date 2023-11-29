// 207789140 Sondos Zoabi.
import biuoop.DrawSurface;

/**
 * Animation interface.
 */
public interface Animation {
    /**
     * creating one frame on d.
     * @param d drawsurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * tells when should stop.
     * @return boolean value.
     */
    boolean shouldStop();
}