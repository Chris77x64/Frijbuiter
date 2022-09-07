package com.mygdx.game.Levels.campaign;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Levels.EnemySpawnPosition;
import com.mygdx.game.Levels.Level;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Player.Enemy;
import com.mygdx.game.Ship.EnemyShip;

/**
 * Created by chris on 05.01.2017.
 */

public class Neapel extends Level {

    public Neapel(PlayScreen screen) {
        super(screen);
        this.name = Name.Neapel;
        this.difficulty = 1;
        this.tiledMapPath = "Level/Neapel/Neapel.tmx";
        this.map = initializeLevel();
        this.initializeMapRenderer();
        this.initializePathfinding();
        this.initializeEnemys();
        this.startPositions = new Vector2[5];
        this.startPositions[0] = new Vector2(200,200);
        this.startPositions[1] = new Vector2(235,200);
        this.startPositions[2] = new Vector2(270,200);
        this.startPositions[3] = new Vector2(305,200);
        this.startPositions[4] = new Vector2(320,200);

    }

    @Override
    protected TiledMap initializeLevel() {
        TmxMapLoader mapLoader = new TmxMapLoader();
        return mapLoader.load("Level/Neapel/Neapel.tmx");
    }

    @Override
    protected void initializeEnemys() {

        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(40,39)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(51,34)));

        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(79,61)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(84,55)));

        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(86,26)));

        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(110,19)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(116,11)));


    }


}
