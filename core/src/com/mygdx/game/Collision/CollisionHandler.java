package com.mygdx.game.Collision;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by chris on 04.01.2017.
 */

public class CollisionHandler {

    public CollisionHandler(PlayScreen screen){
        screen.getWorld().setContactListener(new WorldContactListener(screen));
    }
}
