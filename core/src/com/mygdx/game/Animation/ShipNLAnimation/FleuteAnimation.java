package com.mygdx.game.Animation.ShipNLAnimation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animation.ShipAnimation;

/**
 * Created by chris on 04.01.2017.
 */

public class FleuteAnimation extends ShipAnimation {

    public FleuteAnimation(Sprite ship){
        super();
        this.initAnimations(ship);
    }

    @Override
    protected void initAnimations(Sprite ship) {

        /**
         * Fahren
         */

        // nach vorne fahren
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), 2649+i*129,1,129,203));
        }
        this.go_forward = new Animation(0.5f,frames);
        frames.clear();
        //nach hinten fahren
        for( int i= 1; i < 5 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*71,44,71,111));
        }
        this.go_backward = new Animation(0.5f,frames);
        frames.clear();
        // nach links fahren
        for( int i= 1; i < 5 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), 2717+i*131,384,131,203));
        }
        this.go_left = new Animation(0.5f,frames);
        frames.clear();
        // nach rechts fahren
        for( int i= 1; i < 5 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), 2717+i*129,589,129,205));
        }
        this.go_right = new Animation(0.5f,frames);
        frames.clear();

        /**
         * Brennen
         */


        // brennend nach vorne
        for( int i= 1; i < 23 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*118,583,118,211));
        }
        this.burning_forward = new Animation(0.5f,frames);
        frames.clear();

        // brennend nach hinten
        for( int i= 1; i < 21 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*126,157,126,211));
        }
        this.burning_backward = new Animation(0.5f,frames);
        frames.clear();
        // brennend nach links
        for( int i= 1; i < 21 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*129,370,129,211));
        }
        this.burning_left = new Animation(0.5f,frames);
        frames.clear();
        // brennend nach rechts
        for( int i= 1; i < 20 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), 2712+i*134,206,134,176));
        }
        this.burning_right = new Animation(0.5f,frames);
        frames.clear();

        /**
         * Untergehend
         */

        // untergehen vorne
        for( int i= 1; i < 43 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*137,1426,137,203));
        }
        this.perish_forward = new Animation(0.09f,frames);
        frames.clear();
        // untergehen hinten
        for( int i= 1; i < 43 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*135,1222,135,202));
        }
        this.perish_backward = new Animation(0.09f,frames);
        frames.clear();
        // untergehen links
        for( int i= 1; i < 35 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*137,1009,137,211));
        }
        this.perish_left = new Animation(0.125f,frames);
        frames.clear();
        // untergehen rechts
        for( int i= 1; i < 35 ; i++ ){
            frames.add(new TextureRegion( ship.getTexture(), i*137,796,137,211));
        }
        this.perish_right = new Animation(0.125f,frames);
        frames.clear();

    }
}
