package de.thdeg.amuri.krakout.graphics.staticobject;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.GameObject;
import de.thdeg.amuri.krakout.movement.Position;

import java.awt.*;

/**
 * Score Output and Calculation class, gets manipulated by {@link de.thdeg.amuri.krakout.graphics.moving.Pinball} hitting enemies.
 *
 * @see de.thdeg.amuri.krakout.graphics.moving.alien.Astronaut
 * @see de.thdeg.amuri.krakout.graphics.moving.alien.Bee
 * @see de.thdeg.amuri.krakout.graphics.moving.alien.BeeHive
 * @see de.thdeg.amuri.krakout.graphics.moving.alien.BonusShip
 * @see de.thdeg.amuri.krakout.graphics.moving.alien.Cannibal
 * @see de.thdeg.amuri.krakout.graphics.moving.alien.Egg
 * @see de.thdeg.amuri.krakout.graphics.moving.alien.Exit
 * @see de.thdeg.amuri.krakout.graphics.moving.alien.Face
 * @see de.thdeg.amuri.krakout.graphics.moving.alien.TimeOut
 * @see de.thdeg.amuri.krakout.graphics.moving.alien.TwinBall
 * @see Item
 * @see Brick
 */
public class Score extends GameObject {
    private final int baseScore;
    private final Color color;
    private int highScore;
    private int score;
    private int scoreValue;
    private String scoreOutput;
    private final String highScoreOutput;
    private boolean plusScore;
    private boolean minusScore;

    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public Score(GameView gameView) {
        super(gameView);
        this.baseScore = 0;
        this.score = this.baseScore;
        this.highScore = 10000;
        this.scoreOutput = String.valueOf(score);
        this.highScoreOutput = "Highscore: " + highScore;
        this.size = 18;
        this.position = new Position(GameView.WIDTH - scoreOutput.length() * size, 0);
        this.color = Color.WHITE;
    }

    /**
     * @param plusScore if score needs to be added
     */
    public void isPlusScore(boolean plusScore) {
        this.plusScore = plusScore;
    }

    /**
     * @param minusScore if score needs to be removed
     */
    public void isMinusScore(boolean minusScore) {
        this.minusScore = minusScore;
    }

    /**
     * @param score as Score to be removed or added
     */
    public void setScoreValue(int score) {
        this.scoreValue = score;
    }

    /**
     * @param highScore for Outputting Highscore
     */
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    @Override
    public void addToCanvas() {
        this.scoreOutput = String.valueOf(score);
        this.gameView.addTextToCanvas(scoreOutput, position.x, position.y, size, color, rotation);
        this.gameView.addTextToCanvas(highScoreOutput, GameView.WIDTH - highScoreOutput.length() * size, GameView.HEIGHT - size, size, Color.YELLOW, rotation);
    }

    @Override
    public void updateStatus() {
        if (plusScore) {
            this.score += scoreValue;
        }
        if (minusScore) {
            this.score -= scoreValue;
        }
    }
}
