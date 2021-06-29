package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.game.utilities.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Manages the levels of the game.
 */
class LevelManager {
    private ArrayList<Level> levels;
    private int nextLevel;

    LevelManager(boolean difficultyIsSetToEasy) {
        int basenumberOfBricks = difficultyIsSetToEasy ? 3 : 6;
        int basenumberOfItems = difficultyIsSetToEasy ? 1 : 3;
        int basenumberOfAliens = difficultyIsSetToEasy ? 2 : 4;
        int basenumberOfLive = difficultyIsSetToEasy ? 3 : 2;
        Level level1 = new Level("   Level ∞\n\nInfinityMode", basenumberOfBricks, basenumberOfItems, basenumberOfAliens, basenumberOfLive);
        Level level2 = new Level("   Level Test\n\nInfinityMode",basenumberOfBricks+5, basenumberOfItems+2, basenumberOfAliens+3, basenumberOfLive);
        levels = new ArrayList<>(List.of(level1,level2));
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
            throw new NoMoreLevelsAvailableException();
        }
        nextLevel++;
        return level;
    }
}
