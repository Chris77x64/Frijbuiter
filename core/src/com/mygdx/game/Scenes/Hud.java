package com.mygdx.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Ship.Ship;

/**
 * Created by chris on 30.11.2016.
 */

public class Hud implements Disposable {

    private PlayScreen screen;
    public Stage stage;

    // Background Texture
    private Background background;

    private Image optionsButton;
    private Image pauseButton;
    private Image playButton;

    private Label currentAtackLabel;
    private Label currentHealthLabel;
    private Texture currentShipTexture;
    private Image currentShipImage;

    // Minimap
    private MiniMap miniMap;

    public Hud(PlayScreen screen){

        this.screen = screen;
        this.background = new Background();

        this.stage = new Stage();
        Gdx.input.setInputProcessor(this.stage);

        this.initializeOptionsButton();
        this.initializePauseButton();
        this.initializePlayButton();
        this.initializeLabels();
        this.initializeImage();

       // this.initializeCurrentShip(this.screen.getPlayer().getActive_ship());

        this.stage.addActor(background);
        this.stage.addActor(optionsButton);
        this.stage.addActor(pauseButton);
        this.stage.addActor(playButton);
        this.stage.addActor(this.currentAtackLabel);
        this.stage.addActor(this.currentHealthLabel);
        this.stage.addActor(currentShipImage);

        this.miniMap = new MiniMap(screen);
    }

    private void initializeLabels(){
        this.currentAtackLabel = new Label(Integer.toString(this.screen.getPlayer().getActive_ship().getAttack_damage()), new Label.LabelStyle( createFont(22), Color.BLACK));
        this.currentAtackLabel.setPosition(175,94);

        this.currentHealthLabel = new Label(Integer.toString(this.screen.getPlayer().getActive_ship().getHealth()),new Label.LabelStyle( createFont(22), Color.BLACK ));
        this.currentHealthLabel.setPosition(175,140);
    }

    private void initializeImage(){

        Ship ship = this.screen.getPlayer().getActive_ship();

        switch( ship.getType()){
            case Buese:{
                this.currentShipTexture = new Texture("GUI/buese.png");
                break;
            }
            case Fleute: {
                this.currentShipTexture = new Texture("GUI/fleute.png");
                break;
            }
            case Galeone: {
                this.currentShipTexture = new Texture("GUI/galeone.png");
                break;
            }
            case FliegenderHollaender: {
                this.currentShipTexture = new Texture("GUI/fh.png");
                break;
            }
            default: {
                this.currentShipTexture = null;
            }
        }
        TextureRegion region = new TextureRegion(this.currentShipTexture);
        this.currentShipImage = new Image(region);
        this.currentShipImage.setPosition(9,81);
    }

    public void UpdateCurrentShip(Ship ship ){

        this.currentShipTexture.dispose();

        switch( ship.getType()){
            case Buese:{
                this.currentShipTexture = new Texture("GUI/buese.png");
                break;
            }
            case Fleute: {
                this.currentShipTexture = new Texture("GUI/fleute.png");
                break;
            }
            case Galeone: {
                this.currentShipTexture = new Texture("GUI/galeone.png");
                break;
            }
            case FliegenderHollaender: {
                this.currentShipTexture = new Texture("GUI/fh.png");
                break;
            }
            default: {
                this.currentShipTexture = null;
            }
        }
        TextureRegion region = new TextureRegion(this.currentShipTexture);
        this.currentShipImage.setDrawable(new TextureRegionDrawable(region));

        this.currentHealthLabel.setText(Integer.toString(ship.getHealth()));
        this.currentAtackLabel.setText(Integer.toString(ship.getAttack_damage()));

    }


    private void initializeOptionsButton(){
        Texture texture = new Texture("GUI/options.png");
        TextureRegion region = new TextureRegion(texture);
        this.optionsButton = new Image(region);
        this.optionsButton.setPosition(7,17);
        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        };
        this.optionsButton.addListener(event);
    }

    private void initializePauseButton(){
        Texture texture = new Texture("GUI/pause.png");
        TextureRegion region = new TextureRegion(texture);
        this.pauseButton = new Image(region);
        this.pauseButton.setPosition(81,17);
        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.setPause(true);
            }
        };
        this.pauseButton.addListener(event);
    }

    private void initializePlayButton(){
        Texture texture = new Texture("GUI/play.png");
        TextureRegion region = new TextureRegion(texture);
        this.playButton = new Image(region);
        this.playButton.setPosition(151,17);
        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.setPause(false);
            }
        };
        this.playButton.addListener(event);

    }

    public BitmapFont createFont( int fontsize){
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/timesBold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size=fontsize;
        return fontGenerator.generateFont(fontParameter);
    }


    @Override
    public void dispose() {
        //this.stage.dispose();
        this.background.dispose();
    }

    public MiniMap getMiniMap() {
        return miniMap;
    }

    public void setMiniMap(MiniMap miniMap) {
        this.miniMap = miniMap;
    }

    public void render(){
        //this.initializeCurrentShip(this.screen.getPlayer().getActive_ship());
        this.UpdateCurrentShip(this.screen.getPlayer().getActive_ship());
        this.stage.draw();
        this.miniMap.render();
    }
}
