package com.mygdx.game.Levels.campaign;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Levels.EnemySpawnPosition;
import com.mygdx.game.Levels.Level;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Ship.EnemyShip;

/**
 * Created by chris on 05.01.2017.
 */

public class Luanda extends Level{

    public Luanda(PlayScreen screen) {
        super(screen);
        this.name = Name.Luanda;
        this.tiledMapPath = "Level/Luanda/Map Luanda.tmx";
        this.difficulty = 3;
        this.map = initializeLevel();
        this.initializeMapRenderer();
        this.initializePathfinding();
        this.initializeEnemys();
        this.startPositions = new Vector2[5];
        this.startPositions[0] = new Vector2(400,2520);
        this.startPositions[1] = new Vector2(435,2520);
        this.startPositions[2] = new Vector2(470,2520);
        this.startPositions[3] = new Vector2(505,2520);
        this.startPositions[4] = new Vector2(520,2520);
    }

    @Override
    protected TiledMap initializeLevel() {
        TmxMapLoader mapLoader = new TmxMapLoader();
        return mapLoader.load("Level/Luanda/Map Luanda.tmx");
    }

    @Override
    protected void initializeEnemys() {


        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(40,30)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(100,83)));

        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(10,40)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(20,40)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(69,48)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(62, 42)));

    }


}
