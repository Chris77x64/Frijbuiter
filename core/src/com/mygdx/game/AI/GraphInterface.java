package com.mygdx.game.AI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ClasspathFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.Levels.Level;

import org.xguzm.pathfinding.gdxbridge.NavTmxMapLoader;
import org.xguzm.pathfinding.gdxbridge.NavigationTiledMapLayer;
import org.xguzm.pathfinding.grid.GridCell;
import org.xguzm.pathfinding.grid.NavigationGrid;

import java.util.logging.FileHandler;

/**
 * Created by chris on 09.12.2016.
 */

public class GraphInterface {

    private TmxMapLoader loader;

    private GridCell[][] cells;
    private NavigationGrid<GridCell> navigationGrid;
    private NavigationTiledMapLayer nav;

    public GraphInterface (Level level){


        TiledMap map = new NavTmxMapLoader().load(level.getTiledMapPath());
        this.nav  = (NavigationTiledMapLayer) map.getLayers().get("navigation");

        this.cells = this.nav.getNodes();

        this.navigationGrid = new NavigationGrid();
        this.navigationGrid.setNodes(this.cells);

        GridCell[][] temp = this.navigationGrid.getNodes();
        for( int i=0; i < temp.length ; i++){
            for( int k=0 ; k < temp[i].length ; k++){
                temp[i][k].setWalkable(true);
            }
        }
        this.navigationGrid.setNodes(temp);


    }

    public TmxMapLoader getLoader() {
        return loader;
    }

    public void setLoader(TmxMapLoader loader) {
        this.loader = loader;
    }

    public NavigationGrid<GridCell> getNavigationGrid() {
        return navigationGrid;
    }

    public void setNavigationGrid(NavigationGrid<GridCell> navigationGrid) {
        this.navigationGrid = navigationGrid;
    }
}
