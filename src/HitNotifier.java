// 207789140 Sondos Zoabi
/**
 * interface hit notifier.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl hit listener to add to the list.
     */
    void addHitListener(HitListener hl);

    /**
     *  Remove hl from the list of listeners to hit events.
     * @param hl listener.
     */
    void removeHitListener(HitListener hl);
}