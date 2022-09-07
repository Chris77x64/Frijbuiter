package com.mygdx.game.Ship.Ship_NL;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Ship.Ship;
import com.mygdx.game.Sprites.ShipNlSprites.FleuteSprite;
import com.mygdx.game.Sprites.ShipNlSprites.FliegenderHollaenderSprite;

/**
 * Created by chris on 14.12.2016.
 */

public class FliegenderHollaender extends Ship {

    public FliegenderHollaender(int pos_x , int pos_y, OrthographicCamera camera, PlayScreen screen){
        super(pos_x,pos_y,camera,screen);
        this.attack_damage = 100;
        this.health = 2500;
        this.initialHealth = 2500;
        this.speed = 300;
        this.pathfindingSpeed = speed * PATHFINDING_SPEED_MULTIPLIER;
        this.type = Type.FliegenderHollaender;
        this.shooting_distance = 14 * 37;
        this.sprite = new FliegenderHollaenderSprite(new Vector2(pos_x,pos_y),screen);
    }

}
