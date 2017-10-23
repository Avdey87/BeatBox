package com.aavdeev.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Класс для поиска отслеживание и воспроизведения звуков
public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUND_FOLDER = "sample_sounds";

    //AssetManager класс для обращения к активам
    private AssetManager mAssets;
    private List<Sound> mSound = new ArrayList<>();

    //конструктор класса который получает Context и извлекает AssetManager и сохраняет его на будущее
    public BeatBox(Context context) {
        mAssets = context.getAssets();
        loadSound();
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
            //каждый раз записывая в переменную путь к файллу
            String assestPath = SOUND_FOLDER + "/" + filename;
            //создаем переменную типа Sound и записываем внее путь к звуку
            Sound sound = new Sound(assestPath);
            //вноси
            mSound.add(sound);
        }
    }

    //геттер возвращает списо к звуков
    public List<Sound> getSound() {
        return mSound;
    }
}
