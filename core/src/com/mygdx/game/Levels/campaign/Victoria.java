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

public class Victoria extends Level {

    public Victoria(PlayScreen screen) {
        super(screen);
        this.name = Name.Victoria;
        this.difficulty = 4;
        this.tiledMapPath = "Level/Victoria/Map Vitoria.tmx";
        this.map = initializeLevel();
        this.initializeMapRenderer();
        this.initializePathfinding();
        this.initializeEnemys();
        this.startPositions = new Vector2[5];
        this.startPositions[0] = new Vector2( 3800,2720);
        this.startPositions[1] = new Vector2( 3800,2700);
        this.startPositions[2] = new Vector2( 3800,2680);
        this.startPositions[3] = new Vector2( 3800,2660);
        this.startPositions[4] = new Vector2( 3800,2640);
    }

    @Override
    protected TiledMap initializeLevel() {
        TmxMapLoader mapLoader = new TmxMapLoader();
        return mapLoader.load("Level/Victoria/Map Vitoria.tmx");
    }

    @Override
    protected void initializeEnemys() {
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(115,20)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(117,22)));

        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(116,55)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(59,29)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(75,20)));

        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPFleute,new Vector2(31,45)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(44,75)));
        this.enemyPlacement.add(new EnemySpawnPosition(EnemyShip.Type.ESPGaleone,new Vector2(33,77)));
    }
}
