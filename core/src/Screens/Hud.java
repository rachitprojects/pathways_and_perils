package Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kingsman.dungeon.Dungeon;

public class Hud {

	public Stage stage ;
	private Viewport viewport ;
	
	private Integer worldTimer ;
	private float timeCount ; 
	private Integer score ;
	
	Label countdownLabel ; 
	Label timeLabel ;
	Label levelLabel ;
	Label health_text_label ;
	private static int health;
	private static Label healthLabel;
	
	public Hud(SpriteBatch sb) {
		worldTimer = 300 ;
		timeCount = 0 ;
		health = 500 ;
		
		viewport = new FitViewport(800, 480, new OrthographicCamera()) ;
		stage = new Stage(viewport, sb) ;
		
		Table table = new Table() ;
		table.top(); 
		table.setFillParent(true) ;
	
		countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE)) ;
		healthLabel = new Label(String.format("%03d", health), new Label.LabelStyle(new BitmapFont(), Color.WHITE)) ;
		timeLabel = new Label("TIME LEFT", new Label.LabelStyle(new BitmapFont(), Color.WHITE)) ;
		health_text_label = new Label("HEALTH", new Label.LabelStyle(new BitmapFont(), Color.WHITE)) ;
		
		table.add(timeLabel).expandX().padTop(10) ;
		table.add(health_text_label).expandX().padTop(10) ;
		table.row();
		table.add(countdownLabel).expandX().padTop(10) ;
		table.add(healthLabel).expandX().padTop(10) ;

		stage.addActor(table);
	}
	
	public void update(float dt) {
		timeCount += dt ;
		if(timeCount >= 1) {
			worldTimer-- ;
			countdownLabel.setText(String.format("%03d", worldTimer));
			timeCount = 0 ;
		}
	}
	
	public static void decreaseHealth(int val) {
		health -= val ;
		if(health<0){
			Dungeon.Dead=true;
		}
		healthLabel.setText(String.format("%03d", health));
	}
}