package com.mygdx.game.Levels;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Ship.EnemyShip;

/**
 * Created by chris on 12.01.2017.
 */

public class EnemySpawnPosition {

    private Vector2 position;
    private EnemyShip.Type type;

    public EnemySpawnPosition(EnemyShip.Type type,Vector2 position) {
        this.position = position;
        this.type = type;
    }

    public Vector2 getPosition() {
        return position;
    }

    public EnemyShip.Type getType() {
        return type;
    }
}
