package com.mygdx.game.Player;

import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Ship.Ship;
import com.mygdx.game.Ship.Ship_NL.Buese;
import com.mygdx.game.Ship.Ship_NL.Fleute;
import com.mygdx.game.Ship.Ship_NL.FliegenderHollaender;
import com.mygdx.game.Ship.Ship_NL.Galeone;

import java.util.ArrayList;

/**
 * Created by chris on 07.01.2017.
 */

public class Inventory {

    private final int MAX_SHIPS = 5;

    private ArrayList<Ship.Type> ships;
    private int coins;

    public boolean munitionEisenkugeln;
    public boolean munitionKartaetschen;
    public boolean kalfatern;
    public boolean verstaerkteBordwand;

    public boolean calvinistischeDisziplin;
    public boolean rumFuerDieMannschaft;
    public boolean ausbildungGeschuetz;
    public boolean ausbildungSchiffsbau;


    public Inventory(){
        this.coins = 100000;

        this.ships = new ArrayList<Ship.Type>();
        this.ships.add(Ship.Type.Buese);
        /**
        this.ships.add(Ship.Type.Fleute);
        this.ships.add(Ship.Type.Galeone);
        this.ships.add(Ship.Type.FliegenderHollaender);
        this.ships.add(Ship.Type.FliegenderHollaender);
         **/

        this.munitionEisenkugeln = false;
        this.munitionKartaetschen = false;
        this.kalfatern = false;
        this.verstaerkteBordwand = false;
        this.calvinistischeDisziplin = false;
        this.rumFuerDieMannschaft = false;
        this.ausbildungGeschuetz = false;
        this.ausbildungSchiffsbau = false;

    }

    public void increaseCoins( int value){
        this.coins += value;
    }

    public void decreaseCoins( int value ){
        this.coins -= value;
    }

    public boolean canAffordBuy( int value){
        if( this.coins - value >= 0){
            return true;
        }
        else return false;
    }

    public void addShip( Ship.Type ship){
        this.ships.add(ship);
    }

    public void deleteShip( Ship.Type ship){
        this.ships.remove(ship);
    }

    public void sellShip( Ship.Type ship){
        switch(ship){
            case Buese:{
                this.increaseCoins(100);
                break;
            }
            case Fleute: {
                this.increaseCoins(200);
                break;
            }
            case Galeone: {
                this.increaseCoins(500);
                break;
            }
            case FliegenderHollaender: {
                this.increaseCoins(9999);
                break;
            }
        }
        this.deleteShip(ship);
    }

    public void buyShip( Ship.Type ship){
        switch(ship){
            case Buese:{
                this.decreaseCoins(100);
                break;
            }
            case Fleute: {
                this.decreaseCoins(200);
                break;
            }
            case Galeone: {
                this.decreaseCoins(500);
                break;
            }
            case FliegenderHollaender: {
                this.decreaseCoins(9999);
                break;
            }
        }
        this.addShip(ship);
    }

    public boolean canAddShip(){
        if( this.ships.size() <= MAX_SHIPS-1 ){
            return true;
        }
        else return false;
    }

    public ArrayList<Ship> createShips(PlayScreen screen){
        ArrayList<Ship> result = new ArrayList<Ship>();
        int counter =0;
        for( Ship.Type element: ships){
            switch(element){
                case Buese:{
                    result.add( new Buese(
                            (int) screen.getLevel().getStartPositions()[counter].x,
                            (int) screen.getLevel().getStartPositions()[counter].y,
                            screen.getCamera(),screen));
                    break;
                }
                case Fleute:{
                    result.add( new Fleute(
                            (int) screen.getLevel().getStartPositions()[counter].x,
                            (int) screen.getLevel().getStartPositions()[counter].y,
                            screen.getCamera(),screen));
                    break;
                }
                case Galeone:{
                    result.add( new Galeone(
                            (int) screen.getLevel().getStartPositions()[counter].x,
                            (int) screen.getLevel().getStartPositions()[counter].y,
                            screen.getCamera(),screen));
                    break;

                }
                case FliegenderHollaender: {
                    result.add( new FliegenderHollaender(
                            (int) screen.getLevel().getStartPositions()[counter].x,
                            (int) screen.getLevel().getStartPositions()[counter].y,
                            screen.getCamera(),screen));
                    break;
                }
            }
            counter += 1;
        }
        this.initializeUpgrades(result);
        return result;
    }

    public int totalAttackGain(){
        int result = 0;
        if(this.calvinistischeDisziplin){
            result += 10;
        }
        if( this.ausbildungGeschuetz ){
            result +=20;
        }
        if( this.munitionEisenkugeln){
            result += 10;
        }
        if( this.munitionKartaetschen){
            result += 20;
        }
        return  result;
    }

    public int totalHealthGain(){
        int result=0;
        if(this.kalfatern){
            result += 10;
        }
        if( this.verstaerkteBordwand){
            result += 20;
        }
        if( this.rumFuerDieMannschaft){
            result += 10;
        }
        if( this.ausbildungSchiffsbau){
            result += 20;
        }
        return result;
    }


    public void initializeUpgrades( ArrayList<Ship> ships){

        int additionalHealth = this.totalHealthGain();
        int additionalDamage = this.totalAttackGain();

        for( Ship ship: ships){
            ship.setAttack_damage( ship.getAttack_damage() + additionalDamage);
            ship.setHealth(ship.getHealth() + additionalHealth);
        }
    }

    public ArrayList<Ship.Type> getShips() {
        return ships;
    }

    public int getCoins() {
        return coins;
    }
}
