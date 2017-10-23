package com.aavdeev.beatbox;

import android.content.Intent;

public class Sound {
    private String mAssetParh;
    private String mName;
    private Integer mSoundId;

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        this.mSoundId = soundId;
    }

    public Sound(String assetParh) {
        mAssetParh = assetParh;
        //В строковый массив записываем путьк вайлу разделя путь "/"
        String[] components = assetParh.split("/");
        String filename = components[components.length - 1];
        //после чего удаляем расширение файла. то есть заменяем ".wav" на ""
        mName = filename.replace(".wav", "");
    }

    public String getAssetParh() {
        return mAssetParh;
    }

    public String getName() {
        return mName;
    }
}
