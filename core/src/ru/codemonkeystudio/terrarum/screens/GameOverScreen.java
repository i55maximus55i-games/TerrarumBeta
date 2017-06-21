package ru.codemonkeystudio.terrarum.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import ru.codemonkeystudio.terrarum.Terrarum;

/**
 * Экран проигрыша
 */

public class GameOverScreen implements Screen{

    private SpriteBatch batch;
    private Terrarum game;
    private OrthographicCamera cam;
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private BitmapFont font_16,font_24,font_32;

    private Sound sound;

    private float time;
    private int score;
    private TextField textField;

    public GameOverScreen(Terrarum game, float time, int score){
        this.time = time;
        this.score = score;
        stage = new Stage();
        this.game = game;
        this.batch = game.batch;
        cam = new OrthographicCamera();

        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/select.wav"));

        table = new Table();
        table.center();
        table.setFillParent(true);

        font_16 = new BitmapFont(Gdx.files.internal("fonts/Terrarum_16.fnt"), Gdx.files.internal("fonts/Terrarum_16.png"), false);
        font_24 = new BitmapFont(Gdx.files.internal("fonts/Terrarum_24.fnt"), Gdx.files.internal("fonts/Terrarum_24.png"), false);
        font_32 = new BitmapFont(Gdx.files.internal("fonts/Terrarum_32.fnt"), Gdx.files.internal("fonts/Terrarum_32.png"), false);

    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(960,540, cam));
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();
        atlas = new TextureAtlas(Gdx.files.internal("textures/textureUI.pack"));
        skin.addRegions(atlas);

        Label message = new Label(game.bundle.get("gameOverLabel"), new Label.LabelStyle(font_32, Color.GREEN));

        TextButton.TextButtonStyle menuButtonStyle = new TextButton.TextButtonStyle();
        menuButtonStyle.font = font_24;
        menuButtonStyle.up = skin.getDrawable("btn_default");
        menuButtonStyle.over = skin.getDrawable("btn_active");
        menuButtonStyle.down = skin.getDrawable("btn_pressed");
        menuButtonStyle.pressedOffsetX = 1;
        menuButtonStyle.pressedOffsetY = -1;

        TextButton menuButton = new TextButton(game.bundle.get("menuLabel"), menuButtonStyle);
        menuButton.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                sound.play(game.getSoundVolume());
                stage.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = font_24;
        textFieldStyle.fontColor = Color.GRAY;
        textFieldStyle.focusedFontColor = Color.WHITE;
        textFieldStyle.cursor = skin.getDrawable("cursor");

        textField = new TextField("", textFieldStyle);
        textField.setMessageText("Enter name");
        textField.setMaxLength(10);


        int t = (int) time;
        Label timerLabel = new Label(game.bundle.get("timeLabel") + " " + String.format("%02d", t / 60) + ":" + (String.format("%02d", t % 60)), new Label.LabelStyle(font_32, Color.WHITE));
        Label scoreLabel = new Label(game.bundle.get("scoreLabel") + " " + score, new Label.LabelStyle(font_32, Color.WHITE));

        table.add(message).expandX().padTop(32).row();
        table.add(timerLabel).expandX().row();
        table.add(scoreLabel).expandX().row();
        table.add(textField).expandX().center().width(160).row();
        table.add(menuButton).size(260, 90).expandX();

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        batch.setProjectionMatrix(cam.combined);

        stage.act(delta);
        stage.setDebugAll(true);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        atlas.dispose();
        skin.dispose();
        sound.dispose();

        font_16.dispose();
        font_24.dispose();
        font_32.dispose();
    }
}