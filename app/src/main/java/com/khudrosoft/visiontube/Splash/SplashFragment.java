package com.khudrosoft.visiontube.Splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khudrosoft.visiontube.Auth.LoginActivity;
import com.khudrosoft.visiontube.R;

import java.util.Objects;


public class SplashFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @SuppressLint("NewApi")
            @Override
            public void run() {
              startActivity(new Intent(getActivity(),LoginActivity.class));
              Objects.requireNonNull(getActivity()).overridePendingTransition(0,0);
            }
        }, 100);


        return view;
    }


}
