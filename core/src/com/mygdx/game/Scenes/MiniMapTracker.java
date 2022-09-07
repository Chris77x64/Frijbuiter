package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Ship.EnemyShip;
import com.mygdx.game.Ship.Ship;

import java.util.ArrayList;

/**
 * Created by chris on 11.01.2017.
 */

public class MiniMapTracker {

    private final String ORANGE_DOT_LOCATION = "GUI/PlayerDot.png";
    private final String RED_DOT_LOCATION = "GUI/EnemyDot.png";

    private final float MINIMAP_ASPECT_X = 236;
    private final float MINIMAP_ASPECT_Y = 165;

    private PlayScreen screen;

    private ArrayList<Sprite> dots;



    public MiniMapTracker(PlayScreen screen){
        this.screen = screen;
        this.dots = new ArrayList<Sprite>();

    }


    public void update(){

        for( Sprite sprite : dots){
            sprite.getTexture().dispose();
        }
        dots.clear();

        for(Ship ship: this.screen.getPlayer().getShips()){
            float posX = ship.getSprite().getX() / 32;
            float posY = ship.getSprite().getY() / 32;

            float posMinimapX = (posX * (MINIMAP_ASPECT_X / this.screen.getLevel().getMap().getProperties().get("width", Integer.class))) / 1.710f;
            float posMinimapY = (posY * (MINIMAP_ASPECT_Y / this.screen.getLevel().getMap().getProperties().get("height", Integer.class))) / 1.580f;

            Texture ORANGE_DOT_TEXTURE = new Texture(ORANGE_DOT_LOCATION);
            TextureRegion ORANGE_REGION = new TextureRegion(ORANGE_DOT_TEXTURE);

            Sprite temp = new Sprite(ORANGE_REGION);
            temp.setPosition((int) posMinimapX,(int) posMinimapY);
            this.dots.add(temp);
        }
        for( EnemyShip ship: this.screen.getEnemy().getShips() ){
            float posX = ship.getSprite().getX() / 32;
            float posY = ship.getSprite().getY() / 32;

            float posMinimapX = (posX * (MINIMAP_ASPECT_X / this.screen.getLevel().getMap().getProperties().get("width", Integer.class))) / 1.710f;
            float posMinimapY = (posY * (MINIMAP_ASPECT_Y / this.screen.getLevel().getMap().getProperties().get("height", Integer.class))) / 1.580f;

            Texture RED_DOT_TEXTURE = new Texture(RED_DOT_LOCATION);
            TextureRegion RED_REGION = new TextureRegion(RED_DOT_TEXTURE);
            Sprite temp = new Sprite(RED_REGION);
            temp.setPosition((int) posMinimapX,(int) posMinimapY);
            this.dots.add(temp);
        }
    }

    public void render(SpriteBatch spriteBatch){
        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(this.screen.getHud().getMiniMap().getCamera().combined);
        for ( Sprite sprite : dots){
            //System.out.println(sprite.getX()+" "+sprite.getY());
            sprite.draw(spriteBatch);
        }
        spriteBatch.end();
    }

}
