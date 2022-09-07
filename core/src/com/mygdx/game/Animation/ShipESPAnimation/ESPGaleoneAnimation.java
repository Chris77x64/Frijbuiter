package com.mygdx.game.Animation.ShipESPAnimation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animation.ShipAnimation;

/**
 * Created by chris on 11.01.2017.
 */

public class ESPGaleoneAnimation extends ShipAnimation {

    public ESPGaleoneAnimation(Sprite ship){
        super();
        this.initAnimations(ship);
    }

    @Override
    protected void initAnimations(Sprite ship) {

        // nach vorne fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*131,1681,131,203));
        }
        this.go_forward = new Animation(0.5f,frames);
        frames.clear();
        //nach hinten fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*132,1886,132,202));
        }
        this.go_backward = new Animation(0.5f,frames);
        frames.clear();
        // nach links fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*130,1479,130,200));
        }
        this.go_left = new Animation(0.5f,frames);
        frames.clear();
        // nach rechts fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*130,1279,130,198));
        }
        this.go_right = new Animation(0.5f,frames);
        frames.clear();

        /**
         * Brennen
         */


        // brennend nach vorne
        for( int i= 1; i < 10 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*137,1,137,211));
        }
        this.burning_forward = new Animation(0.5f,frames);
        frames.clear();

        // brennend nach hinten
        for( int i= 1; i < 10 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*137,214,137,211));
        }
        this.burning_backward = new Animation(0.5f,frames);
        frames.clear();
        // brennend nach links
        for( int i= 1; i < 10 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), 1373+i*137,5,137,208));
        }
        this.burning_left = new Animation(0.5f,frames);
        frames.clear();
        // brennend nach rechts
        for( int i= 1; i < 10 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), 1373+i*137,215,137,210));
        }
        this.burning_right = new Animation(0.5f,frames);
        frames.clear();

        /**
         * Untergehend
         */

        // untergehen vorne
        for( int i= 1; i < 15 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*137,853,137,211));
        }
        this.perish_forward = new Animation(0.6f,frames);
        frames.clear();
        // untergehen hinten
        for( int i= 1; i < 15 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*131,427,131,211));
        }
        this.perish_backward = new Animation(0.6f,frames);
        frames.clear();
        // untergehen links
        for( int i= 1; i < 15 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*137,1066,137,211));
        }
        this.perish_left = new Animation(0.6f,frames);
        frames.clear();
        // untergehen rechts
        for( int i= 1; i < 15 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*133,640,133,211));
        }
        this.perish_right = new Animation(0.6f,frames);
        frames.clear();
    }



}
