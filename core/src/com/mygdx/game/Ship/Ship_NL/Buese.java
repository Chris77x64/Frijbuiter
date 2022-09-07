package com.mygdx.game.Ship.Ship_NL;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Ship.Ship;
import com.mygdx.game.Sprites.ShipNlSprites.BueseSprite;
import com.mygdx.game.Sprites.ShipNlSprites.GaleoneSprite;

/**
 * Created by chris on 14.12.2016.
 */

public class Buese extends Ship {

    public Buese(int pos_x , int pos_y, OrthographicCamera camera, PlayScreen screen){
        super(pos_x,pos_y,camera,screen);
        this.attack_damage = 10;
        this.health = 100;
        this.initialHealth = 100;
        this.speed = 150;
        this.pathfindingSpeed = speed * PATHFINDING_SPEED_MULTIPLIER;
        this.type = Type.Buese;
        this.shooting_distance = 37* 8;
        this.sprite = new BueseSprite(new Vector2(pos_x,pos_y),screen);
    }

}
