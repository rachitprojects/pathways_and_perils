package com.mygdx.game.mytile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class MainCharacter extends Sprite {
	private World world_here ;
	public Body circbody ; 
	
	public MainCharacter(World world, TextureRegion tex_region) {
		super(tex_region) ;
		world_here = world ;
		System.out.println("Here") ;
		defineBody() ;
		System.out.println("Here") ;
		setBounds(0, 0, 32, 32) ;
		setRegion(tex_region) ;

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
    	circbody = world_here.createBody(circdef) ;
		System.out.println("Here") ;

    	circshape.setRadius(8f) ;
    	fdefcirc.shape = circshape ; 
    	fdefcirc.density = 1f ;
    	fdefcirc.friction = 2f ; 
//    	fdefcirc.restitution = 3f ;
    	circbody.createFixture(fdefcirc) ; 

	}

	public void update() {
		setPosition(circbody.getPosition().x - getWidth() / 2, circbody.getPosition().y - getHeight() /  2 ) ;
		
	}
}
