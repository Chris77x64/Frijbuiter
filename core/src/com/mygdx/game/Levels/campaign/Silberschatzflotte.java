package com.mygdx.game.Levels.campaign;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Levels.EnemySpawnPosition;
import com.mygdx.game.Levels.Level;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Ship.EnemyShip;
import com.mygdx.game.Ship.Ship;

/**
 * Created by chris on 05.01.2017.
 */

public class Silberschatzflotte extends Level {

    public Silberschatzflotte(PlayScreen screen) {
        super(screen);
        this.name = Name.Silberschatzflotte;
        this.difficulty = 6;
        this.tiledMapPath = "Level/Silberschatzflotte/Silberschatzflotte.tmx";
        this.map = initializeLevel();
        this.initializeMapRenderer();
        this.initializePathfinding();
        this.initializeEnemys();
        this.startPositions = new Vector2[5];
        this.startPositions[0] = new Vector2(200,2000);
        this.startPositions[1] = new Vector2(215,2000);
        this.startPositions[2] = new Vector2(230,2000);
        this.startPositions[3] = new Vector2(245,2000);
        this.startPositions[4] = new Vector2(260,2000);
    }

    @Override
    protected TiledMap initializeLevel() {
        TmxMapLoader mapLoader = new TmxMapLoader();
        return mapLoader.load("Level/Silberschatzflotte/Silberschatzflotte.tmx");
    }

    @Override
    protected void initializeEnemys() {
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(20,80)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(22,68)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(70,50)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(80,50)));

        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(90,50)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(100,60)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(100,50)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(110,70)));

        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(34,44)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(53,51)));

    }


}
