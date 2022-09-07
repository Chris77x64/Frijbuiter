package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Collision.CollisionHandler;
import com.mygdx.game.Frijbuiter;
import com.mygdx.game.GestureInterface.MyInputProcessor;
import com.mygdx.game.Levels.Level;
import com.mygdx.game.Levels.campaign.Luanda;
import com.mygdx.game.Levels.campaign.Neapel;
import com.mygdx.game.Levels.campaign.SalvadorI;
import com.mygdx.game.Levels.campaign.SalvadorII;
import com.mygdx.game.Levels.campaign.Silberschatzflotte;
import com.mygdx.game.Levels.campaign.Victoria;
import com.mygdx.game.PhysX.B2WorldCreator;
import com.mygdx.game.Player.Enemy;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Scenes.MiniMapTracker;
import com.mygdx.game.Sprites.ShipSprite;

/**
 * Created by chris on 30.11.2016.
 */

public class PlayScreen implements Screen {

    // Game Interface
    private Frijbuiter game;
    private MyInputProcessor processor;

    // Level
    private Level level;
    private MiniMapTracker miniMapTracker;

    //Box2D Variables
    private World world;
    private Box2DDebugRenderer b2dr; // graphical representation of object layers
    private B2WorldCreator b2WorldCreator;


    // LibGDX Texture Packer
    private TextureAtlas atlas;

    private Player player;
    private Enemy enemy;

    private Hud hud;

    private OrthographicCamera camera;
    private boolean cameraTopFlag;

    // let the game look good at different screen sizes
    private Viewport gamePort;

    private boolean pause;

    public PlayScreen(Frijbuiter game, Level.Name levelName){

        this.game = game;

        //initial camera position
        this.camera = new OrthographicCamera();
        this.camera.position.set(250,260,0);
        this.cameraTopFlag = false;

        // a fit viewport always maintains the aspect ration we are working with
        this.gamePort = new FitViewport(game.V_WIDTH,game.V_HEIGHT, this.camera);


        // Add level
        this.selectLevel(levelName);

        // Add Physics
        this.world = new World(
                new Vector2(0,0), // (0.0) means no gravity
                true //sleeps bodies that rests , you can always wake up an object
        );
        this.b2dr = new Box2DDebugRenderer();
        this.b2WorldCreator = new B2WorldCreator(this);

        // Add TextureAtlas
        this.atlas = new TextureAtlas("Ships/ships_movement.pack");


        this.player = new Player(this.camera,this);
        this.enemy = new Enemy(this);
        // set map to play with
        this.hud = new Hud(this);
        this.miniMapTracker = new MiniMapTracker(this);
      //  this.espShip = new SpanishShip(this,250,300);

        CollisionHandler collisionHandler = new CollisionHandler(this);
        Gdx.graphics.setContinuousRendering(false);
        this.pause = false;

    }

    public void selectLevel(Level.Name levelName){

        switch (levelName){
            case Neapel: {
                this.level = new Neapel(this);
                break;
            }
            case SalvadorI: {
                this.level =  new SalvadorI(this);
                break;
            }
            case Luanda: {
                this.level = new Luanda(this);
                break;
            }
            case Victoria:{
                this.level =  new Victoria(this);
                break;
            }
            case SalvadorII:{
                this.level =   new SalvadorII(this);
                break;
            }
            case Silberschatzflotte: {
                this.level =  new Silberschatzflotte(this);
                break;
            }
        }
    }

    @Override
    public void show() {

    }


