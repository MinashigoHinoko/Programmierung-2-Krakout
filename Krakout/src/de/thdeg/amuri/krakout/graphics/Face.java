package de.thdeg.amuri.krakout.graphics;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.movement.Position;


public class Face extends GameObject {
    private int live;
    private boolean isHit;
    private double x;

    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView this is for Initialising the game object
     */
    public Face(GameView gameView) {
        super(gameView);
        this.live = 2;
        this.width = 46;
        this.height = 20;
        this.size = 2;
        this.speedInPixel = 10;
        //this.position = new Position(860, 160);
        this.position = new Position(GameView.WIDTH / 2, GameView.HEIGHT / 2);
        this.isHit = false;
    }

    @Override
    public void updatePosition() {
        x = Math.random() * 10;
        switch ((int) x % 10) {
            case 1:
                if (position.x <= GameView.WIDTH - width) {
                    this.position.right(speedInPixel);
                    System.out.println((int) x + " penis2");
                    break;
                } else {
                    System.out.println((int) x);
                    this.position.left(speedInPixel);
                    break;
                }
            case 2:
                if (position.x >= (GameView.WIDTH - GameView.WIDTH) + width) {
                    this.position.left(speedInPixel);
                    System.out.println((int) x + " penis2");
                    break;
                } else {
                    System.out.println((int) x);
                    this.position.right(speedInPixel);
                    break;
                }
            case 3:
                if (position.y <= (50) + height) {
                    this.position.down(speedInPixel);
                    System.out.println((int) x + " penis");
                    break;
                } else {
                    this.position.up(speedInPixel);
                    System.out.println((int) x);
                    break;
                }
            case 4:
                if (position.y >= ((GameView.HEIGHT - 50) - height)) {
                    System.out.println((int) x + " penis");
                    this.position.up(speedInPixel);
                    break;
                } else {
                    this.position.down(speedInPixel);
                    System.out.println((int) x);
                    break;
                }
        }


    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("Face.png", this.position.x, this.position.y, this.size, this.rotation);
    }
}
