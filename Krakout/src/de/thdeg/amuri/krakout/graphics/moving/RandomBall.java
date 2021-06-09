package de.thdeg.amuri.krakout.graphics.moving;


import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.GameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.MovingGameObject;
import de.thdeg.amuri.krakout.movement.MovementPatterns;
import de.thdeg.amuri.krakout.movement.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/** Ball with random movement. */
public class RandomBall extends GameObject implements MovingGameObject {
    private final Position targetPosition;
    private final Random random;
    private ArrayList<Position> patternList;
    private final MovementPatterns movementPatterns;

    /**
     * Creates the GameObject with the GameView to be displayed on.
     *
     * @param gameView Window to show the GameObject on.
     */
    public RandomBall(GameView gameView) {
        super(gameView);
        this.position = new Position(0,0);
        this.targetPosition = new Position(800, 200);
        this.random = new Random();
        this.patternList = new ArrayList<>();
        this.movementPatterns = new MovementPatterns();
        this.size = 50;
        this.speedInPixel = 4;
    }

    @Override
    public void updatePosition() {
        double distance = position.distance(targetPosition);
        if (distance >= speedInPixel) {
            position.right((targetPosition.x - position.x) / distance * speedInPixel);
            position.down((targetPosition.y - position.y) / distance * speedInPixel);
        } else {
            setRandomTargetPosition();
            setPatternTargetPosition();
        }
    }


    @Override
    protected void updateStatus() {
    }

    /**
     * Set position to aim at
     */
    public void setRandomTargetPosition() {
        targetPosition.x = random.nextInt(GameView.WIDTH);
        targetPosition.y = random.nextInt(GameView.HEIGHT);
    }

    @Override
    public void addToCanvas() {
        gameView.addOvalToCanvas(position.x, position.y, size, size, 2, true, Color.YELLOW);
        gameView.addOvalToCanvas(targetPosition.x, targetPosition.y, size, size, 2, false, Color.WHITE);
    }

    public void setPatternTargetPosition(){
        if (this.patternList.size() == 0){
            this.patternList.addAll(this.movementPatterns.getPattern(this.movementPatterns.getRandomPattern()));
        }

        this.targetPosition.x = this.patternList.get(0).x;
        this.targetPosition.y = this.patternList.get(0).y;
        this.patternList.remove(0);
    }
}
