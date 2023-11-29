// 207789140 Sondos Zoabi
/**
 * save the data about collision point and the collision object.
 */
public class CollisionInfo {
    private Collidable c;
    private Point collision;

    /**
     * constructor of collision info.
     * @param c collision object.
     * @param collision collision point.
     */
    public CollisionInfo(Collidable c, Point collision) {
        this.c = c;
        this.collision = collision;
    }
    /**
     * the point at which the collision occurs.
     * @return collision point.
     */
    public Point collisionPoint() {
        return this.collision;
    }
    /**
     * the collidable object involved in the collision.
     * @return collision object.
     */
    public Collidable collisionObject() {
        return this.c;
    }
}