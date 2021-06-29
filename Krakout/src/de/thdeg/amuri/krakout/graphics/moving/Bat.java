package de.thdeg.amuri.krakout.graphics.moving;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.LiveObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.staticobject.Brick;
import de.thdeg.amuri.krakout.movement.Position;

/**
 * This is the Player Figure, that the Player controls. He uses it to manipulate the {@link Pinball} to break {@link Brick}
 */
public class Bat extends LiveObject {
    private final boolean playerGraphic;
    private final boolean hasPowerUp;
    private JumpState jumpState;
    private Status status;
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
        this.speedInPixel = 3;
        this.playerGraphic = true;
        this.position = new Position(20, 280);
        if (playerGraphic) {
            this.hitBox.width = (int) (this.width * this.size);
            this.hitBox.height = (int) (this.height * this.size);
        } else {
            this.hitBox.width = 50;
            this.hitBox.height = 50;
        }
    }

    public void setInvisible() {
        status = Status.DEAD;
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
        if (this.shooting) {
            gamePlayManager.shootPinball(this.position);
            this.shooting = false;
        }
        switch (status) {
            case STANDARD:
                this.gameView.addImageToCanvas("Player.png", this.position.x, this.position.y, this.size, this.rotation);
                break;
            case DEAD:
                break;
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

    @Override
    public void updateStatus() {
        switch (status) {
            case STANDARD:
                jumpingAnimation();
                rollingAnimation();
                break;
            case DEAD:
                break;
        }

    }

    private enum Status {STANDARD, DEAD}

    private enum JumpState {STANDARD, HALF_UP, FULL_UP, HALF_DOWN}
}
