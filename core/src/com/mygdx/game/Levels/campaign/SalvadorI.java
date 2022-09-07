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

public class SalvadorI extends Level {

    public SalvadorI(PlayScreen screen) {
        super(screen);
        this.name = Name.SalvadorI;
        this.difficulty = 2;
        this.tiledMapPath = "Level/SalvadorI/Map Salvador I.tmx";
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
        return mapLoader.load("Level/SalvadorI/Map Salvador I.tmx");
    }

    @Override
    protected void initializeEnemys() {
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(20,10)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(25,10)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(50,40)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(75,20)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(50,65)));
    }
}
