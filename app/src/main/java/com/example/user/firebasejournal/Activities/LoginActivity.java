package com.example.user.firebasejournal.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.firebasejournal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private TextInputEditText emailLoginEdit, passwordLoginEdit;
    private TextInputLayout emailLoginlayout, password_input;
    private TextView clickhere;
    private Button login;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLoginEdit = findViewById(R.id.emailLogin_edittext);
        passwordLoginEdit = findViewById(R.id.passwordLogin_textEdit);

        emailLoginlayout = findViewById(R.id.emailLogin_inputLayout);
        password_input = findViewById(R.id.password_inputlayout);

        login = findViewById(R.id.login);
        login.setOnClickListener(this);

        clickhere = findViewById(R.id.click_here);
        clickhere.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Toast.makeText(LoginActivity.this, "Signed In", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, Dashboard.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Not Signed In", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.login:
                validate();
                break;
            case R.id.click_here:
                startActivity(new Intent(LoginActivity.this, Registration.class));
                break;
        }
    }

    //Validate
    public void validate() {
        String email = emailLoginEdit.getText().toString().trim();
        String pass = passwordLoginEdit.getText().toString().trim();

            if ((TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
                emailLoginlayout.setError("Please Enter a valid email");
            } else if (TextUtils.isEmpty(pass)) {
                password_input.setError("Invalid Password");
            } else {
                createUser(email, pass);
            }
        }

    private void createUser(String email, String pass)
    {
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this, Dashboard.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        if (firebaseUser != null) {
            mAuth.removeAuthStateListener(authStateListener);
        }
    }
}
