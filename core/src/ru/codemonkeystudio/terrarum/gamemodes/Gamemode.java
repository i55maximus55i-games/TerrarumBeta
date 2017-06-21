package ru.codemonkeystudio.terrarum.gamemodes;

import java.util.ArrayList;

import ru.codemonkeystudio.terrarum.Terrarum;
import ru.codemonkeystudio.terrarum.objects.Enemy;
import ru.codemonkeystudio.terrarum.objects.Food;
import ru.codemonkeystudio.terrarum.objects.GameWorld;
import ru.codemonkeystudio.terrarum.objects.Player;
import ru.codemonkeystudio.terrarum.tools.GameRenderer;

/**
 * Created by maximus on 20.06.2017.
 */

public interface Gamemode {
    void init(Terrarum game, GameRenderer renderer, GameWorld gameWorld, Player player, ArrayList<Food> foods, ArrayList<Enemy> enemies);

    public void update(float delta);
    public boolean isGameOver();
    public void endGame();

    public int getScore();
    public float getTimer();
    public int getWorldSize();

    public void dispose();
}
