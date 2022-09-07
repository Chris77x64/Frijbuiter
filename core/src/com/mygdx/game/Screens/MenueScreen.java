package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Frijbuiter;

/**
 * Created by chris on 30.11.2016.
 */

public class MenueScreen implements Screen {

    private Frijbuiter game;
    Texture background;
    Texture playBtn;

    public MenueScreen(Frijbuiter game){
        this.game = game;
        background = new Texture("menue_bg.png");
        playBtn = new Texture("menue_button.png");
    }

    //Called when this screen becomes the current screen for a Game.
    @Override
    public void show() {

    }

    public void update(float deltaTime){
        if(Gdx.input.justTouched()){

            this.game.setScreen(new DockyardScreen(this.game));
            //this.game.setScreen(new MainMenueScreen(this.game));
          //  this.game.setScreen(new MissionScreen(this.game));
          //this.game.setScreen(new PlayScreen(this.game));

        }
    }


    @Override
    public void render(float delta) {
        // repaint the screen fresh and new
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 0);

        //draw images
        this.game.batch.begin();
        this.game.batch.draw(background, 0, 0, Frijbuiter.V_WIDTH, Frijbuiter.V_HEIGHT);
        this.game.batch.draw(playBtn, (int) (Frijbuiter.V_WIDTH / 2) - (playBtn.getWidth() / 2), Frijbuiter.V_HEIGHT / 7);
        this.game.batch.end();

        this.update(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        this.dispose();
    }

    //Called when this screen is no longer the current screen for a Game.
    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void dispose() {
        this.background.dispose();
        this.playBtn.dispose();
    }

}
