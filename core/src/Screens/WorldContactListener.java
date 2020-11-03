package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import alive.Enemy;
import alive.Spyder;

public class WorldContactListener implements ContactListener {

	private Spyder player ;
	
	public WorldContactListener(Spyder player) {
		this.player = player ;
	}

	@Override
	public void beginContact(Contact contact) {
		Fixture body1 = contact.getFixtureA() ;
		Fixture body2 = contact.getFixtureB() ;
		
		if(body1.getUserData() == "main_character" || body2.getUserData() == "main_character") {
			Fixture main_char = body1.getUserData() == "main_character" ? body1 : body2 ;
			Fixture object = main_char == body1 ? body2 : body1 ;
			
			if(object.getUserData() != null && Enemy.class.isAssignableFrom(object.getUserData().getClass())) {
				Enemy currentEnem = (Enemy) object.getUserData() ;
				Vector2 playerpos = (player.b2body.getPosition()) ;
				
				Vector2 dist = currentEnem.calcPath(playerpos); 
				
				if(currentEnem.name == "inner_circ") {
					Hud.decreaseHealth(1);
				}
				
			}
			
			
		}
	}

	@Override
	public void endContact(Contact contact) {

		Fixture body1 = contact.getFixtureA() ;
		Fixture body2 = contact.getFixtureB() ;
		
		if(body1.getUserData() == "main_character" || body2.getUserData() == "main_character") {
			Fixture main_char = body1.getUserData() == "main_character" ? body1 : body2 ;
			Fixture object = main_char == body1 ? body2 : body1 ;
			
			if(object.getUserData() != null && Enemy.class.isAssignableFrom(object.getUserData().getClass())) {
				Enemy currentEnem = (Enemy) object.getUserData() ;
				Vector2 playerpos = (player.b2body.getPosition()) ;
				
				Vector2 dist = currentEnem.calcPath(playerpos); 
				
			}
		}

	
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {}

}
