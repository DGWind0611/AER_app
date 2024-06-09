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
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private TextView tvForgetPassword, tvSignUp;
    private Button btnLogin, btnLoginGuest;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        tvForgetPassword = findViewById(R.id.tv_forget_password);
        tvSignUp = findViewById(R.id.tv_signup);
        tvSignUp.getPaint().setUnderlineText(true);
        btnLogin = findViewById(R.id.btn_login);
        btnLoginGuest = findViewById(R.id.btn_login_guest);
        mAuth = FirebaseAuth.getInstance();

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_login) {
                    String email = etEmail.getText().toString();
                    String password = etPassword.getText().toString();
                    if (email.isEmpty()) {
                        etEmail.setError("請輸入電子郵件地址");
                    }
                    if (password.isEmpty()) {
                        etPassword.setError("請輸入密碼");
                    }
                    if (etEmail.getError() == null && etPassword.getError() == null) {
                        signIn(email, password);
                    }
                } else if (v.getId() == R.id.btn_login_guest) {
                    sinInGuest();
                } else if (v.getId() == R.id.tv_signup) {
                    Intent intent = new Intent();
                    intent.setClass(SignInActivity.this, SignUpActivity.class);
                    SignInActivity.this.startActivity(intent);
                } else if (v.getId() == R.id.tv_forget_password) {
                    Intent intent = new Intent();
                    intent.setClass(SignInActivity.this, ForgetPasswordActivity.class);
                    SignInActivity.this.startActivity(intent);
                }
            }
        };
        btnLogin.setOnClickListener(btnListener);
        btnLoginGuest.setOnClickListener(btnListener);
        tvSignUp.setOnClickListener(btnListener);
        tvForgetPassword.setOnClickListener(btnListener);
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // 登入成功
                    Intent intent = new Intent();
                    intent.setClass(SignInActivity.this, MainActivity.class);
                    SignInActivity.this.startActivity(intent);
                } else {
                    // 清空密碼欄
                    etPassword.setText("");
                    // 登入失敗
                    Toast.makeText(SignInActivity.this, "登入失敗", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sinInGuest() {
        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // 登入成功
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