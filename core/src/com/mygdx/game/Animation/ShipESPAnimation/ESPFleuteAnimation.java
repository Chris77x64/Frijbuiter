package com.mygdx.game.Animation.ShipESPAnimation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animation.ShipAnimation;

/**
 * Created by chris on 11.01.2017.
 */

public class ESPFleuteAnimation extends ShipAnimation {

    public ESPFleuteAnimation(Sprite ship){
        super();
        this.initAnimations(ship);
    }

    @Override
    protected void initAnimations(Sprite ship) {


        // nach vorne fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*129,206,129,201));
        }
        this.go_forward = new Animation(0.5f,frames);
        frames.clear();
        //nach hinten fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*126,1,126,203));
        }
        this.go_backward = new Animation(0.5f,frames);
        frames.clear();
        // nach links fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*130,614,130,203));
        }
        this.go_left = new Animation(0.5f,frames);
        frames.clear();
        // nach rechts fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*129,409,129,203));
        }
        this.go_right = new Animation(0.5f,frames);
        frames.clear();

        /**
         * Brennen
         */


        // brennend nach vorne
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*137,1877,137,211));
        }
        this.burning_forward = new Animation(0.5f,frames);
        frames.clear();

        // brennend nach hinten
        for( int i= 1; i < 24 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*113,1032,113,204));
        }
        this.burning_backward = new Animation(0.5f,frames);
        frames.clear();
        // brennend nach links
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*137,2303,137,211));
        }
        this.burning_left = new Animation(0.5f,frames);
        frames.clear();
        // brennend nach rechts
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), +i*137,2090,137,211));
        }
        this.burning_right = new Animation(0.5f,frames);
        frames.clear();

        /**
         * Untergehend
         */

        // untergehen vorne
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*137,1238,137,211));
        }
        this.perish_forward = new Animation(0.4f,frames);
        frames.clear();
        // untergehen hinten
        for( int i= 1; i < 21 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*128,819,128,211));
        }
        this.perish_backward = new Animation(0.4f,frames);
        frames.clear();
        // untergehen links
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*137,1664,137,211));
        }
        this.perish_left = new Animation(0.4f,frames);
        frames.clear();
        // untergehen rechts
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*137,1451,137,211));
        }
        this.perish_right = new Animation(0.4f,frames);
        frames.clear();


    }

}
