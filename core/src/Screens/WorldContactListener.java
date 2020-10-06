package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import alive.Enemy;

public class WorldContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Fixture body1 = contact.getFixtureA() ;
		Fixture body2 = contact.getFixtureB() ; 
		System.out.println(body2.getUserData()) ;
		System.out.println(body1.getUserData()) ;

		// Add check to make sure neither of the bodies are those of the character 
		if((body1.getUserData() == "wall" || body2.getUserData() == "wall")) {
			Enemy enemy_body = (Enemy) (body1.getUserData() == "wall" ? body2.getUserData() : body1.getUserData()) ; 
			enemy_body.invert_dir() ;
		}
	}

	@Override
	public void endContact(Contact contact) {
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {}

}
