package com.inzsoft.petshopee.pets;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class ProscutAdapter extends FirestoreRecyclerAdapter<
        prodcutslistr, ProscutAdapter.ProductlistsViewHolder> {

    Context context;
    Activity acv;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProscutAdapter(@NonNull FirestoreRecyclerOptions<prodcutslistr> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull ProductlistsViewHolder holder, int position, @NonNull prodcutslistr model) {
        String documentId = getSnapshots().getSnapshot(position).getId();
//            holder.prid.setText(model.getProdcuts_id());
            holder.prdnam.setText(model.getProducts_name());
            holder.prdprice.setText("₹ "+model.getPrice() );
            holder.prddisc.setText("₹ "+model.getDiscount());
            holder.prdqty.setText(model.getProducts_quanity());
            if (model.getProducts_status().equals("1"))
            {

            }
            else
            {

            }
           // Toast.makeText(context,model.getProducts_img().toString(),Toast.LENGTH_SHORT).show();

        Picasso.with(context)
                .load(model.getProducts_img())
                .into(holder.prdimg);
        holder.roots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home=new Intent(context, FullView.class);
                home.putExtra("prdfull",documentId);
                context.startActivity(home);
            }
        });
          /*holder.desablebtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if (model.getProducts_status().equals("1"))
                  {
                      updatests(documentId,"2");
                  }
                  else
                  {
                      updatests(documentId,"1");
                  }
              }
          });
          holder.updatebtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                //  Intent home=new Intent(context, Updateproducts.class);
                  //home.putExtra("prids",documentId);
                 // context.startActivity(home);
              }
          });*/
    }

    @NonNull
    @Override
    public ProductlistsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list, parent, false);
        context=parent.getContext();
        return new ProductlistsViewHolder(view);
    }

    class ProductlistsViewHolder extends RecyclerView.ViewHolder {
        FrameLayout roots;
        TextView prid, prdnam, prdcat,prdprice,prddisc,prddes,prdqty,desablebtn,updatebtn;
        ImageView prdimg;
        Button bkbtn;
        public ProductlistsViewHolder(@NonNull View itemView) {
            super(itemView);
         //   prid=itemView.findViewById(R.id.prdid);
            prdnam=itemView.findViewById(R.id.prdname);
          //  prdcat=itemView.findViewById(R.id.prdname);
            prdprice=itemView.findViewById(R.id.orgiprce);
            prddisc=itemView.findViewById(R.id.disprice);
            prdqty=itemView.findViewById(R.id.qty);
            prdimg=itemView.findViewById(R.id.prvimgs);
            roots=itemView.findViewById(R.id.petlay);


        }
    }
    public void updatests(String doc,String opn)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> Customer = new HashMap<>();
        Customer.put("products_status",opn);
        db.collection("Products").document(doc).update(Customer).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });


    }
}
