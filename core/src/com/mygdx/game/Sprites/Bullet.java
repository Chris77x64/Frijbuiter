package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.JointEdge;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Screens.PlayScreen;

import java.util.ArrayList;

/**
 * Created by chris on 14.12.2016.
 */

public class Bullet extends Sprite{

    private final int BULLET_SPEED = 7000;

    private Vector3 position;
    private Vector3 target;

    private Vector3 acceleration;
    private Vector3 velocity;

    private Body b2body;
    private Fixture fixture;

    private Fixture fixtureShootingShip;

    private PlayScreen screen;

    private int damage;

    private long startTime;
    private boolean enemy;

    public Bullet(Vector2 start , Vector2 destination, PlayScreen screen, int damage, Fixture fixtureShootingShip, long startTime,boolean enemy) {
        super(new Texture(Gdx.files.internal("kanonenkugel.png")));
        this.damage = damage;
        this.startTime = startTime;
        this.fixtureShootingShip = fixtureShootingShip;
        this.screen = screen;
        this.reset(start,destination);
        this.acceleration = new Vector3(0,0,0);
        this.setOrigin(this.getWidth()/2,this.getHeight()/2);
        this.enemy = enemy;
    }

    private void reset(Vector2 start , Vector2 destination){
        this.position = new Vector3(start.x,start.y,0);
        this.target = new Vector3(destination.x,destination.y,0);
        this.velocity = this.target.cpy().sub(this.position).nor().scl(BULLET_SPEED);
        this.define_bullet(start.x,start.y);
    }

    public void render(SpriteBatch batch){
        Vector2 b2body_position = this.b2body.getPosition();
        batch.draw(this.getTexture(),b2body_position.x-12,b2body_position.y-12);
        this.screen.getGame().batch.setProjectionMatrix(this.screen.getPlayer().getCamera().combined);
    }

    public void update(float deltaTime){
        this.setPosition(this.b2body.getPosition().x,this.b2body.getPosition().y);
    }

    public void define_bullet( float x, float y){
        BodyDef bdef = new BodyDef();
        bdef.position.set( x+10 , y+10);
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.bullet = true;

        //create body in the world
        this.b2body = this.screen.getWorld().createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10);
        fdef.shape = shape;
        fdef.isSensor = true;
        this.fixture = this.b2body.createFixture(fdef);
        if(enemy){
            this.fixture.setUserData("EnemyBullet");
        }
        else{
            this.fixture.setUserData("Bullet");
        }
        this.b2body.setLinearVelocity(this.velocity.x,this.velocity.y);
        this.setBounds(0,0,20,20);
    }

    public void destroy(){
        this.getTexture().dispose();
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run () {
              //  final Array<JointEdge> list = b2body.getJointList();
             //   while (list.size > 0) {
             //       screen.getWorld().destroyJoint(list.get(0).joint);
            //    }
                screen.getWorld().destroyBody(b2body);
            }
        });


    }

    public Vector2 getPosition(){
        return this.b2body.getPosition();
    }

    public Fixture getFixtureShootingShip() {
        return fixtureShootingShip;
    }

    public int getDamage() {
        return damage;
    }

    public long getStartTime() {
        return startTime;
    }

}
