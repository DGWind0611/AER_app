package com.fcu.android.animal_emergency_rescure;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordFragment extends Fragment {

    private EditText etNewPassword, etConfirmPassword;
    private Button btnChange;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);

        etNewPassword = view.findViewById(R.id.et_new_password);
        etConfirmPassword = view.findViewById(R.id.et_new_password_2);
        btnChange = view.findViewById(R.id.btn_change);
        mAuth = FirebaseAuth.getInstance();

        // 檢查密碼是否符合規定
        etNewPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String newPassword = etNewPassword.getText().toString();
                    if (newPassword.length() < 9) {
                        etNewPassword.setError("長度不得小於9個字元");
                    } else if (!newPassword.matches(".*\\d.*")) {
                        etNewPassword.setError("密碼必須包含至少一個數字");
                    } else if (!newPassword.matches(".*[a-z].*")) {
                        etNewPassword.setError("密碼必須包含至少一個小寫字母");
                    } else if (!newPassword.matches(".*[A-Z].*")) {
                        etNewPassword.setError("密碼必須包含至少一個大寫字母");
                    }
                }
            }
        });
        // 檢查密碼是否一致
        etConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String newPassword = etNewPassword.getText().toString();
                    String confirmPassword = etConfirmPassword.getText().toString();
                    if (confirmPassword.isEmpty()) {
                        etConfirmPassword.setError("請再次輸入新密碼");
                    } else if (!confirmPassword.equals(newPassword)) {
                        etConfirmPassword.setError("密碼不一致");
                    }
                }
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = etNewPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();
                if (newPassword.isEmpty()) {
                    etNewPassword.setError("新密碼不可為空");
                }
                if (confirmPassword.isEmpty()) {
                    etConfirmPassword.setError("請再次輸入新密碼");
                }
                if (etNewPassword.getError() == null && etConfirmPassword.getError() == null) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    user.updatePassword(newPassword).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "密碼修改成功，請重新登入", Toast.LENGTH_SHORT).show();
                            mAuth.signOut();
                            // 跳轉至登入頁面
                            Intent intent = new Intent();
                            intent.setClass(getActivity(), SignInActivity.class);
                            // 清除所有Activity並啟動新的Activity
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            getActivity().startActivity(intent);
                            getActivity().finish();
                        } else {
                            Toast.makeText(getContext(), "密碼修改失敗", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        return view;
    }
}