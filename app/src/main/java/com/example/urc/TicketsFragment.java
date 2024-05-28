package com.example.urc;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TicketsFragment extends Fragment {
    @BindView(R.id.booked_tickets)
    ListView bookedTicketsListView;
    @BindView(R.id.no_tickets_layout)
    ConstraintLayout noTicketsLayout;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tickets, container, false);

        ButterKnife.bind(this, view);

        List<BookedTicket> bookedTicketsList = new ArrayList<>();
        BookedTicketsAdapter bookedTicketsAdapter = new BookedTicketsAdapter(bookedTicketsList, getActivity());
        bookedTicketsListView.setAdapter(bookedTicketsAdapter);

        firebaseFirestore.collection("booked_tickets")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if(!queryDocumentSnapshots.isEmpty()) {
                        noTicketsLayout.setVisibility(View.GONE);
                        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                            String number = queryDocumentSnapshot.getString("number");
                            String from = queryDocumentSnapshot.getString("from");
                            String to = queryDocumentSnapshot.getString("to");
                            String timeFrom = queryDocumentSnapshot.getString("timeFrom");
                            String timeTo = queryDocumentSnapshot.getString("timeTo");
                            String overall = queryDocumentSnapshot.getString("overall");
                            BookedTicket ticket = new BookedTicket(number, from, to, timeFrom, timeTo, overall);
                            bookedTicketsList.add(ticket);
                        }
                        bookedTicketsAdapter.notifyDataSetChanged();
                    }
                });

        return view;
    }
}