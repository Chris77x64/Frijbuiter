package com.mygdx.game.Levels;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.BatchTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.AI.A_STAR;
import com.mygdx.game.AI.GraphInterface;
import com.mygdx.game.Scenes.MiniMap;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Player.Enemy;
import com.mygdx.game.Ship.EnemyShip;

import org.xguzm.pathfinding.grid.GridCell;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chris on 05.01.2017.
 */

public abstract class Level {

    protected PlayScreen screen;

    /**
     * Tiled Map to play with
     */
    protected TiledMap map;
    protected OrthogonalTiledMapRenderer renderer;

    protected String tiledMapPath;
   // protected MiniMap miniMap;

    /**
     * Name of the specific level
     */
    public enum Name{ Neapel , SalvadorI , Luanda , Victoria , SalvadorII, Silberschatzflotte, Kaperfahrt};
    protected Name name;

    /**
     *  Enemys to play with
     */
    protected Enemy enemy;
    protected int difficulty;

    /**
     * Initial start position
     *
     */
    protected Vector2[] startPositions;


    /**
     * Pathfinding
     */
    private GraphInterface graphInterface;
    private A_STAR a_star;


    /**
     *  Position of opposing ships
     */
    protected ArrayList<EnemySpawnPosition> enemyPlacement;


    protected abstract TiledMap initializeLevel();
    protected abstract void initializeEnemys();


    public Level( PlayScreen screen){
        this.screen = screen;
        this.enemyPlacement = new ArrayList<EnemySpawnPosition>();
    }

    public void initializeMapRenderer(){
        this.renderer = new OrthogonalTiledMapRenderer(this.map);
        //this.miniMap = new MiniMap(this.screen);
    }

    public void initializePathfinding( ){
        this.graphInterface = new GraphInterface(this);
        this.a_star = new A_STAR(this.graphInterface.getNavigationGrid());

    }

    public List<GridCell> pathfinding(int startX, int startY , int destinationX, int destinationY){
        if( startX < 0) startX = 0;
        if( startY < 0 ) startY = 0;
        if(destinationX <0) destinationX = 0;
        if( destinationY < 0) destinationY = 0;
        List<GridCell> result =  this.a_star.A_STAR_Search(startX,startY,destinationX,destinationY);
        return result;
    }

    public void update(float deltaTime){
        this.renderer.setView(this.screen.getCamera());
    }

    public void render(){
        this.renderer.render();
        //this.miniMap.render();
    }

    public void dispose() {
        this.map.dispose();
        this.renderer.dispose();
    }

    public TiledMap getMap() {
        return map;
    }

    public Vector2[] getStartPositions() {
        return startPositions;
    }

    public Name getName() {
        return name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getTiledMapPath() {
        return tiledMapPath;
    }

    public ArrayList<EnemySpawnPosition> getEnemyPlacement() {
        return enemyPlacement;
    }

    public void setEnemyPlacement(ArrayList<EnemySpawnPosition> enemyPlacement) {
        this.enemyPlacement = enemyPlacement;
    }
}
