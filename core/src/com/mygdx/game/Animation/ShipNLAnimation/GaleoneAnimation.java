package com.mygdx.game.Animation.ShipNLAnimation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animation.ShipAnimation;

/**
 * Created by chris on 04.01.2017.
 */

public class GaleoneAnimation extends ShipAnimation {

    public GaleoneAnimation(Sprite ship){
        super();
        this.initAnimations(ship);
    }

    @Override
    protected void initAnimations(Sprite ship) {
        // nach vorne fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*132,221,132,206));
        }
        this.go_forward = new Animation(0.5f,frames);
        frames.clear();
        //nach hinten fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), 5305+i*134,427,134,203));
        }
        this.go_backward = new Animation(0.5f,frames);
        frames.clear();
        // nach links fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), 2663+i*132,428,132,202));
        }
        this.go_left = new Animation(0.5f,frames);
        frames.clear();
        // nach rechts fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*133,429,133,201));
        }
        this.go_right = new Animation(0.5f,frames);
        frames.clear();

        /**
         * Brennen
         */


        // brennend nach vorne
        for( int i= 1; i < 10 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), 6759+i*137,214,137,211));
        }
        this.burning_forward = new Animation(0.7f,frames);
        frames.clear();

        // brennend nach hinten
        for( int i= 1; i < 10 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), 4015+i*137,214,137,211));
        }
        this.burning_backward = new Animation(0.7f,frames);
        frames.clear();
        // brennend nach links
        for( int i= 1; i < 10 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), 5387+i*137,214,137,211));
        }
        this.burning_left = new Animation(0.7f,frames);
        frames.clear();
        // brennend nach rechts
        for( int i= 1; i < 10 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), 2643+i*137,217,137,209));
        }
        this.burning_right = new Animation(0.7f,frames);
        frames.clear();

        /**
         * Untergehend
         */

        // untergehen vorne
        for( int i= 1; i < 15 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), 6022+i*137,1,137,211));
        }
        this.perish_forward = new Animation(0.6f,frames);
        frames.clear();
        // untergehen hinten
        for( int i= 1; i < 15 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*131,8,131,211));
        }
        this.perish_backward = new Animation(0.6f,frames);
        frames.clear();
        // untergehen links
        for( int i= 1; i < 15 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), 3965+i*137,1,137,211));
        }
        this.perish_left = new Animation(0.6f,frames);
        frames.clear();
        // untergehen rechts
        for( int i= 1; i < 15 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), 1968+i*133,4,133,211));
        }
        this.perish_right = new Animation(0.6f,frames);
        frames.clear();


    }

}
