// 207789140 Sondos Zoabi

import java.util.ArrayList;
import java.util.List;

/**
 * creating the game environment and the collides collection.
 */
public class GameEnvironment {
    private List<Collidable> list = new ArrayList<Collidable>();

    /**
     * add the given collidable to the environment.
     *
     * @param c the collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.list.add(c);
    }

    /**
     * @return the collides list.
     */
    public List<Collidable> getList() {
        return list;
    }

    /**
     * removing one collidable from the list.
     *
     * @param c collidable to remove.
     */
    public void removeFromList(Collidable c) {
        this.list.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory of the ball
     * @return all the data about collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> points = new ArrayList<Point>();
        List<Collidable> collidsWith = new ArrayList<Collidable>();
        Point closest;

        for (int i = 0; i < list.size(); i++) {
            if (trajectory.closestIntersectionToStartOfLine(list.get(i).getCollisionRectangle()) != null) {
                points.add(trajectory.closestIntersectionToStartOfLine(list.get(i).getCollisionRectangle()));
                collidsWith.add(list.get(i));
            }
        }
        if (!(points.isEmpty())) {
            closest = points.get(0);
            double distance = closest.distance(trajectory.start());
            int saveIndex = 0;
            for (int i = 0; i < points.size(); i++) {
                double distance2 = points.get(i).distance(trajectory.start());
                if (distance2 <= distance) {
                    distance = distance2;
                    saveIndex = i;
                }
            }
            CollisionInfo collisionInfo = new CollisionInfo(collidsWith.get(saveIndex), points.get(saveIndex));
            return collisionInfo;
        } else {
            return null;
        }
    }
}


