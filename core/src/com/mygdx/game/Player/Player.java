package com.mygdx.game.Player;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Screens.MainMenueScreen;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Ship.EnemyShip;
import com.mygdx.game.Ship.Ship_NL.Fleute;
import com.mygdx.game.Ship.Ship_NL.Galeone;
import com.mygdx.game.Sprites.Bullet;
import com.mygdx.game.Ship.Ship;
import com.mygdx.game.Ship.Ship_NL.Buese;
import com.mygdx.game.Ship.Ship_NL.FliegenderHollaender;

import java.util.ArrayList;

/**
 * Created by chris on 30.11.2016.
 */

public class Player {


    // Camera & PlayScreen
    private OrthographicCamera camera;
    private PlayScreen screen;

    // Attributes
    private int num_coins;
    private int numBuese;
    private int NumPinas;
    private int NumFleute;
    private int NumGaleone;
    private int NumFliegenderHollaender;

    // Ship Management
    private ArrayList<Ship> ships;
    private Ship active_ship;

    // Handling Bullets
    private ArrayList<Bullet> bullets;
    private final int BULLET_VANISH_TIME = 5;
    private final int SHIP_VANISH_TIME = 3;

    public Player(OrthographicCamera camera, PlayScreen screen){

        this.num_coins = 0;
        this.numBuese = 0;
        this.NumFleute = 0;
        this.NumGaleone = 0;
        this.NumPinas = 0;
        this.NumFliegenderHollaender = 0;

        /**
         * Set up Camera & PlayScreen
         */
        this.camera = camera;
        this.screen = screen;

        /**
         * Initialize ship to start with
         */
        this.ships = this.screen.getGame().getInventory().createShips(this.screen);

        this.numBuese += 1;
        this.active_ship = ships.get(0);

        this.bullets = new ArrayList<Bullet>();

        Timer.schedule(new Timer.Task(){
                           @Override
                           public void run() {
                                track_bullets();
                           }
                       }
                , 0        //    (delay)
                , 2     //    (seconds)
        );

    }


    public void track_bullets() {
        if (this.screen.getEnemy().getShips().size() > 0) {
            ArrayList<EnemyShip> possibleTargets = new ArrayList<EnemyShip>();
            for (Ship ship : ships) {
                for (EnemyShip enemyShip : this.screen.getEnemy().getShips()) {
                    if (ship.in_shooting_distance(enemyShip)) {
                        possibleTargets.add(enemyShip);
                    }

                }
                if (possibleTargets.size() > 0) {
                    int min_index = -1;
                    int min_value = Integer.MAX_VALUE;
                    for (int i = 0; i < possibleTargets.size(); i++) {
                        if (possibleTargets.get(i).getHealth() < min_value) {
                            min_value = possibleTargets.get(i).getHealth();
                            min_index = i;
                        }
                    }
                    EnemyShip target = possibleTargets.get(min_index);
                    bullets.add(new Bullet(target.getSprite().b2body.getPosition(),
                            ship.getSprite().b2body.getPosition(),
                            screen,
                            ship.getAttack_damage(),
                            ship.getSprite().getShootingBodyFixture(),
                            System.currentTimeMillis(),false));
                    possibleTargets.clear();
                }
            }
        }
    }
    /**
     * Update
     */
    public void update(float deltaTime){

        if( this.active_ship == null && this.ships.size() > 0){
            active_ship = ships.get(0);
        }
        if( this.ships.size() == 0){
            this.screen.getGame().setScreen(new MainMenueScreen(this.screen.getGame()));
        }

        ArrayList<Ship> shipsToRemove = new ArrayList<Ship>();

        for( Ship ship: this.ships){

            if( !ship.equals(this.active_ship)){
                ship.followActiveShip();
            }


            if( ship.getHealth() <= 0 && !ship.getSprite().isPerishing() ){
                ship.getSprite().setPerishing(true);
                ship.destroy();
                System.out.println("Tagged");
            }
            else if( ship.getHealth() <= 0 && ship.getSprite().isPerishing() ) {
                if( ship.equals(active_ship)){
                    if( this.ships.size() > 1){
                        active_ship = ships.get(1);
                    }
                }

                if(  ! (( (System.currentTimeMillis() - ship.getSprite().getCurrent_vanish_time() ) / 1000) < SHIP_VANISH_TIME)){
                    shipsToRemove.add(ship);
                    ship.getSprite().destroy();
                    System.out.println("DESTROY");
                }

            }
            else if( ship.getHealth() < 0.25 * ship.getInitialHealth() && !ship.getSprite().isBurning() ) ship.getSprite().setBurning(true);

            ship.getSprite().update(deltaTime);

            }

        this.ships.removeAll(shipsToRemove);

        for( Bullet bullet : this.bullets){
            bullet.update(deltaTime);
        }


        //System.out.println("TILE_X: "+this.active_ship.getSprite().b2body.getPosition().x/32+" TILE_Y: "+this.active_ship.getSprite().b2body.getPosition().y/32);

    }

