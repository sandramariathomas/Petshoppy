package com.inzsoft.petshopee.bookings;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.inzsoft.petshopee.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class Request_Adapetr extends FirestoreRecyclerAdapter<
        orderlist, Request_Adapetr.OrderlistsViewHolder> {
    Context context;
    Activity acv;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Request_Adapetr(@NonNull FirestoreRecyclerOptions<orderlist> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull OrderlistsViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull orderlist model) {
        String documentId = getSnapshots().getSnapshot(position).getId();
        holder.prdprice.setText(model.getProductreq_date());
        holder.prddisc.setText(model.getProductreq_time());
        holder.prdnam.setText(model.getProductreq_name());
        holder.prdqty.setText(model.getProductreq_qty());
        holder.odrprice.setText("₹ "+ model.getProductreq_total());
        holder.viewdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Intent reviw = new Intent(context, RequestView.class);
               // reviw.putExtra("ordid", documentId);
               // context.startActivity(reviw);
            }
        });
        Picasso.with(context)
                .load(model.getProductreq_img())
                .into(holder.prdimg);
        if (model.getProductreq_status().equals("1")) {
            //Toast.makeText(context, model.getProductreq_status(),Toast.LENGTH_SHORT).show();

        } else {

            if (model.getProductreq_status().equals("2")) {

            } else if (model.getProductreq_status().equals("4")) {

            } else if (model.getProductreq_status().equals("5")) {

            } else {

            }

        }




    }

    @NonNull
    @Override
    public OrderlistsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.request_list, parent, false);
        context = parent.getContext();
        return new Request_Adapetr.OrderlistsViewHolder(view);
    }

    class OrderlistsViewHolder extends RecyclerView.ViewHolder {
        FrameLayout roots, acbtnFrm, stsFrm;
        TextView prid, prdnam, prdcat, prdprice, prddisc, prddes, prdqty, acptbtn, rjctbtn, odrprice, viewdl, rejviw, crvin;
        ImageView prdimg;
        Button bkbtn;

        public OrderlistsViewHolder(@NonNull View itemView) {
            super(itemView);
            roots = itemView.findViewById(R.id.rootview);
            prid = itemView.findViewById(R.id.prdid);
            prdnam = itemView.findViewById(R.id.prdname);
            //  prdcat=itemView.findViewById(R.id.prdname);
            prdprice = itemView.findViewById(R.id.orgiprce);
            prddisc = itemView.findViewById(R.id.disprice);
            prdqty = itemView.findViewById(R.id.qty);
            odrprice = itemView.findViewById(R.id.odrprice);
            prdimg = itemView.findViewById(R.id.prvimgs);

            viewdl = itemView.findViewById(R.id.viewdl);


        }
    }

    public void updatests(String doc, String opn) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> Customer = new HashMap<>();
        Customer.put("Productreq_status", opn);
        db.collection("Productreq").document(doc).update(Customer).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });


    }
}
