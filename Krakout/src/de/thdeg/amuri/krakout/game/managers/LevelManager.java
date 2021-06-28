package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.game.utilities.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

/**
 * Manages the levels of the game.
 */
class LevelManager {
    private ArrayList<Level> levels;
    private int nextLevel;

    public LevelManager(boolean difficultyIsSetToEasy) {
        int basenumberOfBricks = difficultyIsSetToEasy ? 3 : 6;
        Level level1 = new Level("   Level ∞\n\nInfinityMode",basenumberOfBricks,5,10,5);
        levels = new ArrayList<>(List.of(level1));
        this.nextLevel = 0;
    }

    /**
     * Determines, if a next level exists.
     *
     * @return <code>true</code> if there is a next level.
     */
    public boolean hasNextLevel() {
        return nextLevel < levels.size();
    }

    /**
     * Returns the next level.
     *
     * @return Next level
     * @throws NoSuchElementException if no next level is available.
     */
    public Level getNextLevel() {
        Level level = levels.get(nextLevel);
        if (level == null) {
            throw new NoSuchElementException();
        }
        //nextLevel++;
        return level;
    }
}
