package com.mygdx.game.GameWorldObjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by chris on 30.11.2016.
 */

public abstract class GameWorldObject {

    // Coordinates
    private int x;
    private int y;

    // Rectangle for collision check
    private Rectangle bound;

    // Position in R^3
    private Vector3 position;

    public GameWorldObject( ){

    }

    /**
     * Abstract methods
     */

    public abstract void dispose();

    public abstract Vector3 getPosition();

    public abstract int getX();

    public abstract int getY();

    public abstract boolean collision( Rectangle player);

}
