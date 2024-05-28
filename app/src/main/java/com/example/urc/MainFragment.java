package com.example.urc;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends Fragment {
    @BindView(R.id.fromEditText)
    AutoCompleteTextView fromEditText;
    @BindView(R.id.toEditText)
    AutoCompleteTextView toEditText;
    @BindView(R.id.dateEditText)
    EditText dateEditText;
    @BindView(R.id.time_from_textView)
    TextView timeFromTextView;
    @BindView(R.id.time_to_textView)
    TextView timeToTextView;
    @BindView(R.id.from_textView)
    TextView fromTextView;
    @BindView(R.id.to_textView)
    TextView toTextView;
    @BindView(R.id.time_overall_textView)
    TextView timeOverallTextView;
    @BindView(R.id.error)
    ConstraintLayout error;
    @BindView(R.id.result)
    ConstraintLayout result;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private DatePickerDialog datePickerDialog;
    private final Calendar calendar = Calendar.getInstance();
    private BookedTicket foundTicket;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, view);

        error.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);

        List<String> cities = new ArrayList<>();


        database.collection("cities")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot document :
                            queryDocumentSnapshots) {
                        String cityName = document.getString("name");
                        cities.add(cityName);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, cities);
                    fromEditText.setAdapter(adapter);
                    toEditText.setAdapter(adapter);
                });

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(getContext(), (datePicker, i, i1, i2) -> dateEditText.setText(String.format(Locale.CANADA, "%d.%d.%d", i2, i1 + 1, i)), year, month, day);

        return view;
    }

    @OnClick(R.id.findButton)
    void onFindButtonClicked(){
        String userFrom = fromEditText.getText().toString();
        String userTo = toEditText.getText().toString();
        String userDate = dateEditText.getText().toString();

        database.collection("tickets")
                .whereEqualTo("from", userFrom)
                .whereEqualTo("to", userTo)
                .whereEqualTo("date", userDate)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (queryDocumentSnapshots.isEmpty()) {
                        error.setVisibility(View.VISIBLE);
                    } else {
                        for (QueryDocumentSnapshot querySnapshot : queryDocumentSnapshots) {
                            String from = querySnapshot.getString("from");
                            String to = querySnapshot.getString("to");
                            String timeFrom = querySnapshot.getString("time_from");
                            String timeTo = querySnapshot.getString("time_to");
                            String overall = querySnapshot.getString("overall");

                            foundTicket = new BookedTicket(String.valueOf(new Random().nextInt(900000000) + 1000000000),from, to, timeFrom, timeTo, overall);

                            timeFromTextView.setText(timeFrom);
                            timeToTextView.setText(timeTo);
                            fromTextView.setText(from);
                            toTextView.setText(to);
                            timeOverallTextView.setText(String.format("%s год.", overall));

                            result.setVisibility(View.VISIBLE);
                        }
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show());
    }

    @OnClick(R.id.book)
    void onBookButtonClicked(){
        database.collection("booked_tickets")
                .add(foundTicket);
    }

    @OnClick(R.id.dateEditText)
    void onDateEditTextClicked(){
        datePickerDialog.show();
    }

    @OnClick
    void onCloseImageClicked(){
        error.setVisibility(View.INVISIBLE);
    }
}