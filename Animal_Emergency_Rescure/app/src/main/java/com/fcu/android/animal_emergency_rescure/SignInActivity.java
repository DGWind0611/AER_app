package com.fcu.android.animal_emergency_rescure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.SecureRandom;

import kotlin.random.URandomKt;

public class SignInActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private TextView tvForgetPassword, tvSignUp;
    private Button btnLogin, btnLoginVisitor;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        tvForgetPassword = findViewById(R.id.tv_forget_password);
        tvSignUp = findViewById(R.id.tv_signup);
        btnLogin = findViewById(R.id.btn_login);
        btnLoginVisitor = findViewById(R.id.btn_login_visitor);

        mAuth = FirebaseAuth.getInstance();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_login) {
                    String user = etEmail.getText().toString();
                    String password = etPassword.getText().toString();
                    signIn(user, password);
                    // TODO: Setting user permissions
                } else if (v.getId() == R.id.btn_login_visitor) {
                    // TODO: Setting visitor permissions
                    Intent intent = new Intent();
                    intent.setClass(SignInActivity.this, MainActivity.class);
                    SignInActivity.this.startActivity(intent);
                } else if (v.getId() == R.id.tv_signup) {
                    // TODO: Implement sign up
//                    Intent intent = new Intent();
//                    intent.setClass(SignInActivity.this, SignUpActivity.class);
//                    SignInActivity.this.startActivity(intent);
                } else if (v.getId() == R.id.tv_forget_password) {
                    // TODO: Implement forget password
//                    Intent intent = new Intent();
//                    intent.setClass(SignInActivity.this, ForgetPasswordActivity.class);
//                    SignInActivity.this.startActivity(intent);
                }
            }
        };
        btnLogin.setOnClickListener(listener);
        btnLoginVisitor.setOnClickListener(listener);
    }

    private void signIn(String user, String password) {
        mAuth.signInWithEmailAndPassword(user, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // 登入成功
                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent intent = new Intent();
                    intent.setClass(SignInActivity.this, MainActivity.class);
                    SignInActivity.this.startActivity(intent);
                } else {
                    // 登入失敗
                    Toast.makeText(SignInActivity.this, "登入失敗", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}