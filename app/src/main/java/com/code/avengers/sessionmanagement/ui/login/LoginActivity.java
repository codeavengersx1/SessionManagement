package com.code.avengers.sessionmanagement.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.code.avengers.sessionmanagement.R;
import com.code.avengers.sessionmanagement.ScrollingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity
{
    private static final String KHARA_USERNAME = "admin";
    private static final String KHARA_PASSWORD = "password";

    @BindView(R.id.etxUsername)
    EditText etxUsername;

    @BindView(R.id.etxPassword)
    EditText etxPassword;

    /*
    * Global Instance of SharedPreferences
    * */
    private SharedPreferences sharedPreferences /*= null*/ ;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /*
         * Init the Global Instance of SharedPreferences
         * */
        sharedPreferences = getSharedPreferences("store_login", Context.MODE_PRIVATE);

        /*
        * Before the UI of Login Activity loads, take decision on
        * whether to throw User to ScrollingActivity or keep him on
        * current Activity (LoginActivity)
        * */
        boolean isUserAlreadyLoggedIn = sharedPreferences.getBoolean("key_kasa_kay", false);
        String token = sharedPreferences.getString("key_asach_token", null);
        if (isUserAlreadyLoggedIn)
        {
            if (token.equals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"))
            {
                // Jaude Pudhe
                throwUserToScrolling();
            }
        }


        setContentView(R.layout.activity_login);

        /*
        * Attach ButterKnife to this Activity
        * */
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    void onLoginButtonClick()
    {
        String userChaUserName = etxUsername.getText().toString();
        String userChaPassword = etxPassword.getText().toString();

        if (userChaUserName.equals(KHARA_USERNAME) && userChaPassword.equals(KHARA_PASSWORD))
        {
            final String ASACH_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

            /*
            * If user credentials are correct, store a Flag in SharedPreferences
            * */
            sharedPreferences
                    .edit()
                    .putString("key_asach_token", ASACH_TOKEN)
                    .putBoolean("key_kasa_kay", true)
                    .apply();

            throwUserToScrolling();
        }
        else
        {
            Toast.makeText(this, "Login Failed. Bhetu Parat.", Toast.LENGTH_SHORT).show();

            /*
             * If user credentials are correct, store a Flag in SharedPreferences
             * */
            sharedPreferences
                    .edit()
                    .putString("key_asach_token", null)
                    .putBoolean("key_kasa_kay", false)
                    .apply();
        }
    }

    private void throwUserToScrolling()
    {
        /*
         * Explicit Intent to start ScrollingActivity
         * */
        Intent scrollingIntent = new Intent(LoginActivity.this, ScrollingActivity.class);
        startActivity(scrollingIntent);

        /*
        * Close the Current Screen
        * */
        finish();

        /*
        * To Clear Back Stack
        * */
        // finishAffinity();
    }
}
