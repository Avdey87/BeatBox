package com.aavdeev.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;

//Класс для поиска отслеживание и воспроизведения звуков
public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUND_FOLDER = "sample_sound";
    //AssetManager класс для обращения к активам
    private AssetManager mAssets;
//конструктор класса который получает Context и извлекает AssetManager и сохраняет его на будущее
    public BeatBox(Context context) {
        mAssets = context.getAssets();
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
    }
}
