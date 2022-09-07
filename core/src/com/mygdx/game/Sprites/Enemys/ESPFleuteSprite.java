package com.mygdx.game.Sprites.Enemys;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Animation.ShipESPAnimation.ESPFleuteAnimation;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Sprites.ShipSprite;

/**
 * Created by chris on 12.01.2017.
 */

public class ESPFleuteSprite extends ShipSprite {

    public ESPFleuteSprite(Vector2 position, PlayScreen screen) {
        super(position, screen, true);
        this.atlas = new TextureAtlas("Ships/ESPFleute.atlas");
        super.setRegion(this.atlas.findRegion("ESPFleuteLinks_20x1"));
        this.animation = new ESPFleuteAnimation(this);
        //Initialize sprite in Box2D
        this.defineShip((int) position.x, (int) position.y);
    }

}
