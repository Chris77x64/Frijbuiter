package com.mygdx.game.AI;


import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import org.xguzm.pathfinding.grid.GridCell;
import org.xguzm.pathfinding.grid.NavigationGrid;
import org.xguzm.pathfinding.grid.finders.AStarGridFinder;
import org.xguzm.pathfinding.grid.finders.GridFinderOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 09.12.2016.
 */

public class A_STAR {

    public AStarGridFinder<GridCell> finder;

    //Navigation Grid
    NavigationGrid<GridCell> navigationGrid;

    //Pathfinder options
    GridFinderOptions options;

    public A_STAR(  NavigationGrid<GridCell> navigationGrid){

        this.navigationGrid = navigationGrid;

        this.options = new GridFinderOptions();
        this.options.allowDiagonal = false;

        this.finder = new AStarGridFinder(GridCell.class,options);

    }

    public List<GridCell> A_STAR_Search (int startX, int startY , int destinationX , int destinationY){
        GridCell startNode = this.navigationGrid.getCell(startX, startY);
        GridCell endNode = this.navigationGrid.getCell(destinationX, destinationY);
        if( startNode == null || endNode == null) return new ArrayList<GridCell>();
        else return this.finder.findPath(startX,startY,destinationX,destinationY,this.navigationGrid);
    }




}
