package com.mygdx.game.mytile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class MainCharacter extends Sprite {
	
	private World world ;
	public Body circbody ; 
	public enum State {UP, DOWN, LEFT, RIGHT, STANDING} ;
	public State currentState ;
	public Animation moveRight ;
	public Animation moveUp ;
	public Animation moveDown ; 
	public Animation moveLeft ;
	private float stateTimer ; 
	public TextureRegion standing ;
	private State previousState;
	
	
	public MainCharacter(World world) {
		super(TileTest.charac_im) ;
		this.world = world ;
		currentState = State.STANDING ;
		previousState = State.STANDING ;
		stateTimer = 0 ;
		Array<TextureRegion> frames = new Array<TextureRegion>() ; 
		for(int i = 0 ; i < 3 ; i++)
			frames.add(new TextureRegion(getTexture(), i * 41, 0, 41, 36)) ;
		moveDown = new Animation(0.1f, frames) ;
		frames.clear();
		
		for(int i = 0 ; i < 3 ; i++)
			frames.add(new TextureRegion(getTexture(), i * 41, 36, 41, 36)) ;
		moveRight = new Animation(0.1f, frames) ;
		frames.clear();
		
		for(int i = 0 ; i < 3 ; i++)
			frames.add(new TextureRegion(getTexture(), i * 41, 72, 41, 36)) ;
		moveUp = new Animation(0.1f, frames) ;
		frames.clear();
		
		for(int i = 0 ; i < 3 ; i++)
			frames.add(new TextureRegion(getTexture(), i * 41, 108, 41, 36)) ;
		moveLeft = new Animation(0.1f, frames) ;
		frames.clear() ;
		
		defineBody() ;
//		System.out.println("Here") ;
		setBounds(0, 0, 32, 32) ;
		standing = new TextureRegion(getTexture(), 0, 0, 41, 36) ;
		setRegion(standing) ;

	}
	
	public  void inputHandle() {
	      if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) ) {
//	      	circbody.applyLinearImpulse(new Vector2(1000f, 0), circbody.getWorldCenter(), true) ;
//	      	System.out.println(circbody.getPosition().x);
	    	  if(circbody.getLinearVelocity().x == 0) {
	     	 circbody.setLinearVelocity(400f, 0f);
	    	  }else {
	          	circbody.applyLinearImpulse(new Vector2(1000f, 0f), circbody.getWorldCenter(), true ) ;

	    	  }
	        	System.out.println(circbody.getLinearVelocity());

	    	
	      }else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//	        	circbody.applyLinearImpulse(new Vector2(-1000f, 0), circbody.getWorldCenter(), true ) ;
//	        	System.out.println(circbody.getPosition().x);
	    	  if(circbody.getLinearVelocity().x == 0) {
	     	 circbody.setLinearVelocity(-400f, 0f);
	    	  }else {
	          	circbody.applyLinearImpulse(new Vector2(-1000f, 0), circbody.getWorldCenter(), true ) ;

	    	  }
	        	System.out.println(circbody.getLinearVelocity());
	      }else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
	    	  if(circbody.getLinearVelocity().y == 0) {
	     	 circbody.setLinearVelocity(0f, 400f);
	    	  }else {
	          	circbody.applyLinearImpulse(new Vector2(0f, 1000f), circbody.getWorldCenter(), true ) ;

	    	  }
	        	System.out.println(circbody.getLinearVelocity());

	      }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
	    	  if(circbody.getLinearVelocity().y == 0) {
	     	 circbody.setLinearVelocity(0f, -400f);
	    	  }else {
	          	circbody.applyLinearImpulse(new Vector2(0, -1000f), circbody.getWorldCenter(), true ) ;

	    	  }
	        	System.out.println(circbody.getLinearVelocity());

	      }
	      else {
	    	 circbody.setLinearVelocity(0f, 0f);
	      }
	      

	}
	
	public void defineBody() {
    	BodyDef circdef = new BodyDef() ; 
    	CircleShape circshape = new CircleShape() ; 
    	FixtureDef fdefcirc = new FixtureDef() ; 
    	
    	circdef.type = BodyDef.BodyType.DynamicBody ; 
    	circdef.position.set(100, 400) ;
    	circbody = this.world.createBody(circdef) ;
		System.out.println("Here") ;

    	circshape.setRadius(8f) ;
    	fdefcirc.shape = circshape ; 
    	fdefcirc.density = 1f ;
    	fdefcirc.friction = 2f ; 
//    	fdefcirc.restitution = 3f ;
    	circbody.createFixture(fdefcirc) ; 

	}

	public void update(float dt) {
		setPosition(circbody.getPosition().x - getWidth() / 2, circbody.getPosition().y - getHeight() /  2 ) ;
		setRegion(getFrame(dt)) ;
	}

	private TextureRegion getFrame(float dt) { 
		currentState = getState() ;
		TextureRegion region ; 
		switch(currentState) {
			case UP:
				region = (TextureRegion) moveUp.getKeyFrame(stateTimer, true) ;
				break ;
			case DOWN:
				region = (TextureRegion) moveDown.getKeyFrame(stateTimer, true) ;
				break ;
			case LEFT:
				region = (TextureRegion) moveLeft.getKeyFrame(stateTimer, true) ;
				break ;
			case RIGHT:
				region = (TextureRegion) moveRight.getKeyFrame(stateTimer, true) ;
				break ;
			default:
				region = standing;
				break ;
		}
		stateTimer = currentState == previousState ? stateTimer + dt : 0 ;
		previousState = currentState ; 
		return region ;
	}

	private State getState() {
		if(circbody.getLinearVelocity().x > 0) {
			return State.RIGHT ;
		}else if(circbody.getLinearVelocity().x < 0) {
			return State.LEFT ;
		}else if(circbody.getLinearVelocity().y > 0) {
			return State.UP ; 
		}else if(circbody.getLinearVelocity().y < 0) {
			return State.DOWN ; 
		}else {
		return State.STANDING ;
		}
	}
}
