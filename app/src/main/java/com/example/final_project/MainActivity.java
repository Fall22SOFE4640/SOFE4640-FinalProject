package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText inputEmail, inputPassword;
    Button btnLogin, btnCreateAccount, btnForgotPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth Auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEmail = findViewById(R.id.email1);
        inputPassword = findViewById(R.id.password1);
        btnLogin = findViewById(R.id.btnLogin);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnForgotPassword = findViewById(R.id.btnForgotPassword);
        progressDialog = new ProgressDialog(this);
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignUp.class));
            }
        });

        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ForgotPassword.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if(!email.matches(emailPattern))
                    inputEmail.setError("Please enter a valid email");
                else if(password.isEmpty() || password.length() < 6)
                    inputPassword.setError("Please enter a proper password");
                else
                {
                    progressDialog.setMessage("Please wait ... ");
                    progressDialog.setTitle("Login");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    Auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                sendUserToNextActivity();
                                Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this,""+task.getException(),Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
            }
        });

    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}