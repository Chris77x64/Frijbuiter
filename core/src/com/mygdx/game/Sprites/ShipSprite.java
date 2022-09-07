package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Animation.ShipAnimation;
import com.mygdx.game.Screens.PlayScreen;


/**
 * Created by chris on 04.01.2017.
 */

public abstract class ShipSprite extends Sprite {

    // PlayScreen
    private PlayScreen screen;

    // Enumeration of the possible States of our Ship
    public enum State{ LEFT,RIGHT,FORWARD,BACKWARD};
    public State currentState;
    public State previousState;

    private boolean isBurning;
    private boolean isPerishing;
    private long current_vanish_time;

    // keep track for how long we already stayed in our current state
    public float stateTimer;

    // Physics
    public World world;
    public Body b2body;

    // Fixture for collision detection purposes
    private Fixture shootingBodyFixture;

    // Texture & Animation
    protected ShipAnimation animation;
    private TextureRegion textureRegion;
    protected TextureAtlas atlas;

    protected boolean enemy;

    public ShipSprite(Vector2 position, PlayScreen screen,boolean enemy){
        this.screen = screen;
        this.world = screen.getWorld();

        // Initialize movements and animations
        this.currentState = State.FORWARD;
        this.previousState = State.FORWARD;
        this.stateTimer = 0;
        this.isBurning = false;
        this.isPerishing = false;
        this.enemy = enemy;
    }

    public void defineShip(int x, int y){
        BodyDef bdef = new BodyDef();
        bdef.position.set( x , y);
        bdef.type = BodyDef.BodyType.DynamicBody;

        //create body in the world
        this.b2body = world.createBody(bdef);

        FixtureDef collision = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(20);
        collision.shape = shape;
        this.b2body.createFixture(collision).setUserData("body");


        FixtureDef shootingBody = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(137/2,221/2);
        shootingBody.shape = polygonShape;
        shootingBody.isSensor = true;
        this.shootingBodyFixture = this.b2body.createFixture(shootingBody);
        if( this.enemy){
            this.shootingBodyFixture.setUserData("shootingBodyEnemy");
        }
        else{
            this.shootingBodyFixture.setUserData("shootingBody");
        }


        this.textureRegion = new TextureRegion(getTexture(),0,0,137,221);
        this.setBounds(0,0,137,221);
        this.setRegion(this.textureRegion);
    }

    public void update(float deltaTime){
        this.setPosition(this.b2body.getPosition().x - this.getWidth()/2,this.b2body.getPosition().y-this.getHeight()/2);
        this.setRegion(this.getAnimationFrame(deltaTime));

    }

    public TextureRegion getAnimationFrame( float deltaTime){

        TextureRegion region = this.animation.processMovements(this.currentState,this.stateTimer,this.isBurning,this.isPerishing);

        if( currentState == previousState){
            this.stateTimer = stateTimer + deltaTime;
        }
        else{
            // transition to new state
            this.stateTimer = 0;
        }
        this.previousState = currentState;
        return region;
    }

    public Fixture getShootingBodyFixture() {

        return shootingBodyFixture;
    }

    public void destroy( ){
        this.getTexture().dispose();
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run () {
                screen.getWorld().destroyBody(b2body);
            }
        });
    }

    public boolean isBurning() {
        return isBurning;
    }

    public void setBurning(boolean burning) {
        isBurning = burning;
    }

    public boolean isPerishing() {
        return isPerishing;
    }

    public void setPerishing(boolean perishing) {
        isPerishing = perishing;
    }

    public ShipAnimation getAnimation() {
        return animation;
    }

    public void setAnimation(ShipAnimation animation) {
        this.animation = animation;
    }

    public long getCurrent_vanish_time() {
        return current_vanish_time;
    }

    public void setCurrent_vanish_time(long current_vanish_time) {
        this.current_vanish_time = current_vanish_time;
    }
}
