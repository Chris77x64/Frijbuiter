package com.mygdx.game.Ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Sprites.Bullet;
import com.mygdx.game.Sprites.ShipSprite;

import org.xguzm.pathfinding.grid.GridCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 14.12.2016.
 */

public abstract class Ship {

    /**
     * Screen
     */
    private PlayScreen screen;

    /**
     * Sprite
     */
    protected ShipSprite sprite;

    /**
     * Attributes
     */
    protected float speed;
    protected float pathfindingSpeed;
    protected int attack_damage;
    protected int health;
    protected int initialHealth;
    protected int shooting_distance;


    /**
     * Types
     */
    public enum Type{Buese, Fleute,Pinas,Galeone,FliegenderHollaender};
    public Type type;

    /**
     * Path to active Ship
     */
    private List<GridCell> pathToActiveShip;
    protected final float PATHFINDING_SPEED_MULTIPLIER = 4;
    private Ship copy;

    public Ship(int pos_x , int pos_y, OrthographicCamera camera, final PlayScreen screen) {
        this.screen = screen;
        this.pathToActiveShip = new ArrayList<GridCell>();
        //this.sprite = new ShipSprite(pos_x,pos_y,camera,screen);
        this.copy = this;

        Timer.schedule(new Timer.Task(){
                           @Override
                           public void run() {
                               if (!screen.getPlayer().getActive_ship().equals(copy)) {
                                   if( outofSightActiveShip()){
                                       int relX = (int) sprite.b2body.getPosition().x / 32;
                                       int relY = (int) sprite.b2body.getPosition().y / 32;

                                       int relDestinationX = (int) screen.getPlayer().getActive_ship().getSprite().getX() / 32;
                                       int relDestinationY = (int) screen.getPlayer().getActive_ship().getSprite().getY() / 32;
                                       pathToActiveShip.clear();
                                       pathToActiveShip = screen.getLevel().pathfinding(relX, relY, relDestinationX, relDestinationY);
                                    }


                                  }
                           }
                           }

                , 0        //    (delay)
                , 3     //    (seconds)
        );



    }

    /**
     *  Movement
     */
    public void move_forward(){
        this.sprite.b2body.applyForceToCenter(0,this.speed,true);

    }
    public void move_backward(){
        this.sprite.b2body.applyForceToCenter(0,-this.speed,true);
    }


    public void move_left(){
        this.sprite.b2body.applyForceToCenter(-this.speed,0,true);

    }

    public void move_right(){
        this.sprite.b2body.applyForceToCenter(this.speed,0,true);

    }


    public void destroy(){
        sprite.b2body.setLinearVelocity(0,0);
        sprite.b2body.setActive(false);
        this.getSprite().setCurrent_vanish_time(System.currentTimeMillis());
    }

    /**
     * Is ship in shooting distance ?
     */

    public boolean in_shooting_distance( EnemyShip ship){
        Vector2 curr_pos = this.getSprite().b2body.getPosition();
        Vector2 enemy_pos = ship.getSprite().b2body.getPosition();

        if( curr_pos.dst(enemy_pos) < this.shooting_distance){
            return true;
        }
        else{
            return false;
        }

    }


    public boolean outofSightActiveShip(){
        Vector2 activeShipPos = this.screen.getPlayer().getActive_ship().getSprite().b2body.getPosition();
        if( this.screen.getCamera().frustum.pointInFrustum(activeShipPos.x,activeShipPos.y,0)){
            return true;
        }
        else return false;
    }

    public Vector2 position(){
        return this.sprite.b2body.getPosition();
    }

    /**
     * Attack
     */
    public void attack( Ship ship){
        ship.health -= this.attack_damage;
    }

    /**
     *  On Hit
     */
    public void onHit( Bullet bullet){
        this.health -= bullet.getDamage();
        this.screen.getEnemy().getBullets().remove(bullet);
        bullet.destroy();
    }

    /**
     * Is alive ?
     *
     */
    public boolean isAlive(){
        if( this.health <= 0 ) return false;
        else return true;
    }

    public void followActiveShip(){
        int relX = (int) this.sprite.b2body.getPosition().x / 32;
        int relY = (int) this.sprite.b2body.getPosition().y / 32;

        if( ! (this.pathToActiveShip == null) && !this.pathToActiveShip.isEmpty()) {
            GridCell tempGoal = this.pathToActiveShip.get(0);

            int xDirection = tempGoal.getX()-relX;
            int yDirection = tempGoal.getY()-relY;

            if( xDirection < 0) {
                this.sprite.currentState = ShipSprite.State.LEFT;
                this.sprite.b2body.applyForceToCenter(-this.pathfindingSpeed,0,true);
            }
            else if (xDirection > 0) {
                this.sprite.currentState = ShipSprite.State.RIGHT;
                this.sprite.b2body.applyForceToCenter(this.pathfindingSpeed,0,true);
            }

            if( yDirection < 0) {
                this.sprite.currentState = ShipSprite.State.BACKWARD;
                this.sprite.b2body.applyForceToCenter(0,-this.pathfindingSpeed,true);
            }
            else if ( yDirection > 0) {
                this.sprite.currentState = ShipSprite.State.FORWARD;
                this.sprite.b2body.applyForceToCenter(0,this.pathfindingSpeed,true);
            }

            this.pathToActiveShip.remove(0);
        }

 }





    /**
     * Getter and Setter
     */

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getAttack_damage() {
        return attack_damage;
    }

    public void setAttack_damage(int attack_damage) {
        this.attack_damage = attack_damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ShipSprite getSprite() {
        return sprite;
    }


    public int getShooting_distance() {
        return shooting_distance;
    }

    public void setShooting_distance(int shooting_distance) {
        this.shooting_distance = shooting_distance;
    }

    public int getInitialHealth() {
        return initialHealth;
    }

    public void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }

    /**
     * String representation
     */
    @Override
    public String toString() {
        return "Ship{" +
                "sprite=" + sprite +
                ", speed=" + speed +
                ", attack_damage=" + attack_damage +
                ", health=" + health +
                ", shooting_distance=" + shooting_distance +
                ", type=" + type +
                '}';
    }
}
