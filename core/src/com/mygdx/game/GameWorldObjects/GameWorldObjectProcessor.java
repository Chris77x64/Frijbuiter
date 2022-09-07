package com.mygdx.game.GameWorldObjects;

import com.mygdx.game.Frijbuiter;

import java.util.ArrayList;

/**
 * Created by chris on 30.11.2016.
 */

public class GameWorldObjectProcessor {


    private Frijbuiter game;

    private ArrayList<GameWorldObject> elements;

    // current GameWorld Objects
    private Harbour harbour;

    public GameWorldObjectProcessor( Frijbuiter game){
        this.game = game;
        this.harbour = new Harbour(1190,1324);

        // add objects to object list
        this.elements = new ArrayList<GameWorldObject>();
        this.elements.add(harbour);

    }

    public void render( ){
        this.game.batch.draw(this.harbour.getHarbour(),this.harbour.getPosition().x,this.harbour.getPosition().y);
    }

    public void dispose(){
        for( GameWorldObject object : elements){
            object.dispose();
        }
    }

    public Harbour getHarbour() {
        return harbour;
    }

}