    /**
     * Draw
     */

    public void draw(SpriteBatch batch){


        for( Ship ship: this.ships){
            ship.getSprite().draw(batch);
        }
        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
        for( Bullet bullet : this.bullets){
            if( ( (System.currentTimeMillis() - bullet.getStartTime()) / 1000) < BULLET_VANISH_TIME){
                bullet.render(batch);
            }
            else{
                bulletsToRemove.add(bullet);
                bullet.destroy();
            }

        }
        this.bullets.removeAll(bulletsToRemove);

        //System.out.println(this.active_ship.getSprite().b2body.getPosition().x+" | "+this.active_ship.getSprite().b2body.getPosition().y);
    }


    /**
     * Ship Management
     */

    public void select_ship(Vector3 mouse_pos){
        float mouse_x = mouse_pos.x;
        float mouse_y = mouse_pos.y;

        for( Ship ship : this.ships){

            if(ship.getSprite().getBoundingRectangle().contains(new Vector2(mouse_x,mouse_y))){
                this.active_ship = ship;
            }
        }

    }

    /**
     * shoot at enemy ship
     * @param
     */
    public void shoot_ship(Vector3 mouse_pos){
        float mouse_x = mouse_pos.x;
        float mouse_y = mouse_pos.y;

        for( EnemyShip ship : this.screen.getEnemy().getShips()){

            if(ship.getSprite().getBoundingRectangle().contains(new Vector2(mouse_x,mouse_y)) && this.active_ship.in_shooting_distance(ship)){
                bullets.add(new Bullet(ship.getSprite().b2body.getPosition(),
                        this.active_ship.getSprite().b2body.getPosition(),
                        screen,
                        this.active_ship.getAttack_damage(),
                        this.active_ship.getSprite().getShootingBodyFixture(),
                        System.currentTimeMillis(),false));
                break;
            }
        }

    }


    public void add_ship( Ship ship){
        this.ships.add(ship);
        switch ( ship.getType()){
            case Buese :{
                this.numBuese += 1;
                break;
            }
            case Fleute :{
                this.NumFleute += 1;
                break;
            }
            case FliegenderHollaender: {
                this.NumFliegenderHollaender += 1;
                break;
            }
            case Galeone: {
                this.NumGaleone += 1;
                break;
            }
            case Pinas: {
                this.NumPinas += 1;
                break;
            }
        }
    }

    public void delete_ship( Ship ship){
        for( int i=0; i < this.ships.size() ; i++){
            if( this.ships.get(i).equals(ship)){
                this.ships.remove(i);

                if( this.active_ship.equals(ship) ){
                    this.active_ship = this.ships.get(0);
                }
            }
        }
    }


    /**
     * Getter and Setter
     */

    public int getNum_coins() {
        return num_coins;
    }

    public void setNum_coins(int num_coins) {
        this.num_coins = num_coins;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public Ship getActive_ship() {
        return active_ship;
    }

    public void setActive_ship(Ship active_ship) {
        this.active_ship = active_ship;
    }

    public int getNumBuese() {
        return numBuese;
    }

    public void setNumBuese(int numBuese) {
        this.numBuese = numBuese;
    }

    public int getNumPinas() {
        return NumPinas;
    }

    public void setNumPinas(int numPinas) {
        NumPinas = numPinas;
    }

    public int getNumFleute() {
        return NumFleute;
    }

    public void setNumFleute(int numFleute) {
        NumFleute = numFleute;
    }

    public int getNumGaleone() {
        return NumGaleone;
    }

    public void setNumGaleone(int numGaleone) {
        NumGaleone = numGaleone;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

}
