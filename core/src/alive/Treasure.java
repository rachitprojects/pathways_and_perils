package alive;

import Screens.Playscreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;


public class Treasure extends Sprite {
    public final static byte treasure_byte = 16 ;
    public World world;
    public Body b2body;
    public enum STATES{CLOSE, OPEN}
    public TextureRegion treasure_tex;
    protected Fixture fixture;


    public Treasure(World world, Playscreen screen) {

        super(new Texture("close.png")) ;
        treasure_tex = new TextureRegion(getTexture(),0,0,64,68);
        this.world = world ;


        defineTreasure() ;
        setBounds(0, 0, 64, 68) ;
        setPosition(b2body.getPosition().x- getWidth() / 2, b2body.getPosition().y - getHeight() /  2) ;
        //setRegion(treasure_tex);

    }
    public void update(float dt){
        setPosition(3595, 6229);
        System.out.println(" " + b2body.getPosition().x + " " + b2body.getPosition().y);
        setRegion(treasure_tex);
    }

    public void defineTreasure() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(3495,6229);
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(25);

        fdef.shape = shape;
        //fdef.isSensor = true;
        fdef.filter.categoryBits = treasure_byte ;

        b2body.createFixture(fdef).setUserData("treasure");

    }

}
