package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Player.Inventory;
import com.mygdx.game.Screens.MenueScreen;
import com.mygdx.game.Screens.PlayScreen;


public class Frijbuiter extends Game {

	/**
	 * Width and Height of our screen
	 * 3,7 Zoll Screen ( iPhone for example )
	 */
	public static final int V_WIDTH = 480;
	public static final int V_HEIGHT = 800;

	public static final String TITLE=" Fribujter";

	// We just need one SpriteBatch that gets passed around r
	public SpriteBatch batch;

	private Inventory inventory;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		this.inventory = new Inventory();

		this.setScreen(new MenueScreen(this));
		//this.gameStateManager = new GameStateManager();
		//this.gameStateManager.push(new MenuState(this.gameStateManager));
		//this.gameStateManager.push(new MenuState(this.gameStateManager));

	}

	// render is on loop all the time
	@Override
	public void render () {


		//delegate render method to whatever screen is active at the time
		super.render();
		//this.gameStateManager.update(Gdx.graphics.getDeltaTime());
		//this.gameStateManager.render(batch);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public Inventory getInventory() {
		return inventory;
	}
}
