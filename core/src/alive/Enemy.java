package alive;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Enemy extends Sprite {
	private World world ;
	private Body enemBody;
	public Enemy(World world) {
//		super(new Texture("")) ;
		this.world = world ; 
		defineBody() ;
//		setBounds(0, 0, 32, 32) ;
		setPosition(enemBody.getPosition().x - getWidth() / 2, enemBody.getPosition().y - getHeight() /  2 ) ;

	}
	
	public void defineBody() {
		BodyDef bdef = new BodyDef() ; 
		CircleShape cshape = new CircleShape() ;
		FixtureDef enemfix = new FixtureDef() ; 
		
		bdef.type = BodyDef.BodyType.DynamicBody ; 
		bdef.position.set(50 , -50) ; 
		enemBody = this.world.createBody(bdef) ; 
		
		enemfix.shape = cshape ; 
		enemBody.createFixture(enemfix) ;
		
	}
	
}
