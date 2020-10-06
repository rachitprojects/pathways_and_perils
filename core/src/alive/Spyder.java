package alive;

import Screens.Playscreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public class Spyder extends Sprite {
    public enum State {UP, DOWN, LEFT, RIGHT, STANDING, UP_FAST, DOWN_FAST, LEFT_FAST, RIGHT_FAST};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion spyderStand;
    private Animation<TextureRegion> spyderUP;
    private Animation<TextureRegion> spyderDOWN;
    private Animation<TextureRegion> spyderLEFT;
    private Animation<TextureRegion> spyderRIGHT;
    private Animation<TextureRegion> spyderUP_FAST;
    private Animation<TextureRegion> spyderDOWN_FAST;
    private Animation<TextureRegion> spyderLEFT_FAST;
    private Animation<TextureRegion> spyderRIGHT_FAST;
    private float stateTimer;

    public Spyder(World world, Playscreen screen){
        super(screen.getAtlas().findRegion("Down"));
        this.world = world;
        defineSpyder();

        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i < 5; i++)
            frames.add(new TextureRegion(getTexture(), i*275, 0, 275, 275));
        spyderDOWN= new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for(int i = 0; i < 5; i++)
            frames.add(new TextureRegion(getTexture(), i*275, 275*4, 275, 275));
        spyderLEFT = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for(int i = 0; i < 5; i++)
            frames.add(new TextureRegion(getTexture(), i*275, 275*8, 275, 275));
        spyderRIGHT = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for(int i = 0; i < 5; i++)
            frames.add(new TextureRegion(getTexture(), i*275, 275*12, 275, 275));
        spyderUP = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for(int i = 0; i < 4; i++)
            frames.add(new TextureRegion(getTexture(), i*275, 275*3, 275, 275));
        spyderDOWN_FAST= new Animation<TextureRegion>(0.05f, frames);
        frames.clear();

        for(int i = 0; i < 4; i++)
            frames.add(new TextureRegion(getTexture(), i*275, 275*7, 275, 275));
        spyderLEFT_FAST = new Animation<TextureRegion>(0.05f, frames);
        frames.clear();

        for(int i = 0; i < 4; i++)
            frames.add(new TextureRegion(getTexture(), i*275, 275*11, 275, 275));
        spyderRIGHT_FAST = new Animation<TextureRegion>(0.05f, frames);
        frames.clear();

        for(int i = 0; i < 4; i++)
            frames.add(new TextureRegion(getTexture(), i*275, 275*15, 275, 275));
        spyderUP_FAST = new Animation<TextureRegion>(0.05f, frames);
        frames.clear();

        spyderStand = new TextureRegion(getTexture(), 0, 0, 275, 275);
        setBounds(0,0, 150,150);
        setRegion(spyderStand);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();

        TextureRegion region;
        switch(currentState){
            case DOWN:
                region = spyderDOWN.getKeyFrame(stateTimer, true);
                spyderStand = region;
                break;
            case DOWN_FAST:
                region = spyderDOWN_FAST.getKeyFrame(stateTimer, true);
                spyderStand = region;
                break;
            case UP:
                region = spyderUP.getKeyFrame(stateTimer, true);
                spyderStand = region;
                break;
            case UP_FAST:
                region = spyderUP_FAST.getKeyFrame(stateTimer, true);
                spyderStand = region;
                break;
            case RIGHT:
                region = spyderRIGHT.getKeyFrame(stateTimer, true);
                spyderStand = region;
                break;
            case RIGHT_FAST:
                region = spyderRIGHT_FAST.getKeyFrame(stateTimer, true);
                spyderStand = region;
                break;
            case LEFT:
                region = spyderLEFT.getKeyFrame(stateTimer, true);
                spyderStand = region;
                break;
            case LEFT_FAST:
                region = spyderLEFT_FAST.getKeyFrame(stateTimer, true);
                spyderStand = region;
                break;
            case STANDING:
                region = spyderStand;
                break;
            default:
                region = spyderStand;
                break;
        }
        stateTimer = currentState == previousState ? stateTimer +dt :0;
        previousState = currentState;
        return region;
    }

    public State getState(){
       if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
           return State.RIGHT_FAST;
       else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
           return State.RIGHT;
       else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)&& Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
           return State.LEFT_FAST;
       else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
           return State.LEFT;
       else if(Gdx.input.isKeyPressed(Input.Keys.UP)&& Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
           return State.UP_FAST;
       else if(Gdx.input.isKeyPressed(Input.Keys.UP))
           return State.UP;
       else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)&& Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
           return State.DOWN_FAST;
       else if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
           return State.DOWN;
       else
           return State.STANDING;
    }

    public void defineSpyder(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(1300,1000);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(25);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
