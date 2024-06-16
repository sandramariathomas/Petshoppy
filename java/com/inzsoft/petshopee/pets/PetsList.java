package com.inzsoft.petshopee.pets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.inzsoft.petshopee.R;

public class PetsList extends AppCompatActivity {

    RecyclerView recyclerView,perRcy,revRcy;
    ProscutAdapter Actadapter;
    String newString,img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets_list);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("prdfull");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("prdfull");
        }

        perRcy = findViewById(R.id.recyclerrpt);
        loadPern(newString);
    }

    CollectionReference citiesRef;
    public void loadPern(String ids)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        citiesRef = db.collection("pets");
        Query query;
        //Toast.makeText(getApplicationContext(),ids , Toast.LENGTH_SHORT).show();
        query= citiesRef.whereEqualTo("prodcuts_cat", ids);//.whereEqualTo("dist","Idukki");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        perRcy.setLayoutManager(linearLayoutManager);
        perRcy.addItemDecoration(new DividerItemDecoration(perRcy.getContext(), DividerItemDecoration.VERTICAL));
        FirestoreRecyclerOptions<prodcutslistr> options = new FirestoreRecyclerOptions.Builder<prodcutslistr>()
                .setQuery(query, prodcutslistr.class)
                .build();
        Actadapter = new ProscutAdapter(options);
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