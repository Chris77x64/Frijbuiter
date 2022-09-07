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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Frijbuiter;
import com.mygdx.game.Levels.Level;

/**
 * Created by chris on 05.01.2017.
 */

public class MissionScreen implements Screen {

    private Frijbuiter game;
    private Stage stage;

    private ImageButton button;

    private TextureRegion dotRegion;

    private Image background;
    private Image missionSalvaldorI;
    private Image missionSalvaldorII;
    private Image missionVictoria;
    private Image missionSilberschatzflotte;
    private Image missionLuanda;
    private Image missionNeapel;
    private Image freieKaperfahrt;


    public MissionScreen(Frijbuiter game) {
        this.game = game;

        this.stage = new Stage();
        Gdx.input.setInputProcessor(this.stage);

        Texture dot = new Texture("screens/Missionsmenue/dot.png");
        this.dotRegion = new TextureRegion(dot);

        this.initBackground();
        this.initializeButton();
        this.initMissionSalvadorI();
        this.initMissionSalvadorII();
        this.initMissionVictoria();
        this.initMissionSilberschatzflotte();
        this.initMissionLuanda();
        this.initMissionNeapel();
        this.initFreieKaperfahrt();


        this.stage.addActor(this.background);
        this.stage.addActor(this.button);
        this.stage.addActor(this.missionSalvaldorI);
        this.stage.addActor(this.missionSalvaldorII);
        this.stage.addActor(this.missionVictoria);
        this.stage.addActor(this.missionSilberschatzflotte);
        this.stage.addActor(this.missionLuanda);
        this.stage.addActor(this.missionNeapel);
        this.stage.addActor(this.freieKaperfahrt);

    }


    private void initBackground(){
        Texture texture = new Texture("screens/Missionsmenue/MissionsmenueFINAL.jpg");
        TextureRegion region = new TextureRegion(texture);
        this.background = new Image(region);
        this.background.setPosition(0,0);
    }

    private void initMissionSalvadorI(){
        this.missionSalvaldorI = new Image(this.dotRegion);
        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectMission(Level.Name.SalvadorI);
            }
        };
        this.missionSalvaldorI.addListener(event);
        this.missionSalvaldorI.setPosition(125,320);

    }

    private void initMissionSalvadorII(){
        this.missionSalvaldorII = new Image(this.dotRegion);
        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectMission(Level.Name.SalvadorII);
            }
        };
        this.missionSalvaldorII.addListener(event);
        this.missionSalvaldorII.setPosition(125,290);

    }

    private void initMissionVictoria(){
        this.missionVictoria = new Image(this.dotRegion);
        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectMission(Level.Name.Victoria);
            }
        };
        this.missionVictoria.addListener(event);
        this.missionVictoria.setPosition(115,350);

    }

    private void initMissionSilberschatzflotte(){
        this.missionSilberschatzflotte = new Image(this.dotRegion);
        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectMission(Level.Name.Silberschatzflotte);
            }
        };
        this.missionSilberschatzflotte.addListener(event);
        this.missionSilberschatzflotte.setPosition(90,425);

    }

    private void initMissionLuanda(){
        this.missionLuanda = new Image(this.dotRegion);
        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectMission(Level.Name.Luanda);
            }
        };
        this.missionLuanda.addListener(event);
        this.missionLuanda.setPosition(220,300);

    }

    private void initMissionNeapel(){
        this.missionNeapel = new Image(this.dotRegion);
        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectMission(Level.Name.Neapel);
            }
        };
        this.missionNeapel.addListener(event);
        this.missionNeapel.setPosition(210,500);

    }

    private void initFreieKaperfahrt(){
        this.freieKaperfahrt = new Image(this.dotRegion);
        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
               // selectMission(Level.Name.Neapel);
                System.out.println("freie kaperfahrt");
            }
        };
        this.freieKaperfahrt.addListener(event);
        this.freieKaperfahrt.setPosition(310,200);

    }

    private void selectMission(final Level.Name levelName){
        final Table table = new Table();
        table.setWidth(320);
        table.setHeight(200);
        table.setPosition(80,400);

        Texture popUpBackground = new Texture("screens/Missionsmenue/popupbackground.png");
        TextureRegion region = new TextureRegion(popUpBackground);
        TextureRegionDrawable regionDrawable = new TextureRegionDrawable(region);
        table.setBackground(regionDrawable);

        Label label = new Label( levelName.name(), new Label.LabelStyle( createFont(40), Color.BLACK));


        Texture difficulty;

        switch(levelName){
            case Neapel: {
                difficulty = new Texture("screens/Missionsmenue/diff1.png");
                break;
            }
            case SalvadorI: {
                difficulty = new Texture("screens/Missionsmenue/diff2.png");
                break;
            }
            case Luanda: {
                difficulty = new Texture("screens/Missionsmenue/diff3.png");
                break;
            }
            case Victoria: {
                difficulty = new Texture("screens/Missionsmenue/diff4.png");
                break;
            }
            case SalvadorII: {
                difficulty = new Texture("screens/Missionsmenue/diff5.png");
                break;
            }
            case Silberschatzflotte:{
                difficulty = new Texture("screens/Missionsmenue/diff6.png");
                break;
            }
            default: {
                difficulty = new Texture("screens/Missionsmenue/diff1.png");
            }
        }

        TextureRegion difficultyRegion = new TextureRegion(difficulty);
        Image difficultyImage = new Image(difficultyRegion);

        Texture backBtn = new Texture("screens/Missionsmenue/ZurückButton.png");
        TextureRegion backBtnRegion = new TextureRegion(backBtn);
        Image backButton = new Image(backBtnRegion);


        ClickListener eventBack = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.setVisible(false);
                table.remove();
            }
        };

        backButton.addListener(eventBack);

        Texture playBtn = new Texture("screens/Missionsmenue/StartButton.png");
        TextureRegion playBtnRegion = new TextureRegion(playBtn);
        Image playButton = new Image(playBtnRegion);




        ClickListener eventPlay = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new PlayScreen(game,levelName));
            }
        };

        playButton.addListener(eventPlay);


        //table.row().uniformX();
        table.add(label).expandX();
        table.row().expandX();
        table.add(difficultyImage).padTop(20);
        table.row().uniformX();
        Table nested = new Table();
        nested.add(backButton);//width(62);
        nested.add(playButton).padLeft(40); //width(62);
        table.add(nested).padTop(10).padBottom(10);
        this.stage.addActor(table);
    }


    private void initializeButton( ){

        Texture background2 = new Texture("screens/Missionsmenue/backBtn.png");
        TextureRegion region2 = new TextureRegion(background2);
        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(region2);
        this.button = new ImageButton(textureRegionDrawable);
        ClickListener event = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MainMenueScreen(game));
            }
        };
        this.button.addListener(event);
        this.button.setPosition(20,20);
    }

    public BitmapFont createFont(int fontsize){
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/timesBold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size=fontsize;
        return fontGenerator.generateFont(fontParameter);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.stage);
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
       // this.dispose();
    }

    @Override
    public void hide() {
       // this.dispose();
    }

    @Override
    public void dispose() {
        this.stage.dispose();
    }
}
