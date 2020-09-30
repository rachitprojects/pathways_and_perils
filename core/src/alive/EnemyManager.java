package alive;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import alive.Enemy.INIT;

// This code manages every enemy instance. 
public class EnemyManager {
	private Array<Enemy> enemies = new Array<Enemy>() ;
	
	public void createEnemy(World world , float x, float y, INIT dir) {
		enemies.add(new Enemy(world, x, y, dir));
	}

	public void updateEnemy(float delta) {
		for(Enemy enemy : enemies) {
			enemy.update(delta) ;	
		}
	}
	
	public void drawEnemy(SpriteBatch batch) {
		for(Enemy enemy : enemies) {
			enemy.draw(batch);
		}
	}

}
