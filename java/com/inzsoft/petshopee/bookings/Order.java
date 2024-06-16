package com.inzsoft.petshopee.bookings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.inzsoft.petshopee.GlobalVar;
import com.inzsoft.petshopee.R;

public class Order extends AppCompatActivity {

    RecyclerView recyclerView,perRcy,revRcy;
    Request_Adapetr Actadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        perRcy=findViewById(R.id.orderrecycle);
        loadPern();
    }

    CollectionReference citiesRef;
    public void loadPern()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        citiesRef = db.collection("Productreq");
        Query query;

        query= citiesRef.whereEqualTo("Productreq_customer", GlobalVar.Customer_ID).whereEqualTo("Productreq_status","1");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        perRcy.setLayoutManager(linearLayoutManager);
        perRcy.addItemDecoration(new DividerItemDecoration(perRcy.getContext(), DividerItemDecoration.VERTICAL));
        FirestoreRecyclerOptions<orderlist> options = new FirestoreRecyclerOptions.Builder<orderlist>()
                .setQuery(query, orderlist.class)
                .build();
        Actadapter = new Request_Adapetr(options);
        perRcy.setAdapter(Actadapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        Actadapter.startListening();
        Actadapter.notifyDataSetChanged();

    }

    @Override
    public void onStop() {
        super.onStop();
        Actadapter.stopListening();

    }
}