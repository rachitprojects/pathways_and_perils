package Screens;

import alive.Treasure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import alive.Enemy;
import alive.Spyder;
import com.kingsman.dungeon.Dungeon;

public class WorldContactListener implements ContactListener {

	private Spyder player ;
	
	public WorldContactListener(Spyder player) {
		this.player = player ;
	}

	@Override
	public void beginContact(Contact contact) {
		Fixture body1 = contact.getFixtureA() ;
		Fixture body2 = contact.getFixtureB() ;
		
		
		int cdef = body1.getFilterData().categoryBits | body2.getFilterData().categoryBits ;
		
		switch(cdef) {
		
			case Spyder.spy_byte | Enemy.inner_byte:
				Fixture main_char = body1.getUserData() == "main_character" ? body1 : body2 ;
				Fixture object = main_char == body1 ? body2 : body1 ;
				
				Enemy currentEnem = (Enemy) object.getUserData() ;
				Vector2 playerpos = (player.b2body.getPosition()) ;
				
				Vector2 dist = currentEnem.calcPath(playerpos); 
				Hud.decreaseHealth(10);
			break ;
			
			case Spyder.spy_byte | Enemy.outer_byte:
				Fixture main_char1 = body1.getUserData() == "main_character" ? body1 : body2 ;
				Fixture object1 = main_char1 == body1 ? body2 : body1 ;
				
				Enemy currentEnem1 = (Enemy) object1.getUserData() ;
				Vector2 playerpos1 = (player.b2body.getPosition()) ;
				
				Vector2 dist1 = currentEnem1.calcPath(playerpos1); 
			break ;

			case Spyder.spy_byte | Treasure.treasure_byte:
				System.out.println("Hit Treasure");
				Dungeon.Win = true;
				break;
		
		}
		
		
	}

	@Override
	public void endContact(Contact contact) {

		Fixture body1 = contact.getFixtureA() ;
		Fixture body2 = contact.getFixtureB() ;

		
		int cdef = body1.getFilterData().categoryBits | body2.getFilterData().categoryBits ;
		
		switch(cdef) {
		
			case Spyder.spy_byte | Enemy.inner_byte:
				Fixture main_char = body1.getUserData() == "main_character" ? body1 : body2 ;
				Fixture object = main_char == body1 ? body2 : body1 ;
				
				Enemy currentEnem = (Enemy) object.getUserData() ;
				Vector2 playerpos = (player.b2body.getPosition()) ;
				
				Vector2 dist = currentEnem.calcPath(playerpos); 
			break ;
			
			case Spyder.spy_byte | Enemy.outer_byte:
				Fixture main_char1 = body1.getUserData() == "main_character" ? body1 : body2 ;
				Fixture object1 = main_char1 == body1 ? body2 : body1 ;
				
				Enemy currentEnem1 = (Enemy) object1.getUserData() ;
				Vector2 playerpos1 = (player.b2body.getPosition()) ;
				
				Vector2 dist1 = currentEnem1.calcPath(playerpos1); 
			break ;

				
				
		
		}

	
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {}

}
