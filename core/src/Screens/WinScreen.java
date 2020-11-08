package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.kingsman.dungeon.Dungeon;

/**
 * Created by brentaureli on 10/8/15.
 */
public class WinScreen implements Screen {
    private Viewport viewport;
    private Stage stage;

    private Game game;
    private Music music;

    public WinScreen(Game game){
        Dungeon.Win = false;
        this.game = game;
        viewport = new FitViewport(2000, 2000, new OrthographicCamera());
        stage = new Stage(viewport, ((Dungeon) game).batch);

        music = Gdx.audio.newMusic(Gdx.files.internal("Win.mp3"));
        music.setLooping(true);
        music.setVolume(1.0f);
        music.play();

        Texture Win_bg = new Texture("Win.png");
        Image BG= new Image(Win_bg);
        BG.setZIndex(0);
        stage.addActor(BG);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);


        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("YOU WIN", font);
        Label playAgainLabel = new Label("Click to Play Again", font);
        gameOverLabel.setFontScale(10);
        playAgainLabel.setFontScale(10);

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(playAgainLabel).expandX().padTop(10f);

        table.setZIndex(1);
        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            game.setScreen(new Playscreen((Dungeon) game));
            music.stop();
            music.dispose();
            dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
