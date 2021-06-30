package de.thdeg.amuri.krakout.game.managers;

import de.thdeg.amuri.krakout.game.utilities.Level;

/**
 * Manages the levels of the game.
 */
class LevelManager {
    Level level;

    LevelManager(boolean difficultyIsSetToEasy) {
        int basenumberOfBricks = difficultyIsSetToEasy ? 3 : 6;
        int basenumberOfItems = difficultyIsSetToEasy ? 1 : 3;
        int basenumberOfAliens = difficultyIsSetToEasy ? 2 : 4;
        int basenumberOfLive = difficultyIsSetToEasy ? 3 : 2;
        this.level = new Level("   Level ∞\n\nInfinityMode", basenumberOfBricks, basenumberOfItems, basenumberOfAliens, basenumberOfLive);
    }

    /**
     * Determines, if a next level exists.
     *
     * @return <code>true</code> if there is a next level.
     */
    public boolean hasNextLevel() {
        return true;
    }

    public Level getLevel() {
        return this.level;
    }
}
