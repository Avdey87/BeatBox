package com.aavdeev.beatbox;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Класс для поиска отслеживание и воспроизведения звуков
public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUND_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    //AssetManager класс для обращения к активам
    private AssetManager mAssets;
    private List<Sound> mSound = new ArrayList<>();
    private SoundPool mSoundPool;

    //конструктор класса который получает Context и извлекает AssetManager и сохраняет его на будущее
    public BeatBox(Context context) {
        mAssets = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSound();
    }

    public void play(Sound sound) {
        Integer soundId = sound.getSoundId();
        if (soundId == null) {
            return;
        }
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }
    public void release() {
        mSoundPool.release();
    }

    //метод для загрузки списка звуков
    private void loadSound() {
        //создаем постой строковый массив
        String[] soundNames;
        try {
            //пишим в масив список звуков получаемый из папки sample_sound хронящаеся в константе SOUND_FOLDER
            soundNames = mAssets.list(SOUND_FOLDER);
            //пишим в лог найдено + длинну найденого, тоесть количество звуков
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        }
        //обрабатываем исключение
        catch (IOException e) {
            //пишу в лог что не удалось найти данный списко если его нет.
            Log.e(TAG, "Could not list assests", e);
            return;
        }
        //проходим по списку soundNames
        for (String filename : soundNames) {
            try {
                //каждый раз записывая в переменную путь к файллу
                String assestPath = SOUND_FOLDER + "/" + filename;
                //создаем переменную типа Sound и записываем внее путь к звуку
                Sound sound = new Sound(assestPath);
                load(sound);
                //вноси
                mSound.add(sound);
            } catch (IOException e) {
                Log.e(TAG, "Cloud not load sound" + filename, e);
            }
            }
    }

    private void load(Sound sound) throws IOException {
        //Загружает в soundpool  для последующего воспроизведения
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetParh());
        //возвращает индификатор типа int
        int soundId = mSoundPool.load(afd, 1);
        sound.setSoundId(soundId);
    }

    //геттер возвращает списо к звуков
    public List<Sound> getSound() {
        return mSound;
    }
}
