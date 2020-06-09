package com.example.mohammed.makeupword.Register;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammed.makeupword.MainActivity;
import com.example.mohammed.makeupword.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {
    private EditText loginEmailText;
    private EditText loginPassText;
    private LinearLayout loginBtn;
    private TextView loginRegBtn;

    private FirebaseAuth mAuth;

    private ProgressBar loginProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginEmailText = findViewById(R.id.login_email);
        loginPassText = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_Btn);
        loginRegBtn = findViewById(R.id.login_reg_btn);
        loginProgress = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        loginRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent regIntent = new Intent(loginActivity.this, RegstirActivity.class);
                startActivity(regIntent);

            }
        }); // end onClick Listener

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginEmail = loginEmailText.getText().toString();
                String loginPass = loginPassText.getText().toString();
                if (!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPass)) {
                    loginProgress.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(loginEmail, loginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                SendToMain();
                            } else {
                                String e = task.getException().getMessage();
                                Toast.makeText(loginActivity.this, e, Toast.LENGTH_LONG).show();
                            }
                            loginProgress.setVisibility(View.INVISIBLE);
                        }
                    });
                }
                else
                {
                    Toast.makeText(loginActivity.this,"empty email or password",Toast.LENGTH_LONG).show();
                }

            }

        });





    } // end onCreate

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser() ;

        if (currentUser != null )
        {
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);

            finish();
        }

    } // end method


    private void SendToMain() {
        Intent mainInetnt = new  Intent(loginActivity.this,MainActivity.class);
        startActivity(mainInetnt);
        finish();
    }


} // end Class
