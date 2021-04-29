package de.thdeg.amuri.krakout.graphics.staticobject;


import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.GameObject;
import de.thdeg.amuri.krakout.graphics.moving.Pinball;
import de.thdeg.amuri.krakout.movement.Position;


/**
 * This Object will be used to create Items, these Items can be enemies,PowerUps or PowerDowns
 */
public class Item extends GameObject {
    //declares what kind of item it is
    private int status;
    //Ammount of items
    private int maxAmmount;
    private String itemVisual;

    /**
     * Constructor for filling in parameters and Building gameView
     *
     * @param gameView
     */
    public Item(GameView gameView) {
        super(gameView);
        this.status = 3;
        this.size = 2;
        this.width = 11;
        this.height = 24;
        this.itemVisual = "Herz.png";
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
    public int getStatus() {
        return status;
    }

    /**
     * To Input the Information, what kind of item manipulates the game/player
     *
     * @param status as number, this will convert in the Output to the actual Item
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * necessary to catch How fast the {@link Pinball} flies to adjust to its speed
     *
     * @param speedInPixel as How fast the ball falls to the player
     */
    public void setSpeedInPixel(double speedInPixel) {
        this.speedInPixel = speedInPixel * 0.8;
    }

    /**
     * Get the Size of the Item for Hitbox calculation
     *
     * @return Size as a Number
     */
    public double getSize() {
        return size;
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
    private String itemStatus(int status) {
        switch (status) {
            case 0:
                return "Bomb";      //Destroy surrounding bricks
            case 1:
                return "Double";    //Second Bat
            case 2:
                return "Expand";    //Big Bat
            case 3:
                return "Glue";      //Ball sticks to Bat
            case 4:
                return "Missile";   //Bat can shoot missiles
            case 5:
                return "Shield";    //Wall behind bat
            case 6:
                return "multiply";  //Doubles Points
            case 7:
                return "HealthUp";  //eXtra Life
            case 8:
                return "SlowBall";  //Slows Ball down
            default:
                return "none";

        }
    }

    private void giveItem(int status) {
    }

    @Override
    public void updatePosition() {
        switch(this.status) {
            case 0:
                this.itemVisual = "Bomb.png";      //Destroy surrounding bricks
                break;
            case 1:
                this.itemVisual = "Double.png";    //Second Bat
                break;
            case 2:
                this.itemVisual = "Expand.png";    //Big Bat
                break;
            case 3:
                this.itemVisual = "Glue.png";      //Ball sticks to Bat
                break;
            case 4:
                this.itemVisual = "Missile.png";   //Bat can shoot missiles
                break;
            case 5:
                this.itemVisual = "Shield.png";    //Wall behind bat
                break;
            case 6:
                this.itemVisual = "DoublePoints.png";  //Doubles Points
                break;
            case 7:
                this.itemVisual = "eXtraLife";  //eXtra Life
                break;
            case 8:
                this.itemVisual = "SlowBall.png";  //Slows Ball down
                break;
        }

    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(itemVisual, position.x, position.y, size, rotation);
    }

    @Override
    public String toString() {
        return "Item, " + itemStatus(this.status) + " : " + position;
    }
}
