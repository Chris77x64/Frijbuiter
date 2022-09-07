package com.mygdx.game.Player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntFloatMap;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Levels.EnemySpawnPosition;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Ship.EnemyShip;
import com.mygdx.game.Ship.Ship;
import com.mygdx.game.Ship.Ship_ESP.ESPFleute;
import com.mygdx.game.Ship.Ship_ESP.ESPGaleone;
import com.mygdx.game.Sprites.Bullet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 05.01.2017.
 */

public class Enemy {

    private PlayScreen screen;
    /**
     * current ships of the enemy
     */
    private ArrayList<EnemyShip> ships;

    /**
     * Bullet Management
     */
    private ArrayList<Bullet> bullets;

    /**
     * Constants
     */

    private final int BULLET_VANISH_TIME = 5;
    private final int SHIP_VANISH_TIME = 3;

    public Enemy(final PlayScreen screen){
        this.screen = screen;
        this.ships = new ArrayList<EnemyShip>();
        this.bullets = new ArrayList<Bullet>();
        this.initializeEnemys();

        Timer.schedule(new Timer.Task(){
                           @Override
                           public void run() {
                               if( screen.getPlayer().getShips().size() > 0 ){

                                   ArrayList<Ship> PlayerShips = screen.getPlayer().getShips();

                                   int difficulty = screen.getLevel().getDifficulty();

                                   if( difficulty <= 2){

                                       ArrayList<Ship> possibleTargets = new ArrayList<Ship>();

                                       for( EnemyShip ship: ships){
                                           if( ship.isAwake()) {
                                               for (Ship ownShip : PlayerShips) {
                                                   if(ship.in_shooting_distance(ownShip)){
                                                       possibleTargets.add(ownShip);
                                                   }
                                               }
                                               if( possibleTargets.size() > 0) {
                                                   int random_number = (int) Math.random() * possibleTargets.size();
                                                   Ship target = possibleTargets.get(random_number);
                                                   bullets.add(new Bullet(target.getSprite().b2body.getPosition(),
                                                           ship.getSprite().b2body.getPosition(),
                                                           screen,
                                                           ship.getAttack_damage(),
                                                           ship.getSprite().getShootingBodyFixture(),
                                                           System.currentTimeMillis(),true));
                                               }

                                           }
                                           possibleTargets.clear();
                                       }

                                   }
                                   else if(difficulty <=4){
                                       ArrayList<Ship> possibleTargets = new ArrayList<Ship>();

                                       for( EnemyShip ship: ships) {
                                           if (ship.isAwake()) {
                                               for (Ship ownShip : PlayerShips) {
                                                   if (ship.in_shooting_distance(ownShip)) {
                                                       possibleTargets.add(ownShip);
                                                   }
                                               }
                                               if( possibleTargets.size() > 0) {
                                                    if( possibleTargets.contains(screen.getPlayer().getActive_ship())){
                                                        for( Ship key: possibleTargets){
                                                            if( key.equals(screen.getPlayer().getActive_ship())){
                                                                bullets.add(new Bullet(key.getSprite().b2body.getPosition(),
                                                                        ship.getSprite().b2body.getPosition(),
                                                                        screen,
                                                                        ship.getAttack_damage(),
                                                                        ship.getSprite().getShootingBodyFixture(),
                                                                        System.currentTimeMillis(),true));
                                                            }
                                                        }
                                                    }
                                                   else{
                                                        int random_number = (int) Math.random() * possibleTargets.size();
                                                        Ship target = possibleTargets.get(random_number);
                                                        bullets.add(new Bullet(target.getSprite().b2body.getPosition(),
                                                                ship.getSprite().b2body.getPosition(),
                                                                screen,
                                                                ship.getAttack_damage(),
                                                                ship.getSprite().getShootingBodyFixture(),
                                                                System.currentTimeMillis(),true));
                                                    }
                                               }

                                           }
                                           possibleTargets.clear();
                                       }
                                   }
                                   else{

                                   }
                                       if( screen.getPlayer().getShips().size() > 0){
                                           ArrayList<Ship> possibleTargets = new ArrayList<Ship>();

                                           for( EnemyShip ship: ships) {
                                               if (ship.isAwake()) {
                                                   for (Ship ownShip : PlayerShips) {
                                                       if (ship.in_shooting_distance(ownShip)) {
                                                           possibleTargets.add(ownShip);
                                                       }
                                                   }
                                                   if( possibleTargets.size() > 0){
                                                       int min_index = -1;
                                                       int min_value = Integer.MAX_VALUE;
                                                       for( int i=0; i < possibleTargets.size() ; i++){
                                                           if( possibleTargets.get(i).getHealth() < min_value ){
                                                               min_value = possibleTargets.get(i).getHealth();
                                                               min_index = i;
                                                           }
                                                       }
                                                       Ship target = possibleTargets.get(min_index);
                                                       bullets.add(new Bullet(target.getSprite().b2body.getPosition(),
                                                               ship.getSprite().b2body.getPosition(),
                                                               screen,
                                                               ship.getAttack_damage(),
                                                               ship.getSprite().getShootingBodyFixture(),
                                                               System.currentTimeMillis(),true));

                                                   }

                                               }
                                               possibleTargets.clear();


                                           }



                                   }
                               }

                           }
                       }
                , 0        //    (delay)
                , 2     //    (seconds)
        );




    }

    public void update(float deltaTime){
        ArrayList<EnemyShip> shipsToRemove = new ArrayList<EnemyShip>();

        for( EnemyShip ship: this.ships){

            if( ! ship.isAwake()) {
                for (Ship ownShip : this.screen.getPlayer().getShips()) {
                    if(ship.in_wakeup_distance(ownShip)) {
                        ship.setAwake(true);
                        break;
                    }
                }
            }

            if(ship.isAwake()){
                ship.followShip();
            }



            if( ship.getHealth() <= 0 && !ship.getSprite().isPerishing() ){
                ship.getSprite().setPerishing(true);
                ship.destroy();
                System.out.println("Tagged");
            }
            else if( ship.getHealth() <= 0 && ship.getSprite().isPerishing() ) {

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

    }


    public void draw(SpriteBatch batch){


        for( EnemyShip ship: this.ships){
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

    }


    private void initializeEnemys(){
        for(EnemySpawnPosition position: this.screen.getLevel().getEnemyPlacement()){  //Map.Entry<EnemyShip.Type,Vector2> entry: positions.entrySet()){
            switch(position.getType()){
                case ESPFleute:{
                    System.out.println("!!");
                    this.ships.add(new ESPFleute((int) (position.getPosition().x*32) ,(int) (position.getPosition().y*32) , this.screen.getCamera(),this.screen));
                    break;
                }
                case ESPGaleone:{
                    System.out.println("!!");
                    this.ships.add(new ESPGaleone((int) (position.getPosition().x*32) ,(int) (position.getPosition().y*32) , this.screen.getCamera(),this.screen));
                    break;
                }
            }

        }

    }


    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public ArrayList<EnemyShip> getShips() {
        return ships;
    }

    public void setShips(ArrayList<EnemyShip> ships) {
        this.ships = ships;
    }


}
