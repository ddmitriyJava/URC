package com.example.urc;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainPageActivity extends AppCompatActivity {
    @BindView(R.id.bottom_nav_menu)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ButterKnife.bind(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new MainFragment())
                .commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new MainFragment())
                            .commit();
                    return true;
                case R.id.tickets:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new TicketsFragment())
                            .commit();
                    return true;
                case R.id.support:
                    Intent supportIntent = new Intent(getApplicationContext(), SupportActivity.class);
                    startActivity(supportIntent);
                    return true;
                case R.id.profile:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new ProfileFragment())
                            .commit();
                    return true;
            }
            return false;
        });
    }
}
