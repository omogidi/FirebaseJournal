package com.example.user.firebasejournal.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.firebasejournal.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signIn = findViewById(R.id.sign_in);
        signIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.sign_in:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
        }
    }
}