    public void handleInput( float deltaTime){



         if (Gdx.input.isKeyPressed(Input.Keys.UP)){
         this.player.getActive_ship().getSprite().currentState = ShipSprite.State.FORWARD;
         this.player.getActive_ship().move_forward();
         }
         else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
         this.player.getActive_ship().getSprite().currentState = ShipSprite.State.BACKWARD;
         this.player.getActive_ship().move_backward();
         }
         else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
         this.player.getActive_ship().getSprite().currentState = ShipSprite.State.LEFT;
         this.player.getActive_ship().move_left();
         }
         else if (Gdx.input.isKeyPressed((Input.Keys.RIGHT))){
         this.player.getActive_ship().getSprite().currentState = ShipSprite.State.RIGHT;
         this.player.getActive_ship().move_right();
         }
        else if( Gdx.input.isTouched()){
             Vector3 mouse_pos = this.getMousePosInGameWorld();
             this.player.select_ship(mouse_pos);
             this.player.shoot_ship(mouse_pos);
         }


        this.updateCamera();


    }

    private void updateCamera(){
        int mapLeft = 0;
        int mapRight = this.level.getMap().getProperties().get("tilewidth", Integer.class) * this.level.getMap().getProperties().get("width", Integer.class);
        int mapBottom = 0;
        int mapTop = this.level.getMap().getProperties().get("tileheight", Integer.class) * this.level.getMap().getProperties().get("height", Integer.class);


        float cameraHalfHeight = this.camera.viewportHeight * .5f;
        float cameraHalfWidth = this.camera.viewportWidth * .5f;

        this.camera.position.x =  this.player.getActive_ship().getSprite().b2body.getPosition().x;
        this.camera.position.y =  this.player.getActive_ship().getSprite().b2body.getPosition().y;

        float cameraLeft = this.camera.position.x - cameraHalfWidth;
        float cameraRight = this.camera.position.x + cameraHalfWidth;
        float cameraBottom = this.camera.position.y - cameraHalfHeight;
        float cameraTop = this.camera.position.y + cameraHalfHeight;

        // Horizontal axis
        if( mapRight < this.camera.viewportWidth)
        {
            this.camera.position.x = mapRight / 2;
        }
        else if(cameraLeft <= mapLeft)
        {
            this.camera.position.x = mapLeft + cameraHalfWidth;
        }
        else if(cameraRight >= mapRight)
        {
            this.camera.position.x = mapRight - cameraHalfWidth;
        }

        // Vertical axis
        if(mapTop < this.camera.viewportHeight)
        {
            this.camera.position.y = mapTop / 2;
        }
        else if(cameraBottom <= mapBottom)
        {
            this.camera.position.y = mapBottom + cameraHalfHeight-200;
        }
        else if(cameraTop >= mapTop)
        {
            this.camera.position.y = mapTop - cameraHalfHeight;
        }
    }

    public void update(float deltaTime){

        this.world.step( 1/60f,6,2);
        this.player.update(deltaTime);
        this.enemy.update(deltaTime);
//        this.espShip.update(deltaTime);

        handleInput(deltaTime);
        this.camera.update();
        this.level.update(deltaTime);
        this.miniMapTracker.update();

    }

    @Override
    public void render(float delta) {

        if( !isPause()) {
            Gdx.graphics.requestRendering();

            this.update(delta);

            this.gamePort.apply();
            // repaint the screen fresh and new
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            Gdx.gl.glClearColor(0, 0, 0, 0);

            this.level.render();

            /**
             * Render Phisics Debug
             */
            b2dr.render(this.world, this.camera.combined);


            /**
             * In our render method we need to tell our gamebatch
             * to recognize where the camera currently is
             * and for the sake of performance to render only what the camera can see
             * */


            this.game.batch.setProjectionMatrix(this.camera.combined);
            this.game.batch.begin();
            this.player.draw(game.batch);
            this.enemy.draw(game.batch);
            //  this.espShip.draw(game.batch);
            this.game.batch.end();

            // this.game.batch.setProjectionMatrix(this.hud.stage.getCamera().combined);

            this.hud.render();
            this.miniMapTracker.render(game.batch);



        }

    }

    @Override
    public void resize(int width, int height) {

        //its important when we change the size of our screen
        // that the viewport gets adjusted to know what the current screensize is
        this.gamePort.update(width,height);
    }


    Vector3 getMousePosInGameWorld() {
        return this.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void dispose() {

        this.level.dispose();
        this.world.dispose();
        this.b2dr.dispose();
        this.hud.dispose();

        this.hud.dispose();
        this.hud.stage.dispose();
    }

    public TextureAtlas getAtlas(){
        return this.atlas;
    }


    public World getWorld(){
        return world;
    }

    public Frijbuiter getGame() {
        return game;
    }

    public void setGame(Frijbuiter game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Level getLevel() {
        return level;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public Hud getHud() {
        return hud;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
}
