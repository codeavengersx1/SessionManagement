package com.code.avengers.sessionmanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.code.avengers.sessionmanagement.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class ScrollingActivity extends AppCompatActivity
{
    /*
    * Global Instance of SharedPreferences
    * */
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences("store_login", Context.MODE_PRIVATE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                /*
                * Logic of Logout
                * */
                sharedPreferences
                        .edit()
                        .clear()
                        .apply();

                /*
                * Go to Login clearing Back stack
                * */
                Intent loginIntent = new Intent(ScrollingActivity.this, LoginActivity.class);
                startActivity(loginIntent);

                /*
                * now clear the Back Stack (Memory)
                * */
                finishAffinity();
            }
        });
    }
}
