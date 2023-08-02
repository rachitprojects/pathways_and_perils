

package alive;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import alive.Enemy.INIT;

// This code manages every enemy instance. 
public class EnemyManager {
	private Array<Enemy> enemies = new Array<Enemy>() ;
	private Texture enem_body;
	
	public EnemyManager() {
		enem_body = new Texture("crawlers.png") ;
	}
	
	
	public void createEnemy(World world , float x, float y, INIT dir) {
		enemies.add(new Enemy(world, x, y, dir, this));
	}

	public void updateEnemy(float delta) {
		for(Enemy enemy : enemies) {
			enemy.moveBody();
			enemy.update(delta) ;	
		}
	}
	
	public void drawEnemy(SpriteBatch batch) {
		for(Enemy enemy : enemies) {
			enemy.draw(batch);
		}
	}
	
	public void clearEnemies() {
		enemies.clear();
	}
	
	public Texture getTexture() {
		return enem_body ;
	}

}


