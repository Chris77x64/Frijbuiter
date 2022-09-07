package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Frijbuiter;
import com.mygdx.game.Ship.Ship;

/**
 * Created by chris on 07.01.2017.
 */

public class DockyardScreen implements Screen{

    private final int DRAWING_SHIPS_OFFSET = 468;

    private Frijbuiter game;
    private Stage stage;

    private Image background;
    private Image backButton;

    private Label coins;

    public DockyardScreen(Frijbuiter game){
        this.game = game;

        this.stage = new Stage();
        Gdx.input.setInputProcessor(this.stage);

        this.initializeBackground();
        this.initializeCoins();
        this.initializeBackButton();

        this.stage.addActor(this.background);
        this.stage.addActor(this.coins);
        this.stage.addActor(this.backButton);

        this.initializeShips();
        this.initializeBuyMenue();
    }

    private void initializeBackground(){
        Texture backgroundTexture = new Texture("screens/KaufmenueSchiff/backgroundWerft.png");
        TextureRegion textureRegion = new TextureRegion(backgroundTexture);
        this.background = new Image(textureRegion);
    }

    private void initializeCoins(){
        this.coins = new Label( String.format("%06d",this.game.getInventory().getCoins()), new Label.LabelStyle( createFont(50), Color.BLACK));
        this.coins.setPosition(219,655);
    }

    private void initializeBackButton(){
        Texture texture = new Texture("screens/KaufmenueSchiff/Back.png");
        TextureRegion region = new TextureRegion(texture);
        this.backButton = new Image(region);
        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MainMenueScreen(game));
            }
        };
        this.backButton.addListener(event);
        this.backButton.setPosition(40,40);
    }

    private void initializeBuyMenue(){
        if( this.game.getInventory().canAddShip()) {
            Texture texture = new Texture("screens/KaufmenueSchiff/BuyShips.png");
            TextureRegion region = new TextureRegion(texture);
            Image buyImage = new Image(region);
            buyImage.setPosition(285, 40);

            ClickListener event = new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    game.setScreen(new BuyScreen(game));
                }
            };
            buyImage.addListener(event);
            this.stage.addActor(buyImage);
        }
    }

    private void initializeShips(){

        int offset= DRAWING_SHIPS_OFFSET;

        for(final Ship.Type ship: this.game.getInventory().getShips()){

            Texture texture;
            Label attack;
            Label health;

            switch(ship){
                case Buese:{
                    texture = new Texture("screens/KaufmenueSchiff/sellBuse.png");
                    attack = new Label( Integer.toString(10+this.game.getInventory().totalAttackGain()), new Label.LabelStyle( createFont(20), Color.BLACK));
                    health = new Label( Integer.toString(100+this.game.getInventory().totalHealthGain()), new Label.LabelStyle( createFont(20), Color.BLACK));
                    break;
                }
                case Fleute: {
                    texture = new Texture("screens/KaufmenueSchiff/sellFleute.png");
                    attack = new Label( Integer.toString(30+this.game.getInventory().totalAttackGain()), new Label.LabelStyle( createFont(20), Color.BLACK));
                    health = new Label( Integer.toString(200+this.game.getInventory().totalHealthGain()), new Label.LabelStyle( createFont(20), Color.BLACK));
                    break;
                }
                case Galeone: {
                    texture = new Texture("screens/KaufmenueSchiff/sellGaleone.png");
                    attack = new Label( Integer.toString(50+this.game.getInventory().totalAttackGain()), new Label.LabelStyle( createFont(20), Color.BLACK));
                    health = new Label( Integer.toString(300+this.game.getInventory().totalHealthGain()), new Label.LabelStyle( createFont(20), Color.BLACK));
                    break;
                }
                case FliegenderHollaender: {
                    texture = new Texture("screens/KaufmenueSchiff/sellHollaender.png");
                    attack = new Label( Integer.toString(100+this.game.getInventory().totalAttackGain()), new Label.LabelStyle( createFont(20), Color.BLACK));
                    health = new Label( Integer.toString(2500+this.game.getInventory().totalHealthGain()), new Label.LabelStyle( createFont(20), Color.BLACK));
                    break;
                }
                default: {
                    texture = null;
                    attack = null;
                    health = null;
                    break;
                }
            }

            TextureRegion region = new TextureRegion(texture);
            Image image = new Image(region);
            image.setPosition(0,offset);

            health.setPosition(220,55+offset);
            attack.setPosition(220,15+offset);

            Texture sellTexture = new Texture("screens/KaufmenueSchiff/Verkaufen.png");
            TextureRegion sellTextureRegion = new TextureRegion(sellTexture);
            Image sell = new Image(sellTextureRegion);
            sell.setPosition( 285,10+offset);

            ClickListener event = new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.getInventory().sellShip(ship);
                    dispose();
                    game.setScreen(new DockyardScreen(game));
                }
            };

            sell.addListener(event);

            this.stage.addActor(image);
            this.stage.addActor(attack);
            this.stage.addActor(health);
            this.stage.addActor(sell);
            offset -= 92;

        }
    }


    public BitmapFont createFont(int fontsize){
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/timesBold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size=fontsize;
        return fontGenerator.generateFont(fontParameter);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // repaint the screen fresh and new
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
