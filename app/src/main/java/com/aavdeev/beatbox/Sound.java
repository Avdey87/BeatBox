package com.aavdeev.beatbox;

public class Sound {
    private String mAssetParh;
    private String mName;

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
