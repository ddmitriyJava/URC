package com.example.urc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {
    @BindView(R.id.sign_up_emailEditText)
    EditText signUpEmailEditText;
    @BindView(R.id.sign_up_passwordEditText)
    EditText signUpPasswordEditText;
    @BindView(R.id.sign_up_confirm_passwordEditText)
    EditText signUpConfirmPasswordEditText;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.sing_up_button)
    void onSignUpButtonClicked() {
        if(isEmpty(signUpEmailEditText) || isEmpty(signUpPasswordEditText) || isEmpty(signUpConfirmPasswordEditText)
                || !getText(signUpPasswordEditText).equals(getText(signUpConfirmPasswordEditText))){
            Toast.makeText(getApplicationContext(), "Перевірте правильність введених даних", Toast.LENGTH_SHORT).show();
        } else {
            String userEmail = signUpEmailEditText.getText().toString();
            String userPassword = signUpPasswordEditText.getText().toString();

            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Перевірте правильність введених даних", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private boolean isEmpty(EditText editText){
        return editText.getText().toString().isEmpty();
    }

    private String getText(EditText editText){
        return editText.getText().toString();
    }
}