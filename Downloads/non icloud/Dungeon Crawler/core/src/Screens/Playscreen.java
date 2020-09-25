package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kingsman.dungeon.Dungeon;

import Tools.B2WorldCreator;

public class Playscreen implements Screen {
	private Dungeon game;
	Texture texture;
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private Integer tileWidth;
	private Integer tileHeight;
	private Integer mapWidthInTiles;
	private Integer mapHeightInTiles;
	private int mapWidthInPixels;
	private int mapHeightInPixels; 
	
	private World world;
	private Box2DDebugRenderer b2dr;
	
	

	public Playscreen(Dungeon game) {
		this.game = game ;
		gamecam = new OrthographicCamera(600f , 600f);
		//gamePort = new FitViewport(800, 400, gamecam);
		maploader = new TmxMapLoader();
		map = maploader.load("Dungeon.tmx");
		MapProperties properties = map.getProperties();
        tileWidth         = properties.get("tilewidth", Integer.class);
        tileHeight        = properties.get("tileheight", Integer.class);
        mapWidthInTiles   = properties.get("width", Integer.class);
        mapHeightInTiles  = properties.get("height", Integer.class);
        mapWidthInPixels  = mapWidthInTiles  * tileWidth;
        mapHeightInPixels = mapHeightInTiles * tileHeight;
		renderer = new OrthogonalTiledMapRenderer(map);
		gamecam.position.x = 100;
        gamecam.position.y = 400 ;
        world = new World(new Vector2(0, 0), true);
		b2dr = new Box2DDebugRenderer();
		
		new B2WorldCreator(world , map);
		
		//gamecam.position.set(gamePort.getWorldWidth() / 2 ,gamePort.getWorldHeight() / 2, 0);\
		gamecam.position.set(0, 0, 0);
		
		}
		
		
		
	

	 
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	public void handleInput(float dt) {
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			     gamecam.position.x += 1000 * dt ; 
			 }else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			     gamecam.position.x -= 1000 * dt ;
			 }else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			          gamecam.position.y += 1000 * dt ;
			 }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			          gamecam.position.y -= 1000 * dt ;}
	}
	
	public void update(float dt) {
		handleInput(dt);
		gamecam.update();
		renderer.setView(gamecam);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update(delta);
		renderer.render();
		b2dr.render(world, gamecam.combined );
		game.batch.setProjectionMatrix(gamecam.combined);
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
       // gamePort.update(width, height);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
