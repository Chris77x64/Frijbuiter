package com.mygdx.game.Animation.ShipNLAnimation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animation.ShipAnimation;

/**
 * Created by chris on 04.01.2017.
 */

public class BueseAnimation extends ShipAnimation {

    public BueseAnimation(Sprite ship){
        super();
        this.initAnimations(ship);
    }

    @Override
    protected void initAnimations(Sprite ship) {

        /**
         *  Normal fahren
         */

        // nach vorne fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*115,1060,115,206));
        }
        this.go_forward = new Animation(0.5f,frames);
        frames.clear();
        //nach hinten fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*124,1268,124,206));
        }
        this.go_backward = new Animation(0.5f,frames);
        frames.clear();
        // nach links fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*126,1476,126,200));
        }
        this.go_left = new Animation(0.5f,frames);
        frames.clear();
        // nach rechts fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*127,1678,127,198));
        }
        this.go_right = new Animation(0.5f,frames);
        frames.clear();

        /**
         * Brennen
         */


        // brennend nach vorne
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*99,427,99,205));
        }
        this.burning_forward = new Animation(0.5f,frames);
        frames.clear();

        // brennend nach hinten
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*137,1878,137,211));
        }
        this.burning_backward = new Animation(0.5f,frames);
        frames.clear();
        // brennend nach links
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*137,2304,137,211));
        }
        this.burning_left = new Animation(0.5f,frames);
        frames.clear();
        // brennend nach rechts
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*137,2091,137,211));
        }
        this.burning_right = new Animation(0.5f,frames);
        frames.clear();

        /**
         * Untergehend
         */

        // untergehen vorne
        for( int i= 1; i < 15 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*126,1,126,211));
        }
        this.perish_forward = new Animation(0.5f,frames);
        frames.clear();
        // untergehen hinten
        for( int i= 1; i < 15 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*126,214,126,211));
        }
        this.perish_backward = new Animation(0.5f,frames);
        frames.clear();
        // untergehen links
        for( int i= 1; i < 15 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*137,847,137,211));
        }
        this.perish_left = new Animation(0.5f,frames);
        frames.clear();
        // untergehen rechts
        for( int i= 1; i < 15 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*133,634,133,211));
        }
        this.perish_right = new Animation(0.8f,frames);
        frames.clear();

    }
}
