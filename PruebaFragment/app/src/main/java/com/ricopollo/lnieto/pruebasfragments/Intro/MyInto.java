package com.ricopollo.lnieto.pruebasfragments.Intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.ricopollo.lnieto.pruebasfragments.MainActivity;
import com.ricopollo.lnieto.pruebasfragments.R;

public class MyInto extends AppIntro {

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        addSlide(AppIntroSlider.newInstance(R.layout.app_intro));
        addSlide(AppIntroSlider.newInstance(R.layout.app_intro2));
        addSlide(AppIntroSlider.newInstance(R.layout.app_intro3));

        showStatusBar(false);
        showSkipButton(false);

        setVibrate(true);
        setVibrateIntensity(30);

        setDepthAnimation();
    }

    @Override
    public void onSkipPressed() {
        Toast.makeText(getApplicationContext(),
                getString(R.string.app_name), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {
        finish();
    }

    @Override
    public void onSlideChanged() {

    }
}
