package de.thdeg.amuri.krakout.graphics.moving;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.LiveObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.staticobject.Brick;
import de.thdeg.amuri.krakout.movement.Position;

import java.awt.*;

/**
 * This is the Player Figure, that the Player controls. He uses it to manipulate the {@link Pinball} to break {@link Brick}
 */
public class Bat extends LiveObject {
    private enum Status {STANDARD, DAMAGED1, DAMAGED2, GONE}

    private enum JumpState {STANDARD, HALF_UP, FULL_UP, HALF_DOWN}

    private JumpState jumpState;
    private Status status;
    private final boolean playerGraphic;
    private final boolean hasPowerUp;
    private boolean shooting;
    private boolean movingToRight;
    private boolean movingToLeft;


    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public Bat(GameView gameView) {
        super(gameView);
        this.status = Status.STANDARD;
        this.jumpState = JumpState.STANDARD;
        this.live = totalLive;
        this.size = 1.5;
        this.width = 12;
        this.height = 33;
        this.rotation = 0;
        this.hasPowerUp = false;
        this.speedInPixel = 3.5;
        this.playerGraphic = false;
        this.position = new Position(20, 280);
        if (playerGraphic) {
            this.hitBox.width = (int) (this.width * this.size);
            this.hitBox.height = (int) (this.height * this.size);
        } else {
            this.hitBox.width = 50;
            this.hitBox.height = 50;
        }
    }

    @Override
    protected void updateHitBoxPosition() {
        this.hitBox.x = (int) this.position.x;
        this.hitBox.y = (int) this.position.y;
    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {

    }

    /**
     * interacts with {@link Position} to move left when called
     */
    public void left() {
        this.movingToLeft = true;
        if (this.position.x > 20) {
            this.position.left(this.speedInPixel);
        } else {
            this.gamePlayManager.batMovingLeft(speedInPixel);
        }
    }

    /**
     * interacts with {@link Position} to move right when called
     */
    public void right() {
        this.movingToRight = true;
        if (this.position.x + this.width * this.size <= 20) {
            this.position.right(this.speedInPixel);
        } else {
            this.gamePlayManager.batMovingRight(this.speedInPixel);
        }
    }

    /**
     * interacts with {@link Position} to move up when called
     */
    public void up() {
        if (this.position.y >= 53) {
            this.position.up(this.speedInPixel);
        } else {
            this.gamePlayManager.batMovingUp(this.speedInPixel);
        }
    }

    /**
     * interacts with {@link Position} to move down when called
     */
    public void down() {
        if (this.position.y + this.height * this.size <= GameView.HEIGHT - 53) {
            this.position.down(this.speedInPixel);
        } else {
            this.gamePlayManager.batMovingDown(this.speedInPixel);
        }
    }

    /**
     * Manipulates the Output of the playerObject for the task
     */
    public void shoot() {
        this.shooting = true;
    }

    @Override
    public void addToCanvas() {
        if (this.playerGraphic) {
            if (this.shooting) {
                gamePlayManager.shootPinball(this.position);
                this.shooting = false;
            }
            this.gameView.addImageToCanvas("Player.png", this.position.x, this.position.y, this.size, this.rotation);
        } else {
            if (status == Status.STANDARD) {
                switch (jumpState) {
                    case STANDARD:
                        gameView.addTextToCanvas("X", position.x, position.y, 50, Color.WHITE, rotation);
                        break;
                    case HALF_UP:
                    case HALF_DOWN:
                        gameView.addTextToCanvas("X", position.x, position.y - 20, 50, Color.WHITE, rotation);
                        break;
                    case FULL_UP:
                        gameView.addTextToCanvas("X", position.x, position.y - 40, 50, Color.WHITE, rotation);
                        break;
                }
            } else if (status == Status.DAMAGED1) {
                gameView.addTextToCanvas("X", position.x - 20, position.y - 20, 90, Color.WHITE, rotation);
            } else if (status == Status.DAMAGED2) {
                gameView.addTextToCanvas("X", position.x - 40, position.y - 40, 130, Color.WHITE, rotation);
            }
        }
    }

    private void jumpingAnimation() {
        if (gameView.timerExpired("jumpAnimation", "Bat")) {
            gameView.setTimer("jumpAnimation", "Bat", 80);
            if (movingToRight) {
                switchJumpState();
                movingToRight = false;
            } else {
                jumpState = JumpState.STANDARD;
            }
        }
    }

    private void switchJumpState() {
        switch (jumpState) {
            case STANDARD:
                jumpState = JumpState.HALF_UP;
                break;
            case HALF_UP:
                jumpState = JumpState.FULL_UP;
                break;
            case FULL_UP:
                jumpState = JumpState.HALF_DOWN;
                break;
            case HALF_DOWN:
                jumpState = JumpState.STANDARD;
                break;
        }
    }

    private void rollingAnimation() {
        if (movingToLeft) {
            rotation -= 5;
            movingToLeft = false;
        } else {
            rotation = 0;
        }
    }

    private void damaged1Animation() {
        if (gameView.timerExpired("damaged", "Bat")) {
            gameView.setTimer("damaged", "Bat", 300);
            status = Status.DAMAGED2;
        }
    }

    private void damaged2Animation() {
        if (gameView.timerExpired("damaged", "Bat")) {
            gameView.setTimer("damaged", "Bat", 300);
            status = Status.GONE;
        }
    }

    @Override
    public void updateStatus() {
        switch (status) {
            case STANDARD:
                jumpingAnimation();
                rollingAnimation();
                break;
            case DAMAGED1:
                damaged1Animation();
                break;
            case DAMAGED2:
                damaged2Animation();
                break;
            case GONE:
                break;
        }

    }
}
