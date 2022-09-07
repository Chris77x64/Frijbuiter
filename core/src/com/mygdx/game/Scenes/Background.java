package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Frijbuiter;

/**
 * Created by chris on 03.01.2017.
 */

public class Background extends Actor {

    private Texture background;
    private TextureRegion region;

    public Background(){
        this.background = new Texture("GUI/gui.png");
        this.region = new TextureRegion(background,0,0, Frijbuiter.V_WIDTH,200);
    }

    @Override
    public void draw(Batch batch,float parentAlpha){


        batch.draw(region,getX(),getY());

    }

    public void dispose(){
        this.background.dispose();
    }


}
