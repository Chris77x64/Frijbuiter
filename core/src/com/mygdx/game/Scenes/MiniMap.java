package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by chris on 30.12.2016.
 */

public class MiniMap {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    // Camera for Minimap
    private OrthographicCamera camera;

    //private Viewport viewport;

    private PlayScreen screen;



    public MiniMap(PlayScreen screen){

        this.screen = screen;

        TmxMapLoader mapLoader = new TmxMapLoader();
        this.map = mapLoader.load(this.screen.getLevel().getTiledMapPath());

        this.renderer = new OrthogonalTiledMapRenderer(map,1/29f);

        this.camera = new OrthographicCamera(280,500);
        Vector3 center = new Vector3(0,240, 0);
        this.camera.position.set(center);
        this.camera.update();
    }

    public void render(){
        this.camera.update();
        this.renderer.setView(this.camera);
        this.renderer.render();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }





}
