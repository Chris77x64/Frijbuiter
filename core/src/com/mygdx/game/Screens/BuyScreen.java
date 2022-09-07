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
import com.mygdx.game.Ship.Ship;

/**
 * Created by chris on 07.01.2017.
 */

public class BuyScreen implements Screen {

    private Frijbuiter game;
    private Stage stage;

    private Image background;
    private Image backButton;

    private Image buyBuse;
    private Image buyFleute;
    private Image buyGaleone;
    private Image buyFliegenderHollaender;

    private TextureRegion buyButtonRegion;

    public BuyScreen( Frijbuiter game){
        this.game = game;
        this.game = game;

        this.stage = new Stage();
        Gdx.input.setInputProcessor(this.stage);

        Texture buyButtonTexture = new Texture("screens/KaufmenueSchiff/Kaufbutton.png");
        this.buyButtonRegion = new TextureRegion(buyButtonTexture);

        this.initializeBackground();
        this.initializeBuyBuse();
        this.initializeBuyFleute();
        this.initializeBuyGaleone();
        this.initializeBuyFliegenderHollaender();
        this.initializeBackButton();

        this.stage.addActor(this.background);
        this.stage.addActor(this.backButton);
        this.stage.addActor(this.buyBuse);
        this.stage.addActor(this.buyFleute);
        this.stage.addActor(this.buyGaleone);
        this.stage.addActor(this.buyFliegenderHollaender);

    }

    private void initializeBackground(){
        Texture backgroundTexture = new Texture("screens/KaufmenueSchiff/background.png");
        TextureRegion textureRegion = new TextureRegion(backgroundTexture);
        this.background = new Image(textureRegion);
    }

    private void initializeBackButton(){
        Texture texture = new Texture("screens/KaufmenueSchiff/Back.png");
        TextureRegion region = new TextureRegion(texture);
        this.backButton = new Image(region);
        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new DockyardScreen(game));
            }
        };
        this.backButton.addListener(event);
        this.backButton.setPosition(40,40);
    }

    private void initializeBuyBuse(){
        this.buyBuse = new Image(this.buyButtonRegion);
        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.getInventory().canAffordBuy(100)){
                    game.getInventory().buyShip(Ship.Type.Buese);
                }
                game.setScreen(new DockyardScreen(game));
            }
        };
        this.buyBuse.addListener(event);
        this.buyBuse.setPosition(155,619);

    }

    private void initializeBuyFleute(){
        this.buyFleute = new Image(this.buyButtonRegion);
        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.getInventory().canAffordBuy(200)){
                    game.getInventory().buyShip(Ship.Type.Fleute);
                }
                game.setScreen(new DockyardScreen(game));
            }
        };
        this.buyFleute.addListener(event);
        this.buyFleute.setPosition(155,449);
    }

    private void initializeBuyGaleone(){
        this.buyGaleone = new Image(this.buyButtonRegion);
        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.getInventory().canAffordBuy(500)){
                    game.getInventory().buyShip(Ship.Type.Galeone);
                }
                game.setScreen(new DockyardScreen(game));
            }
        };
        this.buyGaleone.addListener(event);
        this.buyGaleone.setPosition(155,259);
    }

    private void initializeBuyFliegenderHollaender(){
        this.buyFliegenderHollaender = new Image(this.buyButtonRegion);
        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.getInventory().canAffordBuy(100)){
                    game.getInventory().buyShip(Ship.Type.FliegenderHollaender);
                }
                game.setScreen(new DockyardScreen(game));
            }
        };
        this.buyFliegenderHollaender.addListener(event);
        this.buyFliegenderHollaender.setPosition(155,69);
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
