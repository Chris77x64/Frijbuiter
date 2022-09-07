package com.mygdx.game.Animation.ShipNLAnimation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animation.ShipAnimation;

/**
 * Created by chris on 04.01.2017.
 */

public class FliegenderHollaenderAnimation extends ShipAnimation {

    public FliegenderHollaenderAnimation(Sprite ship){
        super();
        this.initAnimations(ship);
    }

    @Override
    protected void initAnimations(Sprite ship) {

        // nach vorne fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*131,1008,131,206));
        }
        this.go_forward = new Animation(0.5f,frames);
        frames.clear();
        //nach hinten fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*130,804,130,202));
        }
        this.go_backward = new Animation(0.5f,frames);
        frames.clear();
        // nach links fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*128,614,128,188));
        }
        this.go_left = new Animation(0.5f,frames);
        frames.clear();
        // nach rechts fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*127,424,127,188));
        }
        this.go_right = new Animation(0.5f,frames);
        frames.clear();

        /**
         * Brennen
         */

        // brennend nach vorne
        for( int i= 1; i < 10 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), +i*137,211,137,211));
        }
        this.burning_forward = new Animation(0.6f,frames);
        frames.clear();

        // brennend nach hinten
        for( int i= 1; i < 10 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), 1373+i*132,212,132,211));
        }
        this.burning_backward = new Animation(0.6f,frames);
        frames.clear();
        // brennend nach links
        for( int i= 1; i < 10 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*131,1,131,208));
        }
        this.burning_left = new Animation(0.6f,frames);
        frames.clear();
        // brennend nach rechts
        for( int i= 1; i < 10 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), 1373+i*131,3,131,207));
        }
        this.burning_right = new Animation(0.6f,frames);
        frames.clear();

        this.perish_forward = this.burning_forward;
        this.perish_backward = this.burning_backward;
        this.perish_left = this.burning_left;
        this.perish_right = this.burning_right;

    }
}
