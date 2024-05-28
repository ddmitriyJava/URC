package com.example.urc;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {
    @BindView(R.id.profile_variants)
    ListView profileVariantsListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, view);

        ProfileAdapter profileAdapter = new ProfileAdapter(List.of("Мої контакти", "Допомога", "Довідка"), List.of(R.drawable.phone, R.drawable.help, R.drawable.info), requireContext());
        profileVariantsListView.setAdapter(profileAdapter);

        profileVariantsListView.setOnItemClickListener((adapterView, view1, i, l) -> {
            switch(i){
                case 0:
                    Intent myContactsIntent = new Intent(getActivity(), MyContactsActivity.class);
                    startActivity(myContactsIntent);
                    break;
                case 1:
                    Intent supportIntent = new Intent(getActivity(), SupportActivity.class);
                    startActivity(supportIntent);
                    break;
                case 2:
                    Intent infoIntent = new Intent(getActivity(), InfoActivity.class);
                    startActivity(infoIntent);
                    break;
            }
        });

        return view;
    }
}