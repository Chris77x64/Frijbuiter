package com.mygdx.game.Ship.Ship_NL;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Ship.Ship;
import com.mygdx.game.Sprites.ShipNlSprites.BueseSprite;
import com.mygdx.game.Sprites.ShipNlSprites.FleuteSprite;

/**
 * Created by chris on 14.12.2016.
 */

public class Fleute extends Ship {

    public Fleute(int pos_x , int pos_y, OrthographicCamera camera, PlayScreen screen) {
        super(pos_x,pos_y,camera,screen);
        this.attack_damage = 30;
        this.health = 200;
        this.initialHealth = 200;
        this.speed = 180;
        this.pathfindingSpeed = speed * PATHFINDING_SPEED_MULTIPLIER;
        this.type = Type.Fleute;
        this.shooting_distance = 9 * 37;
        this.sprite = new FleuteSprite(new Vector2(pos_x,pos_y),screen);
    }


}
