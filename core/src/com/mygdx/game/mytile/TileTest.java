package com.mygdx.game.mytile;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;	
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class TileTest extends ApplicationAdapter {
	private TiledMap map;
	private TiledMapRenderer renderer;
	private OrthographicCamera camera;
	private SpriteBatch batch ;
//	private AssetManager manager;
	private Integer tileWidth;
	private Integer tileHeight;
	private Integer mapWidthInTiles;
	private Integer mapHeightInTiles;
	private int mapWidthInPixels;
	private int mapHeightInPixels;

    private float x_pos_tune = 0.01f ; 
    private float y_pos_tune = 0.01f ; 
    
    private World world ;
    private Box2DDebugRenderer b2dr ; 

//	private Body circbody ;
	private Texture texture;
	private TextureRegion tex_region;
	private MainCharacter charac; 
	    
	@Override
	public void create () {
//        manager = new AssetManager();
//        manager.setLoader(TiledMap.class, new TmxMapLoader());
//        manager.load("level_1_better.tmx", TiledMap.class);
//        manager.finishLoading();

//        map = manager.get("level_1_better.tmx", TiledMap.class);
        
		map = new TmxMapLoader().load("level_1_better.tmx") ;
		
        MapProperties properties = map.getProperties();
        tileWidth         = properties.get("tilewidth", Integer.class);
        tileHeight        = properties.get("tileheight", Integer.class);
        mapWidthInTiles   = properties.get("width", Integer.class);
        mapHeightInTiles  = properties.get("height", Integer.class);
        mapWidthInPixels  = mapWidthInTiles  * tileWidth;
        mapHeightInPixels = mapHeightInTiles * tileHeight;

        renderer = new OrthogonalTiledMapRenderer(map);
        
        camera = new OrthographicCamera(320f, 320f) ; 
//        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        camera.position.x = mapWidthInPixels * .1f ;
//        camera.position.y = mapHeightInPixels * .1f ;
        
        camera.position.x = 100; 
        camera.position.y = 300; 

//          System.out.println(map.getLayers().get(3).getObjects().getCount()) ;
        
        world = new World(new Vector2(0, 0), true) ; 
        b2dr = new Box2DDebugRenderer() ;
        
        BodyDef bdef = new BodyDef() ;
        PolygonShape shape = new PolygonShape() ; 
        FixtureDef fdef = new FixtureDef() ;
        Body body ; 
        
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle() ;
        	
        	bdef.type = BodyDef.BodyType.StaticBody ; 
        	bdef.position.set((rect.getX() + rect.getWidth() / 2), (rect.getY() + rect.getHeight() / 2)) ;
        	
        	body = world.createBody(bdef) ; 
        	shape.setAsBox((rect.getWidth() / 2), (rect.getHeight() / 2));
        	fdef.shape = shape ; 
        	body.createFixture(fdef) ;
        }
        
        
    	texture = new Texture(Gdx.files.internal("hyptosis_tile-art-batch-1.png")) ; ;
//    	tex_region = new TextureRegion(texture, 32, 832, 64, 864) ;
    	tex_region = map.getTileSets().getTileSet(0).getTile(782).getTextureRegion() ; ;

    		charac = new MainCharacter(this.world, tex_region) ;
//            
//        	BodyDef circdef = new BodyDef() ; 
//        	CircleShape circshape = new CircleShape() ; 
//        	FixtureDef fdefcirc = new FixtureDef() ; 
//        	
//        	circdef.type = BodyDef.BodyType.DynamicBody ; 
//        	circdef.position.set(100, 400) ;
//        	circbody = world.createBody(circdef) ;
//
//        	circshape.setRadius(30f) ;
//        	fdefcirc.shape = circshape ; 
//        	fdefcirc.density = 1f ;
//        	fdefcirc.friction = 2f ; 
//        	fdefcirc.restitution = 3f ;
//        	circbody.createFixture(fdefcirc) ; 

        	
//        	circshape.setRadius(60f) ; 
//        	FixtureDef fixtureDef = new FixtureDef();
//        	fixtureDef.shape = circshape;
//        	
//        	Fixture fixture = circbody.createFixture(fixtureDef);         	
        	batch = new SpriteBatch() ;
        	
	}

	@Override
	public void render () {
//        Gdx.gl.glClearColor(.5f, .7f, .9f, 1);
        Gdx.gl.glClearColor(1, 1, 1, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        charac.inputHandle();         
        charac.update();

        
        world.step(1/60f, 6, 2) ;
        camera.position.x = mapWidthInPixels * x_pos_tune ; 
        camera.position.y = mapHeightInPixels * y_pos_tune ; 
        
//        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) x_pos_tune -= 0.01 ; 
//        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x_pos_tune += 0.01 ; 
//
//        if(Gdx.input.isKeyPressed(Input.Keys.UP)) y_pos_tune += 0.01 ; 
//        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) y_pos_tune -= 0.01 ; 
        
      camera.position.x = charac.circbody.getWorldCenter().x; 
      camera.position.y = charac.circbody.getWorldCenter().y; 
      
        camera.update();
        renderer.setView(camera);
        
        renderer.render();	
        b2dr.render(world, camera.combined);

        batch.setProjectionMatrix(camera.combined) ;
        batch.begin() ;
        charac.draw(batch);
        batch.end() ;
        
        

	}
	
	@Override
	public void dispose () {
//		manager.dispose(); 
	}
}
