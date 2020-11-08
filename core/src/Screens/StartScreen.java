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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kingsman.dungeon.Dungeon;


public class StartScreen implements Screen {
    private Viewport viewport;
    private Stage stage;

    private Game game;
    private Music music;

    public StartScreen(Game game){
        Dungeon.Win = false;
        this.game = game;
        viewport = new FitViewport(600, 600, new OrthographicCamera());
        stage = new Stage(viewport, ((Dungeon) game).batch);

        music = Gdx.audio.newMusic(Gdx.files.internal("Start.mp3"));
        music.setLooping(true);
        music.setVolume(1.2f);
        music.play();

        Texture Win_bg = new Texture("Start.jpg");
        Image BG= new Image(Win_bg);
        BG.setZIndex(0);
        stage.addActor(BG);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);


        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("PATHWAYS AND PERILS", font);
        Label playAgainLabel = new Label("Click to START GAME", font);
        gameOverLabel.setFontScale(1);
        playAgainLabel.setFontScale(1);

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
