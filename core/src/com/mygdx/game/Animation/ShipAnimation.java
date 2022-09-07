package com.mygdx.game.Animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Sprites.ShipSprite;

/**
 * Created by chris on 04.01.2017.
 */

public abstract class ShipAnimation {

    // Movement Animations
    protected Animation go_left;
    protected Animation go_right;
    protected Animation go_forward;
    protected Animation go_backward;

    // Burning Animations
    protected Animation burning_left;
    protected Animation burning_right;
    protected Animation burning_forward;
    protected Animation burning_backward;

    // Animation for destroyed ship
    protected Animation perish_left;
    protected Animation perish_right;
    protected Animation perish_forward;
    protected Animation perish_backward;

    // Array of specific frames
    protected Array<TextureRegion> frames;

    public ShipAnimation(){
        this.frames = new Array<TextureRegion>();
    }

    protected abstract void initAnimations(Sprite ship);

    public TextureRegion processMovements(ShipSprite.State currentState, float stateTimer, boolean isBurning, boolean isPerishing){

        if( ! isBurning) {

            switch (currentState) {
                case FORWARD:
                    return this.go_forward.getKeyFrame(stateTimer, true);

                case BACKWARD:
                    return this.go_backward.getKeyFrame(stateTimer, true);

                case LEFT:
                    return this.go_left.getKeyFrame(stateTimer, true);

                case RIGHT:
                    return this.go_right.getKeyFrame(stateTimer, true);

                default:
                    return this.go_left.getKeyFrame(stateTimer, true);
            }
        }
        else{
            if( !isPerishing) {
                switch (currentState) {
                    case FORWARD:
                        return this.burning_forward.getKeyFrame(stateTimer, true);

                    case BACKWARD:
                        return this.burning_backward.getKeyFrame(stateTimer, true);

                    case LEFT:
                        return this.burning_left.getKeyFrame(stateTimer, true);

                    case RIGHT:
                        return this.burning_right.getKeyFrame(stateTimer, true);

                    default:
                        return this.burning_left.getKeyFrame(stateTimer, true);
                }
            }
            else{
                switch (currentState) {
                    case FORWARD:
                        return this.perish_forward.getKeyFrame(stateTimer, true);

                    case BACKWARD:
                        return this.perish_backward.getKeyFrame(stateTimer, true);

                    case LEFT:
                        return this.perish_left.getKeyFrame(stateTimer, true);

                    case RIGHT:
                        return this.perish_right.getKeyFrame(stateTimer, true);

                    default:
                        return this.burning_left.getKeyFrame(stateTimer, true);
                }
            }
        }

    }
}
