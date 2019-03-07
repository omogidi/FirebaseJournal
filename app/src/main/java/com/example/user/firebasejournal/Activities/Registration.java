package com.example.user.firebasejournal.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.user.firebasejournal.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity
{
    private TextInputLayout firstnameInput, lastnameInput, emailInput, usernameInput, passwordInput, confirmpasswordInput;
    private TextInputEditText firstnameEdit, lastnameEdit, emailEdit, usernameEdit, passwordEdit, confirmpasswordedit;
    private Button register;
    private FrameLayout frameLayout;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth rregisterAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        frameLayout = findViewById(R.id.frameLayout);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("REGISTERED_USERS(we_write)");
        rregisterAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        //Instantiate TextInputLayout
        firstnameInput = findViewById(R.id.first_name_inputlayout);
        lastnameInput = findViewById(R.id.last_name_inputlayout);
        emailInput = findViewById(R.id.email_inputlayout);
        usernameInput = findViewById(R.id.username_inputlayout);
        passwordInput = findViewById(R.id.password_inputlayout);
        confirmpasswordInput = findViewById(R.id.confirm_password_inputlayout);

        //Instantiate TextEditLayout
        firstnameEdit = findViewById(R.id.first_name_edittext);
        lastnameEdit = findViewById(R.id.last_name_edittext);
        emailEdit = findViewById(R.id.email_edittext);
        usernameEdit = findViewById(R.id.username_edittext);
        passwordEdit = findViewById(R.id.password_edittext);
        confirmpasswordedit = findViewById(R.id.confirm_password_edittext);

        //Instantiate Buttons
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                validate();
            }
        });
    }

    private void validate()
    {
        final String firstName = firstnameEdit.getText().toString().trim();
        final String lastName = lastnameEdit.getText().toString().trim();
        final String email = emailEdit.getText().toString().trim();
        final String username = usernameEdit.getText().toString().trim();
        final String password = passwordEdit.getText().toString().trim();
        String confirmPass = confirmpasswordedit.getText().toString().trim();


        if (TextUtils.isEmpty(firstName)) {
            firstnameEdit.setError("First name is empty");
        }
        else {
            firstnameEdit.setError(null);
            if (TextUtils.isEmpty(lastName)) {
                lastnameEdit.setError("Last name is empty");
            }
            else {
                lastnameEdit.setError(null);
                if (TextUtils.isEmpty(email)) {
                    emailEdit.setError("Email is empty");
                }
                else {
                    emailEdit.setError(null);
                    if (TextUtils.isEmpty(username)){
                        usernameEdit.setError("Username is empty");
                    }else{
                        usernameEdit.setError(null);
                                if (TextUtils.isEmpty(password)) {
                                    passwordEdit.setError("Password is empty");
                                }
                                else {
                                    passwordEdit.setError(null);
                                    if (TextUtils.isEmpty(confirmPass)) {
                                        confirmpasswordedit.setError("Confirm Pass is empty");
                                    }
                                    else {
                                        confirmpasswordedit.setError(null);
                                        if (!password.equals(confirmPass)) {
                                            Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                                        }else{
                                            progressDialog.setMessage("Registering...");
                                            progressDialog.show();
                                            rregisterAuth.createUserWithEmailAndPassword(email, password)
                                                    .addOnSuccessListener(new OnSuccessListener<AuthResult>()
                                                    {
                                                        @Override
                                                        public void onSuccess(AuthResult authResult)
                                                        {
                                                            if (authResult != null) {
                                                                String userId = rregisterAuth.getCurrentUser().getUid();
                                                                DatabaseReference currentUser = databaseReference.child(userId);
                                                                currentUser.child("first_name").setValue(firstName);
                                                                currentUser.child("last_name").setValue(lastName);
                                                                currentUser.child("email").setValue(email);
                                                                currentUser.child("username").setValue(username);
                                                                currentUser.child("password").setValue(password);

                                                                progressDialog.dismiss();

                                                                //send users to dashboard
                                                                Intent intent = new Intent(Registration.this, Dashboard.class);
                                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                                startActivity(intent);

                                                            }}
                                                    });
                                        }
                                    }
                                }
                    }
                }
            }
        }


    }

}
