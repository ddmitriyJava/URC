package com.example.urc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.widget.EditText;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyContactsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.telephone)
    EditText telephone;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contacts);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_arrow);

        String userUID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
        firebaseFirestore
                .collection("contacts")
                .whereEqualTo("UID", userUID)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                        name.setText(queryDocumentSnapshot.getString("name"));
                        telephone.setText(queryDocumentSnapshot.getString("phone"));
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        getOnBackPressedDispatcher().onBackPressed();
        return true;
    }

    @OnClick(R.id.accept_contacts)
    void onAcceptContactsButtonCLicked() {
        CollectionReference contactsCollection = firebaseFirestore.collection("contacts");

        Task<QuerySnapshot> currentUserTask = contactsCollection.whereEqualTo("UID", firebaseAuth.getUid()).get();

        currentUserTask.addOnSuccessListener(queryDocumentSnapshots -> {
            if (!queryDocumentSnapshots.isEmpty()) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                    contactsCollection
                            .document(queryDocumentSnapshot.getId())
                            .update("name", name.getText().toString(), "phone", telephone.getText().toString());
                }
            } else {
                contactsCollection.add(new UserContact(firebaseAuth.getUid(), name.getText().toString(), telephone.getText().toString()));
            }
        });
    }
}