package de.thdeg.amuri.krakout.graphics.moving;

import de.thdeg.amuri.krakout.movement.Position;

import java.util.Comparator;

/**
 * creates the XAxisComparator
 */
public class XAxisComparator implements Comparator<Position> {

    public int compare(Position obj1, Position obj2){
        return (int) Math.signum(obj1.x-obj2.x);
    }
}
