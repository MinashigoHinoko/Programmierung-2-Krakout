package de.thdeg.amuri.krakout.graphics.moving.alien;

import de.thdeg.amuri.krakout.gameview.GameView;
import de.thdeg.amuri.krakout.graphics.basicobject.AlienObject;
import de.thdeg.amuri.krakout.graphics.basicobject.MovingGameObject;
import de.thdeg.amuri.krakout.graphics.basicobject.collide.CollidableGameObject;
import de.thdeg.amuri.krakout.movement.Position;

import java.util.ArrayList;


/**
 * This is an Enemy Face that will give you Bonus points and destroys itself if living too long.
 * It moves into random directions
 *
 * @see Position
 */
public class Face extends AlienObject implements MovingGameObject {
    private final static String FACE_FACE1 = "Face.png";
    private final static String FACE_FACE2 = "Face2.png";
    private final static String FACE_FACE3 = "Face3.png";
    protected int timer;
    private AnimationStatus animationStatus;

    /**
     * This is the extension constructor, here you can find prebuild parameters.
     *
     * @param gameView             this is for Initialising the game object
     * @param objectsToCollideWith Object list to collide with
     */
    public Face(GameView gameView, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView, objectsToCollideWith);
        this.width = 23;
        this.height = 23;
        this.size = 1.5;
        this.speedInPixel = 3.5;
        this.hitBox.width = (int) (this.width * this.size);
        this.hitBox.height = (int) (this.height * this.size);
        this.animationStatus = AnimationStatus.STAGE_ONE;
        this.timer = 1;
    }

    /**
     * @param timer how many Faces exist
     */
    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public void addToCanvas() {

        switch (animationStatus) {
            case STAGE_ONE:
                this.gameView.addImageToCanvas(FACE_FACE1, this.position.x, this.position.y, this.size, this.rotation);
                break;
            case STAGE_TWO:
                this.gameView.addImageToCanvas(FACE_FACE2, this.position.x, this.position.y, this.size, this.rotation);
                break;
            case STAGE_THREE:
                this.gameView.addImageToCanvas(FACE_FACE3, this.position.x, this.position.y, this.size, this.rotation);
                break;
        }
    }

    @Override
    public void updateStatus() {
        if (this.gameView.timerExpired("AnimationFace", "Face" + this.timer)) {
            switch (animationStatus) {
                case STAGE_ONE:
                    this.animationStatus = AnimationStatus.STAGE_TWO;
                    break;
                case STAGE_TWO:
                    this.animationStatus = AnimationStatus.STAGE_THREE;
                    break;
                case STAGE_THREE:
                    this.animationStatus = AnimationStatus.STAGE_ONE;
                    break;
            }
            this.gameView.setTimer("AnimationFace", "Face" + this.timer, (long) (this.speedInPixel * 100));
        }
    }

    enum AnimationStatus {
        STAGE_ONE, STAGE_TWO, STAGE_THREE;

        AnimationStatus() {

        }
    }
}
