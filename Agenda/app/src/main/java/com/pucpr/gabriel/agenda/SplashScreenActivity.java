package com.pucpr.gabriel.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(
                () -> startActivity(new Intent(SplashScreenActivity.this, ListarPessoasActivity.class)),
                2000);
        finish();
    }
}
