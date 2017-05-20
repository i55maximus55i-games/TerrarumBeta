package ru.codemonkeystudio.terrarum.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

/**
 * Created by maximus on 10.05.2017.
 */

public class MusicPlayer implements Disposable {
    private ArrayList<Music> playList;
    private Music music;
    private int mus;
    private float volume;

    public MusicPlayer(float volume) {
        initMusic();
        setVolume(volume);
    }

    public MusicPlayer() {
        new MusicPlayer(1f);
    }

    private void initMusic() {
        ArrayList<Music> m = new ArrayList<Music>();
        for (int i = 0; i < Gdx.files.internal("music").list().length; i++) {
            m.add(Gdx.audio.newMusic(Gdx.files.internal(Gdx.files.internal("music").list()[i].file().getPath())));
        }

        playList = new ArrayList<Music>();
        while (m.size() > 0) {
            int r = (int) (Math.random() * m.size());
            playList.add(m.get(r));
            m.remove(r);
        }

        mus = 0;
        if (playList.size() > 0) {
            music = playList.get(mus);
        }
        changeMusic();
    }

    public void changeMusic() {
        if (playList.size() > 0) {
            mus++;
            if (mus >= playList.size())
                mus = 0;
            music.stop();
            music = playList.get(mus);
            music.play();
            music.setVolume(volume);
        }
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
        music.setVolume(volume);
    }

    @Override
    public void dispose() {
        music.stop();
        music.dispose();
        for (Music m : playList) {
            m.dispose();
        }
        while (playList.size() > 0) {
            playList.remove(0);
        }
    }
}