package de.thdeg.amuri.krakout.graphics.moving;

import de.thdeg.amuri.krakout.movement.Position;

import java.util.Comparator;

/**
 * creates the XAxisComparator
 */
public class XAxisComparator implements Comparator<Position> {

    /**
     * Compares two positions
     *
     * @param obj1 Main Position
     * @param obj2 Position compared with
     * @return -1 -> obj1 is smaller than obj2; 0 -> obj1 is equal to obj2; 1 -> obj1 is greater than obj2
     */
    public int compare(Position obj1, Position obj2) {
        return (int) Math.signum(obj1.x - obj2.x);
    }
}
