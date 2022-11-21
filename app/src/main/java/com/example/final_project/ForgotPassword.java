package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    EditText inputEmail;
    Button btnSubmit;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        inputEmail = findViewById(R.id.email2);
        btnSubmit = findViewById(R.id.btnSubmit);
        progressDialog = new ProgressDialog(this);
        Auth = FirebaseAuth.getInstance();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                if(email.isEmpty())
                    Toast.makeText(ForgotPassword.this, "Email missing", Toast.LENGTH_SHORT).show();
                else if(!email.matches(emailPattern))
                    Toast.makeText(ForgotPassword.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                else
                {
                    progressDialog.setMessage("Please wait ... ");
                    progressDialog.setTitle("Reset Password");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    Auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                Toast.makeText(ForgotPassword.this, "Reset Email Sent Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(ForgotPassword.this,""+task.getException(),Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
            }
        });
    }
}