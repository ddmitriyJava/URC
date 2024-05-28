package com.example.urc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {
    @BindView(R.id.sign_in_emailEditText)
    EditText signInEmailEditText;
    @BindView(R.id.sign_in_passwordEditText)
    EditText signInPasswordEditText;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.sing_in_button)
    void onSignInButtonClicked(){
        if(signInEmailEditText.getText().toString().isEmpty() || signInPasswordEditText.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Перевірте правильність введених даних", Toast.LENGTH_SHORT).show();
        } else {
            String userEmail = signInEmailEditText.getText().toString();
            String userPassword = signInPasswordEditText.getText().toString();

            firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword)
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
}