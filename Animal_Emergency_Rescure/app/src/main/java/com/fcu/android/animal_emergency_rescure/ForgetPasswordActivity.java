package com.fcu.android.animal_emergency_rescure;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button btnSendEmail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                if (email.isEmpty()) {
                    etEmail.setError("請輸入電子郵件地址");
                } else {
                    mAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // 重設密碼信件已寄出
                                        Toast.makeText(ForgetPasswordActivity.this, "重設密碼信件已寄出", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // 電子郵件地址不存在
                                        etEmail.setText("");
                                        etEmail.setError("電子郵件地址不存在");
                                    }
                                }
                            });
                }
            }
        };
        btnSendEmail.setOnClickListener(listener);
    }
}