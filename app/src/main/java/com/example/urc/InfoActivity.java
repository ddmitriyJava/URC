package com.example.urc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.text_title)
    TextView textTitleTextView;
    @BindView(R.id.text)
    TextView textTextView;
    @BindView(R.id.container1)
    ScrollView container1;
    @BindView(R.id.container2)
    LinearLayoutCompat container2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ButterKnife.bind(this);

        container1.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_arrow);
    }

    @Override
    public boolean onSupportNavigateUp() {
        getOnBackPressedDispatcher().onBackPressed();
        return true;
    }

    @OnClick(R.id.baggage_norms)
    void onBaggageNormsTextViewClicked(){
        container2.setVisibility(View.GONE);
        container1.setVisibility(View.VISIBLE);
        textTitleTextView.setText(R.string.baggage_norms);
        textTextView.setText(R.string.baggage_norms_text);
    }

    @OnClick(R.id.animals_transportation)
    void onAnimalsTransportationTextViewClicked(){
        container2.setVisibility(View.GONE);
        container1.setVisibility(View.VISIBLE);
        textTitleTextView.setText(R.string.animals_transportation);
        textTextView.setText(R.string.animals_transportation_text);
    }

    @OnClick(R.id.bicycle_transportation)
    void onBicycleTransportationTextViewClicked(){
        container2.setVisibility(View.GONE);
        container1.setVisibility(View.VISIBLE);
        textTitleTextView.setText(R.string.bicycle_transportation);
        textTextView.setText(R.string.bicycle_transportation_text);
    }

    @OnClick(R.id.equipment_transportation)
    void onEquipmentTransportationTextViewClicked(){
        container2.setVisibility(View.GONE);
        container1.setVisibility(View.VISIBLE);
        textTitleTextView.setText(R.string.equipment_transportation);
        textTextView.setText(R.string.equipment_transportation_text);
    }

    @OnClick(R.id.close)
    void onCloseImageClicked(){
        container1.setVisibility(View.GONE);
        container2.setVisibility(View.VISIBLE);
    }
}