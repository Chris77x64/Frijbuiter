package com.mygdx.game.Ship.Ship_NL;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Ship.Ship;
import com.mygdx.game.Sprites.ShipNlSprites.GaleoneSprite;

/**
 * Created by chris on 14.12.2016.
 */

public class Galeone extends Ship {

    public Galeone(int pos_x , int pos_y, OrthographicCamera camera, PlayScreen screen){
        super(pos_x,pos_y,camera,screen);
        this.attack_damage = 120;
        this.health = 1200;
        this.initialHealth = 1200;
        this.speed = 220;
        this.pathfindingSpeed = speed * PATHFINDING_SPEED_MULTIPLIER;
        this.type = Type.Galeone;
        this.shooting_distance = 11 * 37;
        this.sprite = new GaleoneSprite(new Vector2(pos_x,pos_y),screen);

    }
}
