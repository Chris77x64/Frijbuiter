package com.mygdx.game.Ship;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Sprites.Bullet;
import com.mygdx.game.Sprites.ShipSprite;

import org.xguzm.pathfinding.grid.GridCell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by chris on 12.01.2017.
 */

public class EnemyShip {

    /**
     * Screen
     */
    private PlayScreen screen;

    /**
     * Sprite
     */
    protected ShipSprite sprite;

    /**
     * Types
     */
    public enum Type{ESPFleute,ESPGaleone};
    public Type type;

    /**
     * Constants
     */
    protected final int WAKE_UP_DISTANCE = 12;
    protected final int TILE_SIZE = 32;

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
     * Path to active Ship
     */
    private List<GridCell> pathToActiveShip;
    protected final float PATHFINDING_SPEED_MULTIPLIER = 4;

    /**
     *  Is awake?
     */
    protected boolean isAwake;




    public  EnemyShip(int pos_x , int pos_y, OrthographicCamera camera, final PlayScreen screen){
        this.screen = screen;
        this.isAwake = false;
        this.pathToActiveShip = new ArrayList<GridCell>();

        Timer.schedule(new Timer.Task(){
                           @Override
                           public void run() {
                               if(isAwake){
                                   int difficulty = screen.getLevel().getDifficulty();
                                   if( difficulty <= 2){
                                       /**
                                        * random target
                                        */
                                       if( screen.getPlayer().getShips().size() > 0){
                                           int random_number = (int) Math.random() * screen.getPlayer().getShips().size();
                                           int relX = (int) sprite.b2body.getPosition().x / 32;
                                           int relY = (int) sprite.b2body.getPosition().y / 32;
                                           int relDestinationX = (int) screen.getPlayer().getShips().get(random_number).getSprite().b2body.getPosition().x / 32;
                                           int relDestinationY = (int) screen.getPlayer().getShips().get(random_number).getSprite().b2body.getPosition().y / 32;
                                           pathToActiveShip.clear();
                                           pathToActiveShip = screen.getLevel().pathfinding(relX, relY, relDestinationX, relDestinationY);
                                       }
                                   }
                                   else if( difficulty <= 4){
                                       /**
                                        * the players ship is primary target
                                        */
                                       int relX = (int) sprite.b2body.getPosition().x / 32;
                                       int relY = (int) sprite.b2body.getPosition().y / 32;

                                       int relDestinationX = (int) screen.getPlayer().getActive_ship().getSprite().getX() / 32;
                                       int relDestinationY = (int) screen.getPlayer().getActive_ship().getSprite().getY() / 32;

                                       pathToActiveShip.clear();
                                       System.out.println(relX+" || "+relY+" "+relDestinationX+" "+relDestinationY);
                                       pathToActiveShip = screen.getLevel().pathfinding(relX, relY, relDestinationX, relDestinationY);
                                   }
                                   else{
                                       /**
                                        * focus on the weakest target
                                        */
                                       if( screen.getPlayer().getShips().size() > 0){
                                           int min_index = -1;
                                           int min_value = Integer.MAX_VALUE;
                                           for( int i=0; i < screen.getPlayer().getShips().size() ; i++){
                                                if( screen.getPlayer().getShips().get(i).health < min_value ){
                                                    min_value = screen.getPlayer().getShips().get(i).health;
                                                    min_index = i;
                                                }
                                           }
                                           int relX = (int) sprite.b2body.getPosition().x / 32;
                                           int relY = (int) sprite.b2body.getPosition().y / 32;

                                           int relDestinationX = (int) screen.getPlayer().getShips().get(min_index).getSprite().b2body.getPosition().x / 32;
                                           int relDestinationY = (int) screen.getPlayer().getShips().get(min_index).getSprite().b2body.getPosition().y / 32;

                                           pathToActiveShip.clear();
                                           pathToActiveShip = screen.getLevel().pathfinding(relX, relY, relDestinationX, relDestinationY);

                                       }

                                   }


                               }


                           }
                       }

                , 0        //    (delay)
                , 3     //    (seconds)
        );

    }

    public void followShip(){
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
     * Is ship in shooting distance ?
     */

    public boolean in_shooting_distance( Ship ship){
        Vector2 curr_pos = this.getSprite().b2body.getPosition();
        Vector2 enemy_pos = ship.getSprite().b2body.getPosition();

        if( curr_pos.dst(enemy_pos) < this.shooting_distance){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Is ship in WakeUp distance
     */
    public boolean in_wakeup_distance( Ship ship){
        Vector2 curr_pos = this.getSprite().b2body.getPosition();
        Vector2 enemy_pos = ship.getSprite().b2body.getPosition();

        int wakeUpDistanceInPixel = TILE_SIZE * WAKE_UP_DISTANCE;

        if( curr_pos.dst(enemy_pos) < wakeUpDistanceInPixel){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     *  On Hit
     */
    public void onHit( Bullet bullet){
        this.health -= bullet.getDamage();
        this.screen.getPlayer().getBullets().remove(bullet);
        bullet.destroy();
    }


    public void destroy(){
        sprite.b2body.setLinearVelocity(0,0);
        sprite.b2body.setActive(false);
        this.getSprite().setCurrent_vanish_time(System.currentTimeMillis());
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

    public EnemyShip.Type getType() {
        return type;
    }

    public void setType(EnemyShip.Type type) {
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




    public boolean isAwake() {
        return isAwake;
    }

    public void setAwake(boolean awake) {
        isAwake = awake;
    }
}
