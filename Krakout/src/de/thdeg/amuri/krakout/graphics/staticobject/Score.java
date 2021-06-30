package de.thdeg.amuri.krakout.graphics.staticobject;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.GameObject;
import de.thdeg.amuri.krakout.movement.Position;

import java.awt.*;

/**
 * Score Output and Calculation class, gets manipulated by {@link de.thdeg.amuri.krakout.graphics.moving.Pinball} hitting enemies.
 *
 * @see de.thdeg.amuri.krakout.graphics.moving.alien.BeeHive
 * @see de.thdeg.amuri.krakout.graphics.moving.alien.Face
 * @see Brick
 */
public class Score extends GameObject {
    private final Color color;
    private int score;
    private int highScore;

    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public Score(GameView gameView) {
        super(gameView);
        this.highScore = 28000;
        this.score = 0;
        this.size = 18;
        this.color = Color.WHITE;
    }

    /**
     * @param score as Score to be removed
     */
    public void minusScore(int score) {
        this.score -= score;
    }

    /**
     * @param score as Score to be added
     */
    public void plusScore(int score) {
        this.score += score;
    }

    /**
     * @return current score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score set new score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return current highscore
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * @param highScore set new highscore
     */
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    @Override
    public void addToCanvas() {
        String scoreOutput = "Currentscore: " + score;
        String highScoreOutput = "Highscore: " + highScore;
        Position scoreposition = new Position(GameView.WIDTH - scoreOutput.length() * size, 0);
        Position highScoreposition = new Position(GameView.WIDTH - highScoreOutput.length() * size, GameView.HEIGHT - size);
        this.gameView.addTextToCanvas(scoreOutput, scoreposition.x, scoreposition.y, size, color, rotation);
        this.gameView.addTextToCanvas(highScoreOutput, highScoreposition.x, highScoreposition.y, size, Color.YELLOW, rotation);
    }

    @Override
    public void updateStatus() {
    }
}
