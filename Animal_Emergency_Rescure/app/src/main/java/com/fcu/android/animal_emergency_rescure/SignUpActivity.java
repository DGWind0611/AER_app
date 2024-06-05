package com.fcu.android.animal_emergency_rescure;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etConfirmPassword;
    private Button btnSend;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email_su);
        etPassword = findViewById(R.id.et_password_su);
        etConfirmPassword = findViewById(R.id.et_password_su2);
        btnSend = findViewById(R.id.btn_send);

        // 檢查輸入的資料是否符合規定
        check();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                // 檢查是否有空白欄位
                if (name.isEmpty()) {
                    etName.setError("請輸入名稱");
                }
                if (email.isEmpty()) {
                    etEmail.setError("請輸入電子郵件地址");
                }
                if (password.isEmpty()) {
                    etPassword.setError("請輸入密碼");
                }
                if (confirmPassword.isEmpty()) {
                    etConfirmPassword.setError("請再次輸入密碼");
                } else if (!password.equals(confirmPassword)) {
                    etConfirmPassword.setError("密碼不一致");
                }
                // 若無錯誤，則將資料註冊
                if (etName.getError() == null && etEmail.getError() == null && etPassword.getError() == null && etConfirmPassword.getError() == null) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpActivity.this, task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "註冊成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(SignUpActivity.this, "註冊失敗", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        };
        btnSend.setOnClickListener(listener);
    }

    private void check() {
        // 檢查姓名是否為空
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String name = etName.getText().toString();
                    if (name.isEmpty()) {
                        etName.setError("請輸入名稱");
                    }
                }
            }
        });
        // 檢查電子郵件地址是否符合格式
        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String email = etEmail.getText().toString();
                    boolean isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
                    if (!isValid) {
                        etEmail.setError("請輸入有效的電子郵件地址");
                    }
                }
            }
        });
        // 檢查密碼是否符合規定
        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String password = etPassword.getText().toString();
                    if (password.length() < 9) {
                        etPassword.setError("長度不得小於9個字元");
                    } else if (!password.matches(".*\\d.*")) {
                        etPassword.setError("密碼必須包含至少一個數字");
                    } else if (!password.matches(".*[a-z].*")) {
                        etPassword.setError("密碼必須包含至少一個小寫字母");
                    } else if (!password.matches(".*[A-Z].*")) {
                        etPassword.setError("密碼必須包含至少一個大寫字母");
                    }
                }
            }
        });
        // 檢查確認密碼是否為空或與密碼不一致
        etConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String confirmPassword = etConfirmPassword.getText().toString();
                    String password = etPassword.getText().toString();
                    if (confirmPassword.isEmpty()) {
                        etConfirmPassword.setError("請再次輸入密碼");
                    } else if (!confirmPassword.equals(password)) {
                        etConfirmPassword.setError("密碼不一致");
                    }
                }
            }
        });
    }
}