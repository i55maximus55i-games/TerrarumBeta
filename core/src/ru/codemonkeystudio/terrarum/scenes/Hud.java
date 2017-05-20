package ru.codemonkeystudio.terrarum.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by maximus on 13.05.2017.
 */

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private float timer;

    private Label timeLabel;
    private Label timerLabel;
    private Label liveLabel;
    private Label livesLabel;
    private Label foodLabel;
    private Label foodsLabel;

    public Hud(SpriteBatch batch) {
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        timeLabel = new Label("Time", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timerLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        liveLabel = new Label("Lives", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        livesLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        foodLabel = new Label("Food", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        foodsLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(liveLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.add(foodLabel).expandX().padTop(10);
        table.row();
        table.add(livesLabel).expandX();
        table.add(timerLabel).expandX();
        table.add(foodsLabel).expandX();

        stage.addActor(table);
    }

    public void update(float delta, int lives, int food) {
        timer += delta;
        int t = (int) timer;
        timerLabel.setText(String.format("%02d", t / 60) + ":" + (String.format("%02d", t % 60)));
        livesLabel.setText(Integer.toString(lives));
        foodsLabel.setText(Integer.toString(food));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}