package com.mygdx.game.Collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Ship.EnemyShip;
import com.mygdx.game.Ship.Ship;
import com.mygdx.game.Sprites.Bullet;

/**
 * Created by chris on 04.01.2017.
 */

public class WorldContactListener implements ContactListener{

    private PlayScreen screen;

    /**
     * A contact listener is what gets called when two fixtures
     * in Box2D collide with each other
     */
    public WorldContactListener( PlayScreen screen){
        this.screen = screen;
    }


    @Override
    public void beginContact(Contact contact) {
        /**
         * A contact consists of 2 fixtures, fixture A and Fixture B
         * and we have to figure out which is which
         */
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();


        if( fixA.getUserData() == "Bullet" && fixB.getUserData() == "shootingBodyEnemy"
                && getPlayerBullet(fixA.getBody().getPosition()) !=null ){

            this.getEnemyShip(fixB.getBody().getPosition()).onHit(getPlayerBullet(fixA.getBody().getPosition()));
        }
        if( fixA.getUserData() == "shootingBodyEnemy" && fixB.getUserData() == "Bullet"  &&
                getPlayerBullet(fixB.getBody().getPosition()) !=null){

            this.getEnemyShip(fixA.getBody().getPosition()).onHit(getPlayerBullet(fixB.getBody().getPosition()));
        }
        if( fixA.getUserData() == "Bullet" && fixB.getUserData() == "shootingBody" &&
                getEnemyBullet(fixA.getBody().getPosition()) != null ){
            System.out.println("HIT");
            this.getPlayerShip(fixB.getBody().getPosition()).onHit(getEnemyBullet(fixA.getBody().getPosition()));
        }
        if( fixA.getUserData() == "shootingBody" && fixB.getUserData() == "Bullet" &&
                getEnemyBullet(fixB.getBody().getPosition()) != null){
            System.out.println("HIIIT");
            this.getPlayerShip(fixA.getBody().getPosition()).onHit(getEnemyBullet(fixB.getBody().getPosition()));
        }


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


    public Ship getPlayerShip(Vector2 position){
        for( Ship ownShip : screen.getPlayer().getShips() ){
            if( ownShip.getSprite().b2body.getPosition().equals(position)){
                return ownShip;
            }
        }
        return null;
    }

    public EnemyShip getEnemyShip(Vector2 position){
        for( EnemyShip ship : screen.getEnemy().getShips() ){
            if( ship.getSprite().b2body.getPosition().equals(position)){
                return ship;
            }
        }
        return null;
    }


    public Bullet getPlayerBullet(Vector2 position){
        for( Bullet bullet : screen.getPlayer().getBullets()){
            if( bullet.getPosition().equals(position)) return bullet;
        }
        return null;
    }

    public Bullet getEnemyBullet(Vector2 position){
        for( Bullet bullet : screen.getEnemy().getBullets()){
            if( bullet.getPosition().equals(position)) return bullet;
        }
        return null;
    }

}
