package com.inzsoft.petshopee.pets;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.inzsoft.petshopee.GlobalVar;
import com.inzsoft.petshopee.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FullView extends AppCompatActivity {

    ImageView pdimgview;
    TextView prdnames,cttext,prddes,orprice,prdiscnt,prqty,tlqty,tlprice;
    int qty=1;
    double acprice=0;
    Button plubtn,minzbtn,plcbtn;
    int maxqty;
    double MAxprice;
    String cutid;
    String newString,img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view);

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
        //newString="EtrghoHQOWGf639xuNB2";
        cutid=GlobalVar.Customer_ID;
        pdimgview=findViewById(R.id.pdimgview);
        prdnames=findViewById(R.id.prdnames);
        cttext=findViewById(R.id.cttext);
        prddes=findViewById(R.id.prddes);
        orprice=findViewById(R.id.orprice);
        prdiscnt=findViewById(R.id.prdiscnt);
        prqty=findViewById(R.id.prqty);
        tlqty=findViewById(R.id.tlqty);
        tlqty.setText(String.valueOf(qty));
        tlprice=findViewById(R.id.tlprice);
        plubtn=findViewById(R.id.plubtn);
        minzbtn=findViewById(R.id.minzbtn);
        plcbtn=findViewById(R.id.plcbtn);
        load_view(newString);
        // Toast.makeText(getApplicationContext(),GlobalVar.Customer_ID,Toast.LENGTH_SHORT).show();
        plubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maxqty>qty)
                {
                    qty = qty + 1;
                    tlqty.setText(String.valueOf(qty));
                    MAxprice=acprice*qty;
                    tlprice.setText("EUR "+String.valueOf(MAxprice));
                }
            }
        });
        minzbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (qty!=1)
                {
                    qty = qty - 1;
                    tlqty.setText(String.valueOf(qty));
                    MAxprice=acprice*qty;
                    tlprice.setText("EUR "+String.valueOf(MAxprice));
                }
            }
        });
        plcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookplace(newString);
            }
        });
    }

    String prdunit;
    CollectionReference loginRef;
    public void load_view(String newString)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("pets").document(newString).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                prdnames.setText(value.get("products_name").toString());
                cttext.setText(value.get("prodcuts_cat").toString());
                prddes.setText(value.get("products_des").toString());
                //prdunit=value.get("products_unit").toString();
                orprice.setText("₹  "+value.get("price").toString());
                if (value.get("discount").toString().equals(""))
                {
                    prdiscnt.setVisibility(View.GONE);
                }
                else
                {
                    prdiscnt.setText("₹ "+value.get("discount").toString());
                }


                prqty.setText(value.get("products_quanity").toString());
                img=value.get("products_img").toString();
                if (value.get("discount").toString().isEmpty())
                {
                    tlprice.setText("₹  "+value.get("price").toString());
                    acprice=Double.parseDouble(value.get("price").toString());
                    MAxprice=Double.parseDouble(value.get("price").toString());
                }
                else
                {
                    tlprice.setText("₹  "+value.get("discount").toString());
                    orprice.setPaintFlags(orprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    acprice=Double.parseDouble(value.get("discount").toString());
                    MAxprice=Double.parseDouble(value.get("discount").toString());
                }
                maxqty=Integer.parseInt(value.get("products_quanity").toString());

                Picasso.with(getApplicationContext())
                        .load(value.get("products_img").toString())
                        .into(pdimgview);
            }
        });

    }

    public void bookplace(String newString)
    {
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> Customer = new HashMap<>();
        // user.put("name",stnames );
        Customer.put("Productreq_customer", GlobalVar.Customer_ID);
      //  Customer.put("Productreq_company", GlobalVar.Company_ID);
        Customer.put("Productreq_productid",newString);
        Customer.put("Productreq_qty",String.valueOf(qty));
        Customer.put("Productreq_total",String.valueOf(MAxprice));
        Customer.put("Productreq_name",prdnames.getText().toString());
        Customer.put("Productreq_status","1");
        Customer.put("Productreq_date",date);
        Customer.put("Productreq_time",currentTime);
        Customer.put("Productreq_img",img);
        Customer.put("Product_unit",prdunit);
        db.collection("Productreq").add(Customer).addOnSuccessListener(new OnSuccessListener<DocumentReference>(){

            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Order placed successfully",Toast.LENGTH_SHORT).show();
                Intent boksus=new Intent(getApplicationContext(),booksuccess.class);
                startActivity(boksus);
                finish();
            }
        });
    }


}