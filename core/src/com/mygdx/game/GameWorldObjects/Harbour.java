package com.mygdx.game.GameWorldObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by chris on 23.11.2016.
 */

public class Harbour extends GameWorldObject{

    private Vector3 position;

    private Texture harbour;

    // Coordinates
    private int x;
    private int y;

    // Rectangle for collision check
    private Rectangle bound;

    public Harbour( int x , int y){
            this.x = x;
            this.y = y;
            this.harbour = new Texture("harbour_small.png");
            this.position = new Vector3(x,y,0);
            this.bound = new Rectangle(x,y,harbour.getWidth(),harbour.getHeight());
    }

    @Override
    public void dispose(){
        this.harbour.dispose();
    }

    @Override
    public Vector3 getPosition() {
        return position;
    }

    public Texture getHarbour() {
        return harbour;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public boolean collision( Rectangle player){
        return player.overlaps(this.bound);
    }


}
