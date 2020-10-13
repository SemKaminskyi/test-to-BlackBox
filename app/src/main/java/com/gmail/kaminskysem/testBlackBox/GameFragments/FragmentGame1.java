package com.gmail.kaminskysem.testBlackBox.GameFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gmail.kaminskysem.testBlackBox.Data.AppDataBase;
import com.gmail.kaminskysem.testBlackBox.Data.Model;
import com.gmail.kaminskysem.testBlackBox.Data.UserDAO;
import com.gmail.kaminskysem.testBlackBox.R;

import java.util.List;
import java.util.Objects;

public class FragmentGame1<alphaImage> extends Fragment {
    private final static String LOG_TAG = FragmentGame1.class.getSimpleName();
    public UserDAO userDAO;
    List<Model>list;
    private Model model;

    private TextView task;
    private ImageButton animal;
    private ImageButton var1;
    private ImageButton var2;
    private ImageButton var3;
    private ImageButton var4;
    private int alphaImage = 150;


    private int idTask;
    private Animation animation;
    private Model model1;
    private Model model2;
    private Model model3;
    private Model model4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreateView" + container);


        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userDAO = AppDataBase.getDatabase(Objects.requireNonNull(getView()).getContext());
        new Thread(()->{

        list = userDAO.getTaskList();
        }).start();
        if (list==null) {
            model = new Model(0);
            insertModel(model);
            getModel(0);

            model1 = new Model(1);
            insertModel(model1);

            model2 = new Model(2);
            insertModel(model2);

            model3 = new Model(3);
            insertModel(model3);

            model4 = new Model(4);
            insertModel(model4);
        }

        task = Objects.requireNonNull(getView()).findViewById(R.id.tv_task);
        animal = getView().findViewById(R.id.iv_animal);
        var1 = Objects.requireNonNull(getView().findViewById(R.id.imb_v1));
        var2 = Objects.requireNonNull(getView().findViewById(R.id.imb_v2));
        var3 = Objects.requireNonNull(getView().findViewById(R.id.imb_v3));
        var4 = Objects.requireNonNull(getView().findViewById(R.id.imb_v4));

        animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(100);
        animation.setStartOffset(50);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(3);


        runGame();

    }

    private void insertModel(Model model) {
        Thread one = new Thread(() -> {
            userDAO.insert(model);
        });
        one.start();

    }


    @SuppressLint("ShowToast")
    private void runGame() {
        switch (idTask) {
            case 0:
                setImageDeff();
                setUp(R.drawable.img_dog, R.drawable.img_trava, R.drawable.img_banan, R.drawable.img_bone, R.drawable.img_potato);

                getModel(1);
                chooseUser(var3);
                break;

            case 1:
                setImageDeff();
                setUp(R.drawable.img_cow, R.drawable.img_beef, R.drawable.img_lemon, R.drawable.img_mouse, R.drawable.img_trava);

                getModel(2);
                chooseUser(var4);
                break;

            case 2:
                setImageDeff();
                setUp(R.drawable.img_cat, R.drawable.img_mouse, R.drawable.img_potato, R.drawable.img_beet, R.drawable.img_trava);

                getModel(3);
                chooseUser(var1);
                break;

            case 3:
                setImageDeff();
                setUp(R.drawable.img_horse, R.drawable.img_smetana, R.drawable.img_oves, R.drawable.img_cat, R.drawable.img_bone);

                getModel(4);
                chooseUser(var2);
                break;

            case 4:
                setImageDeff();
                setUp(R.drawable.img_mouse, R.drawable.img_zerno, R.drawable.img_potato, R.drawable.img_lemon, R.drawable.img_fish);

                getModel(0);
                chooseUser(var1);
                break;

        }
    }

    private void getModel(int num) {
        new Thread(() -> {
            idTask = userDAO.getID(num).getId();
        }).start();
    }

    private void setImageDeff() {
        int imageAlphaDeff = 255;
        var1.setImageAlpha(imageAlphaDeff);
        var1.setBackgroundResource(R.color.white);
        var2.setImageAlpha(imageAlphaDeff);
        var2.setBackgroundResource(R.color.white);
        var3.setImageAlpha(imageAlphaDeff);
        var3.setBackgroundResource(R.color.white);
        var4.setImageAlpha(imageAlphaDeff);
        var4.setBackgroundResource(R.color.white);
    }

    // search truth button
    private void chooseUser(ImageButton imageButton) {
        if (var1.equals(imageButton)) {
            checkOnKlickTrush(var1);
            ClickVar2False();
            ClickVar3False();
            ClickVar4False();

        } else if (var2.equals(imageButton)) {
            checkOnKlickTrush(var2);
            ClickVar1False();
            ClickVar3False();
            ClickVar4False();

        } else if (var3.equals(imageButton)) {
            checkOnKlickTrush(var3);
            ClickVar1False();
            ClickVar2False();
            ClickVar4False();

        } else if (var4.equals(imageButton)) {
            checkOnKlickTrush(var4);
            ClickVar1False();
            ClickVar2False();
            ClickVar3False();
        }
    }

    private void ClickVar1False() {
        var1.setOnClickListener(v -> {
            var1.setImageAlpha(alphaImage);
            var1.setBackgroundResource(R.color.colorRed);
            Toast.makeText(Objects.requireNonNull(getView()).getContext(), "FAIL, try again ", Toast.LENGTH_SHORT).show();
            var1.startAnimation(animation);
        });
    }

    private void ClickVar2False() {
        var2.setOnClickListener(v -> {
            var2.setImageAlpha(alphaImage);
            var2.setBackgroundResource(R.color.colorRed);
            Toast.makeText(Objects.requireNonNull(getView()).getContext(), "FAIL, try again ", Toast.LENGTH_SHORT).show();
            var2.startAnimation(animation);
        });
    }

    private void ClickVar3False() {
        var3.setOnClickListener(v -> {
            var3.setImageAlpha(alphaImage);
            var3.setBackgroundResource(R.color.colorRed);
            Toast.makeText(Objects.requireNonNull(getView()).getContext(), "FAIL, try again ", Toast.LENGTH_SHORT).show();
            var3.startAnimation(animation);
        });
    }

    private void ClickVar4False() {
        var4.setOnClickListener(v -> {
            var4.setImageAlpha(alphaImage);
            var4.setBackgroundResource(R.color.colorRed);
            Toast.makeText(Objects.requireNonNull(getView()).getContext(), "FAIL, try again ", Toast.LENGTH_SHORT).show();
            var4.startAnimation(animation);
        });
    }


    //check clickable on correct image
    @SuppressLint("ResourceAsColor")
    private void checkOnKlickTrush(ImageButton imageBtn) {
        imageBtn.setOnClickListener((view) -> {
            animation.setBackgroundColor(R.color.colorGreen);
            imageBtn.setBackgroundResource(R.color.colorGreen);
            imageBtn.setImageAlpha(alphaImage);
            imageBtn.startAnimation(animation);
            Toast.makeText(Objects.requireNonNull(getView()).getContext(), "SUCCESSFUL", Toast.LENGTH_LONG).show();

            new Handler().postDelayed(this::runGame, 1000);
        });
    }

    //change data on image
    @SuppressLint("ResourceAsColor")
    private void setUp(int animalTask, int v1, int v2, int v3, int v4) {
        animal.setImageResource(animalTask);
        var1.setImageResource(v1);
        var2.setImageResource(v2);
        var3.setImageResource(v3);
        var4.setImageResource(v4);


    }

}
