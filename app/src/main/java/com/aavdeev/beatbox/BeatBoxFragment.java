package com.aavdeev.beatbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class BeatBoxFragment extends Fragment {
    private BeatBox mBeatBox;
    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBeatBox = new BeatBox(getActivity());
    }

    //Преопределяем метод создания виью
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Создаем View переменную и записываем в нее вью элемент с параметрами
        //получеными из fragment_beat_box.xml помещаем это в контейнер
        //(создаем из лайоут файла вью элемент )
        View view = inflater.inflate(R.layout.fragment_beat_box, container, false);
        RecyclerView recyclerView = (RecyclerView) view
                .findViewById(R.id.fragment_beat_box_recycler_view);
       //указываем что сетка состоит из 3х столбцов
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        //подключаем SoundAdapter
        recyclerView.setAdapter(new SoundAdapter());
        return view;
    }

    //Создаем внутренний класс для отображение активности и кнопки на ней
    private class SoundHolder extends RecyclerView.ViewHolder {
        private Button mButton;
        private Sound mSound;

        //Конструктор класс. В нем два параметра типа LayoutInflater и ViewGroup
        //т.е. LayoutInflater тип переменной для программмного создание лайоут объекта, ViewGroup переменная для создание объекта типа вью
        public SoundHolder(LayoutInflater inflater, ViewGroup container) {
            //конструктор возвращает лайоут с ид list_item_sound и переданным в него ViewGroup параметром container, создаем вью программно
            super(inflater.inflate(R.layout.list_item_sound, container));
// определяем кнпку в созданной вью
            mButton = (Button) itemView.findViewById(R.id.list_item_sound_button);
        }

        //Связываем обьект Sound
// метод устанавливат звук на кнопку
        public void bindSound(Sound sound) {
            mSound = sound;
            //установить текс (название звука) на кнопку
            mButton.setText(mSound.getName());
        }
    }

    //Создаем адаптер связанный с SoundHolder
    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {
        private List<Sound> mSound;
//связываем адаптер с объектом Sound
        public SoundAdapter(List<Sound> sounds) {
            mSound = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new SoundHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            Sound sound = mSound.get(position);
            holder.bindSound(sound);
        }

        @Override
        public int getItemCount() {
            return mSound.size();
        }
    }


}
