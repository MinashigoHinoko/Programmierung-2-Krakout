package de.thdeg.amuri.krakout.graphics.staticobject;


import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.movement.Position;


/**
 * This Object will be used to create Items, these Items can be enemies,PowerUps or PowerDowns
 */
public class Item extends CollidableGameObject {
    private String itemVisual;
    private final Status status;
    private int maxAmmount;
    /**
     * Constructor for filling in parameters and Building gameView
     *
     * @param gameView
     */
    public Item(GameView gameView) {
        super(gameView);
        this.status = Status.NONE;
        this.size = 2;
        this.width = 11;
        this.height = 24;
        this.itemVisual = null;
        this.hitBox.width = (int) (this.width * this.size);
        this.hitBox.height = (int) (this.height * this.size);
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
     * Max Ammount of Items per game compared to {@link Brick}
     *
     * @param brickAmmount the ammount of {@link Brick} in the Game
     */
    public void setMaxAmmount(int brickAmmount) {
        this.maxAmmount = brickAmmount / 5;
    }

    /**
     * To Output the Information, what kind of item manipulates the game/player
     *
     * @return status as number, this will convert in the Output to the actual Item
     */
    public Status getStatus() {
        return status;
    }

    /**
     * necessary to catch How fast the {@link Pinball} flies to adjust to its speed
     *
     * @param speedInPixel as How fast the ball falls to the player
     */
    public void setSpeedInPixel(double speedInPixel) {
        this.speedInPixel = speedInPixel * 0.8;
    }

    @Override
    public double getSize() {
        return super.getSize();
    }

    /**
     * necessary to use the {@link Position} of the destroyed {@link Brick}
     *
     * @param position as position of the {@link Brick}
     */
    public void setPosition(Position position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }

    /**
     * For determining which kind of Item it is
     *
     * @param status takes the Status and looks for it in the switch
     * @return kind of Item
     */
    private void giveItem(int status) {
    }

    @Override
    public void addToCanvas() {
        switch (status){
            case BOMB:
                this.itemVisual= "Bomb.png";
                break;
            case DOUBLE:
                this.itemVisual= "Double.png";
                break;
            case EXPAND:
                this.itemVisual= "Expand.png";
                break;
            case GLUE:
                this.itemVisual= "Glue.png";
                break;
            case MISSILE:
                this.itemVisual= "Missile.png";
                break;
            case SHIELD:
                this.itemVisual= "Shield.png";
                break;
            case MULTIPLY:
                this.itemVisual= "DoublePoints.png";
                break;
            case HEALTHUP:
                this.itemVisual= "Herz.png";
                break;
            case SLOWBALL:
                this.itemVisual= "SlowBall.png";
                break;
            case NONE:
                this.itemVisual= null;
                break;
        }
        gameView.addImageToCanvas(itemVisual, position.x, position.y, size, rotation);
    }

    @Override
    protected void updateStatus() {

    }

    enum Status {
        BOMB, DOUBLE, EXPAND, GLUE, MISSILE, SHIELD, MULTIPLY, HEALTHUP, SLOWBALL, NONE
    }
}

