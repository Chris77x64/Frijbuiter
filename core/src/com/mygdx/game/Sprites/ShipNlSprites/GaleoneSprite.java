package com.mygdx.game.Sprites.ShipNlSprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Animation.ShipNLAnimation.GaleoneAnimation;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Sprites.ShipSprite;

/**
 * Created by chris on 04.01.2017.
 */

public class GaleoneSprite extends ShipSprite {

    public GaleoneSprite(Vector2 position, PlayScreen screen){
        super(position,screen,false);
        this.atlas = new TextureAtlas("Ships/Galeone.pack");
        super.setRegion(this.atlas.findRegion("GaleoneLinks_20x1"));
        this.animation = new GaleoneAnimation(this);
        //Initialize sprite in Box2D
        this.defineShip((int) position.x, (int) position.y);
    }

}
