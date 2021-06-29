package de.thdeg.amuri.krakout.graphics.staticobject;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.GameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.MovingGameObject;
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
    private final Color color;
    private int score;
    private String scoreOutput;
    private final String highScoreOutput;

    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public Score(GameView gameView) {
        super(gameView);
        int highScore = 10000;
        this.highScoreOutput = "Highscore: " + highScore;
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

    public int getScore() {
        return score;
    }

    @Override
    public void addToCanvas() {
        this.scoreOutput = ""+score;
        this.position = new Position(GameView.WIDTH - scoreOutput.length() * size, 0);
        this.gameView.addTextToCanvas(scoreOutput, position.x, position.y, size, color, rotation);
        this.gameView.addTextToCanvas(highScoreOutput, GameView.WIDTH - highScoreOutput.length() * size, GameView.HEIGHT - size, size, Color.YELLOW, rotation);
    }

    @Override
    public void updateStatus() {
    }
}
