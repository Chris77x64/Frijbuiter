package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Frijbuiter;

/**
 * Created by chris on 07.01.2017.
 */

public class MainMenueScreen implements Screen {

    private Frijbuiter game;
    private Stage stage;

    private Image background;
    private Image missionMenue;
    private Image upgradeMenue;

    public MainMenueScreen(Frijbuiter game){
        this.game = game;

        this.stage = new Stage();
        Gdx.input.setInputProcessor(this.stage);


        this.initializeBackground();
        this.initializeMissionMenue();
        this.initializeUpgradeMenue();

        this.stage.addActor(this.background);
        this.stage.addActor(this.missionMenue);
        this.stage.addActor(this.upgradeMenue);
    }

    private void initializeUpgradeMenue(){
        Texture menueTexture = new Texture("screens/Hauptmenue/upgrade.png");
        TextureRegion textureRegion = new TextureRegion(menueTexture);
        this.upgradeMenue = new Image(textureRegion);
        this.upgradeMenue.setPosition(250,356);

        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new DockyardScreen(game));
            }
        };
        this.upgradeMenue.addListener(event);
    }

    private void initializeMissionMenue(){
        Texture menueTexture = new Texture("screens/Hauptmenue/mission.png");
        TextureRegion textureRegion = new TextureRegion(menueTexture);
        this.missionMenue = new Image(textureRegion);
        this.missionMenue.setPosition(32,356);

        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MissionScreen(game));
            }
        };
        this.missionMenue.addListener(event);

    }

    private void initializeBackground(){
        Texture backgroundTexture = new Texture("screens/Hauptmenue/background.png");
        TextureRegion textureRegion = new TextureRegion(backgroundTexture);
        this.background = new Image(textureRegion);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 0);

        this.stage.act(delta);
        this.stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        this.stage.dispose();
    }
}
