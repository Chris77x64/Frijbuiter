package com.mygdx.game.Sprites.Enemys;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Animation.ShipESPAnimation.ESPGaleoneAnimation;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Sprites.ShipSprite;

/**
 * Created by chris on 12.01.2017.
 */

public class ESPGaleoneSprite extends ShipSprite {

    public ESPGaleoneSprite(Vector2 position, PlayScreen screen) {
        super(position, screen, true);
        this.atlas = new TextureAtlas("Ships/ESPGaleone.atlas");
        super.setRegion(this.atlas.findRegion("ESPGaleoneLinks_20x1"));
        this.animation = new ESPGaleoneAnimation(this);
        //Initialize sprite in Box2D
        this.defineShip((int) position.x, (int) position.y);
    }

}
