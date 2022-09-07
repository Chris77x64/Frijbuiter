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

public class SalvadorII extends Level{

    public SalvadorII(PlayScreen screen) {
        super(screen);
        this.name = Name.SalvadorII;
        this.difficulty = 5;
        this.tiledMapPath = "Level/SalvadorII/Map Salvador II.tmx";
        this.map = initializeLevel();
        this.initializeMapRenderer();
        this.initializePathfinding();
        this.initializeEnemys();
        this.startPositions = new Vector2[5];
        this.startPositions[0] = new Vector2(3680,400);
        this.startPositions[1] = new Vector2(3665,400);
        this.startPositions[2] = new Vector2(3650,400);
        this.startPositions[3] = new Vector2(3635,400);
        this.startPositions[4] = new Vector2(3620,400);
    }

    @Override
    protected TiledMap initializeLevel() {
        TmxMapLoader mapLoader = new TmxMapLoader();
        return mapLoader.load("Level/SalvadorII/Map Salvador II.tmx");
    }

    @Override
    protected void initializeEnemys() {
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(90,20)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(100,20)));

        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(50,20)));

        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(50,58)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(30,62)));

        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(55,27)));

        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(20,20)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(25,30)));
    }



}
