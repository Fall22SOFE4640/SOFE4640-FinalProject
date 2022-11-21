package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class SignUp extends AppCompatActivity {

    EditText inputEmail, inputPassword, inputConfirmPassword;
    Button btnRegister;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth Auth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        inputConfirmPassword = findViewById(R.id.repassword);
        btnRegister = findViewById(R.id.btnRegister);
        progressDialog = new ProgressDialog(this);
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }
        });
    }

    private void PerformAuth() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();

        if(!email.matches(emailPattern))
            inputEmail.setError("Please enter a valid email");
        else if(password.isEmpty() || password.length() < 6)
            inputPassword.setError("Please enter a proper password");
        else if(!password.equals(confirmPassword))
            inputConfirmPassword.setError("Password does not match");
        else
        {
            progressDialog.setMessage("Please wait ... ");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            Auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(SignUp.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(SignUp.this,""+task.getException(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }



    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(SignUp.this, HomeActivity.class);
        startActivity(intent);
    }
}